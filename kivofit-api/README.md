# KivoFit API - Backend

## 📌 Descripción

Backend de KivoFit desarrollado con **Laravel 13** y **MySQL**. API REST con autenticación por tokens (Sanctum), control de roles (cliente/entrenador/admin) y seeders con datos de prueba.

## 🛠️ Tecnologías

| Tecnología | Uso |
|------------|-----|
| Laravel 13 | Framework |
| MySQL | Base de datos |
| Laragon | Entorno local |
| Postman | Pruebas de API |
| Sanctum | Autenticación con tokens |

## 📦 Instalación (5 pasos)

```bash

# 1. Clonar la rama
git clone -b kivofit-api https://github.com/javii369/KivoFit.git
cd KivoFit

# 2. Instalar dependencias
composer install

# 3. Configurar .env
cp .env.example .env
# Edita .env: cambia DB_CONNECTION=mysql y pon tus credenciales

# 4. Generar key y migrar
php artisan key:generate
php artisan migrate

# 5. Cargar datos de prueba y arrancar
php artisan db:seed
php artisan serve

```

## 📌 Variable de entorno (.env)
DB_CONNECTION=mysql
DB_HOST=127.0.0.1
DB_PORT=3306
DB_DATABASE=kivofit
DB_USERNAME=root
DB_PASSWORD=ç

## 📌 Probar con Postman
POST http://localhost:8000/api/login
Body (JSON):
{
    "email": "daniel@kivofit.com",
    "password": "12345678"
}