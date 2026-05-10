<?php

namespace App\Http\Middleware;

use Closure;
use Illuminate\Http\Request;

class CheckRol
{
    /**
     * Comprueba que el usuario autenticado tiene el rol requerido.
     */
    public function handle(Request $request, Closure $next, string ...$roles): mixed
    {
        if (!in_array($request->user()->rol, $roles)) {
            return response()->json([
                'message' => 'No tienes permisos para realizar esta acción.'
            ], 403);
        }

        return $next($request);
    }
}
