/*
Navicat MySQL Data Transfer

Source Server         : cxz
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : pims

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2014-11-26 13:32:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `course_id` int(20) NOT NULL AUTO_INCREMENT,
  `course_name` varchar(45) DEFAULT NULL,
  `course_description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6584 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('8', 'Computer Science', '');
INSERT INTO `course` VALUES ('14', 'Advanced Computer Science', null);
INSERT INTO `course` VALUES ('6583', 'Computer Security', null);

-- ----------------------------
-- Table structure for first_inspection
-- ----------------------------
DROP TABLE IF EXISTS `first_inspection`;
CREATE TABLE `first_inspection` (
  `first_inspection_id` int(20) NOT NULL AUTO_INCREMENT,
  `student_id` int(20) DEFAULT '0',
  `inspector_id` int(20) DEFAULT NULL,
  `module_id` int(20) DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `first_inspectioncol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`first_inspection_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of first_inspection
-- ----------------------------

-- ----------------------------
-- Table structure for inspector
-- ----------------------------
DROP TABLE IF EXISTS `inspector`;
CREATE TABLE `inspector` (
  `username` char(20) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `inspector_id` int(45) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `capacity` int(45) DEFAULT NULL,
  `timetable_id` int(45) DEFAULT NULL,
  `keywords` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`inspector_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inspector
-- ----------------------------
INSERT INTO `inspector` VALUES ('inspector', '123', '1', 'Mr.', 'Zhiwei', 'Liu', null, null, null, null);

-- ----------------------------
-- Table structure for inspector_keyword
-- ----------------------------
DROP TABLE IF EXISTS `inspector_keyword`;
CREATE TABLE `inspector_keyword` (
  `inspector_keyword_id` int(20) NOT NULL AUTO_INCREMENT,
  `inspector_id` int(20) DEFAULT NULL,
  `keyword_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`inspector_keyword_id`)
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
  PRIMARY KEY (`keyword_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of keyword
-- ----------------------------

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
  `students_enrolled` int(45) DEFAULT NULL,
  `inspector_available` int(45) DEFAULT NULL,
  `default_inspector_capacity` int(45) DEFAULT NULL,
  `first_inspection_start_date` datetime(6) DEFAULT NULL,
  `first_inspection_end_date` datetime(6) DEFAULT NULL,
  `second_inspection_start_date` datetime(6) DEFAULT NULL,
  `second_inspection_end_date` datetime(6) DEFAULT NULL,
  `dissertation_deadline` datetime(6) DEFAULT NULL,
  `pc_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`module_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26588 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of module
-- ----------------------------
INSERT INTO `module` VALUES ('26581', 'BSc project', '2014/15', '2014-09-30 12:00:51.000000', '2015-06-01 00:00:33.000000', null, null, '4', '2014-12-01 00:00:25.000000', '2014-11-10 00:00:43.000000', '2015-05-01 00:00:53.000000', '2014-11-14 00:00:14.000000', null, null);
INSERT INTO `module` VALUES ('26584', 'BSc project', '2013/14', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `module` VALUES ('26587', 'MSc project', '2014/15', null, null, null, null, null, null, null, null, null, null, null);

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
-- Table structure for second_inspection
-- ----------------------------
DROP TABLE IF EXISTS `second_inspection`;
CREATE TABLE `second_inspection` (
  `second_inspection_id` int(20) NOT NULL AUTO_INCREMENT,
  `student_id` int(20) DEFAULT NULL,
  `inspector_id` int(20) DEFAULT NULL,
  `modeule_id` int(20) DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `second_inspectioncol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`second_inspection_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of second_inspection
-- ----------------------------

-- ----------------------------
-- Table structure for slot
-- ----------------------------
DROP TABLE IF EXISTS `slot`;
CREATE TABLE `slot` (
  `slot_id` int(20) NOT NULL AUTO_INCREMENT,
  `start_date` datetime(6) DEFAULT NULL,
  `end_date` datetime(6) DEFAULT NULL,
  `avalibility` bit(1) DEFAULT NULL,
  PRIMARY KEY (`slot_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of slot
-- ----------------------------

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `student_id` int(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `project_title` varchar(45) DEFAULT NULL,
  `project_description` varchar(255) DEFAULT NULL,
  `supervisor` varchar(45) DEFAULT NULL,
  `username` char(20) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `timetable_id` int(20) DEFAULT NULL,
  `course_id` int(20) DEFAULT NULL,
  `module_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`student_id`),
  KEY `course_id` (`course_id`),
  KEY `module_id` (`module_id`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`),
  CONSTRAINT `student_ibfk_2` FOREIGN KEY (`module_id`) REFERENCES `module` (`module_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', 'Christopher', 'Wright', 'cxw518@student.bham.ac.uk', 'mage Analysis for Angiogenesis', '', null, 'cxw518', '123456', '2', '14', '26587');
INSERT INTO `student` VALUES ('2', 'Chenxin', 'Zhao', 'cxz413@student.bham.ac.uk', 'Analysing Algorithms', 'Data structure', null, 'cxz413', '123456', '1', '14', '26587');
INSERT INTO `student` VALUES ('3', null, null, null, null, null, null, 'gd', null, null, null, null);
INSERT INTO `student` VALUES ('4', null, null, null, null, null, null, 'fgd', null, null, null, null);

-- ----------------------------
-- Table structure for student_keyword
-- ----------------------------
DROP TABLE IF EXISTS `student_keyword`;
CREATE TABLE `student_keyword` (
  `student_keyword_id` int(20) NOT NULL,
  `keyword_id` int(20) DEFAULT NULL,
  `student_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`student_keyword_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student_keyword
-- ----------------------------

-- ----------------------------
-- Table structure for timetable
-- ----------------------------
DROP TABLE IF EXISTS `timetable`;
CREATE TABLE `timetable` (
  `timetable_id` int(20) NOT NULL AUTO_INCREMENT,
  `slot_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`timetable_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of timetable
-- ----------------------------
