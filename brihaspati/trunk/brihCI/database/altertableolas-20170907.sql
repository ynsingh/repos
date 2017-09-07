use olas;
ALTER TABLE `student_fees` ADD `sfee_reconcilestatus` VARCHAR(10) NOT NULL DEFAULT 'N' AFTER `sfee_feespaidstatus`, ADD `sfee_whoreconcile` VARCHAR(255) NULL AFTER `sfee_reconcilestatus`, ADD `sfee_reconciledate` VARCHAR(255) NULL AFTER `sfee_whoreconcile`, ADD `sfee_reconcileremark` INT(255) NULL AFTER `sfee_reconciledate`;
