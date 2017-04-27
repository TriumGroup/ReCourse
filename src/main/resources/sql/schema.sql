CREATE DATABASE IF NOT EXISTS `recourse`
  DEFAULT CHARACTER SET utf8;
USE `recourse`;

CREATE USER IF NOT EXISTS 'test'@'localhost'
  IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON `recourse`.* TO 'test'@'localhost';

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id`            INT(11)                 NOT NULL                  AUTO_INCREMENT,
  `email`         VARCHAR(255)            NOT NULL,
  `password_hash` CHAR(60)                NOT NULL,
  `surname`       VARCHAR(50)             NOT NULL,
  `name`          VARCHAR(50)             NOT NULL,
  `gender`        ENUM ('MALE', 'FEMALE') NOT NULL,
  `birthday`      DATE                                              DEFAULT NULL,
  `role`          ENUM ('STUDENT', 'TEACHER', 'ADMIN', 'DISABLED')  DEFAULT NULL,
  `is_deleted`    BOOL                    NOT NULL                  DEFAULT FALSE,
  PRIMARY KEY (`id`),
  KEY `IDX_name` (`name`, `surname`),
  UNIQUE KEY `email_UNIQUE` (`email`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


DROP TABLE IF EXISTS `course` CASCADE;
CREATE TABLE `course` (
  `id`           INT                                          NOT NULL AUTO_INCREMENT,
  `title`        VARCHAR(50)                                  NOT NULL,
  `description`  TEXT                                         NOT NULL,
  `status`       ENUM ('ONGOING', 'REGISTRATION', 'FINISHED') NOT NULL,
  `max_students` INT                                          NOT NULL,
  CONSTRAINT `PK_Course` PRIMARY KEY (`id` ASC)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


DROP TABLE IF EXISTS `course_student` CASCADE;
CREATE TABLE `course_student` (
  `course_id`  INT NOT NULL,
  `student_id` INT NOT NULL,
  CONSTRAINT `PK_course_student` PRIMARY KEY (`course_id` ASC, `student_id` ASC),
  CONSTRAINT `FK_course_student_course`
  FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  CONSTRAINT `FK_course_student_student`
  FOREIGN KEY (`student_id`) REFERENCES `user` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `course_feedback` CASCADE;
CREATE TABLE `course_feedback` (
  `id`         INT         NOT NULL AUTO_INCREMENT,
  `course_id`  INT         NOT NULL,
  `student_id` INT         NOT NULL,
  `heading`    VARCHAR(50) NULL,
  `report`     TEXT        NULL,
  `pros`       TEXT        NULL,
  `cons`       TEXT        NULL,
  CONSTRAINT `PK_course_feedback` PRIMARY KEY (`id` ASC),
  CONSTRAINT `unique_feedback` UNIQUE (`course_id` ASC, `student_id` ASC),
  CONSTRAINT `FK_course_feedback_course`
  FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_course_feedback_user`
  FOREIGN KEY (`student_id`) REFERENCES `user` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `lesson` CASCADE;
