use olas;
alter table `subject_prerequisite` change `spreq_depsubid` `spreq_depsubid` int(11) DEFAULT  NULL ;
alter table `subject_prerequisite` change `spreq_subpid` `spreq_subpid` int(11) DEFAULT  NULL ;
alter table `subject_prerequisite` change `spreq_depsubpid` `spreq_depsubpid` int(11) DEFAULT  NULL ;
alter table `subject_prerequisite` change `spreq_ext1` `spreq_ext1` VARCHAR(255) DEFAULT  NULL ;
alter table `subject_prerequisite` change `spreq_ext2` `spreq_ext2` VARCHAR(255) DEFAULT  NULL ;
