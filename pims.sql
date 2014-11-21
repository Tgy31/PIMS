/*
Navicat MySQL Data Transfer

Source Server         : cxz
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : pims

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2014-11-21 03:56:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `course_id` int(20) NOT NULL,
  `course_name` varchar(45) DEFAULT NULL,
  `course_description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  `student_id` int(20) NOT NULL,
  `inspector_id` int(20) DEFAULT NULL,
  `module_id` int(20) DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `first_inspectioncol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of first_inspection
-- ----------------------------

-- ----------------------------
-- Table structure for inspector
-- ----------------------------
DROP TABLE IF EXISTS `inspector`;
CREATE TABLE `inspector` (
  `username` char(20) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `inspector_id` int(45) DEFAULT NULL,
  `title` varchar(45) DEFAULT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `capacity` int(45) DEFAULT NULL,
  `timetable_id` int(45) DEFAULT NULL,
  `keywords` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`),
  KEY `timetable_id` (`timetable_id`),
  CONSTRAINT `inspector_ibfk_1` FOREIGN KEY (`timetable_id`) REFERENCES `timetable` (`timetable_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inspector
-- ----------------------------
INSERT INTO `inspector` VALUES ('inspector', '123', null, 'Mr.', 'Zhiwei', 'Liu', null, null, null, null);

-- ----------------------------
-- Table structure for module
-- ----------------------------
DROP TABLE IF EXISTS `module`;
CREATE TABLE `module` (
  `module_id` int(20) NOT NULL,
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  `username` char(20) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `pc_id` int(20) NOT NULL,
  `title` varchar(45) DEFAULT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`pc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  `student_id` int(20) NOT NULL,
  `inspector_id` int(20) DEFAULT NULL,
  `modeule_id` int(20) DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `second_inspectioncol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of second_inspection
-- ----------------------------

-- ----------------------------
-- Table structure for slot
-- ----------------------------
DROP TABLE IF EXISTS `slot`;
CREATE TABLE `slot` (
  `slot_id` int(20) NOT NULL,
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
  `student_id` int(20) NOT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `project_id` int(45) DEFAULT NULL,
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('123456', 'Christopher', 'Wright', 'cxw518@student.bham.ac.uk', null, 'mage Analysis for Angiogenesis', '', null, 'cxw518', '123456', '2', '14', '26587');
INSERT INTO `student` VALUES ('1488913', 'Chenxin', 'Zhao', 'cxz413@student.bham.ac.uk', null, 'Analysing Algorithms', 'Data structure', null, 'cxz413', '123456', '1', '14', '26587');

-- ----------------------------
-- Table structure for timetable
-- ----------------------------
DROP TABLE IF EXISTS `timetable`;
CREATE TABLE `timetable` (
  `timetable_id` int(20) NOT NULL,
  `slot_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`timetable_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of timetable
-- ----------------------------
