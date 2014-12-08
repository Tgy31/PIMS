/*
Navicat MySQL Data Transfer

Source Server         : cxz
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : pims

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2014-12-08 12:11:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for inspection
-- ----------------------------
DROP TABLE IF EXISTS `inspection`;
CREATE TABLE `inspection` (
  `inspection_id` int(20) NOT NULL AUTO_INCREMENT,
  `inspectionweek_id` int(20) DEFAULT NULL,
  `student_id` int(20) DEFAULT NULL,
  `inspector_id` int(20) DEFAULT NULL,
  `start_date` datetime(6) DEFAULT NULL,
  `end_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`inspection_id`),
  KEY `inspectionweek_id` (`inspectionweek_id`),
  KEY `student_id` (`student_id`),
  KEY `inspector_id` (`inspector_id`),
  CONSTRAINT `inspection_ibfk_1` FOREIGN KEY (`inspectionweek_id`) REFERENCES `inspectionweek` (`inspectionweek_id`),
  CONSTRAINT `inspection_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`),
  CONSTRAINT `inspection_ibfk_3` FOREIGN KEY (`inspector_id`) REFERENCES `inspector` (`inspector_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inspection
-- ----------------------------

-- ----------------------------
-- Table structure for inspectionweek
-- ----------------------------
DROP TABLE IF EXISTS `inspectionweek`;
CREATE TABLE `inspectionweek` (
  `inspectionweek_id` int(20) NOT NULL AUTO_INCREMENT,
  `module_id` int(20) DEFAULT NULL,
  `inspection_title` varchar(100) DEFAULT NULL,
  `start_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`inspectionweek_id`),
  KEY `module_id` (`module_id`),
  CONSTRAINT `inspectionweek_ibfk_1` FOREIGN KEY (`module_id`) REFERENCES `module` (`module_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inspectionweek
-- ----------------------------

-- ----------------------------
-- Table structure for inspector
-- ----------------------------
DROP TABLE IF EXISTS `inspector`;
CREATE TABLE `inspector` (
  `username` char(20) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `inspector_id` int(45) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `capacity` int(45) DEFAULT NULL,
  PRIMARY KEY (`inspector_id`),
  KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=230 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inspector
-- ----------------------------
INSERT INTO `inspector` VALUES ('Zhiwei', 'Zhiwei', '1', 'Zhiwei', 'Liu', null, null);

-- ----------------------------
-- Table structure for inspector_keyword
-- ----------------------------
DROP TABLE IF EXISTS `inspector_keyword`;
CREATE TABLE `inspector_keyword` (
  `inspector_keyword_id` int(20) NOT NULL AUTO_INCREMENT,
  `inspector_id` int(20) DEFAULT NULL,
  `keyword_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`inspector_keyword_id`),
  KEY `keyword_id` (`keyword_id`),
  KEY `inspector_id` (`inspector_id`),
  CONSTRAINT `inspector_keyword_ibfk_1` FOREIGN KEY (`keyword_id`) REFERENCES `keyword` (`keyword_id`),
  CONSTRAINT `inspector_keyword_ibfk_2` FOREIGN KEY (`inspector_id`) REFERENCES `inspector` (`inspector_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inspector_keyword
-- ----------------------------

-- ----------------------------
-- Table structure for keyword
-- ----------------------------
DROP TABLE IF EXISTS `keyword`;
CREATE TABLE `keyword` (
  `keyword_id` int(20) NOT NULL AUTO_INCREMENT,
  `keyword_name` varchar(45) DEFAULT NULL,
  `module_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`keyword_id`),
  KEY `module_id` (`module_id`),
  CONSTRAINT `keyword_ibfk_1` FOREIGN KEY (`module_id`) REFERENCES `module` (`module_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of keyword
-- ----------------------------
INSERT INTO `keyword` VALUES ('1', 'artificial', '26581');
INSERT INTO `keyword` VALUES ('2', 'www', '26581');

-- ----------------------------
-- Table structure for module
-- ----------------------------
DROP TABLE IF EXISTS `module`;
CREATE TABLE `module` (
  `module_id` int(20) NOT NULL AUTO_INCREMENT,
  `module_name` varchar(45) DEFAULT NULL,
  `year` varchar(255) DEFAULT NULL,
  `start_date` datetime(6) DEFAULT NULL,
  `end_date` datetime(6) DEFAULT NULL,
  `default_inspector_capacity` int(45) DEFAULT NULL,
  PRIMARY KEY (`module_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26588 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of module
-- ----------------------------
INSERT INTO `module` VALUES ('26581', 'BSc project', '2014/15', '2014-09-30 12:00:51.000000', '2015-06-01 00:00:33.000000', null);
INSERT INTO `module` VALUES ('26584', 'BSc project', '2013/14', null, null, null);
INSERT INTO `module` VALUES ('26587', 'MSc project', '2014/15', null, null, null);

-- ----------------------------
-- Table structure for project_coordinator
-- ----------------------------
DROP TABLE IF EXISTS `project_coordinator`;
CREATE TABLE `project_coordinator` (
  `username` char(20) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `pc_id` int(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`pc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project_coordinator
-- ----------------------------
INSERT INTO `project_coordinator` VALUES ('hxs235', 'hxs235', '1', 'Prof.', 'He', 'Shan', 'hxs235@bham.ac.uk');
INSERT INTO `project_coordinator` VALUES ('jxh123', 'jxh123', '2', 'Ms.', 'Julie', 'Heathcote', 'jxh123@bham.ac.uk');
INSERT INTO `project_coordinator` VALUES ('Jane2', 'jane', '3', 'Mr.', 'Jane', null, 'jane@bham.ac.uk');

-- ----------------------------
-- Table structure for slot
-- ----------------------------
DROP TABLE IF EXISTS `slot`;
CREATE TABLE `slot` (
  `slot_id` int(20) NOT NULL AUTO_INCREMENT,
  `start_date` datetime(6) DEFAULT NULL,
  `end_date` datetime(6) DEFAULT NULL,
  `student_id` int(20) DEFAULT NULL,
  `inspector_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`slot_id`),
  KEY `student_id` (`student_id`),
  KEY `inspector_id` (`inspector_id`),
  CONSTRAINT `slot_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`),
  CONSTRAINT `slot_ibfk_2` FOREIGN KEY (`inspector_id`) REFERENCES `inspector` (`inspector_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of slot
-- ----------------------------
INSERT INTO `slot` VALUES ('2', '2014-11-11 11:30:00.000000', '2014-11-11 14:00:00.000000', '1', null);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `student_id` int(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `project_title` varchar(255) DEFAULT NULL,
  `project_description` varchar(255) DEFAULT NULL,
  `supervisor` varchar(45) DEFAULT NULL,
  `username` char(20) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `module_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`student_id`),
  KEY `module_id` (`module_id`),
  KEY `supervisor` (`supervisor`),
  CONSTRAINT `student_ibfk_2` FOREIGN KEY (`module_id`) REFERENCES `module` (`module_id`),
  CONSTRAINT `student_ibfk_3` FOREIGN KEY (`supervisor`) REFERENCES `inspector` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=230 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', 'Chenxin', 'Zhao', 'cxz413@bham.ac.uk', null, 'Artificital', 'Zhiwei', 'cxz413', 'cxz413', '26581');

-- ----------------------------
-- Table structure for student_keyword
-- ----------------------------
DROP TABLE IF EXISTS `student_keyword`;
CREATE TABLE `student_keyword` (
  `student_keyword_id` int(20) NOT NULL AUTO_INCREMENT,
  `keyword_id` int(20) DEFAULT NULL,
  `student_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`student_keyword_id`),
  KEY `keyword_id` (`keyword_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `student_keyword_ibfk_1` FOREIGN KEY (`keyword_id`) REFERENCES `keyword` (`keyword_id`),
  CONSTRAINT `student_keyword_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student_keyword
-- ----------------------------
INSERT INTO `student_keyword` VALUES ('3', '2', '1');
