/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : his

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2015-11-13 17:28:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Medical_No` varchar(100) DEFAULT NULL,
  `state` varchar(100) DEFAULT NULL,
  `sumAmount` double(100,0) DEFAULT NULL,
  `cash` varchar(30) DEFAULT NULL,
  `pay_cash` char(10) DEFAULT NULL,
  `balance` char(10) DEFAULT NULL,
  `feeDate` char(10) DEFAULT NULL,
  `MeFee` char(10) DEFAULT NULL,
  `DrugFee` char(10) DEFAULT NULL,
  `paid_in` char(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account
-- ----------------------------

-- ----------------------------
-- Table structure for dep
-- ----------------------------
DROP TABLE IF EXISTS `dep`;
CREATE TABLE `dep` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `DepCode` varchar(100) DEFAULT NULL,
  `DepName` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dep
-- ----------------------------
INSERT INTO `dep` VALUES ('1', '1', '骨科');
INSERT INTO `dep` VALUES ('2', '2', '五官科');
INSERT INTO `dep` VALUES ('3', '3', '内科');
INSERT INTO `dep` VALUES ('4', '4', '急诊科');
INSERT INTO `dep` VALUES ('5', '5', '五官科');
INSERT INTO `dep` VALUES ('6', '6', '放射科');
INSERT INTO `dep` VALUES ('7', '7', '儿科');
INSERT INTO `dep` VALUES ('8', '7', '外科');

-- ----------------------------
-- Table structure for dispensing
-- ----------------------------
DROP TABLE IF EXISTS `dispensing`;
CREATE TABLE `dispensing` (
  `medicalNo` varchar(100) DEFAULT '',
  `drugId` varchar(100) DEFAULT NULL,
  `dispCount` int(11) DEFAULT NULL,
  `aldispCount` int(11) DEFAULT NULL,
  `nodispCount` int(11) DEFAULT NULL,
  `dispTime` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dispensing
-- ----------------------------
INSERT INTO `dispensing` VALUES ('2015102910003169', '1000', '23', '0', '23', '2015-11-04 19:46:05');
INSERT INTO `dispensing` VALUES ('2015102910134026', '1000', '22', '0', '22', '2015-11-09 00:39:36');
INSERT INTO `dispensing` VALUES ('2015102910003169', '1001', '12', '0', '12', '2015-11-10 16:55:08');

-- ----------------------------
-- Table structure for drugvariety
-- ----------------------------
DROP TABLE IF EXISTS `drugvariety`;
CREATE TABLE `drugvariety` (
  `DID` varchar(100) NOT NULL,
  `drugURL` varchar(100) DEFAULT NULL,
  `purchasing_price` double(100,0) DEFAULT NULL,
  `selling_price` double(100,0) DEFAULT NULL,
  `drugName` varchar(50) DEFAULT NULL,
  `drugType` int(11) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `production_date` varchar(30) DEFAULT NULL,
  `expiration_date` varchar(30) DEFAULT NULL,
  `shelf_life` int(11) DEFAULT NULL,
  `detail` varchar(500) DEFAULT NULL,
  `manufacturer` varchar(80) DEFAULT NULL,
  `directions` varchar(300) DEFAULT NULL,
  `countpurchases` int(11) DEFAULT NULL,
  `inventory` int(11) DEFAULT NULL,
  `drugflag` int(11) DEFAULT NULL,
  `drugcomment` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`DID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of drugvariety
