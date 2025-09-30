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
INSERT INTO courses (title, description, goals, department_id)
VALUES
    ('Object Oriented Programming',
     'A foundational course covering basic programming concepts and practices.',
     'Students will learn fundamental programming concepts, basic syntax, and problem-solving skills.',
     1),
    ('Cyber Security',
     'In-depth study of complex algorithms and their applications.',
     'Master algorithm design, analysis, and optimization techniques for solving complex computational problems.',
     1),
    ('Network Operating Systems',
     'Study of linear equations, matrices, vector spaces, and their applications.',
     'Understand fundamental concepts of linear algebra and their applications in various fields.',
     2),
    ('Databases',
     'Introduction to quantum mechanics and its mathematical foundations.',
     'Comprehend quantum mechanical principles and their implications in modern physics.',
     3);

INSERT INTO time_slots (day, start_time, end_time)
VALUES ('MONDAY', '08:15:00', '10:00:00'),
       ('MONDAY', '10:15:00', '12:00:00'),
       ('TUESDAY', '13:15:00', '15:00:00'),
       ('WEDNESDAY', '08:15:00', '10:00:00'),
       ('THURSDAY', '15:15:00', '17:00:00');

-- Instructors (passwords should be hashed in a real application)
INSERT INTO instructors (firstname, lastname, email, password, department_id)
VALUES ('John', 'Smith', 'john.smith@eth.ch', '89323534', 1),
       ('Maria', 'Garcia', 'maria.garcia@uzh.ch', '89323534', 2),
       ('Thomas', 'Mueller', 'thomas.mueller@zhaw.ch', '89323534',
        3);

-- Students (passwords should be hashed in a real application)
INSERT INTO students (firstname, lastname, email, password, department_id)
VALUES ('Anna', 'Weber', 'anna.weber@student.eth.ch', '89323534', 1),
       ('Michael', 'Schmidt', 'michael.schmidt@student.uzh.ch','89323534', 2),
       ('Laura', 'Fischer', 'laura.fischer@student.zhaw.ch','89323534', 1),
       ('David', 'Wagner', 'david.wagner@student.eth.ch','89323534', 1),
       ('Sophie', 'Meyer', 'sophie.meyer@student.uzh.ch','89323534', 2),
       ('Lucas', 'Berger', 'lucas.berger@student.zhaw.ch','89323534', 3),
       ('Emma', 'Schneider', 'emma.schneider@student.eth.ch','89323534', 4),
       ('Noah', 'Keller', 'noah.keller@student.uzh.ch', '89323534',1),
       ('Lisa', 'Huber', 'lisa.huber@student.zhaw.ch', '89323534',2);

-- Sections
INSERT INTO sections (semester, year, classroom, course_id)
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

INSERT INTO section_timeslot (section_id, time_slot_id)
VALUES (1, 5),
       (1, 3),
       (2, 1),
       (3, 2),
       (4, 4);

-- =====================
-- Java Fundamentals
-- =====================
INSERT INTO modules (name, description, order_index, course_id) VALUES
  ('Introduction to Java', 'Overview of Java language and ecosystem', 1, 1),
  ('Variables and Data Types', 'Understanding primitive types and objects', 2, 1),
  ('Control Flow', 'If statements, loops, and switch', 3, 1),
  ('Methods and Parameters', 'Defining and calling methods', 4, 1),
  ('Object-Oriented Programming', 'Classes, objects, encapsulation', 5, 1),
  ('Inheritance', 'Extending classes and reusing code', 6, 1),
  ('Polymorphism', 'Overloading, overriding, dynamic dispatch', 7, 1),
  ('Abstract Classes & Interfaces', 'Designing contracts for classes', 8, 1),
  ('Collections Framework', 'Lists, Sets, Maps and Iterators', 9, 1),
  ('Exception Handling', 'Try-catch-finally and custom exceptions', 10, 1);

