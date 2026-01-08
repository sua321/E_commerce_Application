CREATE TABLE user_address
(
    id      VARCHAR(36)  NOT NULL
        PRIMARY KEY,
    country VARCHAR(255) NULL,
    city    VARCHAR(255) NULL,
    street  VARCHAR(255) NULL,
    number  VARCHAR(255) NULL,
    CONSTRAINT user_address_user_id_fk
        FOREIGN KEY (id) REFERENCES user (id)
);
CREATE TABLE user_phone_number
(
    id           VARCHAR(36)  NOT NULL
        PRIMARY KEY,
    phone_number VARCHAR(255) NULL,
    CONSTRAINT user_phone_number_user_id_fk
        FOREIGN KEY (id) REFERENCES user (id)
);
CREATE TABLE user_credentials
(
    id       VARCHAR(36)  NOT NULL
        PRIMARY KEY,
    email    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    CONSTRAINT user_credentials_user_id_fk
        FOREIGN KEY (id) REFERENCES user (id)
);