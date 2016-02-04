/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : learn

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2016-02-04 16:58:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for jpa_api_person
-- ----------------------------
DROP TABLE IF EXISTS `jpa_api_person`;
CREATE TABLE `jpa_api_person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `age` int(11) NOT NULL,
  `birthday` datetime DEFAULT NULL,
  `birthdayDate` date DEFAULT NULL,
  `birthdayTime` time DEFAULT NULL,
  `englishScore` double DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `info` longblob,
  `mathScore` float DEFAULT NULL,
  `s_name` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for jpa_cache2_human
-- ----------------------------
DROP TABLE IF EXISTS `jpa_cache2_human`;
CREATE TABLE `jpa_cache2_human` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for jpa_jpql_customer
-- ----------------------------
DROP TABLE IF EXISTS `jpa_jpql_customer`;
CREATE TABLE `jpa_jpql_customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `age` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for jpa_jpql_order
-- ----------------------------
DROP TABLE IF EXISTS `jpa_jpql_order`;
CREATE TABLE `jpa_jpql_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(255) DEFAULT NULL,
  `customerId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ie1lsovbk8xuqh1236nen385y` (`customerId`),
  CONSTRAINT `FK_ie1lsovbk8xuqh1236nen385y` FOREIGN KEY (`customerId`) REFERENCES `jpa_jpql_customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for jpa_m2m_category
-- ----------------------------
DROP TABLE IF EXISTS `jpa_m2m_category`;
CREATE TABLE `jpa_m2m_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for jpa_m2m_category_item
-- ----------------------------
DROP TABLE IF EXISTS `jpa_m2m_category_item`;
CREATE TABLE `jpa_m2m_category_item` (
  `item_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  KEY `FK_qk8ihnetmytwvlls1taut8w3f` (`category_id`),
  KEY `FK_3b95nukp03yc61gkkgjpk5f3b` (`item_id`),
  CONSTRAINT `FK_3b95nukp03yc61gkkgjpk5f3b` FOREIGN KEY (`item_id`) REFERENCES `jpa_m2m_item` (`id`),
  CONSTRAINT `FK_qk8ihnetmytwvlls1taut8w3f` FOREIGN KEY (`category_id`) REFERENCES `jpa_m2m_category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for jpa_m2m_category_jpa_m2m_item
-- ----------------------------
DROP TABLE IF EXISTS `jpa_m2m_category_jpa_m2m_item`;
CREATE TABLE `jpa_m2m_category_jpa_m2m_item` (
  `jpa_m2m_category_id` int(11) NOT NULL,
  `items_id` int(11) NOT NULL,
  KEY `FK_flrjjq8uih98l1po61aytbou7` (`items_id`),
  KEY `FK_a3qm91xg7b3aatbdcutti8f2y` (`jpa_m2m_category_id`),
  CONSTRAINT `FK_a3qm91xg7b3aatbdcutti8f2y` FOREIGN KEY (`jpa_m2m_category_id`) REFERENCES `jpa_m2m_category` (`id`),
  CONSTRAINT `FK_flrjjq8uih98l1po61aytbou7` FOREIGN KEY (`items_id`) REFERENCES `jpa_m2m_item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for jpa_m2m_item
