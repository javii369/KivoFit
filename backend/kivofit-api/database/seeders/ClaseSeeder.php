<?php

namespace Database\Seeders;

use App\Models\Clase;
use App\Models\SesionClase;
use App\Models\User;
use Illuminate\Database\Seeder;

class ClaseSeeder extends Seeder
{
    public function run(): void
    {
        // Obtenemos los entrenadores
        $carlos = User::where('email', 'carlos@kivofit.com')->first();
        $laura  = User::where('email', 'laura@kivofit.com')->first();

        // Clases de Carlos
        $clase1 = Clase::create([
            'entrenador_id' => $carlos->id,
            'nombre_clase'  => 'Musculación avanzada',
            'nivel'         => 'avanzado',
        ]);

        SesionClase::create([
            'clase_id'         => $clase1->id,
            'fecha'            => '2026-05-05',
            'hora_inicio'      => '09:00:00',
            'duracion'         => 60,
            'capacidad_maxima' => 10,
            'sala'             => 'Sala A',
        ]);

        SesionClase::create([
            'clase_id'         => $clase1->id,
            'fecha'            => '2026-05-07',
            'hora_inicio'      => '09:00:00',
            'duracion'         => 60,
            'capacidad_maxima' => 10,
            'sala'             => 'Sala A',
        ]);

        $clase2 = Clase::create([
            'entrenador_id' => $carlos->id,
            'nombre_clase'  => 'Cardio intensivo',
            'nivel'         => 'intermedio',
        ]);

        SesionClase::create([
            'clase_id'         => $clase2->id,
            'fecha'            => '2026-05-06',
            'hora_inicio'      => '18:00:00',
            'duracion'         => 45,
            'capacidad_maxima' => 15,
            'sala'             => 'Sala B',
        ]);

        // Clases de Laura
        $clase3 = Clase::create([
            'entrenador_id' => $laura->id,
            'nombre_clase'  => 'Yoga para principiantes',
            'nivel'         => 'principiante',
        ]);

        SesionClase::create([
            'clase_id'         => $clase3->id,
            'fecha'            => '2026-05-05',
            'hora_inicio'      => '11:00:00',
            'duracion'         => 60,
            'capacidad_maxima' => 12,
            'sala'             => 'Sala C',
        ]);

        $clase4 = Clase::create([
            'entrenador_id' => $laura->id,
            'nombre_clase'  => 'Pilates avanzado',
            'nivel'         => 'avanzado',
        ]);

        SesionClase::create([
            'clase_id'         => $clase4->id,
            'fecha'            => '2026-05-08',
            'hora_inicio'      => '17:00:00',
            'duracion'         => 55,
            'capacidad_maxima' => 8,
            'sala'             => 'Sala C',
        ]);
    }
}
