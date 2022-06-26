/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : task

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2021-03-08 10:19:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_create_task
-- ----------------------------
DROP TABLE IF EXISTS `t_create_task`;
CREATE TABLE `t_create_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `task_name` varchar(50) NOT NULL COMMENT '任务名字',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `user_name` varchar(10) DEFAULT NULL COMMENT '创建者',
  `is_finished` int(1) NOT NULL DEFAULT '0' COMMENT '是否完成 0:未完成；1：完成',
  `flow_id` int(2) NOT NULL DEFAULT '1' COMMENT '节点id',
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_create_task
-- ----------------------------
