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
-- Definition of trigger `update_course_name`
--

DROP TRIGGER /*!50030 IF EXISTS */ `update_course_name`;

DELIMITER $$

CREATE DEFINER = `root`@`localhost` TRIGGER `update_course_name` BEFORE UPDATE ON `course_master` FOR EACH ROW BEGIN
      DECLARE seq_number INT;

        IF (OLD.course_name!=NEW.course_name) AND (OLD.since_session!=NEW.since_session)
        THEN
        SET seq_number=(SELECT COALESCE(MAX(sequence_number),0) INTO seq_number
        FROM course_code_history
        WHERE course_code = NEW.course_code);

        IF seq_number=0
        THEN
        INSERT INTO course_code_history
        SET
        course_code = OLD.course_code,
        course_name = OLD.course_name,
        owner_entity = OLD.owner_entity,
        owner_program = OLD.owner_program,
        owner_branch = OLD.owner_branch,
        owner_specialization = OLD.owner_specialization,
        since_session = OLD.since_session,
        creator_id = OLD.creator_id,
        insert_time = OLD.insert_time,
        sequence_number = seq_number+1;

        SET seq_number = (SELECT COALESCE(MAX(sequence_number),0) INTO seq_number
        FROM course_code_history
        WHERE course_code = NEW.course_code);
        END IF;

        INSERT INTO course_code_history
        SET
        course_code = NEW.course_code,
        course_name = NEW.course_name,
        owner_entity = NEW.owner_entity,
        owner_program = NEW.owner_program,
        owner_branch = NEW.owner_branch,
        owner_specialization = NEW.owner_specialization,
        since_session = NEW.since_session,
        creator_id = NEW.creator_id,
        insert_time = NOW(),
        sequence_number = seq_number+1;
        END IF;
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
