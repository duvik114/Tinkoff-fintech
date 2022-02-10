CREATE TABLE courses
(
    id INT PRIMARY KEY,
    name VARCHAR(64) NOT NULL,
    description VARCHAR(256)
);

ALTER TABLE students
    ADD FOREIGN KEY (course_id)
        REFERENCES courses(id)