-- ----------------------------
DROP TABLE IF EXISTS `jpa_m2m_item`;
CREATE TABLE `jpa_m2m_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for jpa_m2o_department
-- ----------------------------
DROP TABLE IF EXISTS `jpa_m2o_department`;
CREATE TABLE `jpa_m2o_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for jpa_m2o_employee
-- ----------------------------
DROP TABLE IF EXISTS `jpa_m2o_employee`;
CREATE TABLE `jpa_m2o_employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `departmentId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ioyksm237s09qfm5cccv8b4eu` (`departmentId`),
  CONSTRAINT `FK_ioyksm237s09qfm5cccv8b4eu` FOREIGN KEY (`departmentId`) REFERENCES `jpa_m2o_department` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for jpa_o2m2o_customer
-- ----------------------------
DROP TABLE IF EXISTS `jpa_o2m2o_customer`;
CREATE TABLE `jpa_o2m2o_customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for jpa_o2m2o_product_order
-- ----------------------------
DROP TABLE IF EXISTS `jpa_o2m2o_product_order`;
CREATE TABLE `jpa_o2m2o_product_order` (
  `orderId` int(11) NOT NULL AUTO_INCREMENT,
  `orderNum` varchar(255) DEFAULT NULL,
  `customerId` int(11) DEFAULT NULL,
  PRIMARY KEY (`orderId`),
  KEY `FK_1bx8pxi3snhfqi1bsmskbndx8` (`customerId`),
  CONSTRAINT `FK_1bx8pxi3snhfqi1bsmskbndx8` FOREIGN KEY (`customerId`) REFERENCES `jpa_o2m2o_customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for jpa_o2m_address
-- ----------------------------
DROP TABLE IF EXISTS `jpa_o2m_address`;
CREATE TABLE `jpa_o2m_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `userId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_nhv63xdsqyo74786eb81hxc32` (`userId`),
  CONSTRAINT `FK_nhv63xdsqyo74786eb81hxc32` FOREIGN KEY (`userId`) REFERENCES `jpa_o2m_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for jpa_o2m_user
-- ----------------------------
DROP TABLE IF EXISTS `jpa_o2m_user`;
CREATE TABLE `jpa_o2m_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for jpa_o2o_student
-- ----------------------------
DROP TABLE IF EXISTS `jpa_o2o_student`;
CREATE TABLE `jpa_o2o_student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for jpa_o2o_studentcard
-- ----------------------------
DROP TABLE IF EXISTS `jpa_o2o_studentcard`;
CREATE TABLE `jpa_o2o_studentcard` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cardNumber` varchar(255) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_5l3awwecfpminp0mj8gdwlyjc` (`student_id`),
  CONSTRAINT `FK_5l3awwecfpminp0mj8gdwlyjc` FOREIGN KEY (`student_id`) REFERENCES `jpa_o2o_student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for jpa_temp_customer
-- ----------------------------
DROP TABLE IF EXISTS `jpa_temp_customer`;
CREATE TABLE `jpa_temp_customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for jpa_temp_order
-- ----------------------------
DROP TABLE IF EXISTS `jpa_temp_order`;
CREATE TABLE `jpa_temp_order` (
  `orderId` int(11) NOT NULL AUTO_INCREMENT,
  `orderNum` varchar(255) DEFAULT NULL,
  `customerId` int(11) DEFAULT NULL,
  PRIMARY KEY (`orderId`),
  KEY `FK_5ohkhsfwkfqailk841l3g46vt` (`customerId`),
  CONSTRAINT `FK_5ohkhsfwkfqailk841l3g46vt` FOREIGN KEY (`customerId`) REFERENCES `jpa_temp_customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for mybatis_one2many_depart
-- ----------------------------
DROP TABLE IF EXISTS `mybatis_one2many_depart`;
CREATE TABLE `mybatis_one2many_depart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `departno` varchar(255) DEFAULT NULL,
  `departname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for mybatis_one2many_emp
-- ----------------------------
DROP TABLE IF EXISTS `mybatis_one2many_emp`;
CREATE TABLE `mybatis_one2many_emp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `departId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for mybatis_one2one_psg
-- ----------------------------
DROP TABLE IF EXISTS `mybatis_one2one_psg`;
CREATE TABLE `mybatis_one2one_psg` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `psgcardid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for mybatis_one2one_psgcard
-- ----------------------------
DROP TABLE IF EXISTS `mybatis_one2one_psgcard`;
CREATE TABLE `mybatis_one2one_psgcard` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cardno` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for mybatis_person
-- ----------------------------
DROP TABLE IF EXISTS `mybatis_person`;
CREATE TABLE `mybatis_person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for mybatis_user
-- ----------------------------
DROP TABLE IF EXISTS `mybatis_user`;
CREATE TABLE `mybatis_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
