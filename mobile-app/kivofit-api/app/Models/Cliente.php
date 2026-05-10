<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Cliente extends Model
{
    use HasFactory;

    protected $primaryKey = 'user_id';
    protected $table = 'clientes';
    public $incrementing = false;

    protected $fillable = [
        'user_id',
        'peso',
        'altura',
        'nivel',
        'objetivo',
    ];

    protected function casts(): array
    {
        return [
            'peso' => 'float',
            'altura' => 'float',
        ];
    }

    // Relaciones
    public function user()
    {
        return $this->belongsTo(User::class, 'user_id');
    }

    public function clases()
    {
        return $this->belongsToMany(Clase::class, 'cliente_clase', 'user_id', 'clase_id');
    }

    public function sesiones()
    {
        return $this->belongsToMany(SesionClase::class, 'cliente_sesion', 'user_id', 'sesion_id')
            ->withPivot('fecha', 'estado');
    }

    public function dietas()
    {
        return $this->belongsToMany(Dieta::class, 'cliente_dieta', 'user_id', 'dieta_id')
            ->withPivot('fecha', 'estado', 'observaciones_ia');
    }

    public function rutinas()
    {
        return $this->belongsToMany(Rutina::class, 'cliente_rutina', 'user_id', 'rutina_id')
            ->withPivot('fecha', 'estado', 'observaciones_ia');
    }

    public function compras()
    {
        return $this->belongsToMany(Tienda::class, 'compras', 'user_id', 'producto_id')
            ->withPivot('precio_puntos', 'precio_real', 'fecha_compra');
    }

    public function formulario()
    {
        return $this->hasOne(Formulario::class, 'user_id');
    }
}
