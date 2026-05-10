<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    public function up(): void
    {
        Schema::create('cliente_rutina', function (Blueprint $table) {
            $table->unsignedBigInteger('user_id');
            $table->unsignedBigInteger('rutina_id');
            $table->primary(['user_id', 'rutina_id']);
            $table->foreign('user_id')->references('user_id')->on('clientes')->onDelete('cascade');
            $table->foreign('rutina_id')->references('id')->on('rutinas')->onDelete('cascade');
            $table->date('fecha')->nullable();
            $table->string('estado')->default('activa');
            $table->text('observaciones_ia')->nullable();
            $table->timestamps();
        });
    }

    public function down(): void
    {
        Schema::dropIfExists('cliente_rutina');
    }
};
