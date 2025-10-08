CREATE TABLE lote (
    id INT IDENTITY PRIMARY KEY,
    fecha_creacion DATE NOT NULL DEFAULT CAST(GETDATE() AS DATE),
    lote VARCHAR(50) NOT NULL UNIQUE,
    fecha_siembra DATE NOT NULL,
    peces_iniciales INT NOT NULL,
    numero_peces INT NOT NULL,
    especie_id INT NOT NULL,
    proveedor_id INT NOT NULL,
    estanque_id INT NOT NULL
    FOREIGN KEY (especie_id) REFERENCES especie(id),
    FOREIGN KEY (proveedor_id) REFERENCES proveedor(id),
    FOREIGN KEY (estanque_id) REFERENCES estanque(id)
);