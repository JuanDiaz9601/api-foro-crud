create table topicos(
    id bigint not null auto_increment,
    titulo varchar(100),
    mensaje varchar(500),
    nombreCurso varchar(100),
    status tinyint not null default 1,
    fecha datetime not null,

    primary key(id)
);