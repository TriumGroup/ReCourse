--
--  RUN THIS ONLY WITH NEW SCHEMA OR
--  WITH EMPTY TABLES AND AUTO_INCREMENTS = 0
--
--  OTHERWISE FK VIOLATION WILL BE RAISED
--

USE `recourse`;

-- --------------------- USERS -------------------------------

-- admin

-- 1
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Ivan', 'Shimko', 'admin@triumgroup.com',
   'MALE', '1996-05-01', 'ADMIN');

-- students
-- 2
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Mikhail', 'Snitavets', 'mikhail_snitavets@triumgroup.com',
   'MALE', '1996-05-01', 'STUDENT');
-- 3
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Ivan', 'Ivanov', 'ivan_ivanov@triumgroup.com',
   'MALE', '1996-05-01', 'STUDENT');
-- 4
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Petr', 'Petrov', 'petr_petrov@triumgroup.com',
   'MALE', '1996-05-01', 'STUDENT');

-- teachers
-- 5
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Andrey', 'Tatarenko', 'andrey_tatarenko@triumgroup.com',
   'MALE', '1996-05-01', 'TEACHER');
-- 6
INSERT INTO user (password_hash, surname, name, email, gender, birthday, role) VALUES
  ('$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Ivan', 'Petrov', 'ivan_petrov@triumgroup.com',
   'MALE', '1996-05-01', 'TEACHER');

-- --------------------- COURSES -------------------------------
-- 1
INSERT INTO course (title, description, status, registration_end, max_students) VALUES
  ('Java for beginners', 'Donec dapibus. Duis at velit eu est congue elementum. In hac habitasse platea dictumst.',
   'PUBLISHED', '2017-05-20 12:00:00', 10);
-- 2
INSERT INTO course (title, description, status, registration_end, max_students) VALUES
  ('JS for beginners', 'Donec dapibus. Duis at velit eu est congue elementum. In hac habitasse platea dictumst.',
   'PUBLISHED', '2017-05-01 12:00:00', 10);
-- 3
INSERT INTO course (title, description, status, registration_end, max_students) VALUES
  ('Java Pro', 'Duis at velit eu est congue elementum. In hac habitasse platea dictumst.',
   'FINISHED', '2016-05-20 12:00:00', 10);


-- ----------------------- LESSONS ------------------

-- Java for beginners - id = 1, all lessons in the future
-- 1
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-05-21 11:00:00', '1:00:00', 1, 'target end-to-end infomediaries', 5, 'Etiam pretium iaculis justo.');
-- 2
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-05-22 11:00:00', '1:00:00', 1, 'target end-to-end infomediaries', 5, 'Etiam pretium iaculis justo.');
-- 3
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-05-23 11:00:00', '1:00:00', 1, 'target end-to-end infomediaries', 5, '');
-- 4
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-05-24 11:00:00', '1:00:00', 1, 'target end-to-end infomediaries', 5, 'Etiam pretium iaculis justo.');

-- JS for beginners - id = 2, some lessons in the past
-- 5
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-05-01 11:00:00', '1:00:00', 2, 'target end-to-end infomediaries', 6, 'Etiam pretium iaculis justo.');
-- 6
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-05-02 11:00:00', '1:00:00', 2, 'target end-to-end infomediaries', 6, 'Etiam pretium iaculis justo.');
-- 7
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-05-03 11:00:00', '1:00:00', 2, 'target end-to-end infomediaries', 6, 'Etiam pretium iaculis justo.');
-- 8
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-05-20 11:00:00', '1:00:00', 2, 'target end-to-end infomediaries', 6, 'Etiam pretium iaculis justo.');
-- 9
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2017-05-21 11:00:00', '1:00:00', 2, 'target end-to-end infomediaries', 6, 'Etiam pretium iaculis justo.');

-- Java Pro - id = 3, all lessons in the past
-- 10
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2016-05-01 11:00:00', '1:00:00', 3, 'target end-to-end infomediaries', 6, 'Etiam pretium iaculis justo.');
-- 11
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2016-05-02 11:00:00', '1:00:00', 3, 'target end-to-end infomediaries', 6, 'Etiam pretium iaculis justo.');
-- 12
INSERT INTO lesson (start_time, duration, course_id, topic, teacher_id, task) VALUES
  ('2016-05-03 11:00:00', '1:00:00', 3, 'target end-to-end infomediaries', 6, 'Etiam pretium iaculis justo.');
