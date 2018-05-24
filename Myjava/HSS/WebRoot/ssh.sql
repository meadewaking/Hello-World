/*
Navicat MySQL Data Transfer

Source Server         : localhost：3306
Source Server Version : 50627
Source Host           : localhost:3306
Source Database       : ssh

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2016-06-05 21:23:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for info
-- ----------------------------
DROP TABLE IF EXISTS `info`;
CREATE TABLE `info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of info
-- ----------------------------
INSERT INTO `info` VALUES ('1', 'leeon', '21', '西安');
INSERT INTO `info` VALUES ('2', 'alin', '21', '广州');
INSERT INTO `info` VALUES ('27', '梁', '18', '杭州');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(10) NOT NULL,
  `password` varchar(10) NOT NULL,
  `age` int(10) NOT NULL,
  `priority` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'leeon', '666888', '21', '0');
INSERT INTO `user` VALUES ('2', 'rose', '666666', '1', '0');
