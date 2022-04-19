/*
 Navicat Premium Data Transfer

 Source Server         : 本地连接
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : db_003

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 19/04/2022 17:10:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for salary_info
-- ----------------------------
DROP TABLE IF EXISTS `salary_info`;
CREATE TABLE `salary_info`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `empId` int NULL DEFAULT NULL COMMENT '员工ID',
  `salaryMonth` varchar(20) CHARACTER SET utf8 COLLATE utf8_estonian_ci NULL DEFAULT NULL COMMENT '发工资的月份',
  `actual` decimal(30, 0) NULL DEFAULT NULL COMMENT '发的钱数',
  `createtime` datetime NULL DEFAULT NULL COMMENT '发工资的时间',
  `createby` int NULL DEFAULT NULL COMMENT '发工资的人',
  `bonus` decimal(10, 2) NULL DEFAULT NULL COMMENT '奖金',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of salary_info
-- ----------------------------
INSERT INTO `salary_info` VALUES (5, 1, '2022-07', 18100, '2022-04-19 10:30:17', 33, 800.00);
INSERT INTO `salary_info` VALUES (6, 2, '2022-07', 18100, '2022-04-19 10:37:46', 33, 800.00);
INSERT INTO `salary_info` VALUES (7, 4, '2022-07', 12500, '2022-04-19 10:38:08', 33, 200.00);
INSERT INTO `salary_info` VALUES (8, 3, '2022-07', 12100, '2022-04-19 10:38:23', 33, 800.00);
INSERT INTO `salary_info` VALUES (9, 5, '2022-07', 1017300, '2022-04-19 11:00:57', 33, 1000000.00);
INSERT INTO `salary_info` VALUES (10, 3, '2022-08', 11100, '2022-04-19 14:59:18', 33, 800.00);

SET FOREIGN_KEY_CHECKS = 1;
