<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    public function up(): void
    {
        Schema::create('compras', function (Blueprint $table) {
            $table->unsignedBigInteger('user_id');
            $table->unsignedBigInteger('producto_id');
            $table->primary(['user_id', 'producto_id']);
            $table->foreign('user_id')->references('user_id')->on('clientes')->onDelete('cascade');
            $table->foreign('producto_id')->references('id')->on('tienda')->onDelete('cascade');
            $table->string('precio_puntos')->nullable();
            $table->double('precio_real')->nullable();
            $table->timestamp('fecha_compra')->useCurrent();
            $table->timestamps();
        });
    }

    public function down(): void
    {
        Schema::dropIfExists('compras');
    }
};
