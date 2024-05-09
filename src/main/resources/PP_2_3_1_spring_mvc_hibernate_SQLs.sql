DROP DATABASE IF EXISTS`PP_2_3_1_spring_mvc_hibernate`;
CREATE SCHEMA IF NOT EXISTS `PP_2_3_1_spring_mvc_hibernate` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;

SHOW DATABASES;
USE `PP_2_3_1_spring_mvc_hibernate`;
SHOW TABLES FROM `PP_2_3_1_spring_mvc_hibernate`;
SELECT * FROM PP_2_3_1_spring_mvc_hibernate.users;
SELECT * FROM PP_2_3_1_spring_mvc_hibernate.cars;

TRUNCATE TABLE users;
TRUNCATE TABLE cars;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS cars;