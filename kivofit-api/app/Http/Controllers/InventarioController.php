<?php

namespace App\Http\Controllers;

use App\Models\Inventario;
use App\Models\Tienda;
use Illuminate\Http\Request;

class InventarioController extends Controller
{
    /**
     * Devuelve el inventario completo.
     */
    public function index()
    {
        return response()->json(Inventario::with('producto')->get());
    }

    /**
     * Actualiza el stock de un producto.
     */
    public function update(Request $request, Tienda $tienda)
    {
        $request->validate([
            'stock' => 'required|integer|min:0',
        ]);

        $inventario = Inventario::updateOrCreate(
            ['producto_id' => $tienda->id],
            [
                'nombre_producto' => $tienda->nombre_producto,
                'stock'           => $request->stock,
            ]
        );

        return response()->json([
            'message'    => 'Stock actualizado correctamente',
            'inventario' => $inventario,
        ]);
    }
}
