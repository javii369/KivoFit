<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Compra extends Model
{
    use HasFactory;

    protected $table = 'compras';

    protected $fillable = [
        'user_id',
        'producto_id',
        'precio_puntos',
        'precio_real',
        'fecha_compra',
    ];

    protected function casts(): array
    {
        return [
            'precio_real' => 'double',
            'fecha_compra' => 'datetime',
        ];
    }

    // Relaciones
    public function cliente()
    {
        return $this->belongsTo(Cliente::class, 'user_id', 'user_id');
    }

    public function producto()
    {
        return $this->belongsTo(Tienda::class, 'producto_id');
    }
}
