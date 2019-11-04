/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50642
 Source Host           : localhost:3306
 Source Schema         : bbs

 Target Server Type    : MySQL
 Target Server Version : 50642
 File Encoding         : 65001

 Date: 04/11/2019 16:17:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority`  (
  `authorityid` int(15) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `authorityname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限名',
  PRIMARY KEY (`authorityid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for authority_role
-- ----------------------------
DROP TABLE IF EXISTS `authority_role`;
CREATE TABLE `authority_role`  (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `authorityid` int(15) NOT NULL COMMENT '权限表id',
  `position` int(15) NOT NULL COMMENT '职位',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for district
-- ----------------------------
DROP TABLE IF EXISTS `district`;
CREATE TABLE `district`  (
  `districtid` int(15) NOT NULL AUTO_INCREMENT COMMENT '论坛分区id',
  `name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '论坛分区名',
  `roleid` int(15) NOT NULL COMMENT '论坛分区版主id',
  PRIMARY KEY (`districtid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post`  (
  `postid` int(15) NOT NULL AUTO_INCREMENT COMMENT '帖子id',
  `userid` int(15) NOT NULL COMMENT '发帖人id',
  `sectionid` int(15) NOT NULL COMMENT '所属版块id',
  `content` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '内容',
  `title` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
  `postdate` date NOT NULL COMMENT '发帖时间',
  `posttype` int(1) NOT NULL COMMENT '0为普通主题，1为投票主题，2为公告',
  `postip` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '发布IP',
  `readnum` int(10) NOT NULL COMMENT '阅读次数',
  `replynum` int(10) NOT NULL COMMENT '回复总数',
  `hot` int(1) NOT NULL COMMENT '精华帖',
  `hotid` int(15) NULL DEFAULT NULL COMMENT '热帖推荐人id',
  `edituser` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '编辑用户名',
  `edittime` date NOT NULL COMMENT '编辑时间',
  `hotreason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '推荐热帖原因',
  PRIMARY KEY (`postid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for reply
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply`  (
  `replyid` int(15) NOT NULL,
  `postid` int(15) NOT NULL COMMENT '帖子id',
  `userid` int(15) NOT NULL COMMENT '回复人id',
  `content` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '回复内容',
  `replytime` date NOT NULL COMMENT '回复时间',
  PRIMARY KEY (`replyid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `roleid` int(15) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名，职位',
  `position` int(15) NOT NULL COMMENT '职位',
  PRIMARY KEY (`roleid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for section
-- ----------------------------
DROP TABLE IF EXISTS `section`;
CREATE TABLE `section`  (
  `sectionid` int(15) NOT NULL AUTO_INCREMENT COMMENT '版块id',
  `districtid` int(15) NOT NULL COMMENT '所属的分区id',
  `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '版块名称',
  `roleid` int(15) NOT NULL COMMENT '版主id',
  PRIMARY KEY (`sectionid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for shortmessage
-- ----------------------------
DROP TABLE IF EXISTS `shortmessage`;
CREATE TABLE `shortmessage`  (
  `messageid` int(15) NOT NULL AUTO_INCREMENT COMMENT '短消息编号',
  `message` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '短消息',
  `senderid` int(15) NOT NULL COMMENT '发送人id',
  `receiverid` int(15) NOT NULL COMMENT '接收人id',
  `title` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '消息标题',
  `createtime` date NOT NULL COMMENT '创建时间',
  `status` int(1) NOT NULL COMMENT '消息状态',
  PRIMARY KEY (`messageid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userid` int(15) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户昵称',
  `account` int(15) NOT NULL COMMENT '账户，用于登录',
  `password` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `signature` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '个人签名',
  `introduce` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '自我介绍',
  `qq` int(15) NULL DEFAULT NULL COMMENT 'qq',
  `web` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '个人网站',
  `place` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '籍贯',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `sex` int(1) NULL DEFAULT NULL COMMENT '性别',
  `img` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像uri',
  `roleid` int(15) NOT NULL COMMENT '角色id',
  `e_mail` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '电子邮箱',
  `problemhint` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码提示问题',
  `answerhint` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码提示答案',
  `registertime` date NOT NULL COMMENT '注册时间',
  `logintime` date NOT NULL COMMENT '登陆时间',
  `loginIP` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录ip',
  `lastlogintime` date NULL DEFAULT NULL COMMENT '上次登录时间',
  `lastloginIP` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上次登录ip',
  PRIMARY KEY (`userid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
