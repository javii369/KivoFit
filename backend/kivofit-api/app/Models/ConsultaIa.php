<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class ConsultaIa extends Model
{
    use HasFactory;

    protected $table = 'consultas_ia';

    protected $fillable = [
        'user_id',
        'consulta_usuario',
        'respuesta_ia',
        'dieta_id',
        'rutina_id',
        'fecha',
    ];

    protected function casts(): array
    {
        return [
            'fecha' => 'datetime',
        ];
    }

    // Relaciones
    public function user()
    {
        return $this->belongsTo(User::class, 'user_id');
    }

    public function dieta()
    {
        return $this->belongsTo(Dieta::class, 'dieta_id');
    }

    public function rutina()
    {
        return $this->belongsTo(Rutina::class, 'rutina_id');
    }
}
