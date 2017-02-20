use pl;
ALTER TABLE employee_salary_liability drop  esl_debit;
ALTER TABLE employee_salary_liability drop  esl_credit;
ALTER TABLE employee_salary_liability ADD esl_type varchar(30) NOT NULL after esl_totalsalary_amount;
ALTER TABLE employee_salary_liability ADD esl_transaction_date date default NULL after esl_type;
