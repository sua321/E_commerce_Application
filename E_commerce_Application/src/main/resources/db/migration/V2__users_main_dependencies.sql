
CREATE TABLE admin
(
    user_id   VARCHAR(36)  NOT NULL
        PRIMARY KEY,
    adminPass VARCHAR(255) NOT NULL,
    CONSTRAINT admin_user_id_fk
        FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE customer
(
    user_id VARCHAR(36) NOT NULL
        PRIMARY KEY,
    CONSTRAINT customer_user_id_fk
        FOREIGN KEY (user_id) REFERENCES user (id)
);
CREATE TABLE vendor
(
    user_id    VARCHAR(36)  NOT NULL
        PRIMARY KEY,
    vendorPass VARCHAR(255) NOT NULL,
    CONSTRAINT vendor_user_id_fk
        FOREIGN KEY (user_id) REFERENCES user (id)
);
