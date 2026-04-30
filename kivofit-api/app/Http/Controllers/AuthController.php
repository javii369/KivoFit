<?php

namespace App\Http\Controllers;

use App\Models\User;
use App\Models\Cliente;
use App\Models\Entrenador;
use App\Models\Administrador;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Hash;
use Illuminate\Validation\ValidationException;
use Illuminate\Support\Facades\DB;

class AuthController extends Controller
{
    /**
     * Registro de un nuevo usuario.
     * Crea el usuario base y su perfil según el rol asignado.
     */
    public function register(Request $request)
    {
        // Validamos los campos obligatorios
        $request->validate([
            'dni'              => 'required|string|max:9|unique:users',
            'nombre'           => 'required|string|max:255',
            'apellido'         => 'required|string|max:255',
            'segundo_apellido' => 'nullable|string|max:255',
            'email'            => 'required|email|unique:users',
            'password'         => 'required|string|min:8|confirmed',
            'rol'              => 'required|in:cliente,entrenador,administrador',
        ]);

        try {
            $result = \DB::transaction(function () use ($request) {
                // Creamos el usuario base
                $user = User::create([
                    'dni'              => $request->dni,
                    'nombre'           => $request->nombre,
                    'apellido'         => $request->apellido,
                    'segundo_apellido' => $request->segundo_apellido,
                    'email'            => $request->email,
                    'password'         => Hash::make($request->password),
                    'rol'              => $request->rol,
                ]);

                // Creamos el perfil según el rol
                match ($request->rol) {
                    'cliente'       => Cliente::create(['user_id' => $user->id]),
                    'entrenador'    => Entrenador::create(['user_id' => $user->id]),
                    'administrador' => Administrador::create(['user_id' => $user->id]),
                };

                return $user;
            });

            // Generamos el token de autenticación
            $token = $result->createToken('auth_token')->plainTextToken;

            return response()->json([
                'message' => 'Usuario registrado correctamente',
                'user'    => $result,
                'token'   => $token,
            ], 201);
        } catch (\Exception $e) {
            return response()->json([
                'message' => 'Error al registrar el usuario',
                'error'   => $e->getMessage(),
            ], 500);
        }
    }

    /**
     * Login de un usuario existente.
     * Devuelve un token de autenticación si las credenciales son correctas.
     */
    public function login(Request $request)
    {
        // Validamos las credenciales
        $request->validate([
            'email'    => 'required|email',
            'password' => 'required|string',
        ]);

        // Comprobamos si las credenciales son correctas
        if (!Auth::attempt($request->only('email', 'password'))) {
            throw ValidationException::withMessages([
                'email' => ['Las credenciales no son correctas.'],
            ]);
        }

        $user = User::where('email', $request->email)->firstOrFail();

        // Actualizamos el último acceso
        $user->update(['ultimo_acceso' => now()]);

        // Generamos el token de autenticación
        $token = $user->createToken('auth_token')->plainTextToken;

        return response()->json([
            'message' => 'Login correcto',
            'user'    => $user,
            'token'   => $token,
        ]);
    }

    /**
     * Logout del usuario autenticado.
     * Elimina el token actual.
     */
    public function logout(Request $request)
    {
        // Eliminamos el token actual
        $request->user()->currentAccessToken()->delete();

        return response()->json([
            'message' => 'Sesión cerrada correctamente',
        ]);
    }

    /**
     * Devuelve los datos del usuario autenticado.
     */
    public function me(Request $request)
    {
        return response()->json($request->user());
    }
}
