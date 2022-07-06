CREATE DATABASE IF NOT EXISTS socMed_db;

CREATE USER socMed_user@localhost IDENTIFIED BY 'codeup-1';

GRANT ALL ON socMed_db.* TO socMed_user@localhost;

USE socMed_db;