<?php

namespace App\Http\Controllers;

use App\Models\Entrenador;
use App\Models\Clase;
use Illuminate\Http\Request;

class EntrenadorController extends Controller
{
    /**
     * Devuelve el perfil del entrenador autenticado.
     */
    public function show(Request $request)
    {
        $entrenador = Entrenador::with('user')
            ->where('user_id', $request->user()->id)
            ->firstOrFail();

        return response()->json($entrenador);
    }

    /**
     * Actualiza los datos del entrenador autenticado.
     */
    public function update(Request $request)
    {
        $request->validate([
            'especialidad'   => 'nullable|string',
            'calificacion'   => 'nullable|integer|min:0|max:5',
            'disponibilidad' => 'nullable|boolean',
        ]);

        $entrenador = Entrenador::where('user_id', $request->user()->id)->firstOrFail();
        $entrenador->update($request->only('especialidad', 'calificacion', 'disponibilidad'));

        return response()->json([
            'message'    => 'Perfil actualizado correctamente',
            'entrenador' => $entrenador,
        ]);
    }

    /**
     * Devuelve las clases del entrenador autenticado.
     */
    public function clases(Request $request)
    {
        $entrenador = Entrenador::where('user_id', $request->user()->id)->firstOrFail();

        return response()->json($entrenador->clases()->with('sesiones')->get());
    }
}
