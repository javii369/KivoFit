<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class SesionClase extends Model
{
    use HasFactory;

    protected $table = 'sesion_clase';

    protected $fillable = [
        'clase_id',
        'duracion',
        'fecha',
        'capacidad_maxima',
        'sala',
        'hora_inicio',
    ];

    protected function casts(): array
    {
        return [
            'fecha' => 'date',
            'duracion' => 'integer',
            'capacidad_maxima' => 'integer',
        ];
    }

    // Relaciones
    public function clase()
    {
        return $this->belongsTo(Clase::class, 'clase_id');
    }

    public function clientes()
    {
        return $this->belongsToMany(Cliente::class, 'cliente_sesion', 'sesion_id', 'user_id')
            ->withPivot('fecha', 'estado');
    }
}
