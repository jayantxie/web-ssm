/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : stumanager

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-05-06 16:51:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for distractiontime
-- ----------------------------
DROP TABLE IF EXISTS `distractiontime`;
CREATE TABLE `distractiontime` (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `eva_id` int(15) DEFAULT NULL,
  `s_time` datetime DEFAULT NULL,
  `e_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for evaluationdata
-- ----------------------------
DROP TABLE IF EXISTS `evaluationdata`;
CREATE TABLE `evaluationdata` (
  `id` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) NOT NULL,
  `scores` int(4) NOT NULL,
  `begin_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `relax_minutes` int(11) DEFAULT NULL,
  `distraction_times` int(11) DEFAULT NULL,
  `relax_times` int(11) DEFAULT NULL,
  `text` text,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for friend
-- ----------------------------
DROP TABLE IF EXISTS `friend`;
CREATE TABLE `friend` (
  `id` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `friend_name` varchar(255) NOT NULL,
  `state` int(2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for learninglog
-- ----------------------------
DROP TABLE IF EXISTS `learninglog`;
CREATE TABLE `learninglog` (
  `id` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(15) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `content` text,
  `pic1` varchar(255) DEFAULT NULL,
  `pic2` varchar(255) DEFAULT NULL,
  `pic3` varchar(255) DEFAULT NULL,
  `pic4` varchar(255) DEFAULT NULL,
  `pic5` varchar(255) DEFAULT NULL,
  `pic6` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `name` varchar(30) NOT NULL,
  `password` varchar(20) NOT NULL,
  `nickname` varchar(20) NOT NULL,
  `token` varchar(30) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `working_state` tinyint(3) unsigned zerofill DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
