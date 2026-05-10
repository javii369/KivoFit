<?php

namespace Database\Seeders;

use App\Models\Dieta;
use Illuminate\Database\Seeder;

class DietaSeeder extends Seeder
{
    public function run(): void
    {
        $dietas = [
            [
                'nombre'       => 'Dieta mediterránea',
                'dia_semana'   => 'lunes',
                'menu_detalle' => 'Desayuno: tostada con tomate y aceite. Comida: ensalada griega con pollo. Cena: pescado a la plancha con verduras.',
                'calorias'     => 1800,
                'descripcion'  => 'Dieta equilibrada basada en productos mediterráneos.',
            ],
            [
                'nombre'       => 'Dieta proteica',
                'dia_semana'   => 'martes',
                'menu_detalle' => 'Desayuno: huevos revueltos con avena. Comida: pechuga de pollo con arroz integral. Cena: atún con boniato.',
                'calorias'     => 2200,
                'descripcion'  => 'Dieta alta en proteínas para ganar músculo.',
            ],
            [
                'nombre'       => 'Dieta hipocalórica',
                'dia_semana'   => 'miercoles',
                'menu_detalle' => 'Desayuno: yogur con frutas. Comida: ensalada de quinoa con verduras. Cena: sopa de verduras.',
                'calorias'     => 1400,
                'descripcion'  => 'Dieta baja en calorías para perder peso.',
            ],
            [
                'nombre'       => 'Dieta vegetariana',
                'dia_semana'   => 'jueves',
                'menu_detalle' => 'Desayuno: smoothie de frutas con semillas. Comida: lentejas con arroz. Cena: tortilla de verduras.',
                'calorias'     => 1600,
                'descripcion'  => 'Dieta equilibrada sin carne ni pescado.',
            ],
            [
                'nombre'       => 'Dieta de mantenimiento',
                'dia_semana'   => 'viernes',
                'menu_detalle' => 'Desayuno: cereales con leche y fruta. Comida: pasta con salsa de tomate y carne. Cena: ensalada mixta con huevo.',
                'calorias'     => 2000,
                'descripcion'  => 'Dieta equilibrada para mantener el peso actual.',
            ],
        ];

        foreach ($dietas as $dieta) {
            Dieta::create($dieta);
        }
    }
}
