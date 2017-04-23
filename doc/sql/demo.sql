/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-04-23 22:15:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for logininfo
-- ----------------------------
DROP TABLE IF EXISTS `logininfo`;
CREATE TABLE `logininfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `login_name` varchar(255) DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `login_type` int(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of logininfo
-- ----------------------------
INSERT INTO `logininfo` VALUES ('1', null, '1234', '18299392929', null, '0', null);
INSERT INTO `logininfo` VALUES ('2', '1', '4823', '123', '2017-04-23 22:04:00', '0', 'MTpTdW4gQXByIDIzIDIyOjAzOjU5IENTVCAyMDE3');

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL,
  `action` varchar(255) DEFAULT NULL,
  `type` int(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES ('1', '/home/index', '', '0');
INSERT INTO `resource` VALUES ('2', '/home/index2', '', '1');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '系统管理者');

-- ----------------------------
-- Table structure for role_resource
-- ----------------------------
DROP TABLE IF EXISTS `role_resource`;
CREATE TABLE `role_resource` (
  `role_id` int(11) NOT NULL,
  `resource_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_resource
-- ----------------------------
INSERT INTO `role_resource` VALUES ('1', '1');
INSERT INTO `role_resource` VALUES ('1', '2');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '123');
INSERT INTO `user` VALUES ('2', '123');
INSERT INTO `user` VALUES ('3', '123');
INSERT INTO `user` VALUES ('4', '123');
INSERT INTO `user` VALUES ('5', '123');
INSERT INTO `user` VALUES ('6', '123');
INSERT INTO `user` VALUES ('7', '123');
INSERT INTO `user` VALUES ('8', '123');
INSERT INTO `user` VALUES ('9', '123');
INSERT INTO `user` VALUES ('10', '123');
INSERT INTO `user` VALUES ('11', '123');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1');

-- ----------------------------
-- Procedure structure for t2
-- ----------------------------
DROP PROCEDURE IF EXISTS `t2`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `t2`()
BEGIN

	DECLARE b int DEFAULT 0;
	DECLARE t_error INTEGER DEFAULT 0;
	DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET t_error=1;

	START TRANSACTION;
	while b<5000 DO
	INSERT INTO comment (content,articleId) VALUES(CONCAT('评论的内容',b),FLOOR(6872+RAND()*1000));
	set b=b+1;
	end WHILE;
	if t_error=1 THEN
		ROLLBACK;
	ELSE
		COMMIT;
	END IF;
	SELECT t_error;



END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for tt
-- ----------------------------
DROP PROCEDURE IF EXISTS `tt`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `tt`()
BEGIN

	DECLARE a int DEFAULT 0;
	DECLARE t_error INTEGER DEFAULT 0;
	DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET t_error=1;

	START TRANSACTION;
	while a<1000 DO
	INSERT INTO article (name,content) VALUES(CONCAT('文章题目',a),CONCAT('文章内容',a));
	set a=a+1;
	end WHILE;
	if t_error=1 THEN
		ROLLBACK;
	ELSE
		COMMIT;
	END IF;
	SELECT t_error;



END
;;
DELIMITER ;