-- ----------------------------
INSERT INTO `drugvariety` VALUES ('1000', 'medicine/zddf.jpg', '5', '10', '感冒通胶囊', '2', '用于感冒', '2015-11-06', '2015-12-31', '30', '用于头痛感冒流鼻涕', '哈药六厂', '口服一日三次一次三粒', '200', '5', '1', '孕妇禁忌');
INSERT INTO `drugvariety` VALUES ('1001', 'medicine/zddf.jpg', '11', '33', '吗丁啉', '1', '用于感冒', '2014-12-29', '2015-11-18', '34', '用于头痛感冒流鼻涕', '哈药六厂', '口服一日三次一次三粒', '44', '343', '1', '孕妇禁忌');
INSERT INTO `drugvariety` VALUES ('1002', 'medicine/zddf.jpg', '111', '213', '安洛血', '2', '用于感冒', '2015-11-06', '2015-11-18', '11', '用于头痛感冒流鼻涕', '哈药六厂', '口服一日三次一次三粒', '8', '111', '1', '孕妇禁忌');
INSERT INTO `drugvariety` VALUES ('1003', 'medicine/zddf.jpg', '122', '134', '诺和灵', '1', '用于感冒', '2015-11-06', '2015-11-18', '12', '用于头痛感冒流鼻涕', '哈药六厂', '口服一日三次一次三粒', '6', '0', '1', '孕妇禁忌');
INSERT INTO `drugvariety` VALUES ('1004', 'medicine/zddf.jpg', '111', '234', '病毒灵', '1', '用于感冒', '2015-11-09', '2015-11-18', '1', '用于头痛感冒流鼻涕', '哈药六厂', '口服一日三次一次三粒', '2', '1', '1', '孕妇禁忌');
INSERT INTO `drugvariety` VALUES ('1005', 'medicine/zddf.jpg', '13', '31', '阿司匹林', '2', '用于感冒', '2015-11-09', '2015-11-19', '12', '用于头痛感冒流鼻涕', '哈药六厂', '口服一日三次一次三粒', '12', '12', '1', '孕妇禁忌');
INSERT INTO `drugvariety` VALUES ('1006', 'medicine/zddf.jpg', '32', '56', '咳必清', '1', '用于感冒', '2015-11-06', '2015-11-18', '33', '用于头痛感冒流鼻涕', '哈药六厂', '口服一日三次一次三粒', '3', '3', '0', '孕妇禁忌');
INSERT INTO `drugvariety` VALUES ('1007', 'medicine/zddf.jpg', '33', '66', '特普宁', '1', '用于感冒', '2015-11-06', '2015-11-18', '0', '用于头痛感冒流鼻涕', '哈药六厂', '口服一日三次一次三粒', '9', '0', '1', '孕妇禁忌');
INSERT INTO `drugvariety` VALUES ('1008', 'medicine/zddf.jpg', '12', '56', '奥必欣', '2', '用于感冒', '2015-11-06', '2015-11-18', '32', '用于头痛感冒流鼻涕', '哈药六厂', '口服一日三次一次三粒', '32', '32', '0', '孕妇禁忌');

