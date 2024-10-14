Create Database BDTransporte;
use BDTransporte;

create table rol(
idrol int auto_increment primary key,
descripcion varchar(100)
);

create table usuario(
idusuario bigint auto_increment primary key,
nombres varchar(100),
apellidos varchar(100),
usuario varchar(50),
clave varchar(50),
idrol int,
constraint fk_usuario_rol foreign key (idrol) references rol(idrol)
);

create table tb_bus(
	id_bus INT AUTO_INCREMENT PRIMARY KEY not null,
    modelo VARCHAR(50),
	marca VARCHAR(50),
    anio INT,
    capacidad INT,
    placa VARCHAR(20)
);

CREATE TABLE tb_revision (
    revision_id INT PRIMARY KEY AUTO_INCREMENT not null,
    id_bus INT,
    fecha_revision DATE,
    tipo_revision VARCHAR(50),
    resultado VARCHAR(50),
    observaciones VARCHAR(50),
    FOREIGN KEY (id_bus) REFERENCES tb_bus(id_bus)
);


create TABLE personal (
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
  estado varchar(30),
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

insert into rol (descripcion) values ('Administrador'),('Cliente');

insert into usuario (nombres,apellidos,usuario,clave,idrol)
values('Paco','Gonzales','admin','12345',1),
('Jorge','Guerra','jorge','jorge123',2);

INSERT INTO tb_bus (modelo, marca, anio, capacidad, placa)
VALUES ('Volvo 9700', 'Volvo', 2020, 50, 'ABC-123');

INSERT INTO tb_revision (id_bus, fecha_revision, tipo_revision, resultado, observaciones) VALUES 
(1, '2024-01-15', 'Técnica', 'Aprobado', 'Sin observaciones')
