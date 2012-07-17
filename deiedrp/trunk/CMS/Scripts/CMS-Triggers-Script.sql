-- MySQL dump 10.11
--
-- Host: localhost    Database: cms
-- ------------------------------------------------------
-- Server version	5.0.77

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
--
-- Definition of trigger `update_employee_master`
--

DROP TRIGGER /*!50030 IF EXISTS */ `update_employee_master`;

DELIMITER $$

CREATE DEFINER = `root`@`localhost` TRIGGER `update_employee_master` BEFORE UPDATE ON `employee_master` FOR EACH ROW BEGIN
								INSERT INTO receiver
								SET event = 'Update',
								event_time = NOW(),
								flag = 'Before',
								table_name = 'employee_master',
								table_data = CONCAT_WS(',',COALESCE(OLD.employee_id, 'null'), COALESCE(OLD.parent_entity, 'null'), COALESCE(OLD.first_name, 'null'), COALESCE(OLD.middle_name, 'null'),COALESCE(OLD.last_name, 'null'),
														COALESCE(OLD.primary_email_id, 'null'), COALESCE(OLD.secondary_email_id, 'null'), COALESCE(OLD.qualification, 'null'), COALESCE(OLD.designations, 'null'), COALESCE(OLD.dob, 'null'),
														COALESCE(OLD.doj, 'null'), COALESCE(OLD.insert_time, 'null'), COALESCE(OLD.modification_time, 'null'), COALESCE(OLD.creator_id, 'null'), COALESCE(OLD.modifier_id, 'null'),
														COALESCE(OLD.employee_code, 'null'), COALESCE(OLD.university_code, 'null'), COALESCE(OLD.employee_status, 'null'), COALESCE(OLD.category_code, 'null'), COALESCE(OLD.gender, 'null'),
														COALESCE(OLD.physical_handicapped, 'null'), COALESCE(OLD.net, 'null'), COALESCE(OLD.pg, 'null'), COALESCE(OLD.minority, 'null'), COALESCE(OLD.pen, 'null'),
														COALESCE(OLD.dofr, 'null'), COALESCE(OLD.minority_flag, 'null'));

								INSERT INTO receiver
								SET event='Update',
								event_time = NOW(),
								flag = 'After',
								table_name = 'employee_master',
								table_data = CONCAT_WS(',',COALESCE(NEW.employee_id, 'null'), COALESCE(NEW.parent_entity, 'null'), COALESCE(NEW.first_name, 'null'), COALESCE(NEW.middle_name, 'null'),COALESCE(NEW.last_name, 'null'),
														COALESCE(NEW.primary_email_id, 'null'), COALESCE(NEW.secondary_email_id, 'null'), COALESCE(NEW.qualification, 'null'), COALESCE(NEW.designations, 'null'), COALESCE(NEW.dob, 'null'),
														COALESCE(NEW.doj, 'null'), COALESCE(NEW.insert_time, 'null'), COALESCE(NEW.modification_time, 'null'), COALESCE(NEW.creator_id, 'null'), COALESCE(NEW.modifier_id, 'null'),
														COALESCE(NEW.employee_code, 'null'), COALESCE(NEW.university_code, 'null'), COALESCE(NEW.employee_status, 'null'), COALESCE(NEW.category_code, 'null'), COALESCE(NEW.gender, 'null'),
														COALESCE(NEW.physical_handicapped, 'null'), COALESCE(NEW.net, 'null'), COALESCE(NEW.pg, 'null'), COALESCE(NEW.minority, 'null'), COALESCE(NEW.pen, 'null'),
														COALESCE(NEW.dofr, 'null'), COALESCE(NEW.minority_flag, 'null'));
								END $$

DELIMITER ;

--
-- Definition of trigger `delete_employee_master`
--

DROP TRIGGER /*!50030 IF EXISTS */ `delete_employee_master`;

DELIMITER $$

CREATE DEFINER = `root`@`localhost` TRIGGER `delete_employee_master` BEFORE DELETE ON `employee_master` FOR EACH ROW BEGIN
								INSERT INTO receiver
								SET event = 'Delete',
								event_time = NOW(),
								flag = 'Before',
								table_name = 'employee_master',
								table_data = CONCAT_WS(',',COALESCE(OLD.employee_id, 'null'), COALESCE(OLD.parent_entity, 'null'), COALESCE(OLD.first_name, 'null'), COALESCE(OLD.middle_name, 'null'),COALESCE(OLD.last_name, 'null'),
														COALESCE(OLD.primary_email_id, 'null'), COALESCE(OLD.secondary_email_id, 'null'), COALESCE(OLD.qualification, 'null'), COALESCE(OLD.designations, 'null'), COALESCE(OLD.dob, 'null'),
														COALESCE(OLD.doj, 'null'), COALESCE(OLD.insert_time, 'null'), COALESCE(OLD.modification_time, 'null'), COALESCE(OLD.creator_id, 'null'), COALESCE(OLD.modifier_id, 'null'),
														COALESCE(OLD.employee_code, 'null'), COALESCE(OLD.university_code, 'null'), COALESCE(OLD.employee_status, 'null'), COALESCE(OLD.category_code, 'null'), COALESCE(OLD.gender, 'null'),
														COALESCE(OLD.physical_handicapped, 'null'), COALESCE(OLD.net, 'null'), COALESCE(OLD.pg, 'null'), COALESCE(OLD.minority, 'null'), COALESCE(OLD.pen, 'null'),
														COALESCE(OLD.dofr, 'null'), COALESCE(OLD.minority_flag, 'null'));

								END $$