CREATE TABLE `lesson` (
  `id`         INT         NOT NULL AUTO_INCREMENT,
  `start_time` DATETIME    NOT NULL,
  `duration`   TIME        NOT NULL,
  `course_id`  INT         NOT NULL,
  `topic`      VARCHAR(50) NULL,
  `teacher_id` INT         NOT NULL,
  `task`       TEXT        NULL,
  CONSTRAINT `PK_Class` PRIMARY KEY (`id` ASC),
  CONSTRAINT `FK_class_course`
  FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_class_user`
  FOREIGN KEY (`teacher_id`) REFERENCES `user` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `hometask_solution` CASCADE;
CREATE TABLE `hometask_solution` (
  `id`         INT  NOT NULL AUTO_INCREMENT,
  `lesson_id`  INT  NOT NULL,
  `student_id` INT  NOT NULL,
  `solution`   TEXT NULL,
  CONSTRAINT `PK_hometask_solution` PRIMARY KEY (`id` ASC),
  CONSTRAINT `unique_solution` UNIQUE (`lesson_id` ASC, `student_id` ASC),
  CONSTRAINT `FK_hometask_solution_hometask`
  FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_hometask_solution_user`
  FOREIGN KEY (`student_id`) REFERENCES `user` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


DROP TABLE IF EXISTS `mark` CASCADE;
CREATE TABLE `mark`
(
  `id`          INT     NOT NULL AUTO_INCREMENT,
  `score`       TINYINT NOT NULL,
  `solution_id` INT     NOT NULL,
  `comment`     TEXT    NOT NULL,
  CONSTRAINT `PK_Mark` PRIMARY KEY (`id` ASC),
  CONSTRAINT `solution_id` UNIQUE (`solution_id` ASC),
  CONSTRAINT `FK_mark_hometask_solution`
  FOREIGN KEY (`solution_id`) REFERENCES `hometask_solution` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

SET FOREIGN_KEY_CHECKS = 1;

-- -----------------------------------------------------------
-- Stored functions
-- -----------------------------------------------------------

DROP FUNCTION IF EXISTS `can_add_lesson`;
DELIMITER ;;
CREATE FUNCTION `can_add_lesson`(`teacher_id` INT, `new_lesson_start_time` DATETIME, `new_lesson_duration` TIME)
  RETURNS BOOL
  BEGIN
    RETURN can_update_lesson(`teacher_id`, `new_lesson_start_time`, `new_lesson_duration`, -1);
  END ;;
DELIMITER ;


DROP FUNCTION IF EXISTS `can_update_lesson`;
DELIMITER ;;
CREATE FUNCTION `can_update_lesson`(`teacher_id` INT, `new_lesson_start_time` DATETIME, `new_lesson_duration` TIME,
                                    `lesson_id`  INT)
  RETURNS BOOL
  BEGIN
    DECLARE result BOOL;
    SELECT NOT EXISTS(
        SELECT `id`
        FROM
          `lesson`
        WHERE
          `lesson`.`teacher_id` = `teacher_id`
          AND `new_lesson_start_time` <= ADDTIME(`start_time`, `duration`)
          AND ADDTIME(`new_lesson_start_time`, `new_lesson_duration`) >= `start_time`
          AND `lesson_id` != `id`)
    INTO result;
    RETURN result;
  END ;;
DELIMITER ;

-- -----------------------------------------------------------
-- Spring OAuth2 required tables
-- -----------------------------------------------------------

DROP TABLE IF EXISTS `oauth_access_token`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_access_token` (
  `token_id`          VARCHAR(256) DEFAULT NULL,
  `token`             BLOB,
  `authentication_id` VARCHAR(256) DEFAULT NULL,
  `user_name`         VARCHAR(256) DEFAULT NULL,
  `client_id`         VARCHAR(256) DEFAULT NULL,
  `authentication`    BLOB,
  `refresh_token`     VARCHAR(256) DEFAULT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id`               VARCHAR(256) NOT NULL,
  `resource_ids`            VARCHAR(256)  DEFAULT NULL,
  `client_secret`           VARCHAR(256)  DEFAULT NULL,
  `scope`                   VARCHAR(256)  DEFAULT NULL,
  `authorized_grant_types`  VARCHAR(256)  DEFAULT NULL,
  `web_server_redirect_uri` VARCHAR(256)  DEFAULT NULL,
  `authorities`             VARCHAR(256)  DEFAULT NULL,
  `access_token_validity`   INT(11)       DEFAULT NULL,
  `refresh_token_validity`  INT(11)       DEFAULT NULL,
  `additional_information`  VARCHAR(4096) DEFAULT NULL,
  `autoapprove`             VARCHAR(256)  DEFAULT NULL,
  PRIMARY KEY (`client_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `code`           VARCHAR(256) DEFAULT NULL,
  `authentication` BLOB
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token` (
  `token_id`       VARCHAR(256) DEFAULT NULL,
  `token`          BLOB,
  `authentication` BLOB
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- -----------------------------------------------------------
-- OAuth2 clients data
-- -----------------------------------------------------------
INSERT INTO `oauth_client_details` VALUES (
  'web_client' /* Client ID */,
               NULL,
               'lH2HvQ4Vk3f8k0S', /* Client Secret */
               'read,write,trust',
               'password,authorization_code,refresh_token,implicit',
               '',
               'ROLE_USER',
               NULL,
               NULL,
               NULL,
               NULL
);

-- -----------------------------------------------------------
-- User data
-- -----------------------------------------------------------
INSERT INTO `user` (email, password_hash, name, surname, gender, birthday, role) VALUES
  ('Ivan_Shimko@triumgroup.com',
   '$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', /* 12345 */
   'Ivan', 'Shimko', 'male', NULL, 'teacher'),
  ('Andrey_Tatarenko@triumgroup.com',
   '$2a$12$eI7e3tSsXOBx5ZDUxY4gAeZQTu7yxKH7fGmPrVindUoJPDz4Cp03G', /* 54321 */
   'Andrey', 'Tatarenko', 'male', NULL, 'student'),
  ('Mikhail_Snitavets@triumgroup.com',
   '$2a$12$dhZ.l.x3RwbAYSMTOS.ERuRlRy6ikeSVIkdDPbD20uGrP08WmmZRe', /* 09876 */
   'Mikhail', 'Snitavets', 'male', NULL, 'student'),
  ('admin@triumgroup.com',
  '$2a$12$dhZ.l.x3RwbAYSMTOS.ERuRlRy6ikeSVIkdDPbD20uGrP08WmmZRe', /* 09876 */
  'Foo', 'Bar', 'male', NULL, 'admin');

-- -----------------------------------------------------------
-- Course data
-- -----------------------------------------------------------
INSERT INTO `recourse`.`course` (title, description, status, max_students) VALUES
  ('Awesome Java Course',
   'Get out of your fucking mind with our java course',
   'ONGOING',
   20);

INSERT INTO `recourse`.`lesson` (start_time, duration, course_id, topic, teacher_id, task) VALUES
  (date('2016-01-01'),
   time('5:00:00'),
   (select `id` from `recourse`.`course` limit 1),
   'Java Spring',
   (select `id` from `recourse`.`user` where `role` = 'TEACHER' limit 1),
   'Very long long long long long long long long long long long long long text.'
  );

INSERT INTO `recourse`.`hometask_solution` (lesson_id, student_id, solution) VALUES
  ( 1,
    2,
    'Solution');

INSERT INTO `recourse`.`mark` (score, solution_id, comment) VALUES
  (
      10,
    1,
    "comment"
  )