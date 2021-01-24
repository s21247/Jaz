
-- dzial
CREATE TABLE section(
    id INT PRIMARY KEY NOT NULL,
    title TEXT NOT NULL
);
-- kategorie
CREATE TABLE category(
    id INT PRIMARY KEY NOT NULL,
    title TEXT NOT NULL,
    section_id INT NOT NULL,
    CONSTRAINT section_fk FOREIGN KEY (section_id) REFERENCES section (id)
);
-- aukcje
CREATE TABLE auction(
    id INT PRIMARY KEY NOT NULL,
    title VARCHAR NOT NULL,
    description VARCHAR NOT NULL,
    price INT NOT NULL,
    version INT NOT NULL,
    owner_id INT NOT NULL,
    category_id INT NOT NULL,
    CONSTRAINT user_fk FOREIGN KEY (owner_id) REFERENCES "user"(id),
    CONSTRAINT category_fk FOREIGN KEY (category_id) REFERENCES category(id)
);
-- tabela przechowujaca zdjecia
CREATE TABLE photo(
    id INT PRIMARY KEY NOT NULL,
    link VARCHAR NOT NULL,
    "order" INT NOT NULL ,
    auction_id INT NOT NULL,
    CONSTRAINT auction_fk FOREIGN KEY (auction_id) REFERENCES auction(id)
);

CREATE TABLE parameter(
    id INT PRIMARY KEY NOT NULL,
    key VARCHAR NOT NULL
);

CREATE TABLE auction_parameter(
    value VARCHAR NOT NULL,
    parameter_id INT NOT NULL,
    auction_id INT NOT NULL,
    CONSTRAINT parameter_fk FOREIGN KEY (parameter_id) REFERENCES parameter(id),
    CONSTRAINT auction_fk FOREIGN KEY (auction_id) REFERENCES auction(id)
);



