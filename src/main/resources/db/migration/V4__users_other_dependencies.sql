CREATE TABLE user_cart
(
    id        VARCHAR(36) NOT NULL
        PRIMARY KEY,
    date_time TIMESTAMP   NOT NULL,
    user_id   VARCHAR(36) NOT NULL,
    item_id   VARCHAR(36) NOT NULL,
    count     INT         NOT NULL,
    CONSTRAINT user_cart_user_id_fk
        FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE user_favourite
(
    user_id VARCHAR(36) NOT NULL
        PRIMARY KEY,
    item_id VARCHAR(36) NOT NULL,
    CONSTRAINT user_favourite_user_id_fk
        FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE user_comment
(
    id        VARCHAR(36)  NOT NULL
        PRIMARY KEY,
    date_time TIMESTAMP    NOT NULL,
    user_id   VARCHAR(36)  NOT NULL,
    item_id   VARCHAR(36)  NOT NULL,
    comment   VARCHAR(500) NOT NULL,
    CONSTRAINT user_comment_user_id_fk
        FOREIGN KEY (user_id) REFERENCES user (id)
);