-- =====================
-- Cyber Security
-- =====================
INSERT INTO modules (name, description, order_index, course_id) VALUES
  ('Introduction to Cyber Security', 'Basic concepts of security and threats', 1, 2),
  ('Cryptography Basics', 'Encryption, decryption, hashing', 2, 2),
  ('Network Security', 'Firewalls, IDS/IPS, VPNs', 3, 2),
  ('Malware Analysis', 'Viruses, worms, trojans, rootkits', 4, 2),
  ('Ethical Hacking', 'Penetration testing methodologies', 5, 2),
  ('Web Security', 'OWASP Top 10, SQL injection, XSS', 6, 2),
  ('Cloud Security', 'Security concerns in cloud environments', 7, 2),
  ('Incident Response', 'Handling and mitigating cyber incidents', 8, 2),
  ('Digital Forensics', 'Evidence collection and analysis', 9, 2),
  ('Security Policies & Compliance', 'GDPR, ISO 27001, PCI DSS', 10, 2);

-- =====================
-- Network Operating Systems
-- =====================
INSERT INTO modules (name, description, order_index, course_id) VALUES
  ('Introduction to Operating Systems', 'Concepts of processes, threads, memory', 1, 3),
  ('Linux Basics', 'Command line, file system, permissions', 2, 3),
  ('Windows Server Basics', 'Active Directory, Group Policy', 3, 3),
  ('Network Protocols', 'TCP/IP, DNS, DHCP, HTTP', 4, 3),
  ('Network File Systems', 'NFS, SMB, distributed storage', 5, 3),
  ('User and Group Management', 'Accounts, authentication, authorization', 6, 3),
  ('Process and Service Management', 'Systemd, services, background jobs', 7, 3),
  ('Security in Operating Systems', 'Access control, firewalls, patching', 8, 3),
  ('Virtualization Basics', 'VMs, Hypervisors, Containers', 9, 3),
  ('Monitoring and Logging', 'Syslog, event viewer, monitoring tools', 10, 3);

-- =====================
-- Databases
-- =====================
INSERT INTO modules (name, description, order_index, course_id) VALUES
  ('Introduction to Databases', 'Relational vs Non-relational concepts', 1, 4),
  ('SQL Basics', 'SELECT, INSERT, UPDATE, DELETE', 2, 4),
  ('Joins and Subqueries', 'INNER, OUTER, CROSS joins', 3, 4),
  ('Database Normalization', '1NF, 2NF, 3NF, BCNF', 4, 4),
  ('Indexes and Performance', 'B-Trees, Hash Indexes', 5, 4),
  ('Transactions and Concurrency', 'ACID properties, locks, isolation levels', 6, 4),
  ('Stored Procedures & Functions', 'PL/pgSQL, T-SQL basics', 7, 4),
  ('NoSQL Basics', 'Document, Key-Value, Graph databases', 8, 4),
  ('Database Security', 'Encryption, access control, backups', 9, 4),
  ('Replication & Sharding', 'Scaling databases horizontally', 10, 4);

-- Lessons for CourseModule: Introduction to Java (module_id = 1)
INSERT INTO lesson (title, description, order_index, module_id) VALUES
  ('What is Java?', 'History, JVM, JDK vs JRE', 1, 1),
  ('Setting up Development Environment', 'Install JDK, IDE, Hello World', 2, 1);

-- Lessons for CourseModule: Variables and Data Types (module_id = 2)
INSERT INTO lesson (title, description, order_index, module_id) VALUES
    ('Primitive Types', 'int, float, char, boolean', 1, 2),
    ('Reference Types', 'Strings, arrays, objects', 2, 2),
    ('Type Conversion', 'Casting, auto-boxing, unboxing', 3, 2);

-- Lessons for CourseModule: Inheritance (module_id = 6)
INSERT INTO lesson (title, description, order_index, module_id) VALUES
  ('Basics of Inheritance', 'Extending classes and reusing code', 1, 6),
  ('Method Overriding', 'Polymorphic behavior in subclasses', 2, 6),
  ('The super keyword', 'Calling parent constructors and methods', 3, 6);

-- Lessons for CourseModule: Abstract Classes & Interfaces (module_id = 8)
INSERT INTO lesson (title, description, order_index, module_id) VALUES
  ('Abstract Classes', 'Defining common structure with partial implementation', 1, 8),
  ('Interfaces', 'Defining contracts with multiple inheritance', 2, 8),
  ('Strategy Pattern Example', 'Using interfaces for flexible design', 3, 8);
