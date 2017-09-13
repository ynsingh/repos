use login;
alter table authorities add code varchar(255) after id;
 update table authorities set code='D' where id=1;
 update table authorities set code='FO' where id=2;
 update table authorities set code='DRA' where id=3;
 update table authorities set code='ARA' where id=4;
 update table authorities set code='ACC' where id=5;

