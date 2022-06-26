/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : task

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2021-03-08 10:19:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_task
-- ----------------------------
DROP TABLE IF EXISTS `t_task`;
CREATE TABLE `t_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `TASK_NAME` varchar(20) DEFAULT NULL COMMENT '任务名字',
  `START_TIME` timestamp NULL DEFAULT NULL COMMENT '开始时间',
  `END_TIME` timestamp NULL DEFAULT NULL COMMENT '结束时间',
  `USER_NAME` varchar(10) DEFAULT NULL COMMENT '接单人',
  `TASK_KIND` varchar(20) DEFAULT NULL COMMENT '任务类型',
  `START_RANK` varchar(10) DEFAULT NULL COMMENT '初始段位',
  `END_RANK` varchar(10) DEFAULT NULL COMMENT '预期段位',
  `MONEY` decimal(15,2) DEFAULT NULL COMMENT '结算金额',
  `STATUS` int(1) NOT NULL COMMENT '审核状态',
  `TASK_NUMBER` varchar(100) DEFAULT NULL COMMENT '任务序列号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_task
-- ----------------------------
INSERT INTO `t_task` VALUES ('1', '234234', '2021-01-31 00:00:00', '2021-02-11 00:00:00', '周耀武', '英雄联盟', '黄金', '白金', '200.00', '0', '1000001264471766');
INSERT INTO `t_task` VALUES ('2', '234234', '2021-02-01 00:00:00', '2021-02-27 00:00:00', '周聪', '英雄联盟', '黑铁', '黄金', '380.00', '0', '1000000648997039');
INSERT INTO `t_task` VALUES ('3', '234234', '2021-02-28 00:00:00', '2021-03-30 00:00:00', '曾蓝', '英雄联盟', '黄金', '钻石', '600.00', '1', '1000001218476344');
INSERT INTO `t_task` VALUES ('4', '46345', '2021-02-02 00:00:00', '2021-02-18 00:00:00', '周耀武', '英雄联盟', '白银', '白金', '334.01', '1', '1000001611413320');
INSERT INTO `t_task` VALUES ('8', '423', '2021-02-02 00:00:00', '2021-02-02 00:00:00', '李自立', '英雄联盟', '黄金', '钻石', '23424.00', '1', '1000001169360314');
INSERT INTO `t_task` VALUES ('9', '23', '2021-02-03 00:00:00', '2021-02-05 00:00:00', '周耀武', '英雄联盟', '黑铁', '黑铁', '33432342.00', '1', '1000001416206922');
