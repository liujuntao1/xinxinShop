/*
 Navicat Premium Data Transfer

 Source Server         : localhost-mysql
 Source Server Type    : MySQL
 Source Server Version : 50742
 Source Host           : localhost:3306
 Source Schema         : xinxinshop

 Target Server Type    : MySQL
 Target Server Version : 50742
 File Encoding         : 65001

 Date: 07/09/2023 11:09:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户ID',
  `user_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `log_type` int(10) NULL DEFAULT NULL COMMENT '日志类型（1：操作日志2：登录日志）',
  `log_content` varchar(225) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '日志内容',
  `operation_ip` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'ip',
  `is_deleted` int(10) NOT NULL DEFAULT 1 COMMENT '删除状态（1：正常0：已删除）',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES (6, NULL, NULL, 1, '用户分页列表', NULL, 1, '2023-09-07 09:52:36', '2023-09-07 09:52:36');
INSERT INTO `sys_log` VALUES (7, NULL, NULL, 1, '用户分页列表', NULL, 1, '2023-09-07 09:55:40', '2023-09-07 09:55:40');
INSERT INTO `sys_log` VALUES (8, NULL, NULL, 1, '用户分页列表', NULL, 1, '2023-09-07 09:56:19', '2023-09-07 09:56:19');
INSERT INTO `sys_log` VALUES (9, NULL, NULL, 1, '用户分页列表', NULL, 1, '2023-09-07 09:56:24', '2023-09-07 09:56:24');
INSERT INTO `sys_log` VALUES (10, NULL, NULL, 1, '新增用户', NULL, 1, '2023-09-07 09:58:22', '2023-09-07 09:58:22');
INSERT INTO `sys_log` VALUES (11, NULL, NULL, 1, '用户分页列表', NULL, 1, '2023-09-07 09:59:12', '2023-09-07 09:59:12');
INSERT INTO `sys_log` VALUES (12, NULL, NULL, 1, '用户分页列表', NULL, 1, '2023-09-07 10:00:54', '2023-09-07 10:00:54');
INSERT INTO `sys_log` VALUES (13, NULL, NULL, 1, '用户分页列表', NULL, 1, '2023-09-07 10:01:31', '2023-09-07 10:01:31');
INSERT INTO `sys_log` VALUES (14, NULL, NULL, 1, '用户分页列表', NULL, 1, '2023-09-07 10:05:32', '2023-09-07 10:05:32');
INSERT INTO `sys_log` VALUES (15, NULL, NULL, 1, '用户分页列表', NULL, 1, '2023-09-07 10:08:33', '2023-09-07 10:08:33');
INSERT INTO `sys_log` VALUES (16, NULL, NULL, 1, '新增用户', NULL, 1, '2023-09-07 10:09:52', '2023-09-07 10:09:52');
INSERT INTO `sys_log` VALUES (17, NULL, NULL, 1, '新增用户', NULL, 1, '2023-09-07 10:09:53', '2023-09-07 10:09:53');
INSERT INTO `sys_log` VALUES (18, NULL, NULL, 1, '新增用户', NULL, 1, '2023-09-07 10:09:54', '2023-09-07 10:09:54');
INSERT INTO `sys_log` VALUES (19, NULL, NULL, 1, '用户分页列表', NULL, 1, '2023-09-07 10:10:28', '2023-09-07 10:10:28');
INSERT INTO `sys_log` VALUES (20, NULL, NULL, 1, '用户分页列表', NULL, 1, '2023-09-07 10:10:57', '2023-09-07 10:10:57');
INSERT INTO `sys_log` VALUES (21, NULL, NULL, 1, '用户分页列表', NULL, 1, '2023-09-07 10:11:01', '2023-09-07 10:11:01');
INSERT INTO `sys_log` VALUES (22, NULL, NULL, 1, '用户分页列表', NULL, 1, '2023-09-07 10:11:29', '2023-09-07 10:11:29');
INSERT INTO `sys_log` VALUES (23, NULL, NULL, 1, '用户分页列表', NULL, 1, '2023-09-07 10:12:26', '2023-09-07 10:12:26');
INSERT INTO `sys_log` VALUES (24, NULL, NULL, 1, '用户分页列表', NULL, 1, '2023-09-07 10:13:58', '2023-09-07 10:13:58');
INSERT INTO `sys_log` VALUES (25, NULL, NULL, 1, '用户分页列表', NULL, 1, '2023-09-07 10:23:06', '2023-09-07 10:23:06');
INSERT INTO `sys_log` VALUES (26, NULL, NULL, 1, '用户分页列表', NULL, 1, '2023-09-07 10:23:55', '2023-09-07 10:23:55');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单编码',
  `url` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单地址',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '上级菜单',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `status` int(10) NULL DEFAULT NULL COMMENT '菜单状态（1：启用0:禁用）',
  `is_deleted` int(10) NOT NULL DEFAULT 1 COMMENT '删除状态（1：正常0：已删除）',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_role`;
CREATE TABLE `sys_menu_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menu_id` int(11) NULL DEFAULT NULL COMMENT '菜单ID',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色ID',
  `is_deleted` int(10) NOT NULL DEFAULT 1 COMMENT '删除状态（1：正常0：已删除）',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色编码',
  `description` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `status` int(10) NULL DEFAULT NULL COMMENT '启用状态（1：启用0:禁用）',
  `is_deleted` int(10) NOT NULL DEFAULT 1 COMMENT '删除状态（1：正常0：已删除）',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `nick_name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `user_name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `status` int(10) NULL DEFAULT NULL COMMENT '用户状态（1：启用0:禁用）',
  `pwd` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `is_deleted` int(10) NOT NULL DEFAULT 1 COMMENT '删除状态（1：正常0：已删除）',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'John Doe', 'johndoe', '1234567890', 'johndoe@example.com', 'http://example.com/avatar.jpg', NULL, 'e10adc3949ba59abbe56e057f20f883e', 1, '2023-09-07 09:58:22', '2023-09-07 09:58:22');
INSERT INTO `sys_user` VALUES (2, 'John Doe', 'johndoe', '1234567890', 'johndoe@example.com', 'http://example.com/avatar.jpg', NULL, 'e10adc3949ba59abbe56e057f20f883e', 1, '2023-09-07 10:09:52', '2023-09-07 10:09:52');
INSERT INTO `sys_user` VALUES (3, 'John Doe', 'johndoe', '1234567890', 'johndoe@example.com', 'http://example.com/avatar.jpg', NULL, 'e10adc3949ba59abbe56e057f20f883e', 1, '2023-09-07 10:09:53', '2023-09-07 10:09:53');
INSERT INTO `sys_user` VALUES (4, 'John Doe', 'johndoe', '1234567890', 'johndoe@example.com', 'http://example.com/avatar.jpg', NULL, 'e10adc3949ba59abbe56e057f20f883e', 1, '2023-09-07 10:09:54', '2023-09-07 10:09:54');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户ID',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色ID',
  `is_deleted` int(10) NOT NULL DEFAULT 1 COMMENT '删除状态（1：正常0：已删除）',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
