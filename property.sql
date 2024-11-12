/*
 Navicat Premium Data Transfer

 Source Server         : web开发
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : property

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 18/05/2024 15:53:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '物品名',
  `storage` int(0) NOT NULL COMMENT '资产名',
  `goodsType` int(0) NOT NULL COMMENT '分类',
  `count` int(0) NULL DEFAULT NULL COMMENT '数量',
  `remark` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (14, 'Macbook Pro', 2, 1, 500, '');
INSERT INTO `goods` VALUES (15, '晨光签字笔', 6, 5, 1000, '');
INSERT INTO `goods` VALUES (16, '得力打印机', 4, 2, 2000, '');
INSERT INTO `goods` VALUES (17, '得力油墨', 5, 1, 1000, '');
INSERT INTO `goods` VALUES (18, '华为平板', 2, 7, 1000, '');
INSERT INTO `goods` VALUES (19, '华硕天选', 2, 4, 22, '');

-- ----------------------------
-- Table structure for goodstype
-- ----------------------------
DROP TABLE IF EXISTS `goodstype`;
CREATE TABLE `goodstype`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类名',
  `remark` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goodstype
-- ----------------------------
INSERT INTO `goodstype` VALUES (1, '技术部', '无');
INSERT INTO `goodstype` VALUES (2, '财务部', '无');
INSERT INTO `goodstype` VALUES (3, '工厂部', '无');
INSERT INTO `goodstype` VALUES (4, '管理部', '无');
INSERT INTO `goodstype` VALUES (5, '法务部', '无');
INSERT INTO `goodstype` VALUES (6, '劳务部', '无');
INSERT INTO `goodstype` VALUES (7, '人力资源部', '无');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int(0) NOT NULL,
  `menuCode` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单编码',
  `menuName` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名字',
  `menuLevel` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单级别',
  `menuParentCode` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单的父code',
  `menuClick` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '点击触发的函数',
  `menuRight` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限 0超级管理员，1表示管理员，2表示普通用户，可以用逗号组合使用',
  `menuComponent` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `menuIcon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '001', '管理员管理', '1', NULL, 'Admin', '0', 'admin/AdminManage.vue', 'el-icon-s-custom');
INSERT INTO `menu` VALUES (2, '002', '用户管理', '1', NULL, 'User', '0,1', 'user/UserManage.vue', 'el-icon-user-solid');
INSERT INTO `menu` VALUES (3, '003', '资产管理', '1', NULL, 'Storage', '0,1', 'storage/StorageManage.vue', 'el-icon-office-building');
INSERT INTO `menu` VALUES (4, '004', '资产分部', '1', NULL, 'Goodstype', '0,1', 'goodstype/GoodstypeManage.vue', 'el-icon-menu');
INSERT INTO `menu` VALUES (5, '005', '物品管理', '1', NULL, 'Goods', '0,1,2', 'goods/GoodsManage.vue', 'el-icon-s-management');
INSERT INTO `menu` VALUES (6, '006', '资产处理记录', '1', NULL, 'Record', '0,1,2', 'record/RecordManage.vue', 'el-icon-s-order');

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods` int(0) NOT NULL COMMENT '货品id',
  `userId` int(0) NULL DEFAULT NULL COMMENT '取货人/补货人',
  `admin_id` int(0) NULL DEFAULT NULL COMMENT '操作人id',
  `count` int(0) NULL DEFAULT NULL COMMENT '数量',
  `createtime` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '申请时间',
  `remark` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'N' COMMENT '默认为N未审批，Y为审核通过',
  `recordtime` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of record
-- ----------------------------
INSERT INTO `record` VALUES (16, 19, 4, 1, -1, '2024-05-16 21:46:26', '', 'S', '2024-05-17 21:44:13');
INSERT INTO `record` VALUES (17, 19, 4, 1, 2, '2024-05-16 21:46:30', '', 'Y', '2024-05-17 21:44:24');
INSERT INTO `record` VALUES (18, 15, 4, 2, -2, '2024-05-16 21:46:41', '我要拿两支笔', 'N', NULL);
INSERT INTO `record` VALUES (19, 14, 3, 5, -1, '2024-05-17 21:51:48', '我要拿一本笔记本', 'N', NULL);

-- ----------------------------
-- Table structure for storage
-- ----------------------------
DROP TABLE IF EXISTS `storage`;
CREATE TABLE `storage`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资产名',
  `remark` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of storage
-- ----------------------------
INSERT INTO `storage` VALUES (1, '办公椅', '统一配置');
INSERT INTO `storage` VALUES (2, '电脑', '统一配置');
INSERT INTO `storage` VALUES (3, '办公桌', '统一配置');
INSERT INTO `storage` VALUES (4, '打印机', '统一配置');
INSERT INTO `storage` VALUES (5, '油墨', '统一配置');
INSERT INTO `storage` VALUES (6, '中性笔', '统一配置');
INSERT INTO `storage` VALUES (9, '文件夹', '统一配置');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名字',
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `age` int(0) NULL DEFAULT NULL,
  `sex` int(0) NULL DEFAULT NULL COMMENT '性别',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `role_id` int(0) NULL DEFAULT NULL COMMENT '角色 0超级管理员，1管理员，2普通账号',
  `isValid` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'Y' COMMENT '是否有效，Y有效，其他⽆效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'master', '超级管理员', '123', 18, 0, '13145677654', 0, 'Y');
INSERT INTO `user` VALUES (2, 'admin', '管理员', '123', 18, 1, '13234244545', 1, 'Y');
INSERT INTO `user` VALUES (3, 'user', '用户', '123', 12, 0, '13455555555', 2, 'Y');
INSERT INTO `user` VALUES (4, 'user1', '用户1', '123', 15, 1, '13145677654', 2, 'Y');
INSERT INTO `user` VALUES (5, 'admin1', '管理员1', '123', 16, 0, '13455555555', 1, 'Y');

SET FOREIGN_KEY_CHECKS = 1;
