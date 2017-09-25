use payroll;

ALTER TABLE  leave_type_master ADD lt_code VARCHAR(100) NOT NULL AFTER lt_name;
ALTER TABLE  leave_type_master ADD lt_short VARCHAR(100) NOT NULL AFTER lt_code;