DELIMITER ;


--
-- Definition of trigger `update_student_aggregate`
--

DROP TRIGGER /*!50030 IF EXISTS */ `update_student_aggregate`;

DELIMITER $$

CREATE DEFINER = `root`@`localhost` TRIGGER `update_student_aggregate` BEFORE UPDATE ON `student_aggregate` FOR EACH ROW BEGIN
								INSERT INTO receiver
								SET event = 'Update',
								event_time = NOW(),
								flag = 'Before',
								table_name = 'student_aggregate',
								table_data = CONCAT_WS(',',COALESCE(OLD.university_code, 'null'), COALESCE(OLD.roll_number, 'null'), COALESCE(OLD.semester_start_date, 'null'), COALESCE(OLD.semester_end_date, 'null'),
														COALESCE(OLD.theory_weighted_percentage, 0.00), COALESCE(OLD.practical_weighted_percentage, 0.00), COALESCE(OLD.remarks, 'null'), COALESCE(OLD.earned_practical_credit, 0.00),
														COALESCE(OLD.earned_theory_credit, 0.00), COALESCE(OLD.program_course_key, 'null'), COALESCE(OLD.point_secured_theory_sgpa, 0.00), COALESCE(OLD.point_secured_practical_sgpa, 0.00),
														COALESCE(OLD.point_secured_theory_cgpa, 0.00), COALESCE(OLD.point_secured_practical_cgpa, 0.00), COALESCE(OLD.earned_theory_credit_cgpa, 0.00), COALESCE(OLD.earned_practical_credit_cgpa, 0.00),
														COALESCE(OLD.earned_theory_aud_credit, 0.00), COALESCE(OLD.earned_practical_aud_credit, 0.00), COALESCE(OLD.theory_sgpa, 0.00), COALESCE(OLD.practical_sgpa, 0.00), COALESCE(OLD.sgpa, 0.00),
														COALESCE(OLD.weighted_percentage, 0.00));

								INSERT INTO receiver
								SET event = 'Update',
								event_time = NOW(),
								flag = 'After',
								table_name = 'student_aggregate',
								table_data = CONCAT_WS(',',COALESCE(NEW.university_code, 'null'), COALESCE(NEW.roll_number, 'null'), COALESCE(NEW.semester_start_date, 'null'), COALESCE(NEW.semester_end_date, 'null'),
														COALESCE(NEW.theory_weighted_percentage, 0.00), COALESCE(NEW.practical_weighted_percentage, 0.00), COALESCE(NEW.remarks, 'null'), COALESCE(NEW.earned_practical_credit, 0.00),
														COALESCE(NEW.earned_theory_credit, 0.00), COALESCE(NEW.program_course_key, 'null'), COALESCE(NEW.point_secured_theory_sgpa, 0.00), COALESCE(NEW.point_secured_practical_sgpa, 0.00),
														COALESCE(NEW.point_secured_theory_cgpa, 0.00), COALESCE(NEW.point_secured_practical_cgpa, 0.00), COALESCE(NEW.earned_theory_credit_cgpa, 0.00), COALESCE(NEW.earned_practical_credit_cgpa, 0.00),
														COALESCE(NEW.earned_theory_aud_credit, 0.00), COALESCE(NEW.earned_practical_aud_credit, 0.00), COALESCE(NEW.theory_sgpa, 0.00), COALESCE(NEW.practical_sgpa, 0.00), COALESCE(NEW.sgpa, 0.00),
														COALESCE(NEW.weighted_percentage, 0.00));
								END $$

DELIMITER ;

--
-- Definition of trigger `delete_student_aggregate`
--

DROP TRIGGER /*!50030 IF EXISTS */ `delete_student_aggregate`;

DELIMITER $$

