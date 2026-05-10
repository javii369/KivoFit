<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    public function up(): void
    {
        Schema::create('rutinas', function (Blueprint $table) {
            $table->id();
            $table->string('nombre');
            $table->string('nivel')->nullable();
            $table->enum('dia_semana', [
                'lunes',
                'martes',
                'miercoles',
                'jueves',
                'viernes',
                'sabado',
                'domingo'
            ]);
            $table->integer('duracion')->nullable();
            $table->integer('calorias')->nullable();
            $table->text('descripcion')->nullable();
            $table->integer('rutinas_total')->default(0);
            $table->integer('tiempo_total')->default(0);
            $table->timestamps();
        });
    }

    public function down(): void
    {
        Schema::dropIfExists('rutinas');
    }
};
