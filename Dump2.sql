-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: shop2
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `basket`
--

DROP TABLE IF EXISTS `basket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `basket` (
  `id` int(11) NOT NULL,
  `userConfirmStatus` varchar(255) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `adminConfirmStatus` varchar(255) DEFAULT NULL,
  `cost` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfp7yinn3dh4sy1ia364xp3d9g` (`user_id`),
  CONSTRAINT `FKfp7yinn3dh4sy1ia364xp3d9g` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `basket`
--

LOCK TABLES `basket` WRITE;
/*!40000 ALTER TABLE `basket` DISABLE KEYS */;
INSERT INTO `basket` VALUES (247,'1',246,'1','140'),(248,'0',246,NULL,'290');
/*!40000 ALTER TABLE `basket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `basket_product_link`
--

DROP TABLE IF EXISTS `basket_product_link`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `basket_product_link` (
  `basketId` int(11) NOT NULL,
  `productId` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`basketId`,`productId`),
  KEY `FKlp62k222192flpprpxhm2r8pv` (`productId`),
  CONSTRAINT `FKhqq5c73pullf1dvgrlu3v440q` FOREIGN KEY (`basketId`) REFERENCES `basket` (`id`),
  CONSTRAINT `FKlp62k222192flpprpxhm2r8pv` FOREIGN KEY (`productId`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `basket_product_link`
--

LOCK TABLES `basket_product_link` WRITE;
/*!40000 ALTER TABLE `basket_product_link` DISABLE KEYS */;
INSERT INTO `basket_product_link` VALUES (247,3,0),(247,4,1),(247,7,0),(248,7,2),(248,8,1),(248,11,0);
/*!40000 ALTER TABLE `basket_product_link` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (249),(249),(249),(249),(249);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `stock_id` int(11) NOT NULL,
  `type_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK90w0j7y2y7chsmk4v4k02ekqf` (`stock_id`),
  KEY `FKq3fvcsydiaotwy3iqn1erqsfd` (`type_id`),
  CONSTRAINT `FK90w0j7y2y7chsmk4v4k02ekqf` FOREIGN KEY (`stock_id`) REFERENCES `stock` (`id`),
  CONSTRAINT `FKq3fvcsydiaotwy3iqn1erqsfd` FOREIGN KEY (`type_id`) REFERENCES `type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (3,'Мяч',130,1,2),(4,'Косточка',140,1,2),(7,'Ошейник',100,5,6),(8,'Клубок',90,1,2),(9,'Комбинезон',1200,5,6),(11,'Таблетки',180,1,10),(13,'Корм',720,5,12),(15,'Шампунь',270,1,14),(16,'Консервы',240,5,12),(17,'Пелёнки',245,1,14),(18,'Капли',490,1,10),(19,'Расчёска',270,5,14),(20,'Витамины',730,1,10),(21,'Антибиотики',1100,1,10),(22,'Ботинки',900,1,6),(97,'Пищевые добавки',280,1,12);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_stock_link`
--

DROP TABLE IF EXISTS `product_stock_link`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_stock_link` (
  `productId` int(11) NOT NULL,
  `stockId` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`productId`,`stockId`),
  KEY `FKegvr3coamp9vkc9t5dvn7w5ep` (`stockId`),
  CONSTRAINT `FK59o372nnr7566tokkq0gup58b` FOREIGN KEY (`stockId`) REFERENCES `stock` (`id`),
  CONSTRAINT `FKaesek7d7u7hifk2e0khukmxq4` FOREIGN KEY (`productId`) REFERENCES `product` (`id`),
  CONSTRAINT `FKegvr3coamp9vkc9t5dvn7w5ep` FOREIGN KEY (`stockId`) REFERENCES `stock` (`id`),
  CONSTRAINT `FKmfmajvgdrhwt418bkm5737shy` FOREIGN KEY (`productId`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_stock_link`
--

LOCK TABLES `product_stock_link` WRITE;
/*!40000 ALTER TABLE `product_stock_link` DISABLE KEYS */;
INSERT INTO `product_stock_link` VALUES (3,1,74),(4,1,99),(7,5,116),(8,1,58),(9,5,4),(11,1,62),(13,5,42),(15,1,67),(16,5,8),(17,1,117),(18,1,20),(19,5,26),(20,1,6),(21,1,5),(22,1,20),(97,1,159),(97,5,79);
/*!40000 ALTER TABLE `product_stock_link` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock`
--

DROP TABLE IF EXISTS `stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stock` (
  `id` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock`
--

LOCK TABLES `stock` WRITE;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
INSERT INTO `stock` VALUES (1,'СпбЮг'),(5,'СпбСевер');
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type`
--

DROP TABLE IF EXISTS `type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `type` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type`
--

LOCK TABLES `type` WRITE;
/*!40000 ALTER TABLE `type` DISABLE KEYS */;
INSERT INTO `type` VALUES (2,'Игрушки'),(6,'Одежда'),(10,'Медикаменты'),(12,'Еда'),(14,'Гигиена');
/*!40000 ALTER TABLE `type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'admin','123','ROLE_ADMIN'),(246,'Настя','1','ROLE_USER');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-06 22:10:51
