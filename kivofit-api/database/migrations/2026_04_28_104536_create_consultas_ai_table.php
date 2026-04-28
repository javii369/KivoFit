<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    public function up(): void
    {
        Schema::create('consultas_ai', function (Blueprint $table) {
            $table->id();
            $table->unsignedBigInteger('user_id');
            $table->foreign('user_id')->references('id')->on('users')->onDelete('cascade');
            $table->text('consulta_usuario');
            $table->text('respuesta_ia')->nullable();
            $table->unsignedBigInteger('dieta_id')->nullable();
            $table->foreign('dieta_id')->references('id')->on('dietas')->onDelete('set null');
            $table->unsignedBigInteger('rutina_id')->nullable();
            $table->foreign('rutina_id')->references('id')->on('rutinas')->onDelete('set null');
            $table->timestamp('fecha')->useCurrent();
            $table->timestamps();
        });
    }

    public function down(): void
    {
        Schema::dropIfExists('consultas_ai');
    }
};
