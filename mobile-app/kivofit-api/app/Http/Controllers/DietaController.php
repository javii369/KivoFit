<?php

namespace App\Http\Controllers;

use App\Models\Dieta;
use App\Models\Cliente;
use Illuminate\Http\Request;

class DietaController extends Controller
{
    /**
     * Devuelve todas las dietas disponibles.
     */
    public function index()
    {
        return response()->json(Dieta::all());
    }

    /**
     * Devuelve una dieta concreta.
     */
    public function show(Dieta $dieta)
    {
        return response()->json($dieta);
    }

    /**
     * Crea una nueva dieta.
     */
    public function store(Request $request)
    {
        $request->validate([
            'nombre'      => 'required|string',
            'dia_semana'  => 'required|in:lunes,martes,miercoles,jueves,viernes,sabado,domingo',
            'menu_detalle' => 'nullable|string',
            'calorias'    => 'nullable|integer',
            'descripcion' => 'nullable|string',
        ]);

        $dieta = Dieta::create($request->all());

        return response()->json([
            'message' => 'Dieta creada correctamente',
            'dieta'   => $dieta,
        ], 201);
    }

    /**
     * Actualiza una dieta existente.
     */
    public function update(Request $request, Dieta $dieta)
    {
        $dieta->update($request->all());

        return response()->json([
            'message' => 'Dieta actualizada correctamente',
            'dieta'   => $dieta,
        ]);
    }

    /**
     * Elimina una dieta.
     */
    public function destroy(Dieta $dieta)
    {
        $dieta->delete();

        return response()->json([
            'message' => 'Dieta eliminada correctamente',
        ]);
    }

    /**
     * Asigna una dieta a un cliente.
     */
    public function asignar(Request $request, Dieta $dieta)
    {
        $request->validate([
            'user_id' => 'required|exists:clientes,user_id',
            'fecha'   => 'nullable|date',
        ]);

        $cliente = Cliente::findOrFail($request->user_id);
        $cliente->dietas()->attach($dieta->id, [
            'fecha'  => $request->fecha ?? now(),
            'estado' => 'activa',
        ]);

        return response()->json([
            'message' => 'Dieta asignada correctamente',
        ]);
    }
}
