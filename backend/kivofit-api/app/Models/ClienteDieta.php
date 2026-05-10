<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class ClienteDieta extends Model
{
    use HasFactory;

    protected $table = 'cliente_dieta';

    protected $fillable = [
        'user_id',
        'dieta_id',
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

    public function dieta()
    {
        return $this->belongsTo(Dieta::class, 'dieta_id');
    }
}
