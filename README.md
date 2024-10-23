DROP DATABASE IF EXISTS BDTransporte;
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
nombre_des VARCHAR(100),
imagen varchar(255)
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
id_bus INT NOT NULL,
id_destino INT NOT NULL,
fech_sal DATE NOT NULL,
fech_lle DATE NOT NULL,
incidencias VARCHAR(40),
precio DOUBLE NOT NULL,
FOREIGN KEY (id_bus) REFERENCES tb_bus(id_bus),
FOREIGN KEY (id_destino) REFERENCES tb_destino(id_destino)
);

insert into rol (descripcion) values ('Administrador'),('Cliente');

insert into usuario (nombres,apellidos,usuario,clave,idrol)
values('Paco','Gonzales','admin','12345',1),
('Jorge','Guerra','jorge','jorge123',2);

insert into tb_bus (modelo, marca, anio, capacidad, placa) values
('Mercedes-Benz Tourismo','Mercedes-Benz',2019,55,'DEF-456'),
('Scania Irizar', 'Scania', 2021, 60, 'GHI-789'),
('MAN Lion’s Coach', 'MAN', 2018, 50, 'JKL-012'),
('Setra TopClass', 'Setra', 2022, 52, 'MNO-345');

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

INSERT INTO tb_revision (id_bus, fecha_revision, tipo_revision, resultado, observaciones) VALUES
(1, '2024-01-15', 'Técnica', 'Aprobado', 'Sin observaciones'),
(2,'2024-02-10','Mecánica','Aprobado','Requiere cambio de aceite'),
(3,'2024-03-05','Eléctrica','Aprobado','Revisión de luces'),
(4,'2024-04-12','Técnica','Rechazado','Frenos desgastados'),
(2,'2024-05-20','Mecánica','Aprobado','Sin observaciones');

insert into
tb_cliente (nombre_cli, dni)
values
('Carlos Perez', '12345678'),
('Ana Gomez', '87654321'),
('Luis Martinez', '11223344'),
('Maria Rodriguez', '44332211'),
('Jose Fernandez', '55667788');

insert into
tb_pasaje (id_cli, id_destino, id_bus, precio, estado)
values
(1, 1, 1, 150.00, 'Reservado'),
(2, 2, 2, 200.00, 'Pagado'),
(3, 3, 2, 180.00, 'Cancelado'),
(4, 4, 3, 220.00, 'Reservado'),
(5, 5, 4, 250.00, 'Pagado');

insert into tb_viaje (id_bus,id_destino,fech_sal,fech_lle,incidencias,precio)values
(1,1,'2024-06-01','2024-06-02','Ninguna',150.00),
(2,2,'2024-06-05','2024-06-06','Retraso por tráfico',200.00),
(3,3,'2024-06-10','2024-06-11','Parada no programada',180.00),
(4,4,'2024-06-15','2024-06-16','Problema mecánico',220.00),
(1,5,'2024-06-20','2024-06-21','Ninguna',250.00),
(2,6,'2024-06-25','2024-06-26','Cambio de ruta',230.00),
(3,7,'2024-06-30','2024-07-01','Ninguna',210.00);

insert into personal (nombre,apellido,dni,telefono,email,direccion,idrol)values
('Juan','Perez','98765432','123-456-7890','juan.perez@example.com','123 Main St',1),
('Laura','Martinez','87654321','234-567-8901','laura.martinez@example.com','456 Elm St',2),
('Carlos','Lopez','76543210','345-678-9012','carlos.lopez@example.com','789 Oak St',1),
('Ana','Gonzalez','65432109','456-789-0123','ana.gonzalez@example.com','101 Pine St',2),
('Luis','Ramirez','54321098','567-890-1234','luis.ramirez@example.com','202 Maple St',1);
