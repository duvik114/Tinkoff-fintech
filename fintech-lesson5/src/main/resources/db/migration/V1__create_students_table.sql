CREATE TABLE students
(
    id   UUID PRIMARY KEY,
    name VARCHAR(64) NOT NULL,
    age  INT         NOT NULL,
    course_id INT
);

CREATE INDEX idx_age_course
ON students (age, course_id);

CREATE INDEX idx_course_id
    ON students (course_id);
