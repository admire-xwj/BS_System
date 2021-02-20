/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : sys_database

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 20/02/2021 22:34:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父部门id',
  `ancestors` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '部门名称',
  `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `leader` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 203 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (100, 0, '0', '网易科技', 0, 'shier', '15888888887', 'shier@qq.com', '0', '0', 'admin', '2021-02-14 18:58:17', 'admin', '2021-02-15 00:00:00');
INSERT INTO `sys_dept` VALUES (101, 100, '0,100', '深圳总公司', 1, 'shier', '15888888887', 'shier@qq.com', '0', '0', 'admin', '2021-02-14 18:58:17', 'admin', '2021-02-14 00:00:00');
INSERT INTO `sys_dept` VALUES (102, 100, '0,100', '长沙分公司', 2, 'shier', '15888888888', 'shier@qq.com', '0', '0', 'admin', '2021-02-14 18:58:17', '', NULL);
INSERT INTO `sys_dept` VALUES (103, 101, '0,100,101', '研发部门', 1, 'shier', '15888888888', 'shier@qq.com', '0', '0', 'admin', '2021-02-14 18:58:17', '', NULL);
INSERT INTO `sys_dept` VALUES (104, 101, '0,100,101', '市场部门', 2, 'shier', '15888888888', 'shier@qq.com', '0', '0', 'admin', '2021-02-14 18:58:17', 'admin', '2021-02-15 00:00:00');
INSERT INTO `sys_dept` VALUES (105, 101, '0,100,101', '测试部门', 3, 'shier', '15888888888', 'shier@qq.com', '0', '0', 'admin', '2021-02-14 18:58:17', '', NULL);
INSERT INTO `sys_dept` VALUES (106, 101, '0,100,101', '财务部门', 4, 'shier', '15888888888', 'shier@qq.com', '0', '0', 'admin', '2021-02-14 18:58:17', '', NULL);
INSERT INTO `sys_dept` VALUES (107, 101, '0,100,101', '运维部门', 5, 'shier', '15888888888', 'shier@qq.com', '0', '0', 'admin', '2021-02-14 18:58:17', '', NULL);
INSERT INTO `sys_dept` VALUES (108, 102, '0,100,102', '市场部门', 1, 'shier', '15888888888', 'shier@qq.com', '0', '0', 'admin', '2021-02-14 18:58:17', '', NULL);
INSERT INTO `sys_dept` VALUES (109, 102, '0,100,102', '财务部门', 2, 'shier', '15888888888', 'shier@qq.com', '0', '0', 'admin', '2021-02-14 18:58:17', '', NULL);
INSERT INTO `sys_dept` VALUES (200, 101, NULL, '人事部门', NULL, '二哈哈', '15888888888', 'shier@qq.com', '0', '0', 'admin', '2021-02-15 00:00:00', 'admin', '2021-02-15 00:00:00');
INSERT INTO `sys_dept` VALUES (202, 200, NULL, '客服中心', NULL, 'shier', '15888888888', 'shier@qq.com', '0', '0', 'admin', '2021-02-15 00:00:00', 'admin', '2021-02-15 00:00:00');

-- ----------------------------
-- Table structure for sys_evaluate
-- ----------------------------
DROP TABLE IF EXISTS `sys_evaluate`;
CREATE TABLE `sys_evaluate`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(20) NULL DEFAULT 0 COMMENT '用户id',
  `dept_evaluate` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '部门评价',
  `conpany_evaluate` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '公司评价',
  `self_evaluate` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '个人评价',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 200 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '评价表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_evaluate
-- ----------------------------
INSERT INTO `sys_evaluate` VALUES (1, 1, 'very good', 'very good', 'very good', '0', 'admin', '2021-02-19 00:00:00', '', NULL, '还须继续努力');
INSERT INTO `sys_evaluate` VALUES (2, 2, 'very good good', 'very good good', 'very good good', '0', 'admin', '2021-02-19 00:00:00', '', NULL, '还须继续努力');
INSERT INTO `sys_evaluate` VALUES (200, 2, '你是个汗啊汗', '你是个汗啊汗', '你是个汗啊汗', '0', 'admin', '2021-02-19 00:00:00', 'admin', '2021-02-19 00:00:00', '你是个汗啊汗');

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file`  (
  `file_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '档案编号',
  `file_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '档案名称',
  `dept_id` bigint(20) NULL DEFAULT 0 COMMENT '所属部门',
  `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `file_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '文件路径',
  `file_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '#' COMMENT '档案类型',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '上传者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`file_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2008 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '档案信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_file
-- ----------------------------
INSERT INTO `sys_file` VALUES (1, 'a.txt', 109, 1, '', '0', '0', 'admin', '2021-02-16 00:00:00', 'admin', '2021-02-17 00:00:00', '上传文件1');
INSERT INTO `sys_file` VALUES (2, 'b.txt', 200, 2, '', '0', '0', 'admin', '2021-02-16 00:00:00', '', NULL, '上传文件2');
INSERT INTO `sys_file` VALUES (2000, 'data.json', 104, NULL, 'E:\\IDEA\\IDEA_Work\\project3\\bs_system\\src\\main\\resources\\templates\\temp', '0', '0', 'admin', '2021-02-17 00:00:00', NULL, NULL, '111');
INSERT INTO `sys_file` VALUES (2001, '统一身份认证SDK说明手册-支付宝V1.0.6.docx', 104, NULL, 'E:\\IDEA\\IDEA_Work\\project3\\bs_system\\src\\main\\resources\\templates\\temp', '0', '0', 'admin', '2021-02-17 00:00:00', NULL, NULL, '111');
INSERT INTO `sys_file` VALUES (2002, 'LcinsuredMapper.xml', 104, NULL, 'E:\\IDEA\\IDEA_Work\\project3\\bs_system\\src\\main\\resources\\templates\\temp', '0', '0', 'admin', '2021-02-17 00:00:00', NULL, NULL, '222');
INSERT INTO `sys_file` VALUES (2003, 'W020210126422674030313.xls', 202, NULL, 'E:\\IDEA\\IDEA_Work\\project3\\bs_system\\src\\main\\resources\\templates\\temp', '1', '0', 'admin', '2021-02-17 00:00:00', NULL, NULL, '3333');
INSERT INTO `sys_file` VALUES (2004, '批处理清单.txt', 200, NULL, 'E:\\IDEA\\IDEA_Work\\project3\\bs_system\\src\\main\\resources\\templates\\temp', '0', '0', 'admin', '2021-02-17 00:00:00', NULL, NULL, '111');
INSERT INTO `sys_file` VALUES (2005, 'GrpPaySendToBank.java', 105, NULL, 'E:\\IDEA\\IDEA_Work\\project3\\bs_system\\src\\main\\resources\\templates\\temp', '0', '0', 'admin', '2021-02-17 00:00:00', NULL, NULL, '3363');
INSERT INTO `sys_file` VALUES (2006, 'jquery.params.js', 104, NULL, 'E:\\IDEA\\IDEA_Work\\project3\\bs_system\\src\\main\\resources\\templates\\temp', '1', '0', 'admin', '2021-02-17 00:00:00', NULL, NULL, '999');
INSERT INTO `sys_file` VALUES (2007, '批处理清单.txt', 107, NULL, 'E:\\IDEA\\IDEA_Work\\project3\\bs_system\\src\\main\\resources\\templates\\temp', '1', '0', 'admin', '2021-02-17 00:00:00', NULL, NULL, '5555');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父菜单ID',
  `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '#' COMMENT '请求地址',
  `target` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '属性',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统管理', 0, 1, '', '_self', 'fa fa-gear', 'admin', '2021-02-06 18:27:35', '', NULL, '系统管理目录');
INSERT INTO `sys_menu` VALUES ('100', '用户管理', 1, 1, '/system/user', '_self', 'fa fa-user-o', 'admin', '2021-02-06 19:59:42', '', NULL, '用户管理菜单');
INSERT INTO `sys_menu` VALUES ('101', '角色管理', 1, 2, '/system/role', '_self', 'fa fa-user-secret', 'admin', '2021-02-06 19:59:42', '', NULL, '角色管理菜单');
INSERT INTO `sys_menu` VALUES ('102', '部门管理', 1, 4, '/system/dept', '_self', 'fa fa-bullhorn', 'admin', '2021-02-06 19:59:42', '', NULL, '部门管理菜单');
INSERT INTO `sys_menu` VALUES ('105', '档案查询', 2, 6, '/system/file', '_self', 'fa fa-bookmark-o', 'admin', '2021-02-17 18:35:00', '', NULL, '档案查询菜单');
INSERT INTO `sys_menu` VALUES ('106', '档案上传', 2, 7, '/system/upload', '_self', 'fa fa-wpforms', 'admin', '2021-02-17 18:35:00', '', NULL, '档案上传菜单');
INSERT INTO `sys_menu` VALUES ('107', '薪酬管理', 3, 8, '/system/pay', '_self', 'fa fa-sun-o', 'admin', '2021-02-17 18:35:00', '', NULL, '薪酬管理菜单');
INSERT INTO `sys_menu` VALUES ('108', '评价管理', 4, 9, '/system/evaluate', '_self', 'fa fa-bullhorn', 'admin', '2021-02-17 18:35:00', '', NULL, '评价管理菜单');
INSERT INTO `sys_menu` VALUES ('2', '档案管理', 0, 2, '', '_self', 'fa fa-video-camera', 'admin', '2021-02-06 18:27:35', '', NULL, '档案管理目录');
INSERT INTO `sys_menu` VALUES ('3', '财务管理', 0, 3, '', '_self', 'fa fa-bars', 'admin', '2021-02-06 18:27:35', '', NULL, '财务管理目录');
INSERT INTO `sys_menu` VALUES ('4', '个人评价', 0, 4, '', '_self', 'fa fa-location-arrow', 'admin', '2021-02-06 18:27:35', '', NULL, '个人评价目录');

-- ----------------------------
-- Table structure for sys_pay
-- ----------------------------
DROP TABLE IF EXISTS `sys_pay`;
CREATE TABLE `sys_pay`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '员工id',
  `base_pay` double(16, 2) NULL DEFAULT 0.00 COMMENT '基本工资',
  `bonus` double(16, 2) NULL DEFAULT 0.00 COMMENT '奖金',
  `five_insur` double(16, 2) NULL DEFAULT 0.00 COMMENT '五险扣除',
  `other_deduc` double(16, 2) NULL DEFAULT 0.00 COMMENT '其他扣除',
  `other_wages` double(16, 2) NULL DEFAULT 0.00 COMMENT '其它补助',
  `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '上传者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '上传时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '薪资信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_pay
-- ----------------------------
INSERT INTO `sys_pay` VALUES (1, 5055.08, 232.56, 888.00, 0.00, 100.00, 0, 'admin', '2021-02-18 00:00:00', '', NULL, '');
INSERT INTO `sys_pay` VALUES (2, 6158.89, 200.20, 888.00, 0.00, 200.00, 1, 'admin', '2021-02-18 00:00:00', '', NULL, '');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'admin', 1, '1', '0', '0', 'admin', '2021-02-15 21:44:07', 'admin', '2021-02-15 00:00:00', '超级管理员');
INSERT INTO `sys_role` VALUES (2, '普通角色', 'common', 2, '2', '0', '0', 'admin', '2021-02-15 21:44:07', 'admin', '2021-02-15 00:00:00', '普通角色');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户ID',
  `dept_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '部门ID',
  `login_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '登录账号',
  `user_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '用户昵称',
  `user_type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '00' COMMENT '用户类型（00系统用户 01注册用户）',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '手机号码',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '头像路径',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '密码',
  `salt` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '盐加密',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `pwd_update_date` datetime(0) NULL DEFAULT NULL COMMENT '密码最后更新时间',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '103', 'admin', '王大大', '00', 'wangdada@163.com.cn', '15888888888', '1', '', '123456', '111111', '0', '0', '127.0.0.1', '2021-02-10 13:24:25', '2021-02-10 13:24:25', 'admin', '2021-02-10 13:24:25', '', NULL, '管理员');
INSERT INTO `sys_user` VALUES ('2', '105', 'xidada', '习大大', '00', 'ry@qq.com', '15666666666', '1', '', '123456', '222222', '0', '0', '127.0.0.1', '2021-02-10 13:24:25', '2021-02-10 13:24:25', 'admin', '2021-02-10 13:24:25', '', NULL, '测试员');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户和角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2);

SET FOREIGN_KEY_CHECKS = 1;
