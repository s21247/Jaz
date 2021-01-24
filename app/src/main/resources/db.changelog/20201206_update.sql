CREATE TABLE "user"(
    id INT NOT NULL PRIMARY KEY,
    username VARCHAR NOT NULL,
    password VARCHAR NOT NULL,
    firstName VARCHAR NOT NULL,
    lastName VARCHAR NOT NULL
);

CREATE TABLE authority(
    id INT NOT NULL,
    authority VARCHAR NOT NULL,
    CONSTRAINT user_fk FOREIGN KEY (id) REFERENCES "user"(id)
);