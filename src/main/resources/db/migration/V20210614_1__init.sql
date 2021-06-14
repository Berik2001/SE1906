drop table if exists users;
create table users(
    id serial,
    name varchar(255)
);

drop table if exists chat;
create table chat(
    id serial,
    name varchar(255)
);

drop table if exists message;
create table message(
    id serial,
    user_id bigint,
    chat_id bigint,
    text varchar(255)
);
drop table if exists auth;
create table auth(
                     id serial,
                     login varchar(32),
                     password varchar(32),
                     user_id bigint,
                     token varchar(255)
);
