use pl;

ALTER TABLE salary_head_master ADD  sh_code varchar(80) NOT NULL after sh_id;
UPDATE salary_head_master SET sh_code=(select SUBSTR(sh_name, 1, 2));
UPDATE salary_head_master SET sh_code=(select CONCAT(sh_code,sh_id));
ALTER TABLE salary_head_master ADD sh_ledger_code varchar(100)  NULL after sh_process_type;
ALTER TABLE salary_head_master ADD CONSTRAINT sh_salary_head_master UNIQUE(sh_code,sh_org_id);

