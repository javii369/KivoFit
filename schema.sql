CREATE TABLE usuarios (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL,
    email TEXT UNIQUE NOT NULL,
    nivel INTEGER DEFAULT 1,
    puntos INTEGER DEFAULT 0
);

CREATE TABLE rutinas (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL,
    dificultad TEXT,
    duracion INTEGER,
    calorias_estimadas INTEGER,
    creador_id INTEGER,
    FOREIGN KEY (creador_id) REFERENCES usuarios(id)
);

CREATE TABLE ejercicios (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL,
    repeticiones INTEGER
);

CREATE TABLE progreso (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    usuario_id INTEGER,
    rutina_id INTEGER,
    fecha TEXT,
    calorias INTEGER,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (rutina_id) REFERENCES rutinas(id)
);

CREATE TABLE alimentacion (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT,
    calorias INTEGER
);

CREATE TABLE rutina_ejercicios (
    rutina_id INTEGER,
    ejercicio_id INTEGER,
    PRIMARY KEY (rutina_id, ejercicio_id),
    FOREIGN KEY (rutina_id) REFERENCES rutinas(id),
    FOREIGN KEY (ejercicio_id) REFERENCES ejercicios(id)
);

CREATE INDEX idx_usuario_email
ON usuarios(email);
