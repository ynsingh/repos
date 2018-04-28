use olas;

CREATE TABLE `student_doclist` (
  `sdlist_id` int(11) NOT NULL,
  `sdlist_smid` int(11) DEFAULT NULL,
  `sdlist_docname` varchar(255) DEFAULT NULL,
  `sdlist_docoriginal` varchar(255) DEFAULT NULL,
  `sdlist_docduplicate` varchar(255) DEFAULT NULL,
  `sdlist_remarks` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `student_doclist`
  ADD PRIMARY KEY (`sdlist_id`) USING BTREE;

ALTER TABLE `student_doclist`
  MODIFY `sdlist_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

ALTER TABLE `admissionmeritlist` ADD `student_dob` VARCHAR(255) NOT NULL AFTER `student_name`;

