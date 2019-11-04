/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.5.60 : Database - bbs
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bbs` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `bbs`;

/*Table structure for table `authority` */

DROP TABLE IF EXISTS `authority`;

CREATE TABLE `authority` (
  `authorityid` int(15) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `authorityname` char(20) NOT NULL COMMENT '权限名',
  PRIMARY KEY (`authorityid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `authority` */

/*Table structure for table `authority_role` */

DROP TABLE IF EXISTS `authority_role`;

CREATE TABLE `authority_role` (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `authorityid` int(15) NOT NULL COMMENT '权限表id',
  `position` int(15) NOT NULL COMMENT '职位',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `authority_role` */

/*Table structure for table `district` */

DROP TABLE IF EXISTS `district`;

CREATE TABLE `district` (
  `districtid` int(15) NOT NULL AUTO_INCREMENT COMMENT '论坛分区id',
  `name` varchar(15) NOT NULL COMMENT '论坛分区名',
  `roleid` int(15) NOT NULL COMMENT '论坛分区版主id',
  PRIMARY KEY (`districtid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `district` */

/*Table structure for table `post` */

DROP TABLE IF EXISTS `post`;

CREATE TABLE `post` (
  `postid` int(15) NOT NULL AUTO_INCREMENT COMMENT '帖子id',
  `userid` int(15) NOT NULL COMMENT '发帖人id',
  `sectionid` int(15) NOT NULL COMMENT '所属版块id',
  `content` varchar(1024) NOT NULL COMMENT '内容',
  `title` varchar(30) NOT NULL COMMENT '标题',
  `postdate` date NOT NULL COMMENT '发帖时间',
  `posttype` int(1) NOT NULL COMMENT '0为普通主题，1为投票主题，2为公告',
  `postip` varchar(40) DEFAULT NULL COMMENT '发布IP',
  `readnum` int(10) NOT NULL COMMENT '阅读次数',
  `replynum` int(10) NOT NULL COMMENT '回复总数',
  `hot` int(1) NOT NULL COMMENT '是否置顶',
  `hotid` int(15) DEFAULT NULL COMMENT '热帖推荐人id',
  `edituser` char(20) NOT NULL COMMENT '编辑用户名',
  `edittime` date NOT NULL COMMENT '编辑时间',
  `hotreason` char(200) NOT NULL COMMENT '推荐热帖原因',
  PRIMARY KEY (`postid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `post` */

/*Table structure for table `reply` */

DROP TABLE IF EXISTS `reply`;

CREATE TABLE `reply` (
  `replyid` int(15) NOT NULL,
  `postid` int(15) NOT NULL COMMENT '帖子id',
  `userid` int(15) NOT NULL COMMENT '回复人id',
  `content` varchar(200) NOT NULL COMMENT '回复内容',
  `replytime` date NOT NULL COMMENT '回复时间',
  PRIMARY KEY (`replyid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `reply` */

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `roleid` int(15) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `name` varchar(15) NOT NULL COMMENT '角色名，职位',
  `position` int(15) NOT NULL COMMENT '职位',
  PRIMARY KEY (`roleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `role` */

/*Table structure for table `section` */

DROP TABLE IF EXISTS `section`;

CREATE TABLE `section` (
  `sectionid` int(15) NOT NULL AUTO_INCREMENT COMMENT '版块id',
  `districtid` int(15) NOT NULL COMMENT '所属的分区id',
  `name` varchar(10) NOT NULL COMMENT '版块名称',
  `roleid` int(15) NOT NULL COMMENT '版主id',
  PRIMARY KEY (`sectionid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `section` */

/*Table structure for table `shortmessage` */

DROP TABLE IF EXISTS `shortmessage`;

CREATE TABLE `shortmessage` (
  `messageid` int(15) NOT NULL AUTO_INCREMENT COMMENT '短消息编号',
  `message` char(200) DEFAULT NULL COMMENT '短消息',
  `senderid` int(15) NOT NULL COMMENT '发送人id',
  `receiverid` int(15) NOT NULL COMMENT '接收人id',
  `title` char(20) DEFAULT NULL COMMENT '消息标题',
  `createtime` date NOT NULL COMMENT '创建时间',
  `status` int(1) NOT NULL COMMENT '消息状态',
  PRIMARY KEY (`messageid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `shortmessage` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `userid` int(15) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(15) NOT NULL COMMENT '用户昵称',
  `account` int(15) NOT NULL COMMENT '账户，用于登录',
  `password` varchar(15) NOT NULL COMMENT '密码',
  `signature` varchar(512) DEFAULT NULL COMMENT '个人签名',
  `introduce` varchar(255) DEFAULT NULL COMMENT '自我介绍',
  `qq` int(15) DEFAULT NULL COMMENT 'qq',
  `web` varchar(40) DEFAULT NULL COMMENT '个人网站',
  `place` varchar(40) DEFAULT NULL COMMENT '籍贯',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `sex` char(5) DEFAULT NULL COMMENT '性别',
  `img` varchar(30) NOT NULL COMMENT '头像uri',
  `roleid` int(15) NOT NULL COMMENT '角色id',
  `e_mail` char(20) NOT NULL COMMENT '电子邮箱',
  `problemhint` char(40) DEFAULT NULL COMMENT '密码提示问题',
  `answerhint` char(40) DEFAULT NULL COMMENT '密码提示答案',
  `registertime` date NOT NULL COMMENT '注册时间',
  `logintime` date NOT NULL COMMENT '登陆时间',
  `loginIP` varchar(40) NOT NULL COMMENT '登录ip',
  `lastlogintime` date DEFAULT NULL COMMENT '上次登录时间',
  `lastloginIP` varchar(40) DEFAULT NULL COMMENT '上次登录ip',
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `user` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
