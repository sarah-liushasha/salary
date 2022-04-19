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

 Date: 19/04/2022 17:10:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `age` int NULL DEFAULT NULL COMMENT '年龄',
  `sex` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `deptId` int NULL DEFAULT NULL COMMENT '科室主键',
  `performance` decimal(10, 0) NULL DEFAULT NULL COMMENT '绩效工资',
  `insure` decimal(30, 0) NULL DEFAULT NULL COMMENT '保险',
  `fund` decimal(30, 0) NULL DEFAULT NULL COMMENT '公积金',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (1, '张三', 28, '女', 17, 8000, 500, 200);
INSERT INTO `employee` VALUES (2, '王二', 19, '男', 7, 10000, 500, 200);
INSERT INTO `employee` VALUES (3, '李四', 20, '男', 6, 6000, 500, 200);
INSERT INTO `employee` VALUES (4, '孙六', 30, '女', 8, 10000, 500, 200);
INSERT INTO `employee` VALUES (5, '刘数', 18, '女', 7, 10000, 500, 200);

SET FOREIGN_KEY_CHECKS = 1;
