-- DROP TABLE users;
-- CREATE TABLE users(
-- id BIGSERIAL NOT NULL PRIMARY KEY,
-- username VARCHAR NOT NULL,
-- password VARCHAR NOT NULL
-- );
-- CREATE TABLE authorities(
-- authorities_id BIGSERIAL NOT NULL PRIMARY KEY,
-- authority VARCHAR NOT NULL
-- );
-- CREATE TABLE users_authorities(
-- id int NOt NULL,
-- authorities_id int NOT NULL,
-- --     KEY id_fk (id),
-- --     KEY authorities_fk (authorities_id),
-- CONSTRAINT authorities_fk FOREIGN KEY (authorities_id) REFERENCES authorities (authorities_id),
-- CONSTRAINT id_fk FOREIGN KEY (id) REFERENCES users (id)
-- );