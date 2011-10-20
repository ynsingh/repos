/*
SQLyog Community v8.63 
MySQL - 5.1.57-community 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

create table `email_settings` (
	`id` double ,
	`smtp_host` varchar (765),
	`smtp_password` varchar (765),
	`smtp_port` varchar (765),
	`smtp_username` varchar (765)
); 
insert into `email_settings` (`id`, `smtp_host`, `smtp_password`, `smtp_port`, `smtp_username`) values('1','smtp.gmail.com','amritamail','25','email@amrita.ac.in');
