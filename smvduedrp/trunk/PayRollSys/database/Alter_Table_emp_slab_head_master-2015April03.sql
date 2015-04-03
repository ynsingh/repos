use pl;
ALTER TABLE emp_slab_head_master ADD COLUMN id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT;
ALTER TABLE emp_slab_head_master ADD CONSTRAINT gender_code_fk1 FOREIGN KEY (emp_gen_code) REFERENCES ts_gender(ts_seq) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE emp_slab_head_master ADD CONSTRAINT slab_code_fk2 FOREIGN KEY (emp_slab_code) REFERENCES slab_head(sl_head_code) ON UPDATE CASCADE ON DELETE CASCADE;
