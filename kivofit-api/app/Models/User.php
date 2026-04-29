<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Foundation\Auth\User as Authenticatable;
use Illuminate\Notifications\Notifiable;
use Laravel\Sanctum\HasApiTokens;

class User extends Authenticatable
{
    use HasFactory, Notifiable;
    use HasApiTokens;

    protected $fillable = [
        'dni',
        'nombre',
        'apellido',
        'segundo_apellido',
        'foto_url',
        'email',
        'password',
        'rol',
        'activo',
        'fecha_registro',
        'ultimo_acceso',
    ];

    protected $hidden = [
        'password',
        'remember_token',
    ];

    protected function casts(): array
    {
        return [
            'password' => 'hashed',
            'activo' => 'boolean',
            'fecha_registro' => 'datetime',
            'ultimo_acceso' => 'datetime',
        ];
    }

    // Relaciones
    public function cliente()
    {
        return $this->hasOne(Cliente::class, 'user_id');
    }

    public function entrenador()
    {
        return $this->hasOne(Entrenador::class, 'user_id');
    }

    public function administrador()
    {
        return $this->hasOne(Administrador::class, 'user_id');
    }

    public function formulario()
    {
        return $this->hasOne(Formulario::class, 'user_id');
    }

    public function consultasAi()
    {
        return $this->hasMany(ConsultaIa::class, 'user_id');
    }
}
