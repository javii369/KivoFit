<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    public function up(): void
    {
        Schema::create('users', function (Blueprint $table) {
            $table->id();
            $table->string('dni', 9)->unique();
            $table->string('nombre');
            $table->string('apellido');
            $table->string('segundo_apellido')->nullable();
            $table->string('foto_url')->nullable();
            $table->string('email')->unique();
            $table->string('password');
            $table->enum('rol', ['cliente', 'entrenador', 'administrador']);
            $table->boolean('activo')->default(true);
            $table->timestamp('fecha_registro')->useCurrent();
            $table->timestamp('ultimo_acceso')->nullable();
            $table->rememberToken();
            $table->timestamps();
        });
    }

    public function down(): void
    {
        Schema::dropIfExists('users');
    }
};
