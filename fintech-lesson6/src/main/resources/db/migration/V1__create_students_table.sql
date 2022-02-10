CREATE TABLE students
(
    id   UUID PRIMARY KEY,
    name VARCHAR(64) NOT NULL,
    age  INT         NOT NULL,
    course_id INT,
    grade INT
);