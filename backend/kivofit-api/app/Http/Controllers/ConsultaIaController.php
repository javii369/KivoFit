<?php

namespace App\Http\Controllers;

use App\Models\ConsultaIa;
use Illuminate\Http\Request;

class ConsultaIaController extends Controller
{
    /**
     * Devuelve el historial de consultas IA del usuario autenticado.
     */
    public function index(Request $request)
    {
        $consultas = ConsultaIa::where('user_id', $request->user()->id)
            ->with('dieta', 'rutina')
            ->orderBy('fecha', 'desc')
            ->get();

        return response()->json($consultas);
    }

    /**
     * Guarda una nueva consulta y devuelve la respuesta de la IA.
     */
    public function store(Request $request)
    {
        $request->validate([
            'consulta_usuario' => 'required|string',
            'dieta_id'         => 'nullable|exists:dietas,id',
            'rutina_id'        => 'nullable|exists:rutinas,id',
        ]);

        // Aquí irá la integración con la IA en el futuro
        $respuesta = 'Respuesta de la IA pendiente de integración.';

        $consulta = ConsultaIa::create([
            'user_id'          => $request->user()->id,
            'consulta_usuario' => $request->consulta_usuario,
            'respuesta_ia'     => $respuesta,
            'dieta_id'         => $request->dieta_id,
            'rutina_id'        => $request->rutina_id,
        ]);

        return response()->json([
            'message'  => 'Consulta guardada correctamente',
            'consulta' => $consulta,
        ], 201);
    }
}
