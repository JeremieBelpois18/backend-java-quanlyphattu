-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: qlphattu
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `phattudaotrang`
--

DROP TABLE IF EXISTS `phattudaotrang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phattudaotrang` (
  `id` int NOT NULL AUTO_INCREMENT,
  `dathamgia` int DEFAULT NULL,
  `lydokhongthamgia` varchar(255) DEFAULT NULL,
  `daotrangid` int DEFAULT NULL,
  `phattuid` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5pyxf89jmd2oq00dnm3vr1tt6` (`daotrangid`),
  KEY `FK4qlm2ra3jc9hih1qnj33tcybl` (`phattuid`),
  CONSTRAINT `FK4qlm2ra3jc9hih1qnj33tcybl` FOREIGN KEY (`phattuid`) REFERENCES `phattu` (`id`),
  CONSTRAINT `FK5pyxf89jmd2oq00dnm3vr1tt6` FOREIGN KEY (`daotrangid`) REFERENCES `daotrang` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phattudaotrang`
--

LOCK TABLES `phattudaotrang` WRITE;
/*!40000 ALTER TABLE `phattudaotrang` DISABLE KEYS */;
INSERT INTO `phattudaotrang` VALUES (1,1,'0',1,3),(2,1,'0',1,4),(3,1,'0',1,4),(4,1,'0',1,4);
/*!40000 ALTER TABLE `phattudaotrang` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-08 15:38:56