CREATE DEFINER = `root`@`localhost` TRIGGER `delete_student_aggregate` BEFORE DELETE ON `student_aggregate` FOR EACH ROW BEGIN
								INSERT INTO receiver
								SET event = 'Delete',
								event_time = NOW(),
								flag = 'Before',
								table_name = 'student_aggregate',
								table_data = CONCAT_WS(',',COALESCE(OLD.university_code, 'null'), COALESCE(OLD.roll_number, 'null'), COALESCE(OLD.semester_start_date, 'null'), COALESCE(OLD.semester_end_date, 'null'),
														COALESCE(OLD.theory_weighted_percentage, 0.00), COALESCE(OLD.practical_weighted_percentage, 0.00), COALESCE(OLD.remarks, 'null'), COALESCE(OLD.earned_practical_credit, 0.00),
														COALESCE(OLD.earned_theory_credit, 0.00), COALESCE(OLD.program_course_key, 'null'), COALESCE(OLD.point_secured_theory_sgpa, 0.00), COALESCE(OLD.point_secured_practical_sgpa, 0.00),
														COALESCE(OLD.point_secured_theory_cgpa, 0.00), COALESCE(OLD.point_secured_practical_cgpa, 0.00), COALESCE(OLD.earned_theory_credit_cgpa, 0.00), COALESCE(OLD.earned_practical_credit_cgpa, 0.00),
														COALESCE(OLD.earned_theory_aud_credit, 0.00), COALESCE(OLD.earned_practical_aud_credit, 0.00), COALESCE(OLD.theory_sgpa, 0.00), COALESCE(OLD.practical_sgpa, 0.00), COALESCE(OLD.sgpa, 0.00),
														COALESCE(OLD.weighted_percentage, 0.00));
								END $$

DELIMITER ;


--
-- Definition of trigger `update_student_course`
--

DROP TRIGGER /*!50030 IF EXISTS */ `update_student_course`;

DELIMITER $$

CREATE DEFINER = `root`@`localhost` TRIGGER `update_student_course` BEFORE UPDATE ON `student_course` FOR EACH ROW BEGIN
								INSERT INTO receiver
								SET event = 'Update',
								event_time = NOW(),
								flag = 'Before',
								table_name = 'student_course',
								table_data = CONCAT_WS(',',COALESCE(OLD.roll_number, 'null'), COALESCE(OLD.program_course_key, 'null'), COALESCE(OLD.semester_start_date, 'null'), COALESCE(OLD.semester_end_date, 'null'),COALESCE(OLD.course_code, 'null'),
														COALESCE(OLD.orginal_course_code, 'null'), COALESCE(OLD.course_status, 'null'), COALESCE(OLD.student_status, 'null'), COALESCE(OLD.insert_time, 'null'), COALESCE(OLD.modification_time, 'null'),
														COALESCE(OLD.creator_id, 'null'), COALESCE(OLD.modifier_id, 'null'), COALESCE(OLD.attempt_number, 0), COALESCE(OLD.course_group, 'null'));

								INSERT INTO receiver
								SET event='Update',
								event_time = NOW(),
								flag = 'After',
								table_name = 'student_course',
								table_data = CONCAT_WS(',',COALESCE(NEW.roll_number, 'null'), COALESCE(NEW.program_course_key, 'null'), COALESCE(NEW.semester_start_date, 'null'), COALESCE(NEW.semester_end_date, 'null'),COALESCE(NEW.course_code, 'null'),
														COALESCE(NEW.orginal_course_code, 'null'), COALESCE(NEW.course_status, 'null'), COALESCE(NEW.student_status, 'null'), COALESCE(NEW.insert_time, 'null'), COALESCE(NEW.modification_time, 'null'),
														COALESCE(NEW.creator_id, 'null'), COALESCE(NEW.modifier_id, 'null'), COALESCE(NEW.attempt_number, 0), COALESCE(NEW.course_group, 'null'));
								END $$

DELIMITER ;

--
-- Definition of trigger `delete_student_course`
--

DROP TRIGGER /*!50030 IF EXISTS */ `delete_student_course`;

DELIMITER $$

CREATE DEFINER = `root`@`localhost` TRIGGER `delete_student_course` BEFORE DELETE ON `student_course` FOR EACH ROW BEGIN
								INSERT INTO receiver
								SET event = 'Delete',
								event_time = NOW(),
								flag = 'Before',
								table_name = 'student_course',
								table_data = CONCAT_WS(',',COALESCE(OLD.roll_number, 'null'), COALESCE(OLD.program_course_key, 'null'), COALESCE(OLD.semester_start_date, 'null'), COALESCE(OLD.semester_end_date, 'null'),COALESCE(OLD.course_code, 'null'),
														COALESCE(OLD.orginal_course_code, 'null'), COALESCE(OLD.course_status, 'null'), COALESCE(OLD.student_status, 'null'), COALESCE(OLD.insert_time, 'null'), COALESCE(OLD.modification_time, 'null'),
														COALESCE(OLD.creator_id, 'null'), COALESCE(OLD.modifier_id, 'null'), COALESCE(OLD.attempt_number, 0), COALESCE(OLD.course_group, 'null'));
								END $$

DELIMITER ;


--
-- Definition of trigger `update_student_marks`
--

DROP TRIGGER /*!50030 IF EXISTS */ `update_student_marks`;

DELIMITER $$

