
USE AUTOPARTESDB;

insert into tipousuario (tipo_usuario_id,nombre_tipo_usuario) values (1,'Administrador');
insert into tipousuario (tipo_usuario_id,nombre_tipo_usuario) values (2,'Cliente');
insert into tipousuario (tipo_usuario_id,nombre_tipo_usuario) values (3,'Repartidor');

insert into usuario (usuario_nombre,tipo_usuario_id,usuario_usuario,contrasena_usuario)
	values('DemoAdmin',1,'admin','admin');
insert into usuario (usuario_nombre,tipo_usuario_id,usuario_usuario,contrasena_usuario)
	values('AdminCliente',2,'adcliente','adcliente');
insert into usuario (usuario_nombre,tipo_usuario_id,usuario_usuario,contrasena_usuario)
	values('Cliente01',2,'cliente','cliente');
insert into usuario (usuario_nombre,tipo_usuario_id,usuario_usuario,contrasena_usuario)
	values('DemoRepartidor',3,'repartidor','repartidor');
    
insert into cliente (nombre_cliente,apellido_cliente,ruc_cliente,direccion_cliente,telefono_cliente,
	correo_cliente,direccion_entrega_cliente,usuario_id)
    values ('AdminCliente','AdminCliente','12343322343','La calle','(01)-2311234','asd@adsfc.com',
		'La calle',2);
insert into cliente (nombre_cliente,apellido_cliente,ruc_cliente,direccion_cliente,telefono_cliente,
	correo_cliente,direccion_entrega_cliente,usuario_id)
    values ('Cliente','Cliente','20513180054','La calle','(01)-3242666','otrocorroe@adsfc.com',
		'La calle',3);

insert into permiso (nombre_permiso,url_permiso) values ('Mantenimiento','mantenimiento.xhtml');
insert into permiso (nombre_permiso,url_permiso) values ('Reportes','reportes.xhtml');
insert into permiso (nombre_permiso,url_permiso) values ('Consultas','consultas.xhtml');
insert into permiso (nombre_permiso,url_permiso) values ('Modificar perfil','modificar_perfil.xhtml');
insert into permiso (nombre_permiso,url_permiso) values ('Comprar autopartes','comprar_autopartes.xhtml');
insert into permiso (nombre_permiso,url_permiso) values ('Ver entregas pendientes','ver_entregas.xhtml');

insert into permiso_tipousuario(permiso_id,tipousuario_id)values (1,1);
insert into permiso_tipousuario(permiso_id,tipousuario_id)values (2,1);
insert into permiso_tipousuario(permiso_id,tipousuario_id)values (3,1);
insert into permiso_tipousuario(permiso_id,tipousuario_id)values (6,1);
insert into permiso_tipousuario(permiso_id,tipousuario_id)values (4,2);
insert into permiso_tipousuario(permiso_id,tipousuario_id)values (5,2);
insert into permiso_tipousuario(permiso_id,tipousuario_id)values (6,3);

insert into marca (nombre_marca) values ('Abarth');
insert into marca (nombre_marca) values ('Alfa Romeo');
insert into marca (nombre_marca) values ('Aston Martin');
insert into marca (nombre_marca) values ('Bentley');
insert into marca (nombre_marca) values ('BMW');
insert into marca (nombre_marca) values ('BYD');
insert into marca (nombre_marca) values ('Cadillac');
insert into marca (nombre_marca) values ('Caterham');
insert into marca (nombre_marca) values ('Ferrari');
insert into marca (nombre_marca) values ('Mercedes');
insert into marca (nombre_marca) values ('McLaren');
insert into marca (nombre_marca) values ('Mitsubishi');
insert into marca (nombre_marca) values ('Porsche');
insert into marca (nombre_marca) values ('Rolls-Royce');
insert into marca (nombre_marca) values ('Subaru');
insert into marca (nombre_marca) values ('TATA');
insert into marca (nombre_marca) values ('Volkswagen');
insert into marca (nombre_marca) values ('Volvo');

