 use olas;

alter table program add prg_scid  INT(11) NOT NULL after prg_id;
alter table program add prg_deptid  INT(11) NOT NULL after prg_scid;

