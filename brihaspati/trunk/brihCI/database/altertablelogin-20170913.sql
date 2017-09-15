use login;
alter table authorities add code varchar(255) after id;
 update  authorities set code='D' where id=1;
 update  authorities set code='FO' where id=2;
 update  authorities set code='DRA' where id=3;
 update  authorities set code='ARA' where id=4;
 update  authorities set code='ACC' where id=5;

