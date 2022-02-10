CREATE TABLE courses
(
    courseId INT PRIMARY KEY,
    courseName VARCHAR(64) NOT NULL,
    courseDescription VARCHAR(256),
    courseRequiredGrade INT
);

ALTER TABLE students
    ADD FOREIGN KEY (course_id)
        REFERENCES courses(courseId)
