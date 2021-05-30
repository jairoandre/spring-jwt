create sequence user_seq;
create sequence role_seq;

create table role (
    id int8 not null,
    name varchar(20) not null,
    constraint role_pk primary key (id)
);

create table config_user (
    id bigint not null,
    name varchar(30) not null,
    email varchar(100) not null,
    password varchar(120) not null,
    constraint user_pk primary key (id)
);

create table user_role (
    user_id bigint not null,
    role_id int8 not null,
    constraint user_role_pk primary key (user_id, role_id)
);

insert into role (id, name) values (nextval('role_seq'), 'ROLE_ADMIN');
insert into role (id, name) values (nextval('role_seq'), 'ROLE_USER');
insert into role (id, name) values (nextval('role_seq'), 'ROLE_CONFIG');
