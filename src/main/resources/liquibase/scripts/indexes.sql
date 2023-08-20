-- liquibase formatted sql

-- changeset tsalikhov:1
CREATE INDEX students_name ON student(name);

-- changeset tsalikhov:2
CREATE INDEX faculties_name_color ON faculty (name, color);
