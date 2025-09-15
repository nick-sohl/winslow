-- Set the correct schema
SET search_path TO winslow;

-- Addresses
INSERT INTO addresses (street, zipcode, city)
VALUES ('Schulstrasse 1', '8001', 'Zürich'),
       ('Universitätstrasse 21', '8006', 'Zürich'),
       ('Technikumstrasse 9', '8400', 'Winterthur');

-- Schools
INSERT INTO schools (name, address_id)
VALUES ('ETH Zürich', 1),
       ('Universität Zürich', 2),
       ('ZHAW Winterthur', 3);

-- Departments  
INSERT INTO departments (name)
VALUES ('Computer Science'),
       ('Mathematics'),
       ('Physics'),
       ('Engineering');

-- Classrooms
INSERT INTO classrooms (room_no, capacity, school_id)
VALUES (101, 30, 1),
       (102, 25, 1),
       (201, 40, 2),
       (202, 35, 2),
       (301, 28, 3);

-- Courses
INSERT INTO courses (title, department_id)
VALUES ('Introduction to Programming', 1),
       ('Advanced Algorithms', 1),
       ('Linear Algebra', 2),
       ('Quantum Physics', 3),
       ('Software Engineering', 1);

-- Time slots
INSERT INTO time_slots (day, starts_time, end_time)
VALUES ('Monday', '08:15:00+02', '10:00:00+02'),
       ('Monday', '10:15:00+02', '12:00:00+02'),
       ('Tuesday', '13:15:00+02', '15:00:00+02'),
       ('Wednesday', '08:15:00+02', '10:00:00+02'),
       ('Thursday', '15:15:00+02', '17:00:00+02');

-- Instructors (passwords should be hashed in a real application)
INSERT INTO instructors (firstname, lastname, email, password, department_id)
VALUES ('John', 'Smith', 'john.smith@eth.ch', '$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewdBPj2NMWVg3ILm', 1),
       ('Maria', 'Garcia', 'maria.garcia@uzh.ch', '$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewdBPj2NMWVg3ILm', 2),
       ('Thomas', 'Mueller', 'thomas.mueller@zhaw.ch', '$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewdBPj2NMWVg3ILm',
        3);

-- Students (passwords should be hashed in a real application)
INSERT INTO students (firstname, lastname, email, password, department_id)
VALUES ('Anna', 'Weber', 'anna.weber@student.eth.ch', '$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewdBPj2NMWVg3ILm', 1),
       ('Michael', 'Schmidt', 'michael.schmidt@student.uzh.ch','$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewdBPj2NMWVg3ILm', 2),
       ('Laura', 'Fischer', 'laura.fischer@student.zhaw.ch','$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewdBPj2NMWVg3ILm', 1),
       ('David', 'Wagner', 'david.wagner@student.eth.ch','$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewdBPj2NMWVg3ILm', 1),
       ('Sophie', 'Meyer', 'sophie.meyer@student.uzh.ch','$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewdBPj2NMWVg3ILm', 2),
       ('Lucas', 'Berger', 'lucas.berger@student.zhaw.ch','$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewdBPj2NMWVg3ILm', 3),
       ('Emma', 'Schneider', 'emma.schneider@student.eth.ch','$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewdBPj2NMWVg3ILm', 4),
       ('Noah', 'Keller', 'noah.keller@student.uzh.ch', '$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewdBPj2NMWVg3ILm',1),
       ('Lisa', 'Huber', 'lisa.huber@student.zhaw.ch', '$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewdBPj2NMWVg3ILm',2);

-- Sections
INSERT INTO sections (semester, year, room_id, course_id)
VALUES ('Winter', 2023, 1, 1),
       ('Winter', 2023, 2, 2),
       ('Summer', 2024, 3, 3),
       ('Summer', 2024, 4, 4);

-- Enrollments
INSERT INTO enrollments (section_id, student_id, grade)
VALUES (1, 1, 5),
       (1, 2, 6),
       (2, 1, 5),
       (3, 3, 4);

-- Section-Instructor assignments
INSERT INTO section_instructor (section_id, instructor_id)
VALUES (1, 1),
       (2, 1),
       (3, 2),
       (4, 3);

-- Section-Timeslot assignments
INSERT INTO section_timeslot (section_id, time_slot_id)
VALUES (1, 1),
       (1, 2),
       (2, 3),
       (3, 4),
       (4, 5);