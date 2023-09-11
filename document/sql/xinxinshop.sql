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

 Date: 11/09/2023 15:03:12
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
) ENGINE = InnoDB AUTO_INCREMENT = 56 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES (33, 1, 'johndoe', 1, '用户分页列表', '192.168.1.111', 1, '2023-09-07 14:37:11', '2023-09-07 14:37:11');
INSERT INTO `sys_log` VALUES (34, 1, 'johndoe', 1, '用户分页列表', '192.168.1.111', 1, '2023-09-07 14:40:58', '2023-09-07 14:40:58');
INSERT INTO `sys_log` VALUES (35, 1, 'johndoe', 1, '用户分页列表', '192.168.1.111', 1, '2023-09-07 14:40:59', '2023-09-07 14:40:59');
INSERT INTO `sys_log` VALUES (36, 1, 'johndoe', 1, '用户分页列表', '192.168.1.111', 1, '2023-09-07 14:41:14', '2023-09-07 14:41:14');
INSERT INTO `sys_log` VALUES (37, 1, 'johndoe', 1, '用户分页列表', '192.168.1.111', 1, '2023-09-07 14:41:14', '2023-09-07 14:41:14');
INSERT INTO `sys_log` VALUES (38, 1, 'johndoe', 1, '用户分页列表', '192.168.1.111', 1, '2023-09-07 14:56:20', '2023-09-07 14:56:20');
INSERT INTO `sys_log` VALUES (39, 1, 'johndoe', 1, '用户分页列表', '192.168.1.111', 1, '2023-09-07 15:15:10', '2023-09-07 15:15:10');
INSERT INTO `sys_log` VALUES (40, 1, 'johndoe', 1, '用户分页列表', '192.168.1.111', 1, '2023-09-07 15:17:36', '2023-09-07 15:17:36');
INSERT INTO `sys_log` VALUES (41, 1, 'johndoe', 1, '用户分页列表', '192.168.1.111', 1, '2023-09-07 15:17:54', '2023-09-07 15:17:54');
INSERT INTO `sys_log` VALUES (42, 1, 'johndoe', 1, '用户分页列表', '192.168.1.111', 1, '2023-09-07 15:18:06', '2023-09-07 15:18:06');
INSERT INTO `sys_log` VALUES (43, 1, 'johndoe', 1, '用户分页列表', '192.168.1.111', 1, '2023-09-07 15:18:57', '2023-09-07 15:18:57');
INSERT INTO `sys_log` VALUES (44, 1, 'johndoe', 1, '用户分页列表', '192.168.1.111', 1, '2023-09-07 15:19:03', '2023-09-07 15:19:03');
INSERT INTO `sys_log` VALUES (45, 1, 'johndoe', 1, '用户分页列表', '192.168.1.111', 1, '2023-09-07 15:19:03', '2023-09-07 15:19:03');
INSERT INTO `sys_log` VALUES (46, 1, 'johndoe', 1, '用户分页列表', '192.168.1.111', 1, '2023-09-07 15:19:04', '2023-09-07 15:19:04');
INSERT INTO `sys_log` VALUES (47, 1, 'johndoe', 1, '用户分页列表', '192.168.1.111', 1, '2023-09-07 15:19:04', '2023-09-07 15:19:04');
INSERT INTO `sys_log` VALUES (48, 1, 'johndoe', 1, '用户分页列表', '192.168.1.111', 1, '2023-09-07 15:19:04', '2023-09-07 15:19:04');
INSERT INTO `sys_log` VALUES (49, 1, 'johndoe', 1, '用户分页列表', '192.168.1.111', 1, '2023-09-07 15:19:04', '2023-09-07 15:19:04');
INSERT INTO `sys_log` VALUES (50, 1, 'johndoe', 1, '用户分页列表', '192.168.1.111', 1, '2023-09-07 15:19:04', '2023-09-07 15:19:04');
INSERT INTO `sys_log` VALUES (51, 1, 'johndoe', 1, '用户分页列表', '192.168.1.111', 1, '2023-09-07 15:29:52', '2023-09-07 15:29:52');
INSERT INTO `sys_log` VALUES (52, 1, 'johndoe', 1, '用户分页列表', '192.168.1.111', 1, '2023-09-07 15:29:53', '2023-09-07 15:29:53');
INSERT INTO `sys_log` VALUES (53, 1, 'johndoe', 1, '用户分页列表', '192.168.1.111', 1, '2023-09-07 15:29:57', '2023-09-07 15:29:57');
INSERT INTO `sys_log` VALUES (54, 1, 'johndoe', 1, '用户分页列表', '192.168.1.111', 1, '2023-09-08 15:25:35', '2023-09-08 15:25:35');
INSERT INTO `sys_log` VALUES (55, 1, 'johndoe', 1, '用户分页列表', '192.168.1.111', 1, '2023-09-08 15:25:43', '2023-09-08 15:25:43');

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '权限管理', 'permission', NULL, NULL, 0, NULL, 1, 1, '2023-09-11 11:13:16', '2023-09-11 11:13:22');

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu_role
-- ----------------------------
INSERT INTO `sys_menu_role` VALUES (1, 1, 1, 1, '2023-09-11 11:13:28', '2023-09-11 11:13:28');

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员', 'admin', NULL, 1, 1, '2023-09-11 11:12:34', '2023-09-11 11:12:34');

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
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'John Doe', 'johndoe', '1234567890', 'johndoe@example.com', 'http://example.com/avatar.jpg', 1, 'e10adc3949ba59abbe56e057f20f883e', 1, '2023-09-07 09:58:22', '2023-09-07 14:14:22');
INSERT INTO `sys_user` VALUES (5, 'admin', 'admin', '1234567890', 'admin@qq.com', NULL, 1, 'e10adc3949ba59abbe56e057f20f883e', 1, '2023-09-08 17:54:00', '2023-09-08 17:54:03');

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 5, 1, 1, '2023-09-11 11:12:41', '2023-09-11 11:15:31');

SET FOREIGN_KEY_CHECKS = 1;