-- ----------------------------
-- Table structure for feemanager
-- ----------------------------
DROP TABLE IF EXISTS `feemanager`;
CREATE TABLE `feemanager` (
  `gid` int(11) NOT NULL AUTO_INCREMENT,
  `medicalNo` varchar(100) DEFAULT NULL,
  `feeid` int(11) DEFAULT NULL,
  `charge_sum` double(30,0) DEFAULT NULL,
  `feeDate` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`gid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of feemanager
-- ----------------------------
INSERT INTO `feemanager` VALUES ('3', '2015102910003169', '1', '200', '2015-11-04 10:26:23');
INSERT INTO `feemanager` VALUES ('4', '2015102910003169', '2', '380', '2015-11-04 10:26:54');
INSERT INTO `feemanager` VALUES ('5', '2015102910003169', '5', '410', '2015-11-04 13:04:30');
INSERT INTO `feemanager` VALUES ('6', '2015102910003169', '6', '610', '2015-11-04 13:08:16');
INSERT INTO `feemanager` VALUES ('7', '2015102910003169', '7', '810', '2015-11-04 13:16:18');
INSERT INTO `feemanager` VALUES ('8', '2015102910003169', '2', '32590', '2015-11-10 10:00:21');
INSERT INTO `feemanager` VALUES ('9', '2015102910134026', '8', '3232', '2015-11-10 10:00:49');

-- ----------------------------
-- Table structure for feeproject
-- ----------------------------
DROP TABLE IF EXISTS `feeproject`;
CREATE TABLE `feeproject` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `feeName` varchar(100) DEFAULT NULL,
  `amount` double(30,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of feeproject
-- ----------------------------
INSERT INTO `feeproject` VALUES ('1', '心脏检查', '200');
INSERT INTO `feeproject` VALUES ('2', 'CT', '180');
INSERT INTO `feeproject` VALUES ('5', '体检', '30');
INSERT INTO `feeproject` VALUES ('6', '剖腹产', '2000');
INSERT INTO `feeproject` VALUES ('7', '骨髓移植', '30000');
INSERT INTO `feeproject` VALUES ('8', 'add', '3232');

-- ----------------------------
-- Table structure for hospitalsheet
-- ----------------------------
DROP TABLE IF EXISTS `hospitalsheet`;
CREATE TABLE `hospitalsheet` (
  `medicalNo` varchar(100) NOT NULL DEFAULT '',
  `nurse` varchar(30) DEFAULT NULL,
  `bedNo` varchar(40) DEFAULT NULL,
  `payCase` double(100,0) DEFAULT NULL,
  `pcondition` varchar(500) DEFAULT NULL,
  `hosTime` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hospitalsheet
-- ----------------------------
INSERT INTO `hospitalsheet` VALUES ('2015102910134026', '李护士', '323', '232', '严重', '2015-11-06 12：29：09');
INSERT INTO `hospitalsheet` VALUES ('2015102916272304', '王护士', '532', '322', '轻微', '2015-11-06 12：29：10');
INSERT INTO `hospitalsheet` VALUES ('2015102910003169', '徐护士', '368', '323', '严重', '2015-11-06 12：29：11');

-- ----------------------------
-- Table structure for register
-- ----------------------------
DROP TABLE IF EXISTS `register`;
CREATE TABLE `register` (
  `medicalNo` varchar(100) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `identifierType` varchar(100) DEFAULT NULL,
  `identifier` varchar(100) DEFAULT NULL,
  `insuranceNumber` varchar(30) DEFAULT NULL,
  `phoneNumber` varchar(30) DEFAULT NULL,
  `expenseFlag` int(11) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `profession` varchar(200) DEFAULT NULL,
  `czflag` int(30) DEFAULT NULL,
  `docid` int(11) DEFAULT NULL,
  `flag` int(11) DEFAULT NULL,
  `remarks` varchar(500) DEFAULT NULL,
  `regtime` varchar(30) DEFAULT NULL,
  `depid` int(11) DEFAULT NULL,
  `regfee` double(11,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of register
-- ----------------------------
INSERT INTO `register` VALUES ('2015102910003169', '冯志磊', '2', '213214243242421233', '32131', '3232323232', '0', '1', '32', '工人', '1', '2', '2', '我去', '2015-10-28 12:12:12', '1', '5');
INSERT INTO `register` VALUES ('2015102910134026', '焦哲哲', '3', '213213213213323333', '434', '23232332', '1', '1', '34', '农民', '0', '2', '2', '434', '2015-10-29 10:13:40', '2', '12');
INSERT INTO `register` VALUES ('2015102916272304', '章杨奇', '3', '213213214214213213', '32', '23323232232', '0', '1', '32', '学生', '1', '3', '1', '323', '2015-11-10 09:20:23', '3', '11');
INSERT INTO `register` VALUES ('2015102916301305', '孙文斌', '1', '213213213213213231', '323', '23232323232', '1', '1', '32', '教师', '1', '4', '4', '23', '2015-10-30 14:27:13', '4', '9');
INSERT INTO `register` VALUES ('2015102916301306', '郭伟强', '1', '242432432443441111', '323', '323323232', '0', '1', '45', '司机', '0', '5', '7', '424', '2015-11-08 18:58:15', '5', '15');
INSERT INTO `register` VALUES ('2015102916301323', '周坤', '3', '2324324343243', '434343', '434343', '1', '1', '21', '医生', '1', '6', '1', '拉拉', '2015-11-08 18:58:16', '6', '30');
INSERT INTO `register` VALUES ('20151110095523231', '131313', '1', '13313131313131313', '133131313131313131', '313131313131313', '1', '1', '12', '3131', '0', '1', '1', '1221', '2015-11-10 09:55:23', '1', '3131');

-- ----------------------------
-- Table structure for resources
-- ----------------------------
DROP TABLE IF EXISTS `resources`;
CREATE TABLE `resources` (
  `resid` int(11) NOT NULL AUTO_INCREMENT,
  `resname` varchar(30) DEFAULT NULL,
  `resurl` varchar(80) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`resid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resources
-- ----------------------------
INSERT INTO `resources` VALUES ('1', '挂号信息管理', 'http://localhost:8080/his/index.jsp#1/2', '1');
INSERT INTO `resources` VALUES ('2', '门诊医生管理', 'http://localhost:8080/his/index.jsp#1/3', '1');
INSERT INTO `resources` VALUES ('3', '药品管理', 'http://localhost:8080/his/index.jsp#1/4', '1');
INSERT INTO `resources` VALUES ('4', '住院办理', 'http://localhost:8080/his/index.jsp#1/5', '1');
INSERT INTO `resources` VALUES ('5', '收费项目登记', 'http://localhost:8080/his/index.jsp#1/6', '1');
INSERT INTO `resources` VALUES ('6', '在院发药', 'http://localhost:8080/his/index.jsp#1/7', '1');
INSERT INTO `resources` VALUES ('7', '住院结算', 'http://localhost:8080/his/index.jsp#1/8', '1');
INSERT INTO `resources` VALUES ('8', '月营业额统计', 'http://localhost:8080/his/index.jsp#1/9', '1');
INSERT INTO `resources` VALUES ('9', '年营业额统计', 'http://localhost:8080/his/index.jsp#1/10', '1');
INSERT INTO `resources` VALUES ('10', '用户管理', 'http://localhost:8080/his/index.jsp#1/11', '1');
INSERT INTO `resources` VALUES ('11', '角色管理', 'http://localhost:8080/his/index.jsp#1/12', '1');
INSERT INTO `resources` VALUES ('12', '资源管理', 'http://localhost:8080/his/index.jsp#1/13', '1');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `roleid` int(11) NOT NULL AUTO_INCREMENT,
  `rolestatus` int(30) DEFAULT NULL,
  `rolename` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`roleid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '1', '管理员');
INSERT INTO `role` VALUES ('2', '1', '院长');
INSERT INTO `role` VALUES ('3', '0', '医生');
INSERT INTO `role` VALUES ('4', '1', '护士');
INSERT INTO `role` VALUES ('5', '1', '护士长');
INSERT INTO `role` VALUES ('6', '0', '副院长');
INSERT INTO `role` VALUES ('7', '1', '科长');

-- ----------------------------
-- Table structure for role_resources
-- ----------------------------
DROP TABLE IF EXISTS `role_resources`;
CREATE TABLE `role_resources` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `roleid` int(50) DEFAULT NULL,
  `resid` int(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_resources
-- ----------------------------
INSERT INTO `role_resources` VALUES ('3', '3', '1');
INSERT INTO `role_resources` VALUES ('4', '4', '1');
INSERT INTO `role_resources` VALUES ('5', '4', '2');
INSERT INTO `role_resources` VALUES ('6', '4', '7');
INSERT INTO `role_resources` VALUES ('18', '2', '1');
INSERT INTO `role_resources` VALUES ('19', '2', '2');
INSERT INTO `role_resources` VALUES ('20', '2', '3');
INSERT INTO `role_resources` VALUES ('21', '2', '4');
INSERT INTO `role_resources` VALUES ('69', '1', '1');
INSERT INTO `role_resources` VALUES ('70', '1', '2');
INSERT INTO `role_resources` VALUES ('71', '1', '3');
INSERT INTO `role_resources` VALUES ('72', '1', '4');
INSERT INTO `role_resources` VALUES ('73', '1', '5');
INSERT INTO `role_resources` VALUES ('74', '1', '6');
INSERT INTO `role_resources` VALUES ('75', '1', '7');
INSERT INTO `role_resources` VALUES ('76', '1', '8');
INSERT INTO `role_resources` VALUES ('77', '1', '9');
INSERT INTO `role_resources` VALUES ('78', '1', '10');
INSERT INTO `role_resources` VALUES ('79', '1', '11');
INSERT INTO `role_resources` VALUES ('80', '1', '12');
INSERT INTO `role_resources` VALUES ('81', '1', '13');
INSERT INTO `role_resources` VALUES ('82', '1', '14');

-- ----------------------------
-- Table structure for staff
-- ----------------------------
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `identifierType` int(11) DEFAULT NULL,
  `identifier` varchar(100) DEFAULT NULL,
  `telphone` varchar(30) DEFAULT NULL,
  `phone` varchar(30) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `birthDate` varchar(30) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `depid` int(11) DEFAULT NULL,
  `degree` int(11) DEFAULT NULL,
  `remarks` varchar(100) DEFAULT NULL,
  `joinTime` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of staff
-- ----------------------------
INSERT INTO `staff` VALUES ('1', '王医生', '2', '2', '2', '2', '1', '23', '2015-11-10', '33@21.com', '1', '2', '2二位', '2015-11-07 23：14：31');
INSERT INTO `staff` VALUES ('2', '李医生', '1', '323', '323', '23', '0', '33', '2015-11-10', '33@21.com', '2', '1', '的文物', '2015-11-07 23：14：33');
INSERT INTO `staff` VALUES ('3', '张医生', '3', '2323', '2323', '323', '0', '23', '2015-11-10', '33@21.com', '3', '3', '二位', '2015-11-07 23：14：34');
INSERT INTO `staff` VALUES ('4', '赵医生', '1', '323', '23', '323', '0', '34', '2015-11-10', '33@21.com', '3', '3', '323', '2015-11-07 23：14：36');
INSERT INTO `staff` VALUES ('5', '徐医生', '2', '23', '323', '323', '1', '32', '2015-11-10', '2333@21.com', '2', '1', '3231', '2015-11-07 23：14：39');
INSERT INTO `staff` VALUES ('6', '冯医生', '1', '21213', '2313', '123', '0', '33', '2015-11-10', '33@21.com', '1', '2', 'ddd', '2015-11-07 23：14：32');
INSERT INTO `staff` VALUES ('7', '郭医生', '3', '3434', '4343', '3434', '1', '23', '2015-11-02', '343@163.com', '1', '1', 'rsf', '2015-11-08 13：00：33');
INSERT INTO `staff` VALUES ('8', '212', '1', '1212121212121', '232121212', '12121', '1', '21', '2015-11-09', '1212@ww.com', '1', '2', '211321', '2015-11-10 09：58：15');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `realname` varchar(30) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `status` int(30) DEFAULT NULL,
  `roleid` int(11) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=140 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'DullSky', '123456', 'Dull', 'dullsky6@163.com', '1', '1');
INSERT INTO `user` VALUES ('2', 'zhanghao', '12345', '张豪', 'dullsky6@163.com', '1', '2');
INSERT INTO `user` VALUES ('3', 'admin', 'admin', 'admin', 'admin@163.com', '1', '1');
INSERT INTO `user` VALUES ('121', 'bbbb', 'bbbb', 'bbbb', 'bbbb@163.com', '1', '5');
INSERT INTO `user` VALUES ('123', 'cccc', 'cccc', 'cccc', 'cccc@hotmail.com', '0', '6');
INSERT INTO `user` VALUES ('124', 'dddd', 'dddd', 'dddd', 'dddd@gmail.com', '0', '7');
INSERT INTO `user` VALUES ('125', 'eeee', 'eeee', 'eeee', 'eeee@qq.com', '1', '1');
INSERT INTO `user` VALUES ('127', 'ffff', 'ffffff', 'ffffff', 'fffff@qq.com', '1', '2');
INSERT INTO `user` VALUES ('128', 'ggggg', 'ggg', 'ggg', 'ggg@gg.vom', '1', '5');
INSERT INTO `user` VALUES ('129', 'hhhhh', 'hhh', 'hhhhh', 'hh@hh.com', '1', '6');
INSERT INTO `user` VALUES ('130', 'iiii', 'iiiiii', 'iiiii', 'iiiii@qq.com', '1', '7');
INSERT INTO `user` VALUES ('131', 'jjjj', 'jjjjj', 'jjjjj', 'jjjj@qq.com', '1', '3');
INSERT INTO `user` VALUES ('132', 'kkkkk', 'kkkkkk', 'kkkkk', 'kkkk@qq.com', '1', '6');
INSERT INTO `user` VALUES ('133', 'lllllll', 'lllll', 'lllllll', 'lll@qq.com', '1', '5');
INSERT INTO `user` VALUES ('134', 'mmm', 'mmmm', 'mmmm', 'mmm@qq.com', '1', '4');
INSERT INTO `user` VALUES ('135', 'nnnn', 'nnnnn', 'nnnnnn', 'nnnnn@qq.com', '1', '3');
INSERT INTO `user` VALUES ('136', 'oooo', 'oooo', 'oooo', 'oooo@qq.com', '1', '6');
INSERT INTO `user` VALUES ('137', 'pppp', 'ppp', 'pppp', 'pppp@qq.com', '0', '1');
INSERT INTO `user` VALUES ('138', 'tttt', 'ttt', 'ttttt', 'ttttt@qq.com', '0', '1');
INSERT INTO `user` VALUES ('139', 'yyy', 'yyyyy', 'yyyy', 'yyyy@qq.com', '1', '6');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `roleid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
