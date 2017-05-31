update `org_profile` set org_reg_date='2008-07-01', org_close_date='2118-07-01' where org_id=1;

alter table `admissionmeritlist` change `admission_date` `admission_date` DATE DEiFAULT NULL; 
alter table `admissionmeritlist` change `lastdate_admission` `lastdate_admission` DATE DEFAULT NULL; 
alter table `admissionstep` change `step1_date`  `step1_date` DATETIME DEFAULT CURRENT_TIMESTAMP; 
alter table `admissionstep` change `step2_date`  `step2_date`  DATETIME DEFAULT CURRENT_TIMESTAMP; 
alter table `admissionstep` change `step3_date`  `step3_date` DATETIME DEFAULT CURRENT_TIMESTAMP; 
alter table `admissionstep` change `step4_date`  `step4_date` DATETIME DEFAULT CURRENT_TIMESTAMP; 
alter table `admissionstep` change `step5_date`  `step5_date` DATETIME DEFAULT CURRENT_TIMESTAMP; 
alter table `email_setting` change `creatorid` `creatorid`  DATETIME DEFAULT CURRENT_TIMESTAMP; 
alter table `email_setting` change  `modifidate` `modifidate` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP; 
alter table `fees_master` change  `fm_frmdate` `fm_frmdate`   DATE DEFAULT NULL; 
alter table `fees_master` change `fm_todate` `fm_todate` DATE DEFAULT NULL; 
alter table `org_profile` change `org_reg_date` `org_reg_date` DATE DEFAULT NULL; 
alter table `org_profile` change `org_close_date` `org_close_date` DATE DEFAULT NULL; 
alter table `org_profile` change  `create_date` `create_date` DATETIME DEFAULT CURRENT_TIMESTAMP; 
alter table `org_profile` change `modify_date` `modify_date`  DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP; 
alter table `program` change `createdate` `createdate` DATETIME DEFAULT CURRENT_TIMESTAMP; 
alter table `program_subject_teacher` change `pstp_createdate` `pstp_createdate` DATETIME DEFAULT CURRENT_TIMESTAMP; 
alter table `program_subject_teacher` change `pstp_modifydate` `pstp_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP; 
alter table `seat_program_studycenter` change `spsc_createdate` `spsc_createdate` DATETIME DEFAULT CURRENT_TIMESTAMP; 
alter table `seat_program_studycenter` change `spsc_modifydate` `spsc_modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP; 
alter table `seat_reservation` change `createdate` `createdate` DATETIME DEFAULT CURRENT_TIMESTAMP; 
alter table `student_fees` change `sfee_duedate` `sfee_duedate` DATETIME DEFAULT CURRENT_TIMESTAMP; 
alter table `student_fees` change `sfee_depositdate` `sfee_depositdate`  DATETIME DEFAULT CURRENT_TIMESTAMP; 
alter table `student_master` change `sm_dob` `sm_dob` DATETIME DEFAULT CURRENT_TIMESTAMP; 
alter table `study_center` change  `sc_startdate` `sc_startdate` DATE DEFAULT NULL; 
alter table `study_center` change `sc_closedate` `sc_closedate` DATE DEFAULT NULL; 
alter table `study_center` change `createdate` `createdate`  DATETIME DEFAULT CURRENT_TIMESTAMP; 
alter table `subject_paper` change `createdate` `createdate`  DATETIME DEFAULT CURRENT_TIMESTAMP; 
alter table `subject_paper` change `modifydate` `modifydate` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP; 

alter table `student_fees` add  `sfee_feename` varchar(255) NOT NULL after `sfee_spid`;
alter table `admissionmeritlist` add `admission_taken` VARCHAR(255) NOT NULL  after lastdate_admission;
alter table `admissionmeritlist` add `admission_date` DATE DEFAULT NULL  after `admission_taken`;

alter table `fees_master` drop  `fm_installment`;

alter table `student_fees` add `sfee_installment` int(2) NOT NULL after  `sfee_feeamount`;
alter table `student_fees` add `sfee_installment_date` DATE DEFAULT NULL after `sfee_installment`;
