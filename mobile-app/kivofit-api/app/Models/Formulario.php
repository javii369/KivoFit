<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Formulario extends Model
{
    use HasFactory;
    protected $table = 'formularios';

    protected $fillable = [
        'user_id',
        'objetivo',
        'patologia',
        'preferencias_dieta',
        'nivel',
        'numero_telf',
    ];

    // Relaciones
    public function user()
    {
        return $this->belongsTo(User::class, 'user_id');
    }

    public function cliente()
    {
        return $this->belongsTo(Cliente::class, 'user_id', 'user_id');
    }
}