-- -------------------------------------------------------------------

-- -------------------- COURSE STUDENTS ------------------------------
-- Java for beginners - id = 1, all lessons in the future
INSERT INTO course_student (course_id, student_id) VALUES (1, 2);
INSERT INTO course_student (course_id, student_id) VALUES (1, 3);
INSERT INTO course_student (course_id, student_id) VALUES (1, 4);
-- JS for beginners - id = 2, some lessons in the past
INSERT INTO course_student (course_id, student_id) VALUES (2, 2);
INSERT INTO course_student (course_id, student_id) VALUES (2, 3);
INSERT INTO course_student (course_id, student_id) VALUES (2, 4);
-- Java Pro - id = 3, all lessons in the past
INSERT INTO course_student (course_id, student_id) VALUES (3, 2);
INSERT INTO course_student (course_id, student_id) VALUES (3, 3);
INSERT INTO course_student (course_id, student_id) VALUES (3, 4);
--

-- -------------------- HOMETASK SOLUTIONS ------------------------------
-- for past lessons of the Java Pro - id = 3, all lessons in the past
-- 1
INSERT INTO hometask_solution (lesson_id, student_id, solution) VALUES (10, 2, 'Etiam pretium iaculis justo.');
-- 2
INSERT INTO hometask_solution (lesson_id, student_id, solution) VALUES (11, 2, 'Etiam pretium iaculis justo.');
-- 3
INSERT INTO hometask_solution (lesson_id, student_id, solution) VALUES (12, 2, 'Etiam pretium iaculis justo.');
-- 4
INSERT INTO hometask_solution (lesson_id, student_id, solution) VALUES (5, 2, 'Etiam pretium iaculis justo.');
-- 5
INSERT INTO hometask_solution (lesson_id, student_id, solution) VALUES (6, 2, 'Etiam pretium iaculis justo.');

-- 6
INSERT INTO hometask_solution (lesson_id, student_id, solution) VALUES (10, 3, 'Etiam pretium iaculis justo.');
-- 7
INSERT INTO hometask_solution (lesson_id, student_id, solution) VALUES (11, 3, 'Etiam pretium iaculis justo.');
-- 8
INSERT INTO hometask_solution (lesson_id, student_id, solution) VALUES (12, 3, 'Etiam pretium iaculis justo.');

-- 9
INSERT INTO hometask_solution (lesson_id, student_id, solution) VALUES (10, 4, 'Etiam pretium iaculis justo.');
-- 10
INSERT INTO hometask_solution (lesson_id, student_id, solution) VALUES (11, 4, 'Etiam pretium iaculis justo.');
-- 11
INSERT INTO hometask_solution (lesson_id, student_id, solution) VALUES (12, 4, 'Etiam pretium iaculis justo.');


-- -------------------- MARKS ------------------------------
-- for student 2
INSERT INTO mark (score, solution_id, comment) VALUES (10, 1, 'good');
INSERT INTO mark (score, solution_id, comment) VALUES (8, 2, 'not so good');
INSERT INTO mark (score, solution_id, comment) VALUES (10, 3, 'good');
INSERT INTO mark (score, solution_id, comment) VALUES (9, 4, 'good');

INSERT INTO mark (score, solution_id, comment) VALUES (8, 6, 'good');
INSERT INTO mark (score, solution_id, comment) VALUES (8, 7, 'not so good');
INSERT INTO mark (score, solution_id, comment) VALUES (8, 8, 'good');

-- for student 3
INSERT INTO mark (score, solution_id, comment) VALUES (4, 9, 'bad');
INSERT INTO mark (score, solution_id, comment) VALUES (4, 10, 'bad');
INSERT INTO mark (score, solution_id, comment) VALUES (5, 11, 'bad');


-- -------------------- FEEDBACKS ------------------------------
INSERT INTO course_feedback (course_id, student_id, heading, report, pros, cons) VALUES (3, 2, 'Cool', 'Cool Cool Cool Cool Cool', 'Cool', 'Cool');
INSERT INTO course_feedback (course_id, student_id, heading, report, pros, cons) VALUES (3, 3, 'Cool', 'Cool Cool Cool Cool Cool', 'Cool', 'Cool');
INSERT INTO course_feedback (course_id, student_id, heading, report, pros, cons) VALUES (3, 4, 'Cool', 'Cool Cool Cool Cool Cool', 'Cool', 'Cool')

