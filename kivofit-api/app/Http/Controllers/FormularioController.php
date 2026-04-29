<?php

namespace App\Http\Controllers;

use App\Models\Formulario;
use Illuminate\Http\Request;

class FormularioController extends Controller
{
    /**
     * Devuelve el formulario del usuario autenticado.
     */
    public function show(Request $request)
    {
        $formulario = Formulario::where('user_id', $request->user()->id)->firstOrFail();

        return response()->json($formulario);
    }

    /**
     * Crea o actualiza el formulario del usuario autenticado.
     */
    public function store(Request $request)
    {
        $request->validate([
            'objetivo'           => 'nullable|string',
            'patologia'          => 'nullable|string',
            'preferencias_dieta' => 'nullable|string',
            'nivel'              => 'nullable|string',
            'numero_telf'        => 'nullable|string|max:15',
        ]);

        // updateOrCreate para crear si no existe o actualizar si ya existe
        $formulario = Formulario::updateOrCreate(
            ['user_id' => $request->user()->id],
            $request->only('objetivo', 'patologia', 'preferencias_dieta', 'nivel', 'numero_telf')
        );

        return response()->json([
            'message'    => 'Formulario guardado correctamente',
            'formulario' => $formulario,
        ]);
    }
}
