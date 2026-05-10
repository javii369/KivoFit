<?php

namespace App\Http\Controllers;

use App\Models\Administrador;
use App\Models\User;
use Illuminate\Http\Request;

class AdministradorController extends Controller
{
    /**
     * Devuelve la lista de todos los usuarios del sistema.
     */
    public function usuarios()
    {
        return response()->json(User::all());
    }

    /**
     * Activa o desactiva un usuario.
     */
    public function toggleUsuario(User $user)
    {
        $user->update(['activo' => !$user->activo]);

        return response()->json([
            'message' => 'Estado del usuario actualizado',
            'user'    => $user,
        ]);
    }

    /**
     * Elimina un usuario del sistema.
     */
    public function eliminarUsuario(User $user)
    {
        $user->delete();

        return response()->json([
            'message' => 'Usuario eliminado correctamente',
        ]);
    }
}
