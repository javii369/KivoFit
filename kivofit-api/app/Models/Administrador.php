<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Administrador extends Model
{
    use HasFactory;

    protected $primaryKey = 'user_id';
    public $incrementing = false;

    protected $fillable = [
        'user_id',
        'clave_maestra',
        'fecha_ultimo_respaldo',
        'log_actividad_global',
    ];

    protected function casts(): array
    {
        return [
            'fecha_ultimo_respaldo' => 'datetime',
        ];
    }

    // Relaciones
    public function user()
    {
        return $this->belongsTo(User::class, 'user_id');
    }
}
