CREATE TABLE IF NOT EXISTS candidate (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    category VARCHAR(20) NOT NULL,
    part VARCHAR(10),
    team VARCHAR(20),
    vote_count INTEGER NOT NULL DEFAULT 0,
    created_at DATETIME(6),
    updated_at DATETIME(6),
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS user (
    id BIGINT NOT NULL AUTO_INCREMENT,
    login_id VARCHAR(255),
    password VARCHAR(255),
    name VARCHAR(255),
    email VARCHAR(255),
    role TINYINT,
    part TINYINT,
    team TINYINT,
    PRIMARY KEY (id),
    CONSTRAINT UK_email UNIQUE (email),
    CONSTRAINT UK_login_id UNIQUE (login_id)
) ENGINE=InnoDB;