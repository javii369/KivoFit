<?php

namespace App\Http\Controllers;

use App\Models\Tienda;
use Illuminate\Http\Request;

class TiendaController extends Controller
{
    /**
     * Devuelve todos los productos de la tienda.
     */
    public function index()
    {
        return response()->json(Tienda::with('inventario')->get());
    }

    /**
     * Devuelve un producto concreto.
     */
    public function show(Tienda $tienda)
    {
        return response()->json($tienda->load('inventario'));
    }

    /**
     * Crea un nuevo producto en la tienda.
     */
    public function store(Request $request)
    {
        $request->validate([
            'nombre_producto' => 'required|string',
            'precio_puntos'   => 'required|integer',
            'precio_real'     => 'required|numeric',
        ]);

        $producto = Tienda::create($request->all());

        return response()->json([
            'message'  => 'Producto creado correctamente',
            'producto' => $producto,
        ], 201);
    }

    /**
     * Actualiza un producto existente.
     */
    public function update(Request $request, Tienda $tienda)
    {
        $tienda->update($request->all());

        return response()->json([
            'message'  => 'Producto actualizado correctamente',
            'producto' => $tienda,
        ]);
    }

    /**
     * Elimina un producto de la tienda.
     */
    public function destroy(Tienda $tienda)
    {
        $tienda->delete();

        return response()->json([
            'message' => 'Producto eliminado correctamente',
        ]);
    }
}
