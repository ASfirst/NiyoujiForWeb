/*
 Navicat Premium Data Transfer

 Source Server         : ali
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : 112.74.51.247:3306
 Source Schema         : niyouji_db

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 04/02/2018 15:50:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for barrage_tb
-- ----------------------------
DROP TABLE IF EXISTS `barrage_tb`;
CREATE TABLE `barrage_tb`  (
  `barrage_id` int(11) NOT NULL AUTO_INCREMENT,
  `travelnote_id` int(11) DEFAULT NULL,
  `page_id` int(11) DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `is_performers` int(1) DEFAULT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `create_time` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`barrage_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of barrage_tb
-- ----------------------------
INSERT INTO `barrage_tb` VALUES (1, 5, 3, '测试用户', 1, '老铁们，给个双击666', '2018-01-31 16:36:26');

-- ----------------------------
-- Table structure for niyouji_user_tb
-- ----------------------------
DROP TABLE IF EXISTS `niyouji_user_tb`;
CREATE TABLE `niyouji_user_tb`  (
  `user_id` int(11) NOT NULL,
  `gold_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for travelnote_page_tb
-- ----------------------------
DROP TABLE IF EXISTS `travelnote_page_tb`;
CREATE TABLE `travelnote_page_tb`  (
  `page_id` int(11) NOT NULL AUTO_INCREMENT,
  `travelnote_id` int(11) DEFAULT NULL,
  `create_time` datetime(0) DEFAULT NULL,
  `page_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `theme_position` int(11) DEFAULT NULL,
  `resource_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `text_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `background_music_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`page_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of travelnote_page_tb
-- ----------------------------
INSERT INTO `travelnote_page_tb` VALUES (1, 1, '2018-01-31 15:51:50', 'picture_and_word', 1, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/img_1_23441165.jpg', '开了，第一章', NULL);
INSERT INTO `travelnote_page_tb` VALUES (2, 2, '2018-01-31 16:15:39', 'picture_and_word', 0, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/img_1_11959651.jpg', NULL, NULL);
INSERT INTO `travelnote_page_tb` VALUES (3, 3, '2018-01-31 16:34:17', 'picture_and_word', 2, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/img_1_100542295.jpg', '重新出发', NULL);
INSERT INTO `travelnote_page_tb` VALUES (4, 3, '2018-01-31 16:34:49', 'video', 0, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/videos/vdo_1_42235108.mp4', NULL, NULL);
INSERT INTO `travelnote_page_tb` VALUES (5, 3, '2018-01-31 16:35:28', 'picture_and_word', 0, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/img_1_225163245.jpg', '去车站吧现在，妈的，现在我的腿子好冷啊\n一到冷天这腿子就这样', NULL);
INSERT INTO `travelnote_page_tb` VALUES (6, 3, '2018-01-31 16:37:50', 'picture_and_word', 0, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/img_1_204735570.jpg', '继续，目标地铁口，到了地铁口我就结束第一章游记吧', NULL);
INSERT INTO `travelnote_page_tb` VALUES (7, 3, '2018-01-31 16:39:25', 'picture_and_word', 0, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/img_1_235189710.jpg', '我要过去对面，阳光啊，我来了', NULL);
INSERT INTO `travelnote_page_tb` VALUES (8, 3, '2018-01-31 16:41:52', 'picture_and_word', 0, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/img_1_198808167.jpg', '准备到地铁口了加油！！！', NULL);
INSERT INTO `travelnote_page_tb` VALUES (9, 4, '2018-01-31 16:45:00', 'picture_and_word', 0, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/img_1_47236774.jpg', '买票中。。。', NULL);
INSERT INTO `travelnote_page_tb` VALUES (10, 4, '2018-01-31 16:46:54', 'picture_and_word', 0, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/img_1_130962305.jpg', '已经站，进来了', NULL);
INSERT INTO `travelnote_page_tb` VALUES (11, 4, '2018-01-31 16:47:59', 'picture_and_word', 0, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/img_1_19218177.jpg', '到厕所了', NULL);
INSERT INTO `travelnote_page_tb` VALUES (12, 4, '2018-01-31 16:49:47', 'picture_and_word', 0, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/img_1_163370600.jpg', '等车中，希望有靓女，啦啦啦，进来腿子也不是那么冷了现在', NULL);
INSERT INTO `travelnote_page_tb` VALUES (13, 4, '2018-01-31 16:53:18', 'picture_and_word', 0, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/img_1_198098798.jpg', '先结束吧', NULL);

-- ----------------------------
-- Table structure for travelnote_tb
-- ----------------------------
DROP TABLE IF EXISTS `travelnote_tb`;
CREATE TABLE `travelnote_tb`  (
  `travelnote_id` int(11) NOT NULL AUTO_INCREMENT,
  `performer_id` int(11) DEFAULT NULL,
  `create_time` datetime(0) DEFAULT NULL,
  `cover_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `cover_resource_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `travelnote_title` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `attentions_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`travelnote_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of travelnote_tb
-- ----------------------------
INSERT INTO `travelnote_tb` VALUES (1, 1, '2018-01-31 15:51:43', 'image', 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/cover_1517385103125.jpg', '第一次', 0);
INSERT INTO `travelnote_tb` VALUES (2, 1, '2018-01-31 16:15:37', 'image', 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/cover_1517386536735.jpg', '途径', 0);
INSERT INTO `travelnote_tb` VALUES (3, 1, '2018-01-31 16:33:48', 'image', 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/cover_1517387623548.jpg', '去买票', 0);
INSERT INTO `travelnote_tb` VALUES (4, 1, '2018-01-31 16:44:57', 'image', 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/cover_1517388296887.jpg', '已到地铁口', 0);

SET FOREIGN_KEY_CHECKS = 1;
