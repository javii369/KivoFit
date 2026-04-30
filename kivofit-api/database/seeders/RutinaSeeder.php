<?php

namespace Database\Seeders;

use App\Models\Rutina;
use Illuminate\Database\Seeder;

class RutinaSeeder extends Seeder
{
    public function run(): void
    {
        $rutinas = [
            [
                'nombre'       => 'Rutina de fuerza',
                'nivel'        => 'intermedio',
                'dia_semana'   => 'lunes',
                'duracion'     => 60,
                'calorias'     => 400,
                'descripcion'  => 'Rutina enfocada en ejercicios de fuerza con pesas.',
                'rutinas_total' => 3,
                'tiempo_total' => 180,
            ],
            [
                'nombre'       => 'Rutina cardio',
                'nivel'        => 'principiante',
                'dia_semana'   => 'martes',
                'duracion'     => 45,
                'calorias'     => 300,
                'descripcion'  => 'Rutina de cardio para quemar grasa y mejorar resistencia.',
                'rutinas_total' => 4,
                'tiempo_total' => 180,
            ],
            [
                'nombre'       => 'Rutina HIIT',
                'nivel'        => 'avanzado',
                'dia_semana'   => 'miercoles',
                'duracion'     => 30,
                'calorias'     => 500,
                'descripcion'  => 'Entrenamiento de alta intensidad por intervalos.',
                'rutinas_total' => 5,
                'tiempo_total' => 150,
            ],
            [
                'nombre'       => 'Rutina yoga',
                'nivel'        => 'principiante',
                'dia_semana'   => 'jueves',
                'duracion'     => 60,
                'calorias'     => 200,
                'descripcion'  => 'Rutina de yoga para flexibilidad y relajación.',
                'rutinas_total' => 3,
                'tiempo_total' => 180,
            ],
            [
                'nombre'       => 'Rutina funcional',
                'nivel'        => 'intermedio',
                'dia_semana'   => 'viernes',
                'duracion'     => 50,
                'calorias'     => 350,
                'descripcion'  => 'Entrenamiento funcional con ejercicios de cuerpo completo.',
                'rutinas_total' => 4,
                'tiempo_total' => 200,
            ],
        ];

        foreach ($rutinas as $rutina) {
            Rutina::create($rutina);
        }
    }
}
