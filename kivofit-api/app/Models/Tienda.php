<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Tienda extends Model
{
    use HasFactory;
    protected $table = 'tienda';

    protected $fillable = [
        'nombre_producto',
        'precio_puntos',
        'precio_real',
    ];

    protected function casts(): array
    {
        return [
            'precio_puntos' => 'integer',
            'precio_real' => 'double',
        ];
    }

    // Relaciones
    public function inventario()
    {
        return $this->hasOne(Inventario::class, 'producto_id');
    }

    public function clientes()
    {
        return $this->belongsToMany(Cliente::class, 'compras', 'producto_id', 'user_id')
            ->withPivot('precio_puntos', 'precio_real', 'fecha_compra');
    }
}
