CREATE TABLE proveedor (
    id INT IDENTITY PRIMARY KEY,
    fecha_creacion DATE NOT NULL DEFAULT CAST(GETDATE() AS DATE),
    razon_social VARCHAR(50) NOT NULL UNIQUE,
    numero_identificacion VARCHAR(12) NOT NULL UNIQUE,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    celular VARCHAR(10) NOT NULL,
    telefono VARCHAR(10),
    correo VARCHAR(50) NOT NULL,
    direccion VARCHAR(50) NOT NULL,
    tipo_identificacion_id  INT NOT NULL,
    tipo_proveedor_id  INT NOT NULL,
    FOREIGN KEY (tipo_identificacion_id) REFERENCES tipo_identificacion(id),
    FOREIGN KEY (tipo_proveedor_id) REFERENCES tipo_proveedor(id)
);