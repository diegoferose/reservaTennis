create table afiliado (
 id int(11) not null auto_increment,
 identificacion_usuario varchar(100) not null,
 nombre varchar(100) not null,
 categoria varchar(100) not null,
 primary key (id)
);

insert into afiliado(identificacion_usuario,nombre,categoria) values('123','Diego','A');
insert into afiliado(identificacion_usuario,nombre,categoria) values('456','Lina','B');
insert into afiliado(identificacion_usuario,nombre,categoria) values('789','Francy','C');
