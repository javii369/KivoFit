<?php

namespace App\Http\Controllers;

use App\Models\Rutina;
use App\Models\Cliente;
use Illuminate\Http\Request;

class RutinaController extends Controller
{
    /**
     * Devuelve todas las rutinas disponibles.
     */
    public function index()
    {
        return response()->json(Rutina::all());
    }

    /**
     * Devuelve una rutina concreta.
     */
    public function show(Rutina $rutina)
    {
        return response()->json($rutina);
    }

    /**
     * Crea una nueva rutina.
     */
    public function store(Request $request)
    {
        $request->validate([
            'nombre'     => 'required|string',
            'nivel'      => 'nullable|string',
            'dia_semana' => 'required|in:lunes,martes,miercoles,jueves,viernes,sabado,domingo',
            'duracion'   => 'nullable|integer',
            'calorias'   => 'nullable|integer',
            'descripcion' => 'nullable|string',
        ]);

        $rutina = Rutina::create($request->all());

        return response()->json([
            'message' => 'Rutina creada correctamente',
            'rutina'  => $rutina,
        ], 201);
    }

    /**
     * Actualiza una rutina existente.
     */
    public function update(Request $request, Rutina $rutina)
    {
        $rutina->update($request->all());

        return response()->json([
            'message' => 'Rutina actualizada correctamente',
            'rutina'  => $rutina,
        ]);
    }

    /**
     * Elimina una rutina.
     */
    public function destroy(Rutina $rutina)
    {
        $rutina->delete();

        return response()->json([
            'message' => 'Rutina eliminada correctamente',
        ]);
    }

    /**
     * Asigna una rutina a un cliente.
     */
    public function asignar(Request $request, Rutina $rutina)
    {
        $request->validate([
            'user_id' => 'required|exists:clientes,user_id',
            'fecha'   => 'nullable|date',
        ]);

        $cliente = Cliente::findOrFail($request->user_id);
        $cliente->rutinas()->attach($rutina->id, [
            'fecha'  => $request->fecha ?? now(),
            'estado' => 'activa',
        ]);

        return response()->json([
            'message' => 'Rutina asignada correctamente',
        ]);
    }
}
