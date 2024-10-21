CREATE DATABASE BDTransporte;
USE BDTransporte;

CREATE TABLE rol (
    idrol INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(100)
);

CREATE TABLE usuario (
    idusuario BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(100),
    apellidos VARCHAR(100),
    usuario VARCHAR(50),
    clave VARCHAR(50),
    idrol INT,
    CONSTRAINT fk_usuario_rol FOREIGN KEY (idrol) REFERENCES rol(idrol)
);

CREATE TABLE tb_bus (
    id_bus INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    modelo VARCHAR(50),
    marca VARCHAR(50),
    anio INT,
    capacidad INT,
    placa VARCHAR(20)
);

CREATE TABLE tb_revision (
    revision_id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    id_bus INT,
    fecha_revision DATE,
    tipo_revision VARCHAR(50),
    resultado VARCHAR(50),
    observaciones VARCHAR(50),
    FOREIGN KEY (id_bus) REFERENCES tb_bus(id_bus)
);

CREATE TABLE personal (
    id_personal INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    dni VARCHAR(20) UNIQUE NOT NULL,
    telefono VARCHAR(20),
    email VARCHAR(100),
    direccion VARCHAR(255),
    idrol INT,
    FOREIGN KEY (idrol) REFERENCES rol(idrol)
);

CREATE TABLE tb_destino (
    id_destino INT AUTO_INCREMENT PRIMARY KEY,
    nombre_des VARCHAR(100)
);

CREATE TABLE tb_cliente (
    id_cli INT AUTO_INCREMENT PRIMARY KEY,
    nombre_cli VARCHAR(100),
    dni VARCHAR(8) UNIQUE
);

CREATE TABLE tb_pasaje (
    id_pasaje INT AUTO_INCREMENT PRIMARY KEY,
    id_cli INT,
    id_destino INT,
    id_bus INT,
    precio DECIMAL(10,2),
    estado VARCHAR(30),
    FOREIGN KEY (id_cli) REFERENCES tb_cliente(id_cli),
    FOREIGN KEY (id_destino) REFERENCES tb_destino(id_destino),
    FOREIGN KEY (id_bus) REFERENCES tb_bus(id_bus)
);

CREATE TABLE tb_viaje (
    id_viaje INT AUTO_INCREMENT PRIMARY KEY,
    id_bus INT,
    id_destino INT,
    fech_sal DATETIME,
    fech_lle DATETIME,
    incidencias VARCHAR(40),
    FOREIGN KEY (id_bus) REFERENCES tb_bus(id_bus),
    FOREIGN KEY (id_destino) REFERENCES tb_destino(id_destino)
);

-- Inserting initial data into the tables
INSERT INTO rol (descripcion) 
VALUES ('Administrador'), ('Cliente');

INSERT INTO usuario (nombres, apellidos, usuario, clave, idrol) 
VALUES ('Paco', 'Gonzales', 'admin', '12345', 1),
       ('Jorge', 'Guerra', 'jorge', 'jorge123', 2);

INSERT INTO tb_bus (modelo, marca, anio, capacidad, placa) 
VALUES ('Volvo 9700', 'Volvo', 2020, 50, 'ABC-123');

<<<<<<< HEAD
INSERT INTO tb_revision (id_bus, fecha_revision, tipo_revision, resultado, observaciones) VALUES 
(1, '2024-01-15', 'Técnica', 'Aprobado', 'Sin observaciones')

INSERT INTO tb_destino (id_destino, nombre_des, imagen) VALUES
(1, 'Huacachina', 'Destino_HuacaChina.jpg'),
(2, 'Cuzco', 'Destino_MachuPicchu.jpg'),
(3, 'Arequipa', 'Destino_Arequipa.jpg'),
(4, 'Huascarán', 'Destino_Huascaran.jpg'),
(5, 'Lago Titicaca', 'Destino_LagoTiticaca.jpg'),
(6, 'Líneas de Nazca', 'Destino_LineasNazca.jpg'),
(7, 'Máncora', 'Destino_Mancora.jpg'),
(8, 'Montaña de Colores', 'Destino_MontanaColores.jpg'),
(9, 'Tambopata', 'Destino_Tambopata.jpg'),
(10, 'Cañón del Colca', 'Destino_CanonDelColca.jpg');

=======
INSERT INTO tb_revision (id_bus, fecha_revision, tipo_revision, resultado, observaciones) 
VALUES (1, '2024-01-15', 'Técnica', 'Aprobado', 'Sin observaciones');
>>>>>>> branch 'main' of https://github.com/AndersonGcH/PROYECTO-TRANSPORTE-LPII.git
