<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class ClienteSesion extends Model
{
    use HasFactory;

    protected $table = 'cliente_sesion';

    protected $fillable = [
        'user_id',
        'sesion_id',
        'fecha',
        'estado',
    ];

    protected function casts(): array
    {
        return [
            'fecha' => 'date',
        ];
    }

    // Relaciones
    public function cliente()
    {
        return $this->belongsTo(Cliente::class, 'user_id', 'user_id');
    }

    public function sesion()
    {
        return $this->belongsTo(SesionClase::class, 'sesion_id');
    }
}
