
CREATE DATABASE IF NOT EXISTS notetakingappdb;

USE notetakingappdb;

CREATE TABLE Notes (
    idNote INT AUTO_INCREMENT PRIMARY KEY,
    titre VARCHAR(200),
    description VARCHAR(200)
);
