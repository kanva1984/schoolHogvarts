-- liquibase formatted sql

-- changeset tsalikhov:1

CREATE TABLE students{
    id      SERIAL
    name    TEXT
    faculty TEXT
    color   TEXT
}

-- changeset tsalikhov:2
CREATE INDEX students_name_index ON students(students);

-- changeset tsalikhov:3
CREATE INDEX users_fc_idx ON users (faculty, color);
