<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Entrenador extends Model
{
    use HasFactory;

    protected $primaryKey = 'user_id';
    protected $table = 'entrenadores';
    public $incrementing = false;

    protected $fillable = [
        'user_id',
        'especialidad',
        'calificacion',
        'disponibilidad',
    ];

    protected function casts(): array
    {
        return [
            'disponibilidad' => 'boolean',
            'calificacion' => 'integer',
        ];
    }

    // Relaciones
    public function user()
    {
        return $this->belongsTo(User::class, 'user_id');
    }

    public function clases()
    {
        return $this->hasMany(Clase::class, 'entrenador_id');
    }
}
