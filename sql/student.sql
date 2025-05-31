/*
 Navicat Premium Data Transfer

 Source Server         : mysql01_192.168.47.10
 Source Server Type    : MySQL
 Source Server Version : 50733
 Source Host           : 192.168.47.10:3306
 Source Schema         : d2

 Target Server Type    : MySQL
 Target Server Version : 50733
 File Encoding         : 65001

 Date: 31/05/2025 18:07:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学生姓名',
  `age` tinyint(4) NULL DEFAULT NULL COMMENT '年龄',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, '张三_slave', 23);
INSERT INTO `student` VALUES (2, '李四', 24);
INSERT INTO `student` VALUES (3, '王五', 25);
INSERT INTO `student` VALUES (4, '赵六', 26);
INSERT INTO `student` VALUES (5, '周七', 27);
INSERT INTO `student` VALUES (6, '阿花', 17);
INSERT INTO `student` VALUES (7, '阿星', 18);
INSERT INTO `student` VALUES (8, '阿强', 19);

SET FOREIGN_KEY_CHECKS = 1;
