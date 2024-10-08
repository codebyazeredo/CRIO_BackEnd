CREATE TABLE user {
    id CHAR(36) PRIMARY KEY UUID(),
    name VARCHAR(250) UNIQUE NOT NULL,
    email VARCHAR(250) UNIQUE NOT NULL,
    password VARCHAR(250) NOT NULL,
    type INT NOT NULL,
    created_at DATETIME(),
    updated_at DATETIME()
}