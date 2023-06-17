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
-- Table structure for table `phattu`
--

DROP TABLE IF EXISTS `phattu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phattu` (
  `id` int NOT NULL AUTO_INCREMENT,
  `anhchup` varchar(255) DEFAULT NULL,
  `dahoantuc` int DEFAULT NULL,
  `gioitinh` int DEFAULT NULL,
  `ho` varchar(255) DEFAULT NULL,
  `matkhau` varchar(255) DEFAULT NULL,
  `ngaycapnhap` date DEFAULT NULL,
  `ngayhoantuc` date DEFAULT NULL,
  `ngaysinh` date DEFAULT NULL,
  `ngayxuatgia` date DEFAULT NULL,
  `phapdanh` varchar(255) DEFAULT NULL,
  `sodienthoai` varchar(255) DEFAULT NULL,
  `ten` varchar(255) DEFAULT NULL,
  `tendem` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `chuaid` int DEFAULT NULL,
  `kieuthanhvienid` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6a0dp6ir45m7i7fevs72jjxtt` (`chuaid`),
  KEY `FKbytdp6til7mvjj9vd2ugrrp9c` (`kieuthanhvienid`),
  CONSTRAINT `FK6a0dp6ir45m7i7fevs72jjxtt` FOREIGN KEY (`chuaid`) REFERENCES `chua` (`id`),
  CONSTRAINT `FKbytdp6til7mvjj9vd2ugrrp9c` FOREIGN KEY (`kieuthanhvienid`) REFERENCES `kieuthanhvien` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phattu`
--

LOCK TABLES `phattu` WRITE;
/*!40000 ALTER TABLE `phattu` DISABLE KEYS */;
INSERT INTO `phattu` VALUES (3,'anh/',0,0,'tran','anhdadoi@1','2023-06-06','2023-06-06','1999-01-01','2023-06-06','Thich Do tri','12345','a','b','emil@1',1,1),(4,'anh/',0,0,'tran','email@1','2023-06-17','2023-06-06','1999-01-01','2023-06-06','Thich Do tri','123456','a','b','email@2',1,2),(5,'anh/',1,1,'tran','email@1','2023-06-17','2023-06-06','1999-01-01','2023-06-06','Thich Do tri','1234567','a','b','emil@3',1,2),(6,'anh/',1,0,'tran','email@1','2023-06-17','2023-06-06','1999-01-01','2023-06-06','Thich Do tri','12345678','a','b','emila@4',1,2),(7,'anh/',1,1,'tran','email@1','2023-06-17','2023-06-06','1999-01-01','2023-06-06','Thich Do tri','123456789','a','b','email@5',1,2),(8,'anh/',1,0,'tran','email@1','2023-06-17','2023-06-06','1999-01-01','2023-06-06','Thich Do tri','12345789','b','b','adf@1',1,2),(9,'anh/',0,0,'tran','email@1','2023-06-17','2023-06-06','1999-01-01','2023-06-06','Thich Do tri','123789','b','b','dsf@2',1,2),(10,'anh/',1,1,'tran','email@1','2023-06-17','2023-06-06','1999-01-01','2023-06-06','Thich Do tri','1235789','b','b','adf@3',1,2),(11,'anh/',1,1,'tran','email@1','2023-06-17','2023-06-06','1999-01-01','2023-06-06','Thich Do tri','1245789','b','b','ádsa@1',1,2),(12,'anh/',1,0,'tran','email@1','2023-06-17','2023-06-06','1999-01-01','2023-06-06','Thich Do tri','1245789','b','b','ewewe@3',1,2),(13,'anh/',0,1,'tran','email@1','2023-06-17','2023-06-06','1999-01-01','2023-06-06','Thich Do tri','1245789','b','b','dđff@3',1,2),(14,'anh/',0,0,'tran','email@1','2023-06-17','2023-06-06','1999-01-01','2023-06-06','Thich Do tri','1245789','b','b','dfcd@5',1,2),(15,'anh/',1,0,'tran','email@1','2023-06-17','2023-06-06','1999-01-01','2023-06-06','Thich Do tri','1245789','b','b','fdfd@54',1,2),(16,'anh/',0,1,'tran','email@1','2023-06-17','2023-06-06','1999-01-01','2023-06-06','Thich Do tri','121145789','b','b','dfdf%4',1,2),(17,'anh/',0,0,'tran','email@1','2023-06-17','2023-06-06','1999-01-01','2023-06-06','Thich Do tri','1219','b','b','emil@61a',1,2);
/*!40000 ALTER TABLE `phattu` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-18  0:16:37
