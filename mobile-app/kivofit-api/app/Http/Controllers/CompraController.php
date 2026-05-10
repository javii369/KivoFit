<?php

namespace App\Http\Controllers;

use App\Models\Compra;
use App\Models\Cliente;
use App\Models\Tienda;
use App\Models\Inventario;
use Illuminate\Http\Request;

class CompraController extends Controller
{
    /**
     * Devuelve el historial de compras del cliente autenticado.
     */
    public function index(Request $request)
    {
        $cliente = Cliente::where('user_id', $request->user()->id)->firstOrFail();

        return response()->json($cliente->compras()->withPivot('precio_puntos', 'precio_real', 'fecha_compra')->get());
    }

    /**
     * El cliente realiza una compra.
     */
    public function store(Request $request)
    {
        $request->validate([
            'producto_id'  => 'required|exists:tienda,id',
            'usar_puntos'  => 'required|boolean',
        ]);

        $cliente  = Cliente::where('user_id', $request->user()->id)->firstOrFail();
        $producto = Tienda::findOrFail($request->producto_id);

        // Comprobamos stock disponible
        $inventario = Inventario::where('producto_id', $producto->id)->firstOrFail();
        if ($inventario->stock <= 0) {
            return response()->json(['message' => 'No hay stock disponible'], 400);
        }

        // Registramos la compra
        $cliente->compras()->attach($producto->id, [
            'precio_puntos' => $request->usar_puntos ? $producto->precio_puntos : null,
            'precio_real'   => !$request->usar_puntos ? $producto->precio_real : null,
            'fecha_compra'  => now(),
        ]);

        // Reducimos el stock
        $inventario->decrement('stock');

        return response()->json([
            'message' => 'Compra realizada correctamente',
        ], 201);
    }
}
