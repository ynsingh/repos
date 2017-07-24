 use olas;

alter table seat_program_studycenter change spsc_ext1 spsc_acadyear varchar(255) ;
alter table seat_program_studycenter change spsc_ext2 spsc_sem  varchar(255) ;
alter table seat_program_studycenter add spsc_creatorid varchar(255) after spsc_sem;
alter table seat_program_studycenter add spsc_createdate varchar(255) after spsc_creatorid;
alter table seat_program_studycenter add spsc_modifierid varchar(255) after spsc_createdate;
alter table seat_program_studycenter add psc_modifydate varchar(255) after spsc_modifierid;

DROP INDEX sub_name ON subject;
DROP INDEX sub_code ON subject;
