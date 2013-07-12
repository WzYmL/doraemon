-- MySQL dump 10.10
--
-- Host: localhost    Database: kgarten
-- ------------------------------------------------------
-- Server version	5.0.22-community-nt

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


CREATE DATABASE IF NOT EXISTS test;
USE test;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `ID` int(10) unsigned NOT NULL auto_increment,
  `USERNAME` varchar(45) NOT NULL,
  `PASSWORD` varchar(45) NOT NULL,
  `REALNAME` varchar(45) NOT NULL,
  `MOBILE` varchar(45) NOT NULL,
  `EMAIL` varchar(45) default NULL,
  `TYPE` tinyint(3) unsigned NOT NULL COMMENT '管理员还是操作员',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员';

--
-- Dumping data for table `admin`
--


/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
LOCK TABLES `admin` WRITE;
INSERT INTO `admin` VALUES (1,'admin','admin123','系统管理员','13198765432','admin@company.com',1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;

--
-- Table structure for table `card`
--

DROP TABLE IF EXISTS `card`;
CREATE TABLE `card` (
  `ID` int(10) unsigned NOT NULL auto_increment,
  `UNIQUE_CODE` varchar(45) NOT NULL,
  `TYPE` int(10) unsigned NOT NULL COMMENT '教师卡、学生卡还是家长卡',
  `STATUS` int(10) unsigned NOT NULL default '1' COMMENT '卡片状态（未绑定，已绑定，其他）',
  `OWNER` varchar(45) default NULL COMMENT '人名',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备卡';

--
-- Dumping data for table `card`
--


/*!40000 ALTER TABLE `card` DISABLE KEYS */;
LOCK TABLES `card` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `card` ENABLE KEYS */;
