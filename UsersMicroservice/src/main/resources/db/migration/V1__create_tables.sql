CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL
);


CREATE TABLE notes (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       user_id BIGINT NOT NULL,
                       text VARCHAR(1000) NOT NULL,
                       FOREIGN KEY (user_id) REFERENCES users(id)
);