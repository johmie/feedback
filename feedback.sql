CREATE DATABASE IF NOT EXISTS feedback;
USE feedback;

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
-- Table structure for table `Answer`
--

DROP TABLE IF EXISTS `Answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Answer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `value_type` enum('CHOICE','FREE_TEXT') NOT NULL,
  `name` varchar(50) NOT NULL,
  `title` varchar(150) NOT NULL,
  `value` varchar(50) DEFAULT NULL,
  `position` int(11) NOT NULL,
  `question_id` bigint(20) NOT NULL,
  `version` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FOREIGN_ANSWER_QUESTION` (`question_id`),
  KEY `INDEX_POSITION` (`position`),
  CONSTRAINT `FOREIGN_ANSWER_QUESTION` FOREIGN KEY (`question_id`) REFERENCES `Question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `AnswerRow`
--

DROP TABLE IF EXISTS `AnswerRow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AnswerRow` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `title` varchar(150) NOT NULL,
  `value` varchar(50) DEFAULT NULL,
  `position` int(11) NOT NULL,
  `question_id` bigint(20) NOT NULL,
  `version` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FOREIGN_ANSWER_ROW_QUESTION` (`question_id`),
  KEY `INDEX_POSITION` (`position`),
  CONSTRAINT `FOREIGN_ANSWER_ROW_QUESTION` FOREIGN KEY (`question_id`) REFERENCES `Question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Page`
--

DROP TABLE IF EXISTS `Page`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Page` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` enum('ASK','END') NOT NULL,
  `name` varchar(50) NOT NULL,
  `title` varchar(150) NOT NULL,
  `position` int(11) NOT NULL,
  `survey_id` bigint(20) NOT NULL,
  `version` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FOREIGN_PAGE_SURVEY` (`survey_id`),
  KEY `INDEX_POSITION` (`position`),
  CONSTRAINT `FOREIGN_PAGE_SURVEY` FOREIGN KEY (`survey_id`) REFERENCES `Survey` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Question`
--

DROP TABLE IF EXISTS `Question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Question` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` enum('SINGLE_CHOICE','MULTIPLE_CHOICE','MATRIX_SINGLE_CHOICE','MATRIX_MULTIPLE_CHOICE') NOT NULL,
  `name` varchar(50) NOT NULL,
  `title` varchar(150) NOT NULL,
  `position` int(11) NOT NULL,
  `page_id` bigint(20) NOT NULL,
  `version` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FOREIGN_QUESTION_PAGE` (`page_id`),
  KEY `INDEX_POSITION` (`position`),
  CONSTRAINT `FOREIGN_QUESTION_PAGE` FOREIGN KEY (`page_id`) REFERENCES `Page` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Result`
--

DROP TABLE IF EXISTS `Result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Result` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `participation_identifier` varchar(32) NOT NULL,
  `free_text` varchar(255) DEFAULT NULL,
  `answer_id` bigint(20) NOT NULL,
  `answer_row_id` bigint(20) DEFAULT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `remote_address` varchar(39) NOT NULL,
  `version` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FOREIGN_RESULT_ANSWER` (`answer_id`),
  KEY `FOREIGN_RESULT_ANSWER_ROW` (`answer_row_id`),
  CONSTRAINT `FOREIGN_RESULT_ANSWER` FOREIGN KEY (`answer_id`) REFERENCES `Answer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FOREIGN_RESULT_ANSWER_ROW` FOREIGN KEY (`answer_row_id`) REFERENCES `AnswerRow` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Survey`
--

DROP TABLE IF EXISTS `Survey`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Survey` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `title` varchar(150) NOT NULL,
  `version` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

--
-- Dumping data for table `Answer`
--

