<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Rutina extends Model
{
    use HasFactory;

    protected $fillable = [
        'nombre',
        'nivel',
        'dia_semana',
        'duracion',
        'calorias',
        'descripcion',
        'rutinas_total',
        'tiempo_total',
    ];

    protected function casts(): array
    {
        return [
            'duracion' => 'integer',
            'calorias' => 'integer',
            'rutinas_total' => 'integer',
            'tiempo_total' => 'integer',
        ];
    }

    // Relaciones
    public function clientes()
    {
        return $this->belongsToMany(Cliente::class, 'cliente_rutina', 'rutina_id', 'user_id')
            ->withPivot('fecha', 'estado', 'observaciones_ia');
    }

    public function consultasIA()
    {
        return $this->hasMany(ConsultaIa::class, 'rutina_id');
    }
}
