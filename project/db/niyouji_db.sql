/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : niyouji_db

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 23/02/2018 11:30:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for appraise_tb
-- ----------------------------
DROP TABLE IF EXISTS `appraise_tb`;
CREATE TABLE `appraise_tb`  (
  `appraise_id` int(11) NOT NULL AUTO_INCREMENT,
  `travelnote_id` int(11) DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `create_time` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`appraise_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of appraise_tb
-- ----------------------------
INSERT INTO `appraise_tb` VALUES (1, 4, '测试用户', '老铁们，给个双击666', '2018-01-31 16:36:26');

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
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of barrage_tb
-- ----------------------------
INSERT INTO `barrage_tb` VALUES (1, 4, 9, '测试用户', 1, '老铁们，给个双击666', '2018-01-31 16:36:26');
INSERT INTO `barrage_tb` VALUES (5, 2, 18, '韩愈', 0, '未尝游焉，友乐乎', '2017-08-20 14:33:49');
INSERT INTO `barrage_tb` VALUES (6, 2, 18, '柳宗元', 1, '乐乎此耶~', '2017-08-20 14:38:01');
INSERT INTO `barrage_tb` VALUES (7, 2, 22, '柳宗元', 1, '岂不美哉', '2017-08-20 15:49:14');

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
) ENGINE = InnoDB AUTO_INCREMENT = 72 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of travelnote_page_tb
-- ----------------------------
INSERT INTO `travelnote_page_tb` VALUES (1, 1, '2018-01-31 15:51:50', 'video', 1, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/videos/ms2.mp4', '开了，第一章', NULL);
INSERT INTO `travelnote_page_tb` VALUES (18, 2, '2017-08-20 14:17:05', 'picture_and_word', 1, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/xstj1.jpg', '从小土丘向西走一百二十步', NULL);
INSERT INTO `travelnote_page_tb` VALUES (19, 2, '2017-08-20 14:22:52', 'picture_and_word', 1, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/xstj2.jpg', '隔篁竹，闻水声，如鸣佩环，心乐之', NULL);
INSERT INTO `travelnote_page_tb` VALUES (20, 2, '2017-08-20 14:30:09', 'picture_and_word', 1, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/xstj3.jpg', '伐竹取道，下见小潭，水尤清冽', NULL);
INSERT INTO `travelnote_page_tb` VALUES (21, 2, '2017-08-20 14:37:46', 'picture_and_word', 1, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/xstj4.jpg', '全石以为底，近岸，卷石底以出。为坻，为屿，为嵁，为岩。', NULL);
INSERT INTO `travelnote_page_tb` VALUES (22, 2, '2017-08-20 14:53:06', 'picture_and_word', 1, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/xstj5.jpg', '青树翠蔓，蒙络摇缀，参差披拂。', NULL);
INSERT INTO `travelnote_page_tb` VALUES (23, 2, '2017-08-20 14:56:52', 'picture_and_word', 1, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/xstj6.jpg', '皆若空游无所依。日光下澈，影布石上，佁然不动；俶尔远逝；往来翕忽，似与游者相乐。', NULL);
INSERT INTO `travelnote_page_tb` VALUES (24, 2, '2017-08-20 15:00:14', 'picture_and_word', 1, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/xstj7.jpg', '潭西南而望，斗折蛇行，明灭可见。其岸势犬牙差互，不可知其源。坐潭上，四面竹树环合，寂寥无人，凄神寒骨，悄怆幽邃。', NULL);
INSERT INTO `travelnote_page_tb` VALUES (25, 2, '2017-08-20 15:19:23', 'picture_and_word', 1, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/xstj8.jpg', '以其境过清，不可久居，乃记之而去。', NULL);
INSERT INTO `travelnote_page_tb` VALUES (26, 2, '2017-08-20 15:19:45', 'picture_and_word', 1, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/xstj9.jpg', '同游者：吴武陵，龚古，余弟宗玄。隶而从者，崔氏二小生：曰恕己，曰奉壹。', NULL);
INSERT INTO `travelnote_page_tb` VALUES (27, 3, '2018-02-20 19:39:58', 'picture_and_word', 0, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/ms1.jpg', '先准备制作的素材。香菇、香葱、胡萝卜分别切碎，猪肉剁成肉末', NULL);
INSERT INTO `travelnote_page_tb` VALUES (28, 3, '2018-02-20 19:51:21', 'video', 0, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/videos/ms2.mp4', NULL, NULL);
INSERT INTO `travelnote_page_tb` VALUES (29, 3, '2018-02-20 21:40:04', 'picture_and_word', 0, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/ms3.jpg', '将素材倒入容器内，导入你喜欢的佐料加工成肉馅', NULL);
INSERT INTO `travelnote_page_tb` VALUES (30, 3, '2018-02-20 21:45:21', 'picture_and_word', 0, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/ms4.jpg', '肉馅加工完成后，拿出准备好的饺子皮开始包馅', NULL);
INSERT INTO `travelnote_page_tb` VALUES (31, 3, '2018-02-20 21:47:38', 'video', 0, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/videos/ms5.mp4', NULL, NULL);
INSERT INTO `travelnote_page_tb` VALUES (32, 3, '2018-02-20 21:53:07', 'picture_and_word', 0, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/ms6.jpg', '最后，把包好的烧麦放入锅中蒸个桑拿', NULL);
INSERT INTO `travelnote_page_tb` VALUES (33, 3, '2018-02-20 21:57:20', 'video', 0, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/videos/ms7.mp4', NULL, NULL);
INSERT INTO `travelnote_page_tb` VALUES (34, 3, '2018-02-20 22:01:45', 'picture_and_word', 0, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/ms8.jpg', '上蒸8-10分钟左右即可，出锅，倒上一碟酱料就可以开动咯', NULL);
INSERT INTO `travelnote_page_tb` VALUES (35, 4, '2018-02-21 19:04:56', 'video', 0, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/videos/xyms1.mp4', NULL, NULL);
INSERT INTO `travelnote_page_tb` VALUES (36, 4, '2018-02-21 19:03:15', 'picture_and_word', 0, NULL, '以上是刘谦表演香烟魔术的过程，其实原理很简单，接下来我开始视频揭秘', NULL);
INSERT INTO `travelnote_page_tb` VALUES (38, 4, '2018-02-21 19:27:45', 'video', 0, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/videos/xyms3.mp4', NULL, NULL);
INSERT INTO `travelnote_page_tb` VALUES (39, 4, '2018-02-21 19:31:30', 'picture_and_word', 0, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/xyms4.jpg', '原理就是香烟一头扣在衣服里的靠近右边,然后另一边绑住香烟用手遮住,穿过右袖子,然后扣住香烟,香烟把烟嘴就是黄色的那个,做成活动的可以上下移动的,然后用上楼的方法进行上推,会发现香烟进去的效果,然后松手就可以了。', NULL);
INSERT INTO `travelnote_page_tb` VALUES (40, 5, '2018-02-21 19:54:33', 'picture_and_word', 0, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/zrcs1.jpg', '现在我们来到了比赛场地。我要装子弹了', NULL);
INSERT INTO `travelnote_page_tb` VALUES (41, 5, '2018-02-21 20:13:18', 'video', 0, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/videos/zrcs2.mp4', NULL, NULL);
INSERT INTO `travelnote_page_tb` VALUES (42, 5, '2018-02-21 20:17:19', 'picture_and_word', 0, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/zrcs3.jpg', '教练正在给我们做示范解说中。。。', NULL);
INSERT INTO `travelnote_page_tb` VALUES (43, 5, '2018-02-21 20:49:19', 'video', 0, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/videos/zrcs4.mp4', NULL, NULL);
INSERT INTO `travelnote_page_tb` VALUES (44, 5, '2018-02-21 20:53:43', 'picture_and_word', 0, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/zrcs5.jpg', '商量战术中，我们的战术就是没有战术！', NULL);
INSERT INTO `travelnote_page_tb` VALUES (45, 5, '2018-02-21 21:01:03', 'video', 0, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/videos/zrcs6.mp4', NULL, NULL);
INSERT INTO `travelnote_page_tb` VALUES (46, 5, '2018-02-21 21:07:40', 'picture_and_word', 0, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/zrcs7.jpg', '不到两分钟本姑娘就中弹阵亡了，呜呜呜，求安慰~', NULL);
INSERT INTO `travelnote_page_tb` VALUES (47, 5, '2018-02-21 21:09:31', 'picture_and_word', 0, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/zrcs8.jpg', '温馨提示：被子弹打中会非常非常疼！请先穿好厚衣服和后裤子！', NULL);
INSERT INTO `travelnote_page_tb` VALUES (48, 6, '2018-02-22 12:48:57', 'picture_and_word', 2, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/txj1.jpg', '整个车队一共六辆探险者和一辆锐捷的工作车组成，行驶在街上绝对有震撼的感觉，只可惜现在沈阳街上人有点儿少了', NULL);
INSERT INTO `travelnote_page_tb` VALUES (49, 6, '2018-02-22 13:27:01', 'picture_and_word', 2, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/txj2.jpg', '陈教练，本次车队协调总指挥，聊天贼幽默又不缺乏知识性，哈哈，老铁门双击点赞\r\n。', NULL);
INSERT INTO `travelnote_page_tb` VALUES (50, 6, '2018-02-22 13:27:28', 'picture_and_word', 2, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/txj3.jpg', '我们午饭过后开始上路咯。', NULL);
INSERT INTO `travelnote_page_tb` VALUES (51, 6, '2018-02-22 13:27:49', 'picture_and_word', 2, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/txj4.jpg', '探险者前排两个头枕都带有DVD功能，下方的接口也很全面，基本可以pk一下空客机上娱乐系统了。', NULL);
INSERT INTO `travelnote_page_tb` VALUES (52, 6, '2018-02-22 13:28:04', 'picture_and_word', 2, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/txj5.jpg', '\r\n作为福特的全尺寸SUV车型，车内驾驶视野自然不必多说，唯一的遗憾就是内饰豪华程度没有和锐界拉开太多。', NULL);
INSERT INTO `travelnote_page_tb` VALUES (53, 6, '2018-02-22 13:28:17', 'picture_and_word', 2, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/txj6.jpg', '\r\n豪华虽没有拉开太多，但功能上探险者和锐界相比就不是一个层次的了，探险者的ACC系统非常好用，高速利器也。', NULL);
INSERT INTO `travelnote_page_tb` VALUES (54, 6, '2018-02-22 13:28:38', 'picture_and_word', 2, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/txj7.jpg', '\r\n没回走高速都会遇到乐事，老铁门看看那个微卡上带了I类“保护动物”转移，而且还是那么明目张胆的。', NULL);
INSERT INTO `travelnote_page_tb` VALUES (55, 6, '2018-02-22 13:30:25', 'picture_and_word', 2, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/txj8.jpg', '\n我们到了本溪关门山了，这个景区素有“东北小黄山”之称，风景充门票上来看还是不错的。', NULL);
INSERT INTO `travelnote_page_tb` VALUES (56, 6, '2018-02-22 13:30:49', 'picture_and_word', 2, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/txj9.jpg', '\n水库浏览区位于公园正门口，但从景色上来看，我们明显来晚了，据导游说我们来得头一天刮完大风，很多树叶都被刮走了，所以呈现晚秋的景色。', NULL);
INSERT INTO `travelnote_page_tb` VALUES (57, 6, '2018-02-22 13:51:31', 'picture_and_word', 2, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/txj10.jpg', '\n从地图上看景区面积非常大，但由于时间关系我们只能浏览其中一个经点，真要完整弯下来估计一天时间都不够。', NULL);
INSERT INTO `travelnote_page_tb` VALUES (58, 6, '2018-02-22 13:51:49', 'picture_and_word', 2, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/txj11.jpg', '\n凡是厕所，必有商店。这句话在关门山景区同样应验，各种玩具和纪念品应有尽有，看上去感觉不错。', NULL);
INSERT INTO `travelnote_page_tb` VALUES (59, 6, '2018-02-22 13:52:18', 'picture_and_word', 2, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/txj12.jpg', '\r\n七彩风车转起来非常漂亮，大风车吱呀吱呀转~~', NULL);
INSERT INTO `travelnote_page_tb` VALUES (60, 6, '2018-02-22 13:52:33', 'picture_and_word', 2, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/txj13.jpg', '\r\n进入景区我们上了景区的摆渡车,go go go', NULL);
INSERT INTO `travelnote_page_tb` VALUES (61, 6, '2018-02-22 13:52:52', 'picture_and_word', 2, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/txj14.jpg', '\r\n司机师傅开车很猛，错车几乎一脚刹车都不踩还抽着烟，抽完烟烟蒂随手往窗外一扔，这要是着火可是毁了几代人的事啊', NULL);
INSERT INTO `travelnote_page_tb` VALUES (62, 6, '2018-02-22 13:53:11', 'picture_and_word', 2, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/txj15.jpg', '\r\n龙门峡景区是关山门景区最美的景点，但树叶已经掉落的差不多了，剩下的就只有光秃秃的树枝了。', NULL);
INSERT INTO `travelnote_page_tb` VALUES (63, 6, '2018-02-22 13:53:49', 'picture_and_word', 2, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/txj16.jpg', '\r\n晶帘瀑布的水流非常小，底下湖里的水位就可以看出水势是忽然一下减小的。', NULL);
INSERT INTO `travelnote_page_tb` VALUES (64, 6, '2018-02-22 13:54:09', 'picture_and_word', 2, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/txj17.jpg', '\r\n接下来是大地森林公园的轻度越野项目。', NULL);
INSERT INTO `travelnote_page_tb` VALUES (65, 6, '2018-02-22 13:54:25', 'picture_and_word', 2, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/txj18.jpg', '\r\n森林公园没有任何铺装路面，而且几乎都是由小块碎石组成，不过探险者悬挂表现还不错的，舒适和操控可以取平衡。', NULL);
INSERT INTO `travelnote_page_tb` VALUES (66, 6, '2018-02-22 13:54:43', 'picture_and_word', 2, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/txj19.jpg', '\r\n由于这个景区并不出名，所以几乎没有游客来到这里，但也正因此里面的环境保护非常好，空气页达到醉氧级别。', NULL);
INSERT INTO `travelnote_page_tb` VALUES (67, 6, '2018-02-22 13:55:02', 'picture_and_word', 2, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/txj20.jpg', '\r\n被秋风打黄的松树非常漂亮，我觉得比枫叶还要有感觉，但在这里出去脚印和快门就不要留下别的东西了，更严禁吸烟。', NULL);
INSERT INTO `travelnote_page_tb` VALUES (68, 6, '2018-02-22 13:55:17', 'picture_and_word', 2, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/txj21.jpg', '\r\n沿着道路旁边还有一条小溪，里面水流非常清澈，同时石块上也布满青色的青苔，很原始也很自然。', NULL);
INSERT INTO `travelnote_page_tb` VALUES (69, 6, '2018-02-22 13:55:32', 'picture_and_word', 2, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/txj22.jpg', '\r\n过河咯，不过车辆过水路面时一定要放慢速度下水，不然就可能导致发动机进气系统进水而导致发动机顶杆。', NULL);
INSERT INTO `travelnote_page_tb` VALUES (70, 6, '2018-02-22 13:58:27', 'picture_and_word', 2, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/txj23.jpg', '\r\n终于到终点了，get it！', NULL);
INSERT INTO `travelnote_page_tb` VALUES (71, 9, '2018-02-22 19:29:06', 'video', 0, 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/videos/vdo_2_148952131.mp4', NULL, NULL);

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
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`travelnote_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of travelnote_tb
-- ----------------------------
INSERT INTO `travelnote_tb` VALUES (2, 3, '2017-08-20 13:24:57', 'image', 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/xstj_cover.jpg', '小石潭记', 321, '永州市芝山区');
INSERT INTO `travelnote_tb` VALUES (3, 2, '2018-02-20 19:31:57', 'image', 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/ms_cover.jpg', '烧卖简易制作，美味！', 321, NULL);
INSERT INTO `travelnote_tb` VALUES (4, 2, '2018-02-21 18:52:25', 'image', 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/xyms_cover.jpg', '刘谦春晚魔术揭晓', 333, NULL);
INSERT INTO `travelnote_tb` VALUES (5, 2, '2018-02-21 19:47:35', 'image', 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/zrcs_cover.jpg', '真人CS，come on！', 1000, '上海市闵行区莲花南路1500号绿地集团6号楼1层');
INSERT INTO `travelnote_tb` VALUES (6, 2, '2018-02-22 11:10:29', 'image', 'http://niyouji.oss-cn-shenzhen.aliyuncs.com/images/txj_cover.jpg', ' 福特探险者“探鲜记”之旅', 321, '沈阳 三台子川江街36号');

-- ----------------------------
-- Function structure for getRowCountByTravelnoteId
-- ----------------------------
DROP FUNCTION IF EXISTS `getRowCountByTravelnoteId`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `getRowCountByTravelnoteId`(`in_travelnote_id` int) RETURNS int(11)
BEGIN
	#Routine body goes here...
	set @mytemp = 0;
	SET @rowCount=(select row_count from (
	SELECT  (@mytemp:=@mytemp+1) as row_count,travelnote_id,create_time 	FROM travelnote_tb ORDER BY create_time DESC) AS A
	WHERE A.travelnote_id=in_travelnote_id);

	RETURN @rowCount;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
