<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class ClienteRutina extends Model
{
    use HasFactory;

    protected $table = 'cliente_rutina';

    protected $fillable = [
        'user_id',
        'rutina_id',
        'fecha',
        'estado',
        'observaciones_ia',
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

    public function rutina()
    {
        return $this->belongsTo(Rutina::class, 'rutina_id');
    }
}
