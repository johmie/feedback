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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
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
  `type` enum('SINGLE_CHOICE','MULTIPLE_CHOICE','MATRIX') NOT NULL,
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
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `remote_address` varchar(39) NOT NULL,
  `version` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FOREIGN_RESULT_ANSWER` (`answer_id`),
  CONSTRAINT `FOREIGN_RESULT_ANSWER` FOREIGN KEY (`answer_id`) REFERENCES `Answer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
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
INSERT INTO `Answer` VALUES (1,'CHOICE','männlich','ein Mann','male',0,1,0),(2,'CHOICE','weiblich','eine Frau','female',1,1,0),(3,'CHOICE','Sport','Sport','Sport',1,2,0),(4,'CHOICE','Kunst','Kunst','Kunst',2,2,0),(5,'CHOICE','Andere Kulturen','Andere Kulturen','Andere Kulturen',3,2,0),(6,'CHOICE','Musik','Musik','Musik',4,2,0),(7,'CHOICE','Rhetorik','Rhetorik','Rhetorik',5,2,0),(8,'CHOICE','sarkastisch','sarkastisch','sarkastisch',1,3,0),(9,'CHOICE','sparsam','sparsam','sparsam',2,3,0),(10,'CHOICE','introvertiert','introvertiert','introvertiert',3,3,0),(11,'CHOICE','extrovertiert','extrovertiert','extrovertiert',4,3,0),(12,'CHOICE','pessimistisch','pessimistisch','pessimistisch',5,3,0),(13,'CHOICE','optimistisch','optimistisch','optimistisch',6,3,0),(14,'CHOICE','idealistisch','idealistisch','idealistisch',7,3,0),(15,'FREE_TEXT','Freitext #1','Etwas anderers','???',8,3,0),(16,'FREE_TEXT','Freitext #2','Etwas anderers','???',9,3,0),(17,'FREE_TEXT','Freitext #3','Etwas anderers','???',10,3,0),(18,'CHOICE','Google','Google','Google',1,4,0),(19,'CHOICE','GitHub','GitHub','GitHub',2,4,0),(20,'CHOICE','E-Mail','E-Mail','E-Mail',3,4,0),(21,'CHOICE','miemietz.de','miemietz.de','miemietz.de',4,4,0),(22,'FREE_TEXT','Freitext','Etwas anderes','???',5,4,0);
/*!40000 ALTER TABLE `Answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Page`
--

LOCK TABLES `Page` WRITE;
/*!40000 ALTER TABLE `Page` DISABLE KEYS */;
INSERT INTO `Page` VALUES (1,'ASK','Page #1','Hallo und willkommen',1,1,0),(2,'ASK','Page #2','Jetzt mal ehrlich!',2,1,0),(3,'ASK','Page #3','Sehr gut',3,1,0),(4,'END','Page #4','1000 Dank!',4,1,0);
/*!40000 ALTER TABLE `Page` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Question`
--

LOCK TABLES `Question` WRITE;
/*!40000 ALTER TABLE `Question` DISABLE KEYS */;
INSERT INTO `Question` VALUES (1,'SINGLE_CHOICE','Geschlecht','Du bist ...',1,1,0),(2,'MULTIPLE_CHOICE','Interessen','Was interessiert dich?',0,2,0),(3,'MULTIPLE_CHOICE','Charaktereigenschaften','Welche Eigenschaften treffen auf dich zu?',1,3,0),(4,'SINGLE_CHOICE','Herkunft','Wie bist du hier gelandet?',2,3,0);
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
INSERT INTO `Survey` VALUES (1,'Internal name of my survey','Title of my survey',0);
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