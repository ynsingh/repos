CREATE TABLE leavetype_org_record (
        ltr_leave_id int(11) NOT NULL,
        ltr_org_id int(11) NOT NULL,
	KEY ltr_leave_id (ltr_leave_id),
        KEY ltr_or_gid (ltr_org_id),
	CONSTRAINT `leavetype_org_record_fk1` FOREIGN KEY (`ltr_org_id`) REFERENCES `org_profile` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE,
        CONSTRAINT `leavetype_org_record_ibfk_1` FOREIGN KEY (`ltr_leave_id`) REFERENCES `leave_type_master` (`lt_id`) ON DELETE CASCADE ON UPDATE CASCADE

);