CREATE DEFINER = `root`@`localhost` TRIGGER `update_student_marks` BEFORE UPDATE ON `student_marks` FOR EACH ROW BEGIN
								INSERT INTO receiver
								SET event = 'Update',
								event_time = NOW(),
								flag = 'Before',
								table_name = 'student_marks',
								table_data = CONCAT_WS(',',COALESCE(OLD.university_code, 'null'), COALESCE(OLD.roll_number, 'null'), COALESCE(OLD.program_course_key, 'null'), COALESCE(OLD.evaluation_id, 'null'),COALESCE(OLD.marks, 0.00),
														COALESCE(OLD.grades, 'null'), COALESCE(OLD.pass_fail, 'null'), COALESCE(OLD.status, 'null'), COALESCE(OLD.course_code, 'null'), COALESCE(OLD.semester_start_date, 'null'),
														COALESCE(OLD.semester_end_date, 'null'), COALESCE(OLD.insert_time, 'null'), COALESCE(OLD.modification_time, 'null'), COALESCE(OLD.creator_id, 'null'), COALESCE(OLD.modifier_id, 'null'),
														COALESCE(OLD.attempt_number, 0));
	
								INSERT INTO receiver
								SET event='Update',
								event_time = NOW(),
								flag = 'After',
								table_name = 'student_marks',
								table_data = CONCAT_WS(',',COALESCE(NEW.university_code, 'null'), COALESCE(NEW.roll_number, 'null'), COALESCE(NEW.program_course_key, 'null'), COALESCE(NEW.evaluation_id, 'null'),COALESCE(NEW.marks, 0.00),
														COALESCE(NEW.grades, 'null'), COALESCE(NEW.pass_fail, 'null'), COALESCE(NEW.status, 'null'), COALESCE(NEW.course_code, 'null'), COALESCE(NEW.semester_start_date, 'null'),
														COALESCE(NEW.semester_end_date, 'null'), COALESCE(NEW.insert_time, 'null'), COALESCE(NEW.modification_time, 'null'), COALESCE(NEW.creator_id, 'null'), COALESCE(NEW.modifier_id, 'null'),
														COALESCE(NEW.attempt_number, 0));
								END $$

DELIMITER ;

--
-- Definition of trigger `delete_student_marks`
--

DROP TRIGGER /*!50030 IF EXISTS */ `delete_student_marks`;

DELIMITER $$

CREATE DEFINER = `root`@`localhost` TRIGGER `delete_student_marks` BEFORE DELETE ON `student_marks` FOR EACH ROW BEGIN
								INSERT INTO receiver
								SET event = 'Delete',
								event_time = NOW(),
								flag = 'Before',
								table_name = 'student_marks',
								table_data = CONCAT_WS(',',COALESCE(OLD.university_code, 'null'), COALESCE(OLD.roll_number, 'null'), COALESCE(OLD.program_course_key, 'null'), COALESCE(OLD.evaluation_id, 'null'),COALESCE(OLD.marks, 0.00),
														COALESCE(OLD.grades, 'null'), COALESCE(OLD.pass_fail, 'null'), COALESCE(OLD.status, 'null'), COALESCE(OLD.course_code, 'null'), COALESCE(OLD.semester_start_date, 'null'),
														COALESCE(OLD.semester_end_date, 'null'), COALESCE(OLD.insert_time, 'null'), COALESCE(OLD.modification_time, 'null'), COALESCE(OLD.creator_id, 'null'), COALESCE(OLD.modifier_id, 'null'),
														COALESCE(OLD.attempt_number, 0));

								END $$

DELIMITER ;


--
-- Definition of trigger `update_student_marks_summary`
--

DROP TRIGGER /*!50030 IF EXISTS */ `update_student_marks_summary`;

DELIMITER $$

CREATE DEFINER = `root`@`localhost` TRIGGER `update_student_marks_summary` BEFORE UPDATE ON `student_marks_summary` FOR EACH ROW BEGIN
								INSERT INTO receiver
								SET event = 'Update',
								event_time = NOW(),
								flag = 'Before',
								table_name = 'student_marks_summary',
								table_data = CONCAT_WS(',',COALESCE(OLD.university_code, 'null'), COALESCE(OLD.roll_number, 'null'), COALESCE(OLD.program_course_key, 'null'), COALESCE(OLD.semester_start_date, 'null'),COALESCE(OLD.semester_end_date, 0.00),
														COALESCE(OLD.total_internal, 0), COALESCE(OLD.total_external, 0), COALESCE(OLD.total_marks, 0), COALESCE(OLD.course_code, 'null'), COALESCE(OLD.internal_grade, 'null'),
														COALESCE(OLD.external_grade, 'null'), COALESCE(OLD.final_grade_point, 0.00), COALESCE(OLD.insert_time, 'null'), COALESCE(OLD.modification_time, 'null'), COALESCE(OLD.creator_id, 'null'),
														COALESCE(OLD.modifier_id, 'null'), COALESCE(OLD.earned_credits, 0.00));
	
								INSERT INTO receiver
								SET event='Update',
								event_time = NOW(),
								flag = 'After',
								table_name = 'student_marks_summary',
								table_data = CONCAT_WS(',',COALESCE(NEW.university_code, 'null'), COALESCE(NEW.roll_number, 'null'), COALESCE(NEW.program_course_key, 'null'), COALESCE(NEW.semester_start_date, 'null'),COALESCE(NEW.semester_end_date, 0.00),
														COALESCE(NEW.total_internal, 0), COALESCE(NEW.total_external, 0), COALESCE(NEW.total_marks, 0), COALESCE(NEW.course_code, 'null'), COALESCE(NEW.internal_grade, 'null'),
														COALESCE(NEW.external_grade, 'null'), COALESCE(NEW.final_grade_point, 0.00), COALESCE(NEW.insert_time, 'null'), COALESCE(NEW.modification_time, 'null'), COALESCE(NEW.creator_id, 'null'),
														COALESCE(NEW.modifier_id, 'null'), COALESCE(NEW.earned_credits, 0.00));

								END $$

