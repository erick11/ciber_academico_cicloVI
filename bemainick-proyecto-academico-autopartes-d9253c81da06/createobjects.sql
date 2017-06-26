DROP DATABASE IF EXISTS AUTOPARTESDB;
CREATE DATABASE AUTOPARTESDB;
USE AUTOPARTESDB;

CREATE TABLE `marca` (
  `marca_id` INTEGER PRIMARY KEY AUTO_INCREMENT,
  `nombre_marca` VARCHAR(255) NOT NULL
);

CREATE TABLE `modelo` (
  `modelo_id` INTEGER PRIMARY KEY AUTO_INCREMENT,
  `nombre_modelo` VARCHAR(255) NOT NULL,
  `marca_id` INTEGER NOT NULL
);

CREATE INDEX `idx_modelo__marca_id` ON `modelo` (`marca_id`);

ALTER TABLE `modelo` ADD CONSTRAINT `fk_modelo__marca_id` FOREIGN KEY (`marca_id`) REFERENCES `marca` (`marca_id`);

CREATE TABLE `autoparte` (
  `autoparte_id` INTEGER PRIMARY KEY AUTO_INCREMENT,
  `nombre_autoparte` VARCHAR(100) NOT NULL,
  `precio_autoparte` DECIMAL(12, 2) NOT NULL,
  `stock_autoparte` DECIMAL(12, 2) NOT NULL,
  `modelo_id` INTEGER NOT NULL
);

CREATE INDEX `idx_autoparte__modelo_id` ON `autoparte` (`modelo_id`);

ALTER TABLE `autoparte` ADD CONSTRAINT `fk_autoparte__modelo_id` FOREIGN KEY (`modelo_id`) REFERENCES `modelo` (`modelo_id`);

CREATE TABLE `MOVIMIENTO` (
  `movimiento_id` INTEGER PRIMARY KEY AUTO_INCREMENT,
  `autoparte_id` INTEGER NOT NULL,
  `tipo_movimiento` INTEGER NOT NULL,
  `cantidad_movimiento` DECIMAL(12, 2) NOT NULL,
  `fecha_movimiento` DATE NOT NULL
);

CREATE INDEX `idx_movimiento__autoparte_id` ON `MOVIMIENTO` (`autoparte_id`);

ALTER TABLE `MOVIMIENTO` ADD CONSTRAINT `fk_movimiento__autoparte_id` FOREIGN KEY (`autoparte_id`) REFERENCES `autoparte` (`autoparte_id`);

CREATE TABLE `permiso` (
  `permiso_id` INTEGER PRIMARY KEY AUTO_INCREMENT,
  `nombre_permiso` VARCHAR(255) NOT NULL,
  `url_permiso` VARCHAR(255) NOT NULL
);

CREATE TABLE `tipousuario` (
  `tipo_usuario_id` INTEGER PRIMARY KEY AUTO_INCREMENT,
  `nombre_tipo_usuario` VARCHAR(255) NOT NULL
);

CREATE TABLE `permiso_tipousuario` (
  `permiso_id` INTEGER NOT NULL,
  `tipousuario_id` INTEGER NOT NULL,
  PRIMARY KEY (`permiso_id`, `tipousuario_id`)
);

CREATE INDEX `idx_permiso_tipousuario` ON `permiso_tipousuario` (`tipousuario_id`);

ALTER TABLE `permiso_tipousuario` ADD CONSTRAINT `fk_permiso_tipousuario__permiso` FOREIGN KEY (`permiso_id`) REFERENCES `permiso` (`permiso_id`);

ALTER TABLE `permiso_tipousuario` ADD CONSTRAINT `fk_permiso_tipousuario__tipousuario` FOREIGN KEY (`tipousuario_id`) REFERENCES `tipousuario` (`tipo_usuario_id`);

