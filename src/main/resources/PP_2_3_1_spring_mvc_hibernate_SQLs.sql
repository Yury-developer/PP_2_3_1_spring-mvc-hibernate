# При разработке часто использовались запросы, вывел в этот фаил.
DROP DATABASE IF EXISTS`PP_2_3_1_spring_mvc_hibernate`;
CREATE SCHEMA IF NOT EXISTS `PP_2_3_1_spring_mvc_hibernate` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;

SHOW DATABASES;
USE `PP_2_3_1_spring_mvc_hibernate`;
SHOW TABLES FROM `PP_2_3_1_spring_mvc_hibernate`;
SELECT * FROM PP_2_3_1_spring_mvc_hibernate.users;
SELECT * FROM PP_2_3_1_spring_mvc_hibernate.address;
SELECT * FROM PP_2_3_1_spring_mvc_hibernate.phones;
SELECT * FROM PP_2_3_1_spring_mvc_hibernate.emails;

TRUNCATE TABLE users;
DROP TABLE IF EXISTS users;

drop table if exists address;
drop table if exists phones;
drop table if exists emails;
drop table if exists users;


create table emails (emailEntry_id integer not null auto_increment, emailEntry_description varchar(255), emailEntry_value varchar(255), fk_emailEntry_id integer, primary key (emailEntry_id)) engine=MyISAM;
create table phones (phoneEntry_id integer not null auto_increment, phoneEntry_description varchar(255), phoneEntry_value varchar(255), fk_phoneEntry_id integer, primary key (phoneEntry_id)) engine=MyISAM;
create table address (address_id integer not null auto_increment, address varchar(255), city varchar(255), primary key (address_id)) engine=MyISAM;
create table users (user_id integer not null auto_increment, dateBirth date, name varchar(50) not null, fk_address_id integer, primary key (user_id)) engine=MyISAM;
alter table emails add constraint FKovmge846i5orpfl7d3dqy75j4 foreign key (fk_emailEntry_id) references users (user_id);
alter table phones add constraint FKa7tj3n2il7516jsh8v0x2wafy foreign key (fk_phoneEntry_id) references users (user_id);
alter table users add constraint FKm17m36qxyja8k4t4yqhkp6lr9 foreign key (fk_address_id) references address (address_id);