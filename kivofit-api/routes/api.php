<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\AuthController;
use App\Http\Controllers\ClienteController;
use App\Http\Controllers\EntrenadorController;
use App\Http\Controllers\AdministradorController;
use App\Http\Controllers\DietaController;
use App\Http\Controllers\RutinaController;
use App\Http\Controllers\ClaseController;
use App\Http\Controllers\SesionClaseController;
use App\Http\Controllers\FormularioController;
use App\Http\Controllers\TiendaController;
use App\Http\Controllers\InventarioController;
use App\Http\Controllers\CompraController;
use App\Http\Controllers\ConsultaIaController;

// -------------------------------------------------------
// Rutas públicas — no requieren autenticación
// -------------------------------------------------------
Route::post('/register', [AuthController::class, 'register']);
Route::post('/login',    [AuthController::class, 'login']);

// -------------------------------------------------------
// Rutas protegidas — requieren token de autenticación
// -------------------------------------------------------
Route::middleware('auth:sanctum')->group(function () {

    // Auth
    Route::post('/logout', [AuthController::class, 'logout']);
    Route::get('/me',      [AuthController::class, 'me']);

    // Cliente
    Route::get('/cliente',         [ClienteController::class, 'show']);
    Route::put('/cliente',         [ClienteController::class, 'update']);
    Route::get('/cliente/dietas',  [ClienteController::class, 'dietas']);
    Route::get('/cliente/rutinas', [ClienteController::class, 'rutinas']);
    Route::get('/cliente/clases',  [ClienteController::class, 'clases']);

    // Entrenador
    Route::get('/entrenador',        [EntrenadorController::class, 'show']);
    Route::put('/entrenador',        [EntrenadorController::class, 'update']);
    Route::get('/entrenador/clases', [EntrenadorController::class, 'clases']);

    // Formulario
    Route::get('/formulario',  [FormularioController::class, 'show']);
    Route::post('/formulario', [FormularioController::class, 'store']);

    // Dietas — lectura para todos, escritura solo entrenadores y administradores
    Route::get('/dietas',         [DietaController::class, 'index']);
    Route::get('/dietas/{dieta}', [DietaController::class, 'show']);
    Route::middleware('rol:entrenador,administrador')->group(function () {
        Route::post('/dietas',                 [DietaController::class, 'store']);
        Route::put('/dietas/{dieta}',          [DietaController::class, 'update']);
        Route::delete('/dietas/{dieta}',       [DietaController::class, 'destroy']);
        Route::post('/dietas/{dieta}/asignar', [DietaController::class, 'asignar']);
    });

    // Rutinas — lectura para todos, escritura solo entrenadores y administradores
    Route::get('/rutinas',          [RutinaController::class, 'index']);
    Route::get('/rutinas/{rutina}', [RutinaController::class, 'show']);
    Route::middleware('rol:entrenador,administrador')->group(function () {
        Route::post('/rutinas',                  [RutinaController::class, 'store']);
        Route::put('/rutinas/{rutina}',          [RutinaController::class, 'update']);
        Route::delete('/rutinas/{rutina}',       [RutinaController::class, 'destroy']);
        Route::post('/rutinas/{rutina}/asignar', [RutinaController::class, 'asignar']);
    });

    // Clases — lectura para todos, escritura solo entrenadores y administradores
    Route::get('/clases',         [ClaseController::class, 'index']);
    Route::get('/clases/{clase}', [ClaseController::class, 'show']);
    Route::middleware('rol:entrenador,administrador')->group(function () {
        Route::post('/clases',           [ClaseController::class, 'store']);
        Route::put('/clases/{clase}',    [ClaseController::class, 'update']);
        Route::delete('/clases/{clase}', [ClaseController::class, 'destroy']);
    });

    // Sesiones de clase
    Route::get('/sesiones',                      [SesionClaseController::class, 'index']);
    Route::post('/sesiones',                     [SesionClaseController::class, 'store']);
    Route::post('/sesiones/{sesion}/apuntarse',  [SesionClaseController::class, 'apuntarse']);
    Route::delete('/sesiones/{sesion}/cancelar', [SesionClaseController::class, 'cancelar']);

    // Tienda — lectura para todos, escritura solo administradores
    Route::get('/tienda',          [TiendaController::class, 'index']);
    Route::get('/tienda/{tienda}', [TiendaController::class, 'show']);
    Route::middleware('rol:administrador')->group(function () {
        Route::post('/tienda',            [TiendaController::class, 'store']);
        Route::put('/tienda/{tienda}',    [TiendaController::class, 'update']);
        Route::delete('/tienda/{tienda}', [TiendaController::class, 'destroy']);
    });

    // Inventario — lectura para todos, escritura solo administradores
    Route::get('/inventario', [InventarioController::class, 'index']);
    Route::middleware('rol:administrador')->group(function () {
        Route::put('/inventario/{tienda}', [InventarioController::class, 'update']);
    });

    // Compras
    Route::get('/compras',  [CompraController::class, 'index']);
    Route::post('/compras', [CompraController::class, 'store']);

    // Consultas IA
    Route::get('/consultas-ia',  [ConsultaIaController::class, 'index']);
    Route::post('/consultas-ia', [ConsultaIaController::class, 'store']);

    // Admin — solo administradores
    Route::prefix('admin')->middleware('rol:administrador')->group(function () {
        Route::get('/usuarios',               [AdministradorController::class, 'usuarios']);
        Route::put('/usuarios/{user}/toggle', [AdministradorController::class, 'toggleUsuario']);
        Route::delete('/usuarios/{user}',     [AdministradorController::class, 'eliminarUsuario']);
    });
});
