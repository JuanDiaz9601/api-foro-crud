create table usuarios (
    id bigint not null auto_increment,
    login varchar(100) unique,
    clave varchar(300),

    primary key(id)
);