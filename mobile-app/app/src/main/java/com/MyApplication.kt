package com.KivoFit

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * ----------------------------------------------------------------------------
 * MyApplication.kt
 * ----------------------------------------------------------------------------
 *
 * 🔹 Descripción general:
 * Clase base de la aplicación que **inicializa Hilt**.
 *
 * Esta clase es el **entry point** del contenedor de dependencias global de Dagger Hilt.
 * Al anotarla con `@HiltAndroidApp`, se genera automáticamente todo el grafo de
 * dependencias de la aplicación, permitiendo la inyección de clases anotadas con `@AndroidEntryPoint`.
 *
 * ----------------------------------------------------------------------------
 * 🔹 Contexto arquitectónico:
 * - Capa: **App-level / Dependency Injection**
 * - Vive durante todo el ciclo de vida de la aplicación.
 * - Cualquier dependencia con `@Singleton` se mantiene en memoria mientras
 *   esta clase exista.
 *
 * ----------------------------------------------------------------------------
 * 🔹 Por qué es importante:
 * ----------------------------------------------------------------------------
 * ✅ Es obligatorio para que Hilt funcione.
 * ✅ Crea el “ApplicationComponent” (en Hilt, `SingletonComponent`).
 * ✅ Permite usar `@Inject` en cualquier parte del proyecto.
 *
 * ----------------------------------------------------------------------------
 * 🔹 Cómo se conecta con el sistema:
 * ----------------------------------------------------------------------------
 * 1️⃣ Declarada en `AndroidManifest.xml`:
 * ```xml
 * <application
 *     android:name=".MyApplication"
 *     android:label="@string/app_name"
 *     android:icon="@mipmap/ic_launcher"
 *     ... />
 * ```
 *
 * 2️⃣ Desde aquí, Hilt genera los componentes base para toda la app.
 * 3️⃣ A partir de ahí, puedes usar `@AndroidEntryPoint` en Activities, Fragments y ViewModels.
 *
 * ----------------------------------------------------------------------------
 * 🔹 Ejemplo de flujo:
 * ----------------------------------------------------------------------------
 * ```
 * MyApplication (@HiltAndroidApp)
 *      ↳ SingletonComponent
 *          ↳ ActivityRetainedComponent
 *              ↳ ViewModelComponent
 *                  ↳ ActivityComponent
 * ```
 *
 * ----------------------------------------------------------------------------
 */
@HiltAndroidApp
class MyApplication : Application()
