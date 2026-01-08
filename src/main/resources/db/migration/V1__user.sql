
CREATE TABLE user
(
    id        VARCHAR(36)  NOT NULL
        PRIMARY KEY,
    user_name VARCHAR(255) NOT NULL,
    dob DATE NOT NULL,
    full_name VARCHAR(50) NOT NULL,
    user_type VARCHAR(255) NOT NULL
);