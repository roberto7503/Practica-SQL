DROP TABLE IF EXISTS empleado;

CREATE TABLE empleado
(
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombres VARCHAR(50) NOT NULL,
    apellidos VARCHAR(50) NOT NULL,
    cedula VARCHAR(20) NOT NULL UNIQUE,
    correo VARCHAR(80) UNIQUE,
    telefono VARCHAR(20),
    cargo VARCHAR(50) NOT NULL,
    departamento VARCHAR(50) NOT NULL,
    salario NUMERIC(10,2) NOT NULL,
    fecha_contratacion DATE NOT NULL,
    estado VARCHAR(10) NOT NULL
);

INSERT INTO empleado
    (nombres, apellidos, cedula, correo, telefono, cargo, departamento, salario, fecha_contratacion, estado)
VALUES
    ('Carlos', 'Martinez', '001-010101-0001A', 'carlos.martinez@empresa.com', '8888-1001', 'Programador', 'Tecnologia', 32000.00, '2024-01-15', 'Activo'),
    ('Ana', 'Lopez', '001-020202-0002B', 'ana.lopez@empresa.com', '8888-1002', 'Contador', 'Contabilidad', 28000.00, '2023-08-10', 'Activo'),
    ('Maria', 'Gonzalez', '001-030303-0003C', 'maria.gonzalez@empresa.com', '8888-1003', 'Analista', 'Recursos Humanos', 26000.00, '2024-03-20', 'Activo'),
    ('Jose', 'Ramirez', '001-040404-0004D', 'jose.ramirez@empresa.com', '8888-1004', 'Vendedor', 'Ventas', 22000.00, '2023-11-05', 'Activo'),
    ('Sofia', 'Hernandez', '001-050505-0005E', 'sofia.hernandez@empresa.com', '8888-1005', 'Supervisor', 'Bodega', 30000.00, '2022-09-12', 'Inactivo'),
    ('Pedro', 'Sanchez', '001-060606-0006F', 'pedro.sanchez@empresa.com', '8888-1006', 'Administrador', 'Administracion', 40000.00, '2021-06-18', 'Activo'),
    ('Lucia', 'Torres', '001-070707-0007G', 'lucia.torres@empresa.com', '8888-1007', 'Analista', 'Tecnologia', 29000.00, '2024-05-02', 'Activo'),
    ('Miguel', 'Castillo', '001-080808-0008H', 'miguel.castillo@empresa.com', '8888-1008', 'Vendedor', 'Ventas', 21000.00, '2023-04-25', 'Inactivo'),
    ('Daniela', 'Morales', '001-090909-0009I', 'daniela.morales@empresa.com', '8888-1009', 'Contador', 'Contabilidad', 27500.00, '2022-12-01', 'Activo'),
    ('Fernando', 'Ruiz', '001-101010-0010J', 'fernando.ruiz@empresa.com', '8888-1010', 'Supervisor', 'Bodega', 31000.00, '2023-07-17', 'Activo'),
    ('Andrea', 'Lopez', '001-111111-0011K', 'andrea.lopez@empresa.com', '8888-1011', 'Administrador', 'Administracion', 39000.00, '2021-10-09', 'Activo'),
    ('Luis', 'Mendoza', '001-121212-0012L', 'luis.mendoza@empresa.com', '8888-1012', 'Analista', 'Recursos Humanos', 25500.00, '2024-02-11', 'Activo'),
    ('Gabriela', 'Rojas', '001-131313-0013M', 'gabriela.rojas@empresa.com', '8888-1013', 'Programador', 'Tecnologia', 35000.00, '2023-05-30', 'Activo'),
    ('Manuel', 'Perez', '001-141414-0014N', 'manuel.perez@empresa.com', '8888-1014', 'Supervisor', 'Ventas', 30500.00, '2022-08-22', 'Inactivo'),
    ('Valeria', 'Flores', '001-151515-0015O', 'valeria.flores@empresa.com', '8888-1015', 'Analista', 'Recursos Humanos', 27000.00, '2024-06-03', 'Activo');

SELECT * FROM empleado;

SELECT nombres, apellidos, cargo
FROM empleado;

SELECT * FROM empleado
WHERE departamento = 'Tecnologia';

SELECT * FROM empleado
WHERE salario > 30000;

SELECT * FROM empleado
ORDER BY salario DESC;

SELECT COUNT(*) AS total_empleados
FROM empleado;

SELECT AVG(salario) AS salario_promedio
FROM empleado;

SELECT SUM(salario) AS suma_salarios
FROM empleado;

SELECT * FROM empleado
WHERE estado = 'Activo';

SELECT departamento, COUNT(*) AS cantidad_empleados
FROM empleado
GROUP BY departamento;