insert into modelo (nombre_modelo,marca_id) values ('500',1);
insert into modelo (nombre_modelo,marca_id) values ('MiTo',2);
insert into modelo (nombre_modelo,marca_id) values ('Giulietta',2);
insert into modelo (nombre_modelo,marca_id) values ('4C',2);
insert into modelo (nombre_modelo,marca_id) values ('DB9',3);
insert into modelo (nombre_modelo,marca_id) values ('Rapide',3);
insert into modelo (nombre_modelo,marca_id) values ('Vanquish',3);
insert into modelo (nombre_modelo,marca_id) values ('Continental GT',4);
insert into modelo (nombre_modelo,marca_id) values ('Flying Spur',4);
insert into modelo (nombre_modelo,marca_id) values ('Mulsanne',4);
insert into modelo (nombre_modelo,marca_id) values ('X1',5);
insert into modelo (nombre_modelo,marca_id) values ('X3',5);
insert into modelo (nombre_modelo,marca_id) values ('X5',5);
insert into modelo (nombre_modelo,marca_id) values ('E6',6);
insert into modelo (nombre_modelo,marca_id) values ('ATS',7);
insert into modelo (nombre_modelo,marca_id) values ('CTS',7);
insert into modelo (nombre_modelo,marca_id) values ('Escalade',7);
insert into modelo (nombre_modelo,marca_id) values ('Seven',8);
insert into modelo (nombre_modelo,marca_id) values ('458',9);
insert into modelo (nombre_modelo,marca_id) values ('LaFerrari',9);
insert into modelo (nombre_modelo,marca_id) values ('Clase A',10);
insert into modelo (nombre_modelo,marca_id) values ('Clase B',10);
insert into modelo (nombre_modelo,marca_id) values ('Clase C',10);
insert into modelo (nombre_modelo,marca_id) values ('P1',11);
insert into modelo (nombre_modelo,marca_id) values ('650S',11);
insert into modelo (nombre_modelo,marca_id) values ('540C / 570S',11);
insert into modelo (nombre_modelo,marca_id) values ('Montero',12);
insert into modelo (nombre_modelo,marca_id) values ('Outlander',12);
insert into modelo (nombre_modelo,marca_id) values ('Space Star',12);
insert into modelo (nombre_modelo,marca_id) values ('911',13);
insert into modelo (nombre_modelo,marca_id) values ('Cayman',13);
insert into modelo (nombre_modelo,marca_id) values ('Panamera',13);
insert into modelo (nombre_modelo,marca_id) values ('Ghost',14);
insert into modelo (nombre_modelo,marca_id) values ('Phantom',14);
insert into modelo (nombre_modelo,marca_id) values ('Wraith',14);
insert into modelo (nombre_modelo,marca_id) values ('BRZ',15);
insert into modelo (nombre_modelo,marca_id) values ('Forester',15);
insert into modelo (nombre_modelo,marca_id) values ('XV',15);
insert into modelo (nombre_modelo,marca_id) values ('Aria',16);
insert into modelo (nombre_modelo,marca_id) values ('Vista',16);
insert into modelo (nombre_modelo,marca_id) values ('Xenon',16);
insert into modelo (nombre_modelo,marca_id) values ('Beetle',17);
insert into modelo (nombre_modelo,marca_id) values ('Polo',17);
insert into modelo (nombre_modelo,marca_id) values ('Touran',17);
insert into modelo (nombre_modelo,marca_id) values ('S60',18);
insert into modelo (nombre_modelo,marca_id) values ('S60',18);
insert into modelo (nombre_modelo,marca_id) values ('V70',18);
insert into modelo (nombre_modelo,marca_id) values ('XC90',18);

insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('suspensión',300.50,100,1);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('suspensión',320.50,100,2);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('suspensión',250.50,100,3);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('suspensión',500.00,100,4);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('suspensión',400.00,100,5);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('suspensión',200.50,100,6);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('suspensión',600.50,100,7);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('suspensión',150.50,100,8);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('suspensión',235.50,100,9);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('suspensión',643.20,100,10);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('suspensión',450.22,100,11);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('suspensión',240.22,100,12);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('suspensión',123.00,100,13);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('suspensión',762.21,100,14);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('suspensión',123.50,100,15);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('suspensión',600.50,100,16);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('suspensión',120.60,100,17);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('suspensión',100.50,100,18);

insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Caja de cambio',300.50,100,1);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Caja de cambio',320.50,100,2);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Caja de cambio',250.50,100,3);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Caja de cambio',500.00,100,4);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Caja de cambio',400.00,100,5);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Caja de cambio',200.50,100,6);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Caja de cambio',600.50,100,7);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Caja de cambio',150.50,100,8);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Caja de cambio',755.50,100,9);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Caja de cambio',233.20,100,10);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Caja de cambio',120.22,100,11);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Caja de cambio',780.22,100,12);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Caja de cambio',533.00,100,13);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Caja de cambio',562.21,100,14);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Caja de cambio',143.50,100,15);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Caja de cambio',660.50,100,16);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Caja de cambio',160.60,100,17);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Caja de cambio',100.50,100,18);


insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Carrocería',300.50,100,1);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Carrocería',320.50,100,2);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Carrocería',250.50,100,3);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Carrocería',500.00,100,4);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Carrocería',400.00,100,5);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Carrocería',200.50,100,6);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Carrocería',600.50,100,7);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Carrocería',150.50,100,8);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Carrocería',755.50,100,9);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Carrocería',233.20,100,10);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Carrocería',120.22,100,11);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Carrocería',780.22,100,12);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Carrocería',533.00,100,13);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Carrocería',562.21,100,14);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Carrocería',143.50,100,15);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Carrocería',660.50,100,16);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Carrocería',160.60,100,17);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Carrocería',100.50,100,18);

insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Faro',300.50,100,1);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Faro',320.50,100,2);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Faro',250.50,100,3);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Faro',500.00,100,4);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Faro',400.00,100,5);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Faro',200.50,100,6);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Faro',600.50,100,7);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Faro',150.50,100,8);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Faro',755.50,100,9);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Faro',233.20,100,10);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Faro',120.22,100,11);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Faro',780.22,100,12);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Faro',533.00,100,13);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Faro',562.21,100,14);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Faro',143.50,100,15);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Faro',660.50,100,16);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Faro',160.60,100,17);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Faro',100.50,100,18);

insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Motor',300.50,100,1);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Motor',320.50,100,2);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Motor',250.50,100,3);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Motor',500.00,100,4);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Motor',400.00,100,5);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Motor',200.50,100,6);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Motor',600.50,100,7);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Motor',150.50,100,8);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Motor',755.50,100,9);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Motor',233.20,100,10);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Motor',120.22,100,11);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Motor',780.22,100,12);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Motor',533.00,100,13);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Motor',562.21,100,14);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Motor',143.50,100,15);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Motor',660.50,100,16);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Motor',160.60,100,17);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Motor',100.50,100,18);

insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Radiador',300.50,100,1);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Radiador',320.50,100,2);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Radiador',250.50,100,3);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Radiador',500.00,100,4);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Radiador',400.00,100,5);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Radiador',200.50,100,6);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Radiador',600.50,100,7);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Radiador',150.50,100,8);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Radiador',755.50,100,9);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Radiador',233.20,100,10);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Radiador',120.22,100,11);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Radiador',780.22,100,12);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Radiador',533.00,100,13);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Radiador',562.21,100,14);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Radiador',143.50,100,15);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Radiador',660.50,100,16);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Radiador',160.60,100,17);
insert into autoparte (nombre_autoparte,precio_autoparte,stock_autoparte ,modelo_id)
	values ('Radiador',100.50,100,18);
commit;