LOCK TABLES `Answer` WRITE;
/*!40000 ALTER TABLE `Answer` DISABLE KEYS */;
INSERT INTO `Answer` VALUES
(1,'CHOICE','Full-time remote','Full-time remote','remote',1,1,0),
(2,'CHOICE','Full-time in-office','Full-time in-office','in-office',2,1,0),
(3,'CHOICE','Hybrid (some days home, some days office)','Hybrid (some days home, some days office)','Hybrid',3,1,0),
(4,'CHOICE','Occasionally (less than once a week)','Occasionally (less than once a week)','Occasionally',4,1,0),
(5,'FREE_TEXT','Free text','Free text','Free text',5,1,0),
(6,'CHOICE','Slack / Microsoft Teams','Slack / Microsoft Teams','Slack / Microsoft Teams',1,2,0),
(7,'CHOICE','Zoom / Google Meet','Zoom / Google Meet','Zoom / Google Meet',2,2,0),
(8,'CHOICE','Trello / Asana / Jira','Trello / Asana / Jira','Trello / Asana / Jira',3,2,0),
(9,'CHOICE','Google Drive / Dropbox','Google Drive / Dropbox','Google Drive / Dropbox',4,2,0),
(10,'CHOICE','Email','Email','Email',5,2,0),
(11,'FREE_TEXT','Free text #1','Free text #1','Free text #1',6,2,0),
(12,'FREE_TEXT','Free text #2','Free text #2','Free text #2',7,2,0),
(13,'CHOICE','Very Dissatisfied','Very Dissatisfied','Very Dissatisfied',1,3,0),
(14,'CHOICE','Dissatisfied','Dissatisfied','Dissatisfied',2,3,0),
(15,'CHOICE','Neutral','Neutral','Neutral',3,3,0),
(16,'CHOICE','Satisfied','Satisfied','Satisfied',4,3,0),
(17,'CHOICE','Very Satisfied','Very Satisfied','Very Satisfied',5,3,0),
(18,'CHOICE','Better Focus','Better Focus','Better Focus',1,4,0),
(19,'CHOICE','Social Interaction','Social Interaction','Social Interaction',2,4,0),
(20,'CHOICE','Cost Savings','Cost Savings','Cost Savings',3,4,0),
(21,'CHOICE','Better Equipment','Better Equipment','Better Equipment',4,4,0),
(22,'CHOICE','Full-time remote','Full-time remote','remote',1,5,0),
(23,'CHOICE','Full-time in-office','Full-time in-office','in-office',2,5,0),
(24,'CHOICE','Hybrid (some days home, some days office)','Hybrid (some days home, some days office)','Hybrid',3,5,0),
(25,'CHOICE','Occasionally (less than once a week)','Occasionally (less than once a week)','Occasionally',4,5,0),
(26,'FREE_TEXT','Free text','Free text','Free text',5,5,0),
(27,'CHOICE','Slack / Microsoft Teams','Slack / Microsoft Teams','Slack / Microsoft Teams',1,6,0),
(28,'CHOICE','Zoom / Google Meet','Zoom / Google Meet','Zoom / Google Meet',2,6,0),
(29,'CHOICE','Trello / Asana / Jira','Trello / Asana / Jira','Trello / Asana / Jira',3,6,0),
(30,'CHOICE','Google Drive / Dropbox','Google Drive / Dropbox','Google Drive / Dropbox',4,6,0),
(31,'CHOICE','Email','Email','Email',5,6,0),
(32,'FREE_TEXT','Free text #1','Free text #1','Free text #1',6,6,0),
(33,'FREE_TEXT','Free text #2','Free text #2','Free text #2',7,6,0),
(34,'CHOICE','Very Dissatisfied','Very Dissatisfied','Very Dissatisfied',1,7,0),
(35,'CHOICE','Dissatisfied','Dissatisfied','Dissatisfied',2,7,0),
(36,'CHOICE','Neutral','Neutral','Neutral',3,7,0),
(37,'CHOICE','Satisfied','Satisfied','Satisfied',4,7,0),
(38,'CHOICE','Very Satisfied','Very Satisfied','Very Satisfied',5,7,0),
(39,'CHOICE','Better Focus','Better Focus','Better Focus',1,8,0),
(40,'CHOICE','Social Interaction','Social Interaction','Social Interaction',2,8,0),
(41,'CHOICE','Cost Savings','Cost Savings','Cost Savings',3,8,0),
(42,'CHOICE','Better Equipment','Better Equipment','Better Equipment',4,8,0);
/*!40000 ALTER TABLE `Answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `AnswerRow`
--

LOCK TABLES `AnswerRow` WRITE;
/*!40000 ALTER TABLE `AnswerRow` DISABLE KEYS */;
INSERT INTO `AnswerRow` VALUES
(1,'Internet Speed','Internet Speed','Internet Speed',1,3,0),
(2,'Ergonomic Setup','Ergonomic Setup','Ergonomic Setup',2,3,0),
(3,'Noise Level','Noise Level','Noise Level',3,3,0),
(4,'Home Office','Home Office','Home Office',1,4,0),
(5,'Traditional Office','Traditional Office','Traditional Office',2,4,0),
(6,'Coworking Space','Coworking Space','Coworking Space',3,4,0),
(7,'Internet Speed','Internet Speed','Internet Speed',1,7,0),
(8,'Ergonomic Setup','Ergonomic Setup','Ergonomic Setup',2,7,0),
(9,'Noise Level','Noise Level','Noise Level',3,7,0),
(10,'Home Office','Home Office','Home Office',1,8,0),
(11,'Traditional Office','Traditional Office','Traditional Office',2,8,0),
(12,'Coworking Space','Coworking Space','Coworking Space',3,8,0);
/*!40000 ALTER TABLE `AnswerRow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Page`
--

LOCK TABLES `Page` WRITE;
/*!40000 ALTER TABLE `Page` DISABLE KEYS */;
INSERT INTO `Page` VALUES
(1,'ASK','Page #1','Single Choice Question',1,1,0),
(2,'ASK','Page #2','Multiple Choice Question',2,1,0),
(3,'ASK','Page #3','Single Choice Matrix',3,1,0),
(4,'ASK','Page #4','Multiple Choice Matrix',4,1,0),
(5,'END','Page #5','Thank you',5,1,0),
(6,'ASK','Page #1','All questions on one page',1,2,0),
(7,'END','Page #2','Thank you',2,2,0);
/*!40000 ALTER TABLE `Page` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Question`
--

LOCK TABLES `Question` WRITE;
/*!40000 ALTER TABLE `Question` DISABLE KEYS */;
INSERT INTO `Question` VALUES
(1,'SINGLE_CHOICE','Work from home','How often do you currently work from home?',1,1,0),
(2,'MULTIPLE_CHOICE','Collaboration tools','Which of the following tools do you use for daily collaboration?',1,2,0),
(3,'MATRIX_SINGLE_CHOICE','Workspace satisfaction','Please rate your satisfaction with the following aspects of your current workspace:',1,3,0),
(4,'MATRIX_MULTIPLE_CHOICE','Benefits association','Which benefits do you associate with the following work environments?',1,4,0),
(5,'SINGLE_CHOICE','Work from home','How often do you currently work from home?',1,6,0),
(6,'MULTIPLE_CHOICE','Collaboration tools','Which of the following tools do you use for daily collaboration?',2,6,0),
(7,'MATRIX_SINGLE_CHOICE','Workspace satisfaction','Please rate your satisfaction with the following aspects of your current workspace:',3,6,0),
(8,'MATRIX_MULTIPLE_CHOICE','Benefits association','Which benefits do you associate with the following work environments?',4,6,0);
/*!40000 ALTER TABLE `Question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Result`
--

LOCK TABLES `Result` WRITE;
/*!40000 ALTER TABLE `Result` DISABLE KEYS */;
/*!40000 ALTER TABLE `Result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Survey`
--

LOCK TABLES `Survey` WRITE;
/*!40000 ALTER TABLE `Survey` DISABLE KEYS */;
INSERT INTO `Survey` VALUES
(1,'Remote work','Remote Work Trends',0),
(2,'Remote work (one page)','Remote Work Trends',0);
/*!40000 ALTER TABLE `Survey` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;