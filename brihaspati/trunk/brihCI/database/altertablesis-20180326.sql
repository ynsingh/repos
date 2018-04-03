use payroll;

alter table uo_list add  `ul_authuoid` int(11)  NOT NULL after  `ul_empcode`;
alter table employee_master add emp_grade varchar(255) Default NULL;
