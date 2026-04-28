<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    public function up(): void
    {
        Schema::create('cliente_sesion', function (Blueprint $table) {
            $table->unsignedBigInteger('user_id');
            $table->unsignedBigInteger('sesion_id');
            $table->primary(['user_id', 'sesion_id']);
            $table->foreign('user_id')->references('user_id')->on('clientes')->onDelete('cascade');
            $table->foreign('sesion_id')->references('id')->on('sesion_clase')->onDelete('cascade');
            $table->date('fecha')->nullable();
            $table->string('estado')->default('reservada');
            $table->timestamps();
        });
    }

    public function down(): void
    {
        Schema::dropIfExists('cliente_sesion');
    }
};
