<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Dieta extends Model
{
    use HasFactory;
    protected $table = 'dietas';

    protected $fillable = [
        'nombre',
        'dia_semana',
        'menu_detalle',
        'calorias',
        'descripcion',
    ];

    protected function casts(): array
    {
        return [
            'calorias' => 'integer',
        ];
    }

    // Relaciones
    public function clientes()
    {
        return $this->belongsToMany(Cliente::class, 'cliente_dieta', 'dieta_id', 'user_id')
            ->withPivot('fecha', 'estado', 'observaciones_ia');
    }

    public function consultasIA()
    {
        return $this->hasMany(ConsultaIa::class, 'dieta_id');
    }
}
