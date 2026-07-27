CREATE TABLE roles (

id UUID PRIMARY KEY ,
name varchar(20) NOT NULL UNIQUE CHECK( name in('ROLE_CLIENT','ROLE_ADMIN','ROLE_ORGANIZER'))

);

INSERT INTO roles (id, name) VALUES
(gen_random_uuid(), 'ROLE_CLIENT'),
(gen_random_uuid(), 'ROLE_ADMIN'),
(gen_random_uuid(), 'ROLE_ORGANIZER');