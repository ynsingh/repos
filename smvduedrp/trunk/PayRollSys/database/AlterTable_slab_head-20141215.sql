use pl;

ALTER TABLE slab_head ADD sl_session_id int(11) NOT NULL AFTER sl_head_code;
ALTER TABLE slab_head ADD sl_surcharge float(9,3) NOT NULL AFTER sl_percent;
ALTER TABLE slab_head ADD sl_edu_cess float(9,3) NOT NULL AFTER sl_surcharge;
ALTER TABLE slab_head ADD sl_hedu_cess float(9,3) NOT NULL AFTER sl_edu_cess;
ALTER TABLE slab_head CHANGE slab_head_name slab_head_name varchar(100) NOT NULL;
ALTER TABLE slab_head CHANGE sl_start_value sl_start_value int(100) NOT NULL;
ALTER TABLE slab_head CHANGE sl_end_value sl_end_value int(100) NOT NULL;
ALTER TABLE slab_head CHANGE sl_orgCode sl_orgCode int(11) NOT NULL;
ALTER TABLE slab_head DROP INDEX slab_head_name;
ALTER TABLE slab_head ADD CONSTRAINT slab_head_name_uq UNIQUE (sl_session_id, slab_head_name);
ALTER TABLE slab_head ADD CONSTRAINT slab_session_fk1 FOREIGN KEY (sl_session_id) REFERENCES session_master(ss_id) ON UPDATE CASCADE ON DELETE CASCADE;

