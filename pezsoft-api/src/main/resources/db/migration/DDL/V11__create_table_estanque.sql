CREATE TABLE estanque (
    id INT IDENTITY PRIMARY KEY,
    fecha_creacion DATE NOT NULL DEFAULT CAST(GETDATE() AS DATE),
    estanque VARCHAR(50) NOT NULL UNIQUE,
    coordenadas VARCHAR(50) NOT NULL UNIQUE,
    largo DECIMAL(6,2) NOT NULL,
    ancho DECIMAL(6,2) NOT NULL,
    profundidad DECIMAL(6,2) NOT NULL,
    estado BIT NOT NULL,
    tipo_estanque_id  INT NOT NULL,
    FOREIGN KEY (tipo_estanque_id) REFERENCES tipo_estanque(id)
);