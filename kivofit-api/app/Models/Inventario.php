<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Inventario extends Model
{
    use HasFactory;

    protected $primaryKey = 'producto_id';
    public $incrementing = false;

    protected $fillable = [
        'producto_id',
        'nombre_producto',
        'stock',
    ];

    protected function casts(): array
    {
        return [
            'stock' => 'integer',
        ];
    }

    // Relaciones
    public function producto()
    {
        return $this->belongsTo(Tienda::class, 'producto_id');
    }
}
