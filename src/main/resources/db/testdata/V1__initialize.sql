drop table if exists users;
create table users (
    user_id               bigserial,
    username                 VARCHAR(30) not null UNIQUE,
    password              VARCHAR(80) not null,
    first_name            VARCHAR(50),
    last_name             VARCHAR(50),
    email                 VARCHAR(100),
    phone                 VARCHAR(20),
    PRIMARY KEY (user_id )
);
--
-- drop table if exists roles;
-- create table roles (
--     id                    serial,
--     name                  VARCHAR(50) not null,
--     primary key (id)
-- );
--
-- drop table if exists users_roles;
-- create table users_roles (
--     user_id               INT NOT NULL,
--     role_id               INT NOT NULL,
--     primary key (user_id, role_id),
--     FOREIGN KEY (user_id)
--         REFERENCES users (id),
--     FOREIGN KEY (role_id)
--         REFERENCES roles (id)
-- );
--
-- insert into roles (name)
-- values
-- ('ROLE_USER'), ('ROLE_ADMIN');

insert into users (username, password, first_name, last_name, email, phone)
values
('admin','000','admin','admin','admin@gmail.com', '+12348788567890'),
('kazakov','000','Anrey','Kazakov','kazakov@mail.ru', '+1234666567890'),
('potapov','000','Leonid','Potapov','potapov@mail.ru', '+1255534567890');


-- insert into users_roles (user_id, role_id)
-- values
-- (1, 1),
-- (1, 2),
-- (2, 1),
-- (3, 1);
