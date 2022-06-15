insert into cliente(id, nombre, tipo_cliente) values(1,'Cliente 1','PREFERENCIAL');
insert into cliente(id, nombre, tipo_cliente) values(2,'Cliente 2','COMUN');
insert into producto(id, nombre, aplica_iva, valor) values(1,'Producto 1',1, 25000);
insert into producto(id, nombre, aplica_iva, valor) values(2,'Producto 2',0, 10000);

insert into factura(id_cliente, valor_total, estado ) values(1, 25000, 'ACTIVA');
insert into factura(id_cliente, valor_total, estado ) values(2, 3000, 'ANULADA');
insert into producto_facturar( id_factura, id_producto, cantidad ) values(1, 1, 2);

insert into articulo(id, tipo_flor, cantidad_disponible, valor_unidad, fecha_creacion) values(1, 'hortencia', 6, 1000, '2022-06-13');
insert into articulo(id, tipo_flor, cantidad_disponible, valor_unidad, fecha_creacion) values(2, 'rosas', 20, 1000, '2022-06-13');