DELIMITER ;

--
-- Definition of trigger `delete_student_marks_summary`
--

DROP TRIGGER /*!50030 IF EXISTS */ `delete_student_marks_summary`;

DELIMITER $$

CREATE DEFINER = `root`@`localhost` TRIGGER `delete_student_marks_summary` BEFORE DELETE ON `student_marks_summary` FOR EACH ROW BEGIN
								INSERT INTO receiver
								SET event = 'Delete',
								event_time = NOW(),
								flag = 'Before',
								table_name = 'student_marks_summary',
								table_data = CONCAT_WS(',',COALESCE(OLD.university_code, 'null'), COALESCE(OLD.roll_number, 'null'), COALESCE(OLD.program_course_key, 'null'), COALESCE(OLD.semester_start_date, 'null'),COALESCE(OLD.semester_end_date, 0.00),
														COALESCE(OLD.total_internal, 0), COALESCE(OLD.total_external, 0), COALESCE(OLD.total_marks, 0), COALESCE(OLD.course_code, 'null'), COALESCE(OLD.internal_grade, 'null'),
														COALESCE(OLD.external_grade, 'null'), COALESCE(OLD.final_grade_point, 0.00), COALESCE(OLD.insert_time, 'null'), COALESCE(OLD.modification_time, 'null'), COALESCE(OLD.creator_id, 'null'),
														COALESCE(OLD.modifier_id, 'null'), COALESCE(OLD.earned_credits, 0.00));
								END $$

DELIMITER ;


--
-- Definition of trigger `update_student_master`
--

DROP TRIGGER /*!50030 IF EXISTS */ `update_student_master`;

DELIMITER $$

CREATE DEFINER = `root`@`localhost` TRIGGER `update_student_master` BEFORE UPDATE ON `student_master` FOR EACH ROW BEGIN
								INSERT INTO receiver
								SET event = 'Update',
								event_time = NOW(),
								flag = 'Before',
								table_name = 'student_master',
								table_data = CONCAT_WS(',',COALESCE(OLD.university_code, 'null'), COALESCE(OLD.student_id, 'null'), COALESCE(OLD.enrollment_number, 'null'), COALESCE(OLD.student_first_name, 'null'),COALESCE(OLD.student_middle_name, 'null'),
														COALESCE(OLD.student_last_name, 'null'), COALESCE(OLD.primary_email_id, 'null'), COALESCE(OLD.secondary_email_id, 'null'), COALESCE(OLD.date_of_birth, 'null'), COALESCE(OLD.category_code, 'null'),
														COALESCE(OLD.gender, 'null'), COALESCE(OLD.father_first_name, 'null'), COALESCE(OLD.father_middle_name, 'null'), COALESCE(OLD.father_last_name, 'null'), COALESCE(OLD.mother_first_name, 'null'),
														COALESCE(OLD.mother_middle_name, 'null'), COALESCE(OLD.mother_last_name, 'null'), COALESCE(OLD.registered_in_session, 'null'), COALESCE(OLD.status, 'null'), COALESCE(OLD.insert_time, 'null'),
														COALESCE(OLD.modification_time, 'null'), COALESCE(OLD.creator_id, 'null'), COALESCE(OLD.modifier_id, 'null'), COALESCE(OLD.parent_entity, 'null'), COALESCE(OLD.name_in_hindi, 'null'),
														COALESCE(OLD.father_name_in_hindi, 'null'), COALESCE(OLD.mother_name_in_hindi, 'null'), COALESCE(OLD.photo_path, 'null'), COALESCE(OLD.nationality, 'null'),
														COALESCE(OLD.religion, 'null'), COALESCE(OLD.guardian_name, 'null'), COALESCE(OLD.session_start_date, 'null'), COALESCE(OLD.session_end_date, 'null'));

								INSERT INTO receiver
								SET event='Update',
								event_time = NOW(),
								flag = 'After',
								table_name = 'student_master',
								table_data = CONCAT_WS(',',COALESCE(NEW.university_code, 'null'), COALESCE(NEW.student_id, 'null'), COALESCE(NEW.enrollment_number, 'null'), COALESCE(NEW.student_first_name, 'null'),COALESCE(NEW.student_middle_name, 'null'),
														COALESCE(NEW.student_last_name, 'null'), COALESCE(NEW.primary_email_id, 'null'), COALESCE(NEW.secondary_email_id, 'null'), COALESCE(NEW.date_of_birth, 'null'), COALESCE(NEW.category_code, 'null'),
														COALESCE(NEW.gender, 'null'), COALESCE(NEW.father_first_name, 'null'), COALESCE(NEW.father_middle_name, 'null'), COALESCE(NEW.father_last_name, 'null'), COALESCE(NEW.mother_first_name, 'null'),
														COALESCE(NEW.mother_middle_name, 'null'), COALESCE(NEW.mother_last_name, 'null'), COALESCE(NEW.registered_in_session, 'null'), COALESCE(NEW.status, 'null'), COALESCE(NEW.insert_time, 'null'),
														COALESCE(NEW.modification_time, 'null'), COALESCE(NEW.creator_id, 'null'), COALESCE(NEW.modifier_id, 'null'), COALESCE(NEW.parent_entity, 'null'), COALESCE(NEW.name_in_hindi, 'null'),
														COALESCE(NEW.father_name_in_hindi, 'null'), COALESCE(NEW.mother_name_in_hindi, 'null'), COALESCE(NEW.photo_path, 'null'), COALESCE(NEW.nationality, 'null'),
														COALESCE(NEW.religion, 'null'), COALESCE(NEW.guardian_name, 'null'), COALESCE(NEW.session_start_date, 'null'), COALESCE(NEW.session_end_date, 'null'));
								END $$

DELIMITER ;

--
-- Definition of trigger `delete_student_master`
--

DROP TRIGGER /*!50030 IF EXISTS */ `delete_student_master`;

DELIMITER $$

CREATE DEFINER = `root`@`localhost` TRIGGER `delete_student_master` BEFORE DELETE ON `student_master` FOR EACH ROW BEGIN
								INSERT INTO receiver
								SET event = 'Delete',
								event_time = NOW(),
								flag = 'Before',
								table_name = 'student_master',
								table_data = CONCAT_WS(',',COALESCE(OLD.university_code, 'null'), COALESCE(OLD.student_id, 'null'), COALESCE(OLD.enrollment_number, 'null'), COALESCE(OLD.student_first_name, 'null'),COALESCE(OLD.student_middle_name, 'null'),
														COALESCE(OLD.student_last_name, 'null'), COALESCE(OLD.primary_email_id, 'null'), COALESCE(OLD.secondary_email_id, 'null'), COALESCE(OLD.date_of_birth, 'null'), COALESCE(OLD.category_code, 'null'),
														COALESCE(OLD.gender, 'null'), COALESCE(OLD.father_first_name, 'null'), COALESCE(OLD.father_middle_name, 'null'), COALESCE(OLD.father_last_name, 'null'), COALESCE(OLD.mother_first_name, 'null'),
														COALESCE(OLD.mother_middle_name, 'null'), COALESCE(OLD.mother_last_name, 'null'), COALESCE(OLD.registered_in_session, 'null'), COALESCE(OLD.status, 'null'), COALESCE(OLD.insert_time, 'null'),
														COALESCE(OLD.modification_time, 'null'), COALESCE(OLD.creator_id, 'null'), COALESCE(OLD.modifier_id, 'null'), COALESCE(OLD.parent_entity, 'null'), COALESCE(OLD.name_in_hindi, 'null'),
														COALESCE(OLD.father_name_in_hindi, 'null'), COALESCE(OLD.mother_name_in_hindi, 'null'), COALESCE(OLD.photo_path, 'null'), COALESCE(OLD.nationality, 'null'),
														COALESCE(OLD.religion, 'null'), COALESCE(OLD.guardian_name, 'null'), COALESCE(OLD.session_start_date, 'null'), COALESCE(OLD.session_end_date, 'null'));
								END $$

DELIMITER ;


--
-- Definition of trigger `update_student_program`
--

DROP TRIGGER /*!50030 IF EXISTS */ `update_student_program`;

DELIMITER $$

