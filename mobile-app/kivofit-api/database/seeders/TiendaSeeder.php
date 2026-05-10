<?php

namespace Database\Seeders;

use App\Models\Tienda;
use App\Models\Inventario;
use Illuminate\Database\Seeder;

class TiendaSeeder extends Seeder
{
    public function run(): void
    {
        $productos = [
            [
                'nombre_producto' => 'Camiseta KivoFit',
                'precio_puntos'   => 500,
                'precio_real'     => 19.99,
                'stock'           => 50,
            ],
            [
                'nombre_producto' => 'Botella de agua KivoFit',
                'precio_puntos'   => 300,
                'precio_real'     => 12.99,
                'stock'           => 30,
            ],
            [
                'nombre_producto' => 'Mochila deportiva',
                'precio_puntos'   => 1000,
                'precio_real'     => 39.99,
                'stock'           => 20,
            ],
            [
                'nombre_producto' => 'Toalla de gimnasio',
                'precio_puntos'   => 200,
                'precio_real'     => 8.99,
                'stock'           => 100,
            ],
            [
                'nombre_producto' => 'Guantes de entrenamiento',
                'precio_puntos'   => 400,
                'precio_real'     => 15.99,
                'stock'           => 40,
            ],
        ];

        foreach ($productos as $producto) {
            $stock = $producto['stock'];
            unset($producto['stock']);

            $tienda = Tienda::create($producto);

            Inventario::create([
                'producto_id'     => $tienda->id,
                'nombre_producto' => $tienda->nombre_producto,
                'stock'           => $stock,
            ]);
        }
    }
}