CREATE TABLE `usuario` (
  `usuario_id` INTEGER PRIMARY KEY AUTO_INCREMENT,
  `usuario_nombre` VARCHAR(255) NOT NULL,
  `tipo_usuario_id` INTEGER NOT NULL,
  `usuario_usuario` VARCHAR(255) NOT NULL,
  `contrasena_usuario` VARCHAR(255) NOT NULL
);

CREATE INDEX `idx_usuario__tipo_usuario_id` ON `usuario` (`tipo_usuario_id`);

ALTER TABLE `usuario` ADD CONSTRAINT `fk_usuario__tipo_usuario_id` FOREIGN KEY (`tipo_usuario_id`) REFERENCES `tipousuario` (`tipo_usuario_id`);

CREATE TABLE `cliente` (
  `cliente_id` INTEGER PRIMARY KEY AUTO_INCREMENT,
  `nombre_cliente` VARCHAR(255) NOT NULL,
  `apellido_cliente` VARCHAR(255) NOT NULL,
  `ruc_cliente` VARCHAR(255) UNIQUE,
  `direccion_cliente` VARCHAR(255) NOT NULL,
  `telefono_cliente` VARCHAR(255) NOT NULL,
  `correo_cliente` VARCHAR(255) UNIQUE NOT NULL,
  `direccion_entrega_cliente` VARCHAR(255) NOT NULL,
  `usuario_id` INTEGER NOT NULL
);

CREATE INDEX `idx_cliente__usuario_id` ON `cliente` (`usuario_id`);

ALTER TABLE `cliente` ADD CONSTRAINT `fk_cliente__usuario_id` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`usuario_id`);

CREATE TABLE `compra` (
  `compra_id` INTEGER PRIMARY KEY,
  `numero_comprobante_compra` VARCHAR(255) NOT NULL,
  `total_compra` DECIMAL(12, 2) NOT NULL,
  `subtotal_compra` DECIMAL(12, 2) NOT NULL,
  `igv_compra` DECIMAL(12, 2),
  `estado_compra` INTEGER NOT NULL,
  `direccion_entrega_compra` VARCHAR(255),
  `fecha_compra` DATETIME,
  `fecha_entrega_compra` DATETIME,
  `estado_entrega_compra` INTEGER,
  `usuario_repartidor_id` INTEGER,
  `cliente_id` INTEGER NOT NULL
);

CREATE INDEX `idx_compra__cliente_id` ON `compra` (`cliente_id`);

ALTER TABLE `compra` ADD CONSTRAINT `fk_compra__cliente_id` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`cliente_id`);

CREATE TABLE `detalleclientemodelo` (
  `cliente` INTEGER NOT NULL,
  `modelo` INTEGER NOT NULL,
  PRIMARY KEY (`cliente`, `modelo`)
);

CREATE INDEX `idx_detalleclientemodelo__modelo` ON `detalleclientemodelo` (`modelo`);

ALTER TABLE `detalleclientemodelo` ADD CONSTRAINT `fk_detalleclientemodelo__cliente` FOREIGN KEY (`cliente`) REFERENCES `cliente` (`cliente_id`);

ALTER TABLE `detalleclientemodelo` ADD CONSTRAINT `fk_detalleclientemodelo__modelo` FOREIGN KEY (`modelo`) REFERENCES `modelo` (`modelo_id`);

CREATE TABLE `detallecompra` (
  `autoparte_id` INTEGER NOT NULL,
  `compra_id` INTEGER NOT NULL,
  `subtotal` DECIMAL(12, 2),
  `cantidad` INTEGER NOT NULL,
  PRIMARY KEY (`autoparte_id`, `compra_id`)
);

CREATE INDEX `idx_detallecompra__compra_id` ON `detallecompra` (`compra_id`);

ALTER TABLE `detallecompra` ADD CONSTRAINT `fk_detallecompra__autoparte_id` FOREIGN KEY (`autoparte_id`) REFERENCES `autoparte` (`autoparte_id`);

ALTER TABLE `detallecompra` ADD CONSTRAINT `fk_detallecompra__compra_id` FOREIGN KEY (`compra_id`) REFERENCES `compra` (`compra_id`);

commit;