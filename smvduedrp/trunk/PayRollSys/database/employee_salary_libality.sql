CREATE TABLE employee_salary_liability
(
	esl_emp_code varchar(40) NOT NULL,
   	esl_month int(11) NOT NULL,
    	esl_year int(11) NOT NULL,
    	esl_totalsalary_amount int(11) NOT NULL,
    	esl_debit int(11) NOT NULL,
    	esl_credit int(11) NOT NULL,
    	esl_org_id int(11) NOT NULL,
	KEY `esl_emp_code` (`esl_emp_code`),
	KEY `esl_org_id` (`esl_org_id`),
	CONSTRAINT `employee_salary_liability_fk1` FOREIGN KEY (`esl_org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  	CONSTRAINT `employee_salary_liability_ibfk_1` FOREIGN KEY (`esl_emp_code`) REFERENCES `employee_master` (`emp_code`) ON DELETE CASCADE ON UPDATE CASCADE

);
