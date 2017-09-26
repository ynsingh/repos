drop index `satd_smid` on `student_attendance`;
create index  `satd_smid` on `student_attendance` (`satd_smid`,`satd_scid`,`satd_acadyear`,`satd_prgid`,`satd_sem`,`satd_subid`,`satd_papid`,`satd_classtype`,`satd_adate`);
