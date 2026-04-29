<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Clase extends Model
{
    use HasFactory;

    protected $fillable = [
        'entrenador_id',
        'nombre_clase',
        'nivel',
    ];

    // Relaciones
    public function entrenador()
    {
        return $this->belongsTo(Entrenador::class, 'entrenador_id', 'user_id');
    }

    public function sesiones()
    {
        return $this->hasMany(SesionClase::class, 'clase_id');
    }

    public function clientes()
    {
        return $this->belongsToMany(Cliente::class, 'cliente_clase', 'clase_id', 'user_id');
    }
}
