create table users (
    id serial primary key,
    email TEXT unique,
    password TEXT
)