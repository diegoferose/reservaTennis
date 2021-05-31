create table reserva (
 id int(11) not null auto_increment,
 identificacion_usuario varchar(100) not null,
 fecha datetime not null,
 hora_inicio datetime not null,
 hora_fin datetime not null,
 estado varchar(100) not null,
 valor_a_pagar double not null,
 primary key (id)
);