CREATE DEFINER = `root`@`localhost` TRIGGER `update_student_program` BEFORE UPDATE ON `student_program` FOR EACH ROW BEGIN
								INSERT INTO receiver
								SET event = 'Update',
								event_time = NOW(),
								flag = 'Before',
								table_name = 'student_program',
								table_data = CONCAT_WS(',',COALESCE(OLD.cgpa, 0.00), COALESCE(OLD.enrollment_number, 'null'), COALESCE(OLD.roll_number, 'null'), COALESCE(OLD.register_date, 'null'),COALESCE(OLD.program_completion_date, 'null'),
														COALESCE(OLD.current_semester, 'null'), COALESCE(OLD.program_status, 'null'), COALESCE(OLD.insert_time, 'null'), COALESCE(OLD.modification_time, 'null'), COALESCE(OLD.creator_id, 'null'),
														COALESCE(OLD.modifier_id, 'null'), COALESCE(OLD.entity_id, 'null'), COALESCE(OLD.program_id, 'null'), COALESCE(OLD.branch_id, 'null'), COALESCE(OLD.specialization_id, 'null'),
														COALESCE(OLD.switch_number, 0), COALESCE(OLD.sequence_number, 0), COALESCE(OLD.in_semester, 'null'), COALESCE(OLD.out_semester, 'null'), COALESCE(OLD.mode_of_entry, 'null'),
														COALESCE(OLD.division, 'null'), COALESCE(OLD.switched_date, 'null'), COALESCE(OLD.theory_cgpa, 0.00), COALESCE(OLD.practical_cgpa, 0.00), COALESCE(OLD.theory_cwp, 0.00),
														COALESCE(OLD.practical_cwp, 0.00), COALESCE(OLD.cumulative_wp, 0.00), COALESCE(OLD.registered_from_session, 'null'), COALESCE(OLD.passed_from_session, 'null'),
														COALESCE(OLD.passed_to_session, 'null'), COALESCE(OLD.registered_to_session, 'null'), COALESCE(OLD.theory_division, 'null'), COALESCE(OLD.practical_division, 'null'));

								INSERT INTO receiver
								SET event='Update',
								event_time = NOW(),
								flag = 'After',
								table_name = 'student_program',
								table_data = CONCAT_WS(',',COALESCE(NEW.cgpa, 0.00), COALESCE(NEW.enrollment_number, 'null'), COALESCE(NEW.roll_number, 'null'), COALESCE(NEW.register_date, 'null'),COALESCE(NEW.program_completion_date, 'null'),
														COALESCE(NEW.current_semester, 'null'), COALESCE(NEW.program_status, 'null'), COALESCE(NEW.insert_time, 'null'), COALESCE(NEW.modification_time, 'null'), COALESCE(NEW.creator_id, 'null'),
														COALESCE(NEW.modifier_id, 'null'), COALESCE(NEW.entity_id, 'null'), COALESCE(NEW.program_id, 'null'), COALESCE(NEW.branch_id, 'null'), COALESCE(NEW.specialization_id, 'null'),
														COALESCE(NEW.switch_number, 0), COALESCE(NEW.sequence_number, 0), COALESCE(NEW.in_semester, 'null'), COALESCE(NEW.out_semester, 'null'), COALESCE(NEW.mode_of_entry, 'null'),
														COALESCE(NEW.division, 'null'), COALESCE(NEW.switched_date, 'null'), COALESCE(NEW.theory_cgpa, 0.00), COALESCE(NEW.practical_cgpa, 0.00), COALESCE(NEW.theory_cwp, 0.00),
														COALESCE(NEW.practical_cwp, 0.00), COALESCE(NEW.cumulative_wp, 0.00), COALESCE(NEW.registered_from_session, 'null'), COALESCE(NEW.passed_from_session, 'null'),
														COALESCE(NEW.passed_to_session, 'null'), COALESCE(NEW.registered_to_session, 'null'), COALESCE(NEW.theory_division, 'null'), COALESCE(NEW.practical_division, 'null'));
								END $$

DELIMITER ;

--
-- Definition of trigger `delete_student_program`
--

DROP TRIGGER /*!50030 IF EXISTS */ `delete_student_program`;

DELIMITER $$

CREATE DEFINER = `root`@`localhost` TRIGGER `delete_student_program` BEFORE DELETE ON `student_program` FOR EACH ROW BEGIN
								INSERT INTO receiver
								SET event = 'Delete',
								event_time = NOW(),
								flag = 'Before',
								table_name = 'student_program',
								table_data = CONCAT_WS(',',COALESCE(OLD.cgpa, 0.00), COALESCE(OLD.enrollment_number, 'null'), COALESCE(OLD.roll_number, 'null'), COALESCE(OLD.register_date, 'null'),COALESCE(OLD.program_completion_date, 'null'),
														COALESCE(OLD.current_semester, 'null'), COALESCE(OLD.program_status, 'null'), COALESCE(OLD.insert_time, 'null'), COALESCE(OLD.modification_time, 'null'), COALESCE(OLD.creator_id, 'null'),
														COALESCE(OLD.modifier_id, 'null'), COALESCE(OLD.entity_id, 'null'), COALESCE(OLD.program_id, 'null'), COALESCE(OLD.branch_id, 'null'), COALESCE(OLD.specialization_id, 'null'),
														COALESCE(OLD.switch_number, 0), COALESCE(OLD.sequence_number, 0), COALESCE(OLD.in_semester, 'null'), COALESCE(OLD.out_semester, 'null'), COALESCE(OLD.mode_of_entry, 'null'),
														COALESCE(OLD.division, 'null'), COALESCE(OLD.switched_date, 'null'), COALESCE(OLD.theory_cgpa, 0.00), COALESCE(OLD.practical_cgpa, 0.00), COALESCE(OLD.theory_cwp, 0.00),
														COALESCE(OLD.practical_cwp, 0.00), COALESCE(OLD.cumulative_wp, 0.00), COALESCE(OLD.registered_from_session, 'null'), COALESCE(OLD.passed_from_session, 'null'),
														COALESCE(OLD.passed_to_session, 'null'), COALESCE(OLD.registered_to_session, 'null'), COALESCE(OLD.theory_division, 'null'), COALESCE(OLD.practical_division, 'null'));
								END $$

