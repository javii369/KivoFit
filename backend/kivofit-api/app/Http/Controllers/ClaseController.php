<?php

namespace App\Http\Controllers;

use App\Models\Clase;
use Illuminate\Http\Request;

class ClaseController extends Controller
{
    /**
     * Devuelve todas las clases disponibles.
     */
    public function index()
    {
        return response()->json(Clase::with('entrenador.user')->get());
    }

    /**
     * Devuelve una clase concreta.
     */
    public function show(Clase $clase)
    {
        return response()->json($clase->load('entrenador.user', 'sesiones'));
    }

    /**
     * Crea una nueva clase.
     */
    public function store(Request $request)
    {
        $request->validate([
            'entrenador_id' => 'required|exists:entrenadores,user_id',
            'nombre_clase'  => 'required|string',
            'nivel'         => 'nullable|string',
        ]);

        $clase = Clase::create($request->all());

        return response()->json([
            'message' => 'Clase creada correctamente',
            'clase'   => $clase,
        ], 201);
    }

    /**
     * Actualiza una clase existente.
     */
    public function update(Request $request, Clase $clase)
    {
        $clase->update($request->all());

        return response()->json([
            'message' => 'Clase actualizada correctamente',
            'clase'   => $clase,
        ]);
    }

    /**
     * Elimina una clase.
     */
    public function destroy(Clase $clase)
    {
        $clase->delete();

        return response()->json([
            'message' => 'Clase eliminada correctamente',
        ]);
    }
}
