alter table salary_leave_entry add `sle_deptid` int(3) default null after `sle_empid`;
alter table salary_transfer_entry add `ste_deptid` int(3) default null after `ste_empid`;
alter table salary_leave_entry_archive add `slea_deptid` int(3) default null after `slea_empid`;
alter table salary_transfer_entry_archive add `stea_deptid` int(3) default null after `stea_empid`;
