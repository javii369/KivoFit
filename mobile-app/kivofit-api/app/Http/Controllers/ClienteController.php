<?php

namespace App\Http\Controllers;

use App\Models\Cliente;
use Illuminate\Http\Request;

class ClienteController extends Controller
{
    /**
     * Devuelve el perfil del cliente autenticado.
     */
    public function show(Request $request)
    {
        // Cargamos el cliente con su usuario y formulario
        $cliente = Cliente::with(['user', 'formulario'])
            ->where('user_id', $request->user()->id)
            ->firstOrFail();

        return response()->json($cliente);
    }

    /**
     * Actualiza los datos del cliente autenticado.
     */
    public function update(Request $request)
    {
        $request->validate([
            'peso'    => 'nullable|numeric',
            'altura'  => 'nullable|numeric',
            'nivel'   => 'nullable|string',
            'objetivo' => 'nullable|string',
        ]);

        $cliente = Cliente::where('user_id', $request->user()->id)->firstOrFail();
        $cliente->update($request->only('peso', 'altura', 'nivel', 'objetivo'));

        return response()->json([
            'message' => 'Perfil actualizado correctamente',
            'cliente' => $cliente,
        ]);
    }

    /**
     * Devuelve las dietas asignadas al cliente autenticado.
     */
    public function dietas(Request $request)
    {
        $cliente = Cliente::where('user_id', $request->user()->id)->firstOrFail();

        return response()->json($cliente->dietas()->withPivot('fecha', 'estado', 'observaciones_ia')->get());
    }

    /**
     * Devuelve las rutinas asignadas al cliente autenticado.
     */
    public function rutinas(Request $request)
    {
        $cliente = Cliente::where('user_id', $request->user()->id)->firstOrFail();

        return response()->json($cliente->rutinas()->withPivot('fecha', 'estado', 'observaciones_ia')->get());
    }

    /**
     * Devuelve las clases del cliente autenticado.
     */
    public function clases(Request $request)
    {
        $cliente = Cliente::where('user_id', $request->user()->id)->firstOrFail();

        return response()->json($cliente->clases()->get());
    }
}
