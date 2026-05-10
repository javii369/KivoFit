<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;

class DatabaseSeeder extends Seeder
{
    public function run(): void
    {
        $this->call([
            UserSeeder::class,    // Primero usuarios
            DietaSeeder::class,   // Luego dietas
            RutinaSeeder::class,  // Rutinas
            ClaseSeeder::class,   // Clases y sesiones (dependen de usuarios)
            TiendaSeeder::class,  // Tienda e inventario
        ]);
    }
}