DELIMITER ;


--
-- Definition of trigger `update_srsh_string`
--

DROP TRIGGER /*!50030 IF EXISTS */ `update_srsh_string`;

DELIMITER $$

CREATE DEFINER = `root`@`localhost` TRIGGER `update_srsh_string` BEFORE UPDATE ON `student_registration_semester_header` FOR EACH ROW BEGIN
								INSERT INTO receiver
								SET event = 'Update',
								event_time = NOW(),
								flag = 'Before',
								table_name = 'student_registration_semester_header',
								table_data = CONCAT_WS(',',COALESCE(OLD.register_Date, 'null'), COALESCE(OLD.number_of_remedials, 0), COALESCE(OLD.status, 'null'), COALESCE(OLD.insert_time, 'null'),COALESCE(OLD.modification_time, 'null'),
														COALESCE(OLD.creator_id, 'null'), COALESCE(OLD.modifier_id, 'null'), COALESCE(OLD.roll_number, 'null'), COALESCE(OLD.session_start_date, 'null'), COALESCE(OLD.session_end_date, 'null'),
														COALESCE(OLD.attempt_number, 0), COALESCE(OLD.total_credit_earned, 0), COALESCE(OLD.sgpa, 0.00), COALESCE(OLD.weighted_percentage, 0.00), COALESCE(OLD.student_process_status, 'null'),
														COALESCE(OLD.register_due_date, 'null'), COALESCE(OLD.entity_id, 'null'), COALESCE(OLD.program_course_key, 'null'), COALESCE(OLD.registered_credit, 0.00), COALESCE(OLD.registered_theory_credit_excluding_audit, 0.00),
														COALESCE(OLD.registered_practical_credit_excluding_audit, 0.00), COALESCE(OLD.registration_credit_excluding_audit, 0.00));

								INSERT INTO receiver
								SET event='Update',
								event_time = NOW(),
								flag = 'After',
								table_name = 'student_registration_semester_header',
								table_data = CONCAT_WS(',',COALESCE(NEW.register_Date, 'null'), COALESCE(NEW.number_of_remedials, 0), COALESCE(NEW.status, 'null'), COALESCE(NEW.insert_time, 'null'),COALESCE(NEW.modification_time, 'null'),
														COALESCE(NEW.creator_id, 'null'), COALESCE(NEW.modifier_id, 'null'), COALESCE(NEW.roll_number, 'null'), COALESCE(NEW.session_start_date, 'null'), COALESCE(NEW.session_end_date, 'null'),
														COALESCE(NEW.attempt_number, 0), COALESCE(NEW.total_credit_earned, 0), COALESCE(NEW.sgpa, 0.00), COALESCE(NEW.weighted_percentage, 0.00), COALESCE(NEW.student_process_status, 'null'),
														COALESCE(NEW.register_due_date, 'null'), COALESCE(NEW.entity_id, 'null'), COALESCE(NEW.program_course_key, 'null'), COALESCE(NEW.registered_credit, 0.00), COALESCE(NEW.registered_theory_credit_excluding_audit, 0.00),
														COALESCE(NEW.registered_practical_credit_excluding_audit, 0.00), COALESCE(NEW.registration_credit_excluding_audit, 0.00));
								END $$

DELIMITER ;

--
-- Definition of trigger `delete_srsh`
--

DROP TRIGGER /*!50030 IF EXISTS */ `delete_srsh`;

DELIMITER $$

CREATE DEFINER = `root`@`localhost` TRIGGER `delete_srsh` BEFORE DELETE ON `student_registration_semester_header` FOR EACH ROW BEGIN
								INSERT INTO receiver
								SET event = 'Delete',
								event_time = NOW(),
								flag = 'Before',
								table_name = 'student_registration_semester_header',
								table_data = CONCAT_WS(',',COALESCE(OLD.register_Date, 'null'), COALESCE(OLD.number_of_remedials, 0), COALESCE(OLD.status, 'null'), COALESCE(OLD.insert_time, 'null'),COALESCE(OLD.modification_time, 'null'),
														COALESCE(OLD.creator_id, 'null'), COALESCE(OLD.modifier_id, 'null'), COALESCE(OLD.roll_number, 'null'), COALESCE(OLD.session_start_date, 'null'), COALESCE(OLD.session_end_date, 'null'),
														COALESCE(OLD.attempt_number, 0), COALESCE(OLD.total_credit_earned, 0), COALESCE(OLD.sgpa, 0.00), COALESCE(OLD.weighted_percentage, 0.00), COALESCE(OLD.student_process_status, 'null'),
														COALESCE(OLD.register_due_date, 'null'), COALESCE(OLD.entity_id, 'null'), COALESCE(OLD.program_course_key, 'null'), COALESCE(OLD.registered_credit, 0.00), COALESCE(OLD.registered_theory_credit_excluding_audit, 0.00),
														COALESCE(OLD.registered_practical_credit_excluding_audit, 0.00), COALESCE(OLD.registration_credit_excluding_audit, 0.00));
								END $$

DELIMITER ;



/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
