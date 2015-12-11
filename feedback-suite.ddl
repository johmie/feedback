-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: 192.168.99.100    Database: feedback
-- ------------------------------------------------------
-- Server version	5.5.35-1ubuntu1

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-11 14:09:37
