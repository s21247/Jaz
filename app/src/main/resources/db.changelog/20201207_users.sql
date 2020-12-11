CREATE TABLE users (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    username VARCHAR NOT NULL,
    password VARCHAR NOT NULL,
    firstName VARCHAR NOT NULL,
    lastName VARCHAR NOT NULL

);

CREATE TABLE authorities(
    authorities_id int NOT NULL,
    users_id int NOT NULL,
    authority VARCHAR NOT NULL,
    CONSTRAINT users_fk FOREIGN KEY (users_id) REFERENCES users(id)
)