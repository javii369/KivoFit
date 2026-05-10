<?php

namespace Database\Seeders;

use App\Models\User;
use App\Models\Cliente;
use App\Models\Entrenador;
use App\Models\Administrador;
use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\Hash;

class UserSeeder extends Seeder
{
    public function run(): void
    {
        // Administrador
        $admin = User::create([
            'dni'      => '00000000A',
            'nombre'   => 'Admin',
            'apellido' => 'KivoFit',
            'email'    => 'admin@kivofit.com',
            'password' => Hash::make('12345678'),
            'rol'      => 'administrador',
        ]);
        Administrador::create(['user_id' => $admin->id]);

        // Entrenadores
        $entrenador1 = User::create([
            'dni'      => '11111111A',
            'nombre'   => 'Carlos',
            'apellido' => 'Lopez',
            'email'    => 'carlos@kivofit.com',
            'password' => Hash::make('12345678'),
            'rol'      => 'entrenador',
        ]);
        Entrenador::create([
            'user_id'       => $entrenador1->id,
            'especialidad'  => 'Fuerza y musculación',
            'calificacion'  => 5,
            'disponibilidad' => true,
        ]);

        $entrenador2 = User::create([
            'dni'      => '22222222B',
            'nombre'   => 'Laura',
            'apellido' => 'Martinez',
            'email'    => 'laura@kivofit.com',
            'password' => Hash::make('12345678'),
            'rol'      => 'entrenador',
        ]);
        Entrenador::create([
            'user_id'       => $entrenador2->id,
            'especialidad'  => 'Yoga y pilates',
            'calificacion'  => 4,
            'disponibilidad' => true,
        ]);

        // Clientes
        $cliente1 = User::create([
            'dni'      => '33333333C',
            'nombre'   => 'Daniel',
            'apellido' => 'Aguilar',
            'email'    => 'daniel@kivofit.com',
            'password' => Hash::make('12345678'),
            'rol'      => 'cliente',
        ]);
        Cliente::create([
            'user_id' => $cliente1->id,
            'peso'    => 75.5,
            'altura'  => 1.80,
            'nivel'   => 'intermedio',
            'objetivo' => 'ganar músculo',
        ]);

        $cliente2 = User::create([
            'dni'      => '44444444D',
            'nombre'   => 'Maria',
            'apellido' => 'Garcia',
            'email'    => 'maria@kivofit.com',
            'password' => Hash::make('12345678'),
            'rol'      => 'cliente',
        ]);
        Cliente::create([
            'user_id' => $cliente2->id,
            'peso'    => 60.0,
            'altura'  => 1.65,
            'nivel'   => 'principiante',
            'objetivo' => 'perder peso',
        ]);
    }
}
