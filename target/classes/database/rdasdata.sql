-- MySQL dump 10.13  Distrib 5.7.27, for Win64 (x86_64)
--
-- Host: localhost    Database: rdasdata202106
-- ------------------------------------------------------
-- Server version	5.7.27
create database rdasdata202106;
use rdasdata202106;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=40@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `equipmentdata`
--

DROP TABLE IF EXISTS `equipmentdata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipmentdata` (
  `CollectTime` datetime NOT NULL,
  `Equipment_ID` varchar(50) NOT NULL,
  `UploadTime` datetime NOT NULL,
  `Data` longtext NOT NULL,
  `STR` longtext,
  KEY `IX_EquipmentData` (`CollectTime`),
  KEY `IX_EquipmentData_1` (`Equipment_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipmentdata`
--

LOCK TABLES `equipmentdata` WRITE;
/*!40000 ALTER TABLE `equipmentdata` DISABLE KEYS */;
/*!40000 ALTER TABLE `equipmentdata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipmentdayaccumulatedata`
--

DROP TABLE IF EXISTS `equipmentdayaccumulatedata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipmentdayaccumulatedata` (
  `CollectTime` datetime NOT NULL,
  `Equipment_ID` varchar(50) NOT NULL,
  `DataType_ID` int(11) NOT NULL,
  `UploadTime` datetime NOT NULL,
  `Value` double NOT NULL,
  `STR` longtext,
  KEY `IX_EquipmentDayAccumulateData` (`CollectTime`),
  KEY `IX_EquipmentDayAccumulateData_1` (`Equipment_ID`),
  KEY `IX_EquipmentDayAccumulateData_2` (`DataType_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipmentdayaccumulatedata`
--

LOCK TABLES `equipmentdayaccumulatedata` WRITE;
/*!40000 ALTER TABLE `equipmentdayaccumulatedata` DISABLE KEYS */;
/*!40000 ALTER TABLE `equipmentdayaccumulatedata` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-08  9:32:30
