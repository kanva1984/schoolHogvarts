ALTER TABLE student
    ADD CONSTRAINT age_constraint CHECK (age > 16);

ALTER TABLE student
    ADD CONSTRAINT name_constraint UNIQUE (name);

ALTER TABLE faculty
    ADD CONSTRAINT faculty_constraint UNIQUE (name, color);

ALTER TABLE student
    ALTER COLUMN name set not null;

ALTER TABLE student
    ALTER COLUMN age set DEFAULT 20;