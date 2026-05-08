La base de datos del proyecto KivoFit ha sido diseñada utilizando SQLite como sistema gestor de bases de datos relacional. La elección de SQLite se debe a su simplicidad, facilidad de integración y capacidad para almacenar la información de forma estructurada dentro de un único archivo portable.

El diseño de la base de datos se ha enfocado en representar correctamente las relaciones entre los diferentes componentes de la aplicación, permitiendo almacenar usuarios, rutinas de entrenamiento, ejercicios, progreso y planes de alimentación.

Además, se han utilizado claves primarias y claves foráneas para mantener la integridad de los datos y asegurar relaciones consistentes entre las distintas tablas del sistema.

El modelo de datos de KivoFit está compuesto por las siguientes entidades principales:

* **usuarios**: almacena la información principal de cada usuario de la aplicación, incluyendo nombre, correo electrónico, nivel y puntos acumulados.
* **rutinas**: contiene las rutinas de entrenamiento disponibles dentro de la plataforma.
* **ejercicios**: almacena los ejercicios individuales que pueden formar parte de una rutina.
* **rutina_ejercicios**: tabla intermedia encargada de relacionar rutinas con ejercicios, permitiendo relaciones de muchos a muchos.
* **progreso**: registra el avance de los usuarios, incluyendo calorías consumidas, rutinas realizadas y fechas de actividad.
* **alimentacion**: almacena información relacionada con planes alimenticios y recomendaciones nutricionales.

Las relaciones entre las tablas se gestionan mediante claves foráneas, permitiendo mantener una estructura organizada y escalable.

La estructura relacional de la base de datos se organiza mediante varias tablas conectadas entre sí.

Tabla usuarios:

* id
* nombre
* email
* nivel
* puntos

Tabla rutinas:

* id
* nombre
* dificultad
* duracion
* calorias_estimadas
* creador_id

Tabla ejercicios:

* id
* nombre
* repeticiones

Tabla rutina_ejercicios:

* rutina_id
* ejercicio_id

Tabla progreso:

* id
* usuario_id
* rutina_id
* fecha
* calorias

Tabla alimentacion:

* id
* nombre
* calorias

La estructura ha sido diseñada buscando simplicidad, eficiencia y facilidad de mantenimiento.

