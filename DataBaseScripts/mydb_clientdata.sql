-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mydb
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Table structure for table `clientdata`
--

DROP TABLE IF EXISTS `clientdata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clientdata` (
  `idClientData` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `idNumber` varchar(45) NOT NULL,
  `pesel` varchar(14) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `place` varchar(45) NOT NULL,
  `houseNr` int(11) NOT NULL,
  `flatNr` int(11) DEFAULT NULL,
  `street` varchar(45) NOT NULL,
  PRIMARY KEY (`idClientData`),
  KEY `idUser_idx` (`userId`),
  CONSTRAINT `idUser` FOREIGN KEY (`userId`) REFERENCES `user` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientdata`
--

LOCK TABLES `clientdata` WRITE;
/*!40000 ALTER TABLE `clientdata` DISABLE KEYS */;
INSERT INTO `clientdata` VALUES (1,22,'qez 45454','12345678901','123456789','sdfsdf',12,12,'sdfsdf'),(2,22,'YTR 16843','87061612165','773834911','Kielce',12,3,'Młoda'),(3,22,'QYM 16796','97641128566','665348887','Kielce',23,2,'Miła'),(4,22,'QEZ 56286','86021287596','772415324','Kielce',23,6,'Miła'),(5,22,'QEZ 56286','86021287596','772415324','Kielce',23,6,'Miła'),(6,22,'QEZ 56286','86021287596','772415324','Kielce',23,6,'Miła'),(7,22,'YTM 26553','94120311253','548667112','Kielce',44,9,'Miła'),(8,22,'YUM 38856','94121143775','661221187','Kielce',23,8,'Miła');
/*!40000 ALTER TABLE `clientdata` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-14 20:44:07
