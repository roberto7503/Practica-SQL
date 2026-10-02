--Practica Semana 7

-- Crear nuevamente la base

CREATE DATABASE biblioteca_fx;


-- Eliminar la tabla si ya existe

DROP TABLE IF EXISTS empleado;


--Creamos la tabla de empleado

CREATE TABLE empleado (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombres VARCHAR(50) NOT NULL,
    apellidos VARCHAR(50) NOT NULL,
    cedula VARCHAR(20) NOT NULL UNIQUE,
    correo VARCHAR(100) NOT NULL UNIQUE,
    telefono VARCHAR(20) NOT NULL UNIQUE,
    cargo VARCHAR(50) NOT NULL,
    departamento VARCHAR(50) NOT NULL,
    salario NUMERIC(10, 2) NOT NULL,
    fecha_contratacion DATE NOT NULL,
    estado VARCHAR(10) NOT NULL
);


-- Insertar los empleados

INSERT
    INTO empleado (
        nombres,
        apellidos,
        cedula,
        correo,
        telefono,
        cargo,
        departamento,
        salario,
        fecha_contratacion,
        estado
    )
    VALUES
        ('Roberto', 'Macias', '001-010101-0001A', 'roberto.macias@empresa.com', '8888-1001', 'Contador', 'Contabilidad', 18500.00, '2024-01-15', 'Activo'),
        ('Carlos', 'Rodriguez', '001-020202-0002B', 'carlos.martinez@empresa.com', '8888-1002', 'Programador', 'Tecnologia', 25000.00, '2023-06-10', 'Activo'),
        ('Reynaldo', 'Molina', '001-030303-0003C', 'maria.gonzalez@empresa.com', '8888-1003', 'Analista', 'Recursos Humanos', 21000.00, '2022-09-01', 'Activo'),
        ('Roger', 'Sequeira', '001-040404-0004D', 'jose.ramirez@empresa.com', '8888-1004', 'Vendedor', 'Ventas', 16000.00, '2021-03-20', 'Inactivo'),
        ('Sofia', 'Hernandez', '001-050505-0005E', 'sofia.hernandez@empresa.com', '8888-1005', 'Supervisora', 'Ventas', 23000.00, '2024-04-08', 'Activo'),
        ('Erving', 'Miranda', '001-060606-0006F', 'pedro.sanchez@empresa.com', '8888-1006', 'Administrador', 'Administracion', 50000.00, '2020-07-12', 'Activo'),
        ('Lucia', 'Torres', '001-070707-0007G', 'lucia.torres@empresa.com', '8888-1007', 'Secretaria', 'Administracion', 14500.00, '2023-02-14', 'Activo'),
        ('Freddy', 'Lopez', '001-080808-0008H', 'miguel.castillo@empresa.com', '8888-1008', 'Tecnico', 'Tecnologia', 40500.00, '2022-11-05', 'Inactivo'),
        ('Armando', 'Lopez', '001-090909-0009I', 'daniela.morales@empresa.com', '8888-1009', 'Reclutadora', 'Recursos Humanos', 20500.00, '2024-03-18', 'Activo'),
        ('Fernando', 'Ruiz', '001-101010-0010J', 'fernando.ruiz@empresa.com', '8888-1010', 'Cajero', 'Contabilidad', 15000.00, '2021-08-25', 'Activo'),
        ('Andrea', 'Lopez', '001-111111-0011K', 'andrea.lopez@empresa.com', '8888-1011', 'Ejecutiva', 'Ventas', 17500.00, '2023-10-09', 'Activo'),
        ('Steven', 'Cuadra', '001-121212-0012L', 'luis.mendoza@empresa.com', '8888-1012', 'Bodeguero', 'Bodega', 13500.00, '2020-05-16', 'Inactivo'),
        ('Jose', 'Duran', '001-131313-0013M', 'gabriela.rojas@empresa.com', '8888-1013', 'Disenadora', 'Tecnologia', 24000.00, '2024-06-03', 'Activo'),
        ('Ivan', 'Arguello', '001-141414-0014N', 'manuel.perez@empresa.com', '8888-1014', 'Supervisor', 'Bodega', 19000.00, '2022-01-27', 'Activo'),
        ('Valeria', 'Flores', '001-151515-0015O', 'valeria.flores@empresa.com', '8888-1015', 'Auditora', 'Contabilidad', 26500.00, '2021-12-01', 'Inactivo');


--Luego, aqui tenemos las consultas


-- Consulta 1: mostrar todos los empleados

SELECT * FROM empleado;


-- Consulta 2: mostrar nombres, apellidos y cargo

SELECT nombres, apellidos, cargo
FROM empleado;


-- Consulta 3: mostrar empleados del departamento de Ventas

SELECT * FROM empleado
WHERE departamento = 'Ventas';


-- Consulta 4: mostrar empleados con salario mayor a 20000

SELECT * FROM empleado
WHERE salario > 20000;


-- Consulta 5: ordenar empleados por salario de mayor a menor

SELECT * FROM empleado
ORDER BY salario DESC;


-- Consulta 6: contar la cantidad total de empleados

SELECT COUNT(*) AS total_empleados
FROM empleado;


-- Consulta 7: obtener el salario promedio

SELECT AVG(salario) AS salario_promedio
FROM empleado;


-- Consulta 8: obtener la suma de todos los salarios

SELECT SUM(salario) AS total_salarios
FROM empleado;


-- Consulta 9: mostrar solamente empleados activos

SELECT * FROM empleado
WHERE estado = 'Activo';


-- Consulta 10: contar empleados por departamento

SELECT departamento, COUNT(*) AS cantidad_empleados
FROM empleado
GROUP BY departamento;


--Consulta extra: para ver los id
SELECT id, nombres, apellidos
FROM empleado;
