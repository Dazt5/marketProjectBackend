/********** CHANGELOG ***********

-- V2: agregadas REFERENCIAL ACTIONS a las tablas con llaves foraneas, agregados atributos de prueba, agregadas SELECT QUERIES para cada tabla.
-- V3: agregada la codificación UTF8 al crear la base de datos.
-- V4: Agregada consulta SQL para obtener la lista de productos obteniendo el nombre del proveedor y no su NIT.
-- V5: Agregato autoincrement para la PK ventas y detalleVentas
***********/


DROP DATABASE IF EXISTS tienda_generica; /*Elimina la bdd si existe asi evitar errores*/

CREATE DATABASE tienda_generica CHARACTER SET utf8 collate utf8_general_ci; /*Vuelve a crear la base de datos*/

USE tienda_generica; 

DROP TABLE IF EXISTS clientes,usuarios,proveedores,productos,ventas, detalle_ventas; /*Elimina las tablas si existen para evitar errores*/

/* Creación de las tablas. */

CREATE TABLE clientes(
	cedula_cliente BIGINT PRIMARY KEY,
    direccion_cliente VARCHAR(255),
    email_cliente VARCHAR(255),
	nombre_cliente VARCHAR(255),
	telefono_cliente VARCHAR(255)
);

CREATE TABLE usuarios(
	cedula_usuario BIGINT PRIMARY KEY,
    email_usuario VARCHAR(255),
    nombre_usuario VARCHAR(255),
    password VARCHAR(255),
	usuario VARCHAR(255)
);
    
CREATE TABLE proveedores(
	nitproveedor BIGINT PRIMARY KEY,
    ciudad_proveedor VARCHAR(255),
    direccion_proveedor VARCHAR(255),
    nombre_proveedor VARCHAR(255),
    telefono_proveedor VARCHAR(255)
);

CREATE TABLE productos(
	codigo_producto BIGINT PRIMARY KEY,
    ivacompra DOUBLE NOT NULL,
    nitproveedor BIGINT,
    nombre_producto VARCHAR(255),
    precio_compra DOUBLE NOT NULL,
    precio_venta DOUBLE  NOT NULL,
	CONSTRAINT fk_proveedor FOREIGN KEY (nitproveedor) REFERENCES proveedores(nitproveedor)
	ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE ventas(
	codigo_venta BIGINT PRIMARY KEY AUTO_INCREMENT,
    cedula_cliente BIGINT,
    cedula_usuario BIGINT,
    ivaventa DOUBLE NOT NULL,
    total_venta DOUBLE NOT NULL,
    valor_venta DOUBLE NOT NULL,
    CONSTRAINT fk_cliente FOREIGN KEY (cedula_cliente) REFERENCES clientes(cedula_cliente)
	ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_usuario FOREIGN KEY (cedula_usuario) REFERENCES usuarios(cedula_usuario)
	ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE detalle_ventas(
	codigo_detalle_venta BIGINT PRIMARY KEY AUTO_INCREMENT,
    cantidad_producto INT NOT NULL,
    codigo_producto BIGINT,
    codigo_venta BIGINT,
	valor_total DOUBLE NOT NULL,
    valor_venta DOUBLE NOT NULL,
    valoriva DOUBLE NOT NULL,
    CONSTRAINT fk_producto FOREIGN KEY (codigo_producto) REFERENCES productos(codigo_producto) 
    ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_venta FOREIGN KEY (codigo_venta) REFERENCES ventas(codigo_venta)
	ON DELETE CASCADE ON UPDATE CASCADE
);

SHOW TABLES;

/************** Datos de prueba, descomentarlo y ejecutar estos query para insertar datos de prueba *******************/

/*

INSERT INTO usuarios(cedula_usuario, email_usuario, nombre_usuario, password, usuario)
VALUES(123456789, "usuario@usuario.com", "administrador","password","admin");

INSERT INTO clientes(cedula_cliente, direccion_cliente, email_cliente, nombre_cliente, telefono_cliente)
VALUES (112233445,"San antonio","david.alvarez@valid.com","David Alvarez","3026241411");

INSERT INTO proveedores(nitproveedor, ciudad_proveedor, direccion_proveedor, nombre_proveedor, telefono_proveedor)
VALUES(987654321,"Bogotá","Calle 150", "VALID S.A", "3025555555");

INSERT INTO productos(codigo_producto, ivacompra, nitproveedor, nombre_producto, precio_compra,precio_venta)
VALUES(5,0.13,987654321,"Coca Cola",2300,2500);

INSERT INTO ventas(codigo_venta, cedula_cliente, cedula_usuario, ivaventa, total_venta, valor_venta)
VALUES(99,1235241711,123456789,500,4000,3500);

INSERT INTO detalle_ventas(codigo_detalle_venta,cantidad_producto,codigo_producto,codigo_venta, valor_total,valor_venta,valoriva)
VALUES(1,1,5,99,3500,4000,500);
*/

/*******  SELECT QUERYS ********/
/* GET PRODUCT QUERY REPLACING NITPROVEEDOR_FK TO NOMBRE_PROVEEDOR */
SELECT codigo_producto,ivacompra,proveedores.nombre_proveedor,nombre_producto,precio_compra,precio_venta FROM productos INNER JOIN proveedores ON productos.nitproveedor = proveedores.nitproveedor;

/*GET VENTA RENPLACING CEDULA_CLIENTE Y CEDULA_USUARIO*/
SELECT codigo_venta, clientes.nombre_cliente, usuarios.nombre_usuario,valor_venta,ivaventa,total_venta FROM ventas INNER JOIN clientes ON ventas.cedula_cliente = clientes.cedula_cliente INNER JOIN usuarios ON ventas.cedula_usuario = usuarios.cedula_usuario;

/*Detalle de ventas por cliente*/
SELECT codigo_venta, clientes.nombre_cliente, usuarios.nombre_usuario,valor_venta,ivaventa,total_venta FROM ventas
INNER JOIN clientes ON ventas.cedula_cliente = clientes.cedula_cliente INNER JOIN usuarios ON ventas.cedula_usuario = usuarios.cedula_usuario WHERE ventas.cedula_cliente="29523249"; 

CREATE TABLE ventas(
	codigo_venta BIGINT PRIMARY KEY AUTO_INCREMENT,
    cedula_cliente BIGINT,
    cedula_usuario BIGINT,
    ivaventa DOUBLE NOT NULL,
    total_venta DOUBLE NOT NULL,
    valor_venta DOUBLE NOT NULL,
    CONSTRAINT fk_cliente FOREIGN KEY (cedula_cliente) REFERENCES clientes(cedula_cliente)
	ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_usuario FOREIGN KEY (cedula_usuario) REFERENCES usuarios(cedula_usuario)
	ON DELETE CASCADE ON UPDATE CASCADE
);

/* GET SELLINGDETAILS REPLACING CODIGO_VENTA AND CÓDIGO PRODUCTO*/
SELECT codigo_detalle_venta,codigo_venta,productos.nombre_producto,cantidad_producto,valor_venta,valorIva,valor_total FROM detalle_ventas INNER JOIN productos ON detalle_ventas.codigo_producto = productos.codigo_producto;

SELECT * FROM usuarios;
SELECT * FROM clientes;
SELECT * FROM proveedores;
SELECT * FROM productos;
SELECT * FROM ventas;
SELECT * FROM detalle_ventas;

DELETE FROM ventas WHERE codigo_venta=1;
ALTER TABLE ventas AUTO_INCREMENT = 0;