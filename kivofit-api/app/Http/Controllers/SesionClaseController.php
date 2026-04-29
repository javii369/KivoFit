<?php

namespace App\Http\Controllers;

use App\Models\SesionClase;
use App\Models\Cliente;
use Illuminate\Http\Request;

class SesionClaseController extends Controller
{
    /**
     * Devuelve todas las sesiones de una clase.
     */
    public function index(Request $request)
    {
        return response()->json(SesionClase::with('clase')->get());
    }

    /**
     * Crea una nueva sesión de clase.
     */
    public function store(Request $request)
    {
        $request->validate([
            'clase_id'        => 'required|exists:clases,id',
            'fecha'           => 'required|date',
            'hora_inicio'     => 'required',
            'duracion'        => 'nullable|integer',
            'capacidad_maxima' => 'nullable|integer',
            'sala'            => 'nullable|string',
        ]);

        $sesion = SesionClase::create($request->all());

        return response()->json([
            'message' => 'Sesión creada correctamente',
            'sesion'  => $sesion,
        ], 201);
    }

    /**
     * El cliente se apunta a una sesión.
     */
    public function apuntarse(Request $request, SesionClase $sesion)
    {
        $cliente = Cliente::where('user_id', $request->user()->id)->firstOrFail();

        $cliente->sesiones()->attach($sesion->id, [
            'fecha'  => $sesion->fecha,
            'estado' => 'reservada',
        ]);

        return response()->json([
            'message' => 'Apuntado a la sesión correctamente',
        ]);
    }

    /**
     * El cliente cancela su reserva en una sesión.
     */
    public function cancelar(Request $request, SesionClase $sesion)
    {
        $cliente = Cliente::where('user_id', $request->user()->id)->firstOrFail();
        $cliente->sesiones()->detach($sesion->id);

        return response()->json([
            'message' => 'Reserva cancelada correctamente',
        ]);
    }
}
