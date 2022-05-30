create database if not exists kata_bootstrap;

use kata_bootstrap;

create table if not exists users
(
    id       bigint auto_increment primary key,
    email    varchar(255) null,
    password varchar(255) null,
    name     varchar(255) null,
    age      int          not null,
    role     varchar(255) not null,
    enabled  bit          not null,
    constraint email unique (email)
);

insert into users (email, password, name, age, role, enabled)
values
(
    'admin@mail.box',
    '$2a$12$TOrllWxKsB1synV3tEZYNOhOGmckD7igMph/4KeZ0b3YWzz/3uIru',
    'Admin',
    0,
    'ADMIN',
    true
);
