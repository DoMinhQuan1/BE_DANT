/*
 Navicat Premium Dump SQL

 Source Server         : dbgears
 Source Server Type    : MySQL
 Source Server Version : 80041 (8.0.41)
 Source Host           : localhost:3306
 Source Schema         : dbgears

 Target Server Type    : MySQL
 Target Server Version : 80041 (8.0.41)
 File Encoding         : 65001

 Date: 02/07/2025 23:04:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `user_id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `user_pass` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `user_fullname` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `user_roles` varchar(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', '21232f297a57a5a743894a0e4a801fc3', 'Đỗ Minh Quân', '1');
INSERT INTO `admin` VALUES (2, 'admin2', '21232f297a57a5a743894a0e4a801fc3', 'MrJoker', '1');

-- ----------------------------
-- Table structure for bill
-- ----------------------------
DROP TABLE IF EXISTS `bill`;
CREATE TABLE `bill`  (
  `bill_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NULL DEFAULT NULL,
  `total` decimal(10, 0) NULL DEFAULT NULL,
  `payment` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL,
  `address` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL,
  `date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL,
  `status` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT 'Process',
  PRIMARY KEY (`bill_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1638588930949 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bill
-- ----------------------------
INSERT INTO `bill` VALUES (1638588930926, 2, 30000000, 'Bank transfer', '2213123', '2025-05-05 22:00:00', 'Nguyễn Văn A', '0123456789', 'Delivered');
INSERT INTO `bill` VALUES (1638588930928, 3, 10000000, 'Live', 'test', '2025-05-09 16:30:00', 'Nguyễn Văn B', '123456', 'Process');
INSERT INTO `bill` VALUES (1638588930929, 3, 12900000, 'Live', 'test', '2025-01-01 00:00:00', 'Nguyễn Văn B', '123456', 'Process');
INSERT INTO `bill` VALUES (1638588930930, 4, 12900000, 'Live', 'test', '2025-01-01 00:00:00', 'Nguyễn Văn B', '123456', 'Process');
INSERT INTO `bill` VALUES (1638588930931, 5, 12900000, 'Bank transfer', 'test', '2025-01-01 00:00:00', 'Nguyễn Văn B', '123456', 'Process');
INSERT INTO `bill` VALUES (1638588930941, 1488431413954, 35000000, 'Live', 'sggfdgfsd', '2025-06-28 12:08:00', 'doquan', '3413241324', 'Process');
INSERT INTO `bill` VALUES (1638588930942, 1488431413954, 35000000, 'Bank transfer', 'shajhdjkas', '2025-07-01 00:35:51', 'doquan', '357794049', 'Process');
INSERT INTO `bill` VALUES (1638588930943, 1488431413954, 20000000, 'Bank transfer', 'sadasd', '2025-07-01 00:36:20', 'doquan', '357794049', 'Process');
INSERT INTO `bill` VALUES (1638588930944, 1488431413954, 35000000, 'Bank transfer', 'êwrqwe', '2025-07-01 00:44:10', 'doquan', '31827381', 'Delivery');
INSERT INTO `bill` VALUES (1638588930945, 1488431413954, 172000000, 'Live', 'sadja', '2025-07-02 15:16:24', 'doquan', '357794049', 'Process');
INSERT INTO `bill` VALUES (1638588930946, 1488431413954, 30000000, 'Live', 'hà nội', '2025-07-02 15:17:16', 'doquan', '12345678', 'Process');
INSERT INTO `bill` VALUES (1638588930947, 1488431413954, 190000000, 'Live', 'hà nội', '2025-07-02 15:18:19', 'doquan', '12321756', 'Process');
INSERT INTO `bill` VALUES (1638588930948, 1488431413954, 270000000, 'Live', 'ewrq', '2025-07-02 15:19:19', 'doquan', '12632616', 'Process');

-- ----------------------------
-- Table structure for bill_detail
-- ----------------------------
DROP TABLE IF EXISTS `bill_detail`;
CREATE TABLE `bill_detail`  (
  `bill_detail_id` bigint NOT NULL AUTO_INCREMENT,
  `bill_id` bigint NULL DEFAULT NULL,
  `product_id` bigint NULL DEFAULT NULL,
  `price` double NULL DEFAULT NULL,
  `quantity` int NULL DEFAULT NULL,
  PRIMARY KEY (`bill_detail_id`) USING BTREE,
  INDEX `FKeolgwyayei3o80bb7rj7t207q`(`bill_id`) USING BTREE,
  INDEX `FKe7fmo7042u349ftue4g4oeiuy`(`product_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 87 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of bill_detail
-- ----------------------------
INSERT INTO `bill_detail` VALUES (54, 1638588930927, 53, 1231241123, 1);
INSERT INTO `bill_detail` VALUES (53, 1638588930927, 5, 7000000, 2);
INSERT INTO `bill_detail` VALUES (52, 1638588930926, 5, 7000000, 1);
INSERT INTO `bill_detail` VALUES (51, 1638588930926, 2, 23000000, 1);
INSERT INTO `bill_detail` VALUES (50, 1638588930925, 1, 29800000, 1);
INSERT INTO `bill_detail` VALUES (49, 1638588930925, 4, 18000000, 1);
INSERT INTO `bill_detail` VALUES (48, 1638588930920, 49, 18990000, 1);
INSERT INTO `bill_detail` VALUES (47, 1638588930919, 5, 7000000, 1);
INSERT INTO `bill_detail` VALUES (46, 1638588930919, 49, 18990000, 1);
INSERT INTO `bill_detail` VALUES (55, 1638588930928, 59, 10000000, 1);
INSERT INTO `bill_detail` VALUES (56, 1638588930928, 58, 22900000, 1);
INSERT INTO `bill_detail` VALUES (57, 1638588930929, 59, 10000000, 1);
INSERT INTO `bill_detail` VALUES (58, 1638588930929, 58, 22900000, 1);
INSERT INTO `bill_detail` VALUES (59, 1638588930930, 59, 10000000, 1);
INSERT INTO `bill_detail` VALUES (60, 1638588930930, 58, 22900000, 1);
INSERT INTO `bill_detail` VALUES (61, 1638588930931, 59, 10000000, 1);
INSERT INTO `bill_detail` VALUES (62, 1638588930931, 58, 22900000, 1);
INSERT INTO `bill_detail` VALUES (63, 1638588930932, 59, 10000000, 1);
INSERT INTO `bill_detail` VALUES (64, 1638588930932, 58, 22900000, 1);
INSERT INTO `bill_detail` VALUES (65, 1638588930933, 59, 10000000, 1);
INSERT INTO `bill_detail` VALUES (66, 1638588930933, 58, 22900000, 1);
INSERT INTO `bill_detail` VALUES (67, 1638588930934, 59, 10000000, 1);
INSERT INTO `bill_detail` VALUES (68, 1638588930934, 58, 22900000, 1);
INSERT INTO `bill_detail` VALUES (69, 1638588930935, 59, 10000000, 1);
INSERT INTO `bill_detail` VALUES (70, 1638588930935, 58, 22900000, 1);
INSERT INTO `bill_detail` VALUES (71, 1638588930936, 59, 10000000, 1);
INSERT INTO `bill_detail` VALUES (72, 1638588930936, 58, 22900000, 1);
INSERT INTO `bill_detail` VALUES (73, 1638588930937, 59, 10000000, 1);
INSERT INTO `bill_detail` VALUES (74, 1638588930937, 58, 22900000, 1);
INSERT INTO `bill_detail` VALUES (75, 1638588930938, 58, 22900000, 1);
INSERT INTO `bill_detail` VALUES (76, 1638588930939, 59, 10000000, 1);
INSERT INTO `bill_detail` VALUES (77, 1638588930940, 57, 13000000, 1);
INSERT INTO `bill_detail` VALUES (78, 1638588930941, 3, 35000000, 1);
INSERT INTO `bill_detail` VALUES (79, 1638588930942, 3, 35000000, 1);
INSERT INTO `bill_detail` VALUES (80, 1638588930943, 2, 20000000, 1);
INSERT INTO `bill_detail` VALUES (81, 1638588930944, 3, 35000000, 1);
INSERT INTO `bill_detail` VALUES (82, 1638588930945, 2, 20000000, 1);
INSERT INTO `bill_detail` VALUES (83, 1638588930945, 4, 19000000, 8);
INSERT INTO `bill_detail` VALUES (84, 1638588930946, 3, 30000000, 1);
INSERT INTO `bill_detail` VALUES (85, 1638588930947, 4, 19000000, 10);
INSERT INTO `bill_detail` VALUES (86, 1638588930948, 3, 30000000, 9);

-- ----------------------------
-- Table structure for brand
-- ----------------------------
DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand`  (
  `brand_id` bigint NOT NULL AUTO_INCREMENT,
  `brand_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`brand_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 24 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of brand
-- ----------------------------
INSERT INTO `brand` VALUES (1, 'Gigabyte');
INSERT INTO `brand` VALUES (2, 'MSI');
INSERT INTO `brand` VALUES (3, 'Asus');
INSERT INTO `brand` VALUES (4, 'Razer');
INSERT INTO `brand` VALUES (5, 'Logitech');
INSERT INTO `brand` VALUES (6, 'AULA');
INSERT INTO `brand` VALUES (7, 'Dare U');
INSERT INTO `brand` VALUES (8, 'Intel');
INSERT INTO `brand` VALUES (23, 'LG');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `category_id` bigint NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, 'Card đồ họa');
INSERT INTO `category` VALUES (2, 'Màn hình');
INSERT INTO `category` VALUES (3, 'Bàn Phím');
INSERT INTO `category` VALUES (4, 'Chuột');
INSERT INTO `category` VALUES (5, 'CPU');

-- ----------------------------
-- Table structure for config
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(225) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `value` varchar(225) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 8 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config
-- ----------------------------
INSERT INTO `config` VALUES (1, 'email_root', 'quan1742002@gmail.com');
INSERT INTO `config` VALUES (2, 'subject', '[SHOP]THONG BAO DON HANG #__idBill__');
INSERT INTO `config` VALUES (3, 'mail.smtp.port', '587');
INSERT INTO `config` VALUES (4, 'mail.smtp.auth', 'true');
INSERT INTO `config` VALUES (5, 'mail.smtp.starttls.enable', 'true');
INSERT INTO `config` VALUES (6, 'content', '<p>Xin chao <strong>__name__</strong>,</p>\r\n\r\n<p>Ban da mua hang o cua hang:</p>\r\n\r\n__content__\r\n\r\n<p>Tong don hang :&nbsp; __total__ VND</p>\r\n\r\n<p>Xin tran trong cam on!</p>\r\n');
INSERT INTO `config` VALUES (7, 'pass_root', '12345');

-- ----------------------------
-- Table structure for contact
-- ----------------------------
DROP TABLE IF EXISTS `contact`;
CREATE TABLE `contact`  (
  `contact_id` bigint NOT NULL AUTO_INCREMENT,
  `contact_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL,
  `contact_email` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL,
  `contact_title` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL,
  `contact_message` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL,
  `contact_date` timestamp NULL DEFAULT NULL,
  `contact_web` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`contact_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 3 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of contact
-- ----------------------------
INSERT INTO `contact` VALUES (1, 'TOP GEARS', 'topgears@gmail.com', NULL, NULL, '2025-05-05 00:00:00', NULL);
INSERT INTO `contact` VALUES (2, 'Dỗ Minh Quân', 'quantlu2002@gmail.com', 'Test Contact', 'yeu cau nt', '2025-05-05 11:31:44', NULL);

-- ----------------------------
-- Table structure for email_job
-- ----------------------------
DROP TABLE IF EXISTS `email_job`;
CREATE TABLE `email_job`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` bigint UNSIGNED NOT NULL,
  `subject` varchar(225) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `content` varchar(455) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `status` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '0 la chua 1 la roi 2 la loi',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKdl8k43x6d5nf0i7fav4qvuvuc`(`user_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 7 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of email_job
-- ----------------------------
INSERT INTO `email_job` VALUES (1, 1603337116132, 'true', 'abc', 1);
INSERT INTO `email_job` VALUES (2, 1603337116134, '[TOP GEARS]THONG BAO DON HANG #1638588930919', '<p>Xin chao <strong>hai</strong>,</p>\r\n\r\n<p>Ban da mua hang o cua hang:</p>\r\n\r\n<p style=\"margin-left:40px\">Bàn phím AULA F75 : 1 (sp)</p><p style=\"margin-left:40px\">Chu?t Logitech G304 Wireless White : 1 (sp)</p>\r\n\r\n<p>Tong don hang :&nbsp; 1,950,000 VND</p>\r\n\r\n<p>Xin tran trong cam on!</p>\r\n', 1);
INSERT INTO `email_job` VALUES (3, 1603337116134, '[TOP GEARS]THONG BAO DON HANG #1638588930920', '<p>Xin chao <strong>hai</strong>,</p>\r\n\r\n<p>Ban da mua hang o cua hang:</p>\r\n\r\n<p style=\"margin-left:40px\">B? vi x? lý Intel Core i7 14700F : 1 (sp)</p>\r\n\r\n<p>Tong don hang :&nbsp; 8,490,000 VND</p>\r\n\r\n<p>Xin tran trong cam on!</p>\r\n', 1);
INSERT INTO `email_job` VALUES (4, 1603337116134, '[TOP GEARS]THONG BAO DON HANG #1638588930925', '<p>Xin chao <strong>hai</strong>,</p>\r\n\r\n<p>Ban da mua hang o cua hang:</p>\r\n\r\n<p style=\"margin-left:40px\">Card màn hình ASUS Dual GeForce RTX 4060 V2 OC Edition 8GB GDDR6 : 1 (sp)</p><p style=\"margin-left:40px\">Màn hình ASUS ProArt PA27JCV 27\" IPS 5K USBC : 1 (sp)</p>\r\n\r\n<p>Tong don hang :&nbsp; 30,190,000 VND</p>\r\n\r\n<p>Xin tran trong cam on!</p>\r\n', 1);

-- ----------------------------
-- Table structure for price_history
-- ----------------------------
DROP TABLE IF EXISTS `price_history`;
CREATE TABLE `price_history`  (
  `history_id` int NOT NULL AUTO_INCREMENT,
  `product_id` int NOT NULL,
  `product_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `old_price` bigint NOT NULL,
  `new_price` bigint NOT NULL,
  `changed_by` int NOT NULL,
  `change_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`history_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of price_history
-- ----------------------------
INSERT INTO `price_history` VALUES (1, 82, 'Card màn hình ASUS Dual GeForce RTX 4060 V2 OC Edition 8GB GDDR6', 1, 0, 1, '2025-06-03 23:38:46');
INSERT INTO `price_history` VALUES (5, 3, 'Màn hình cong GIGABYTE G34WQCP 34\" 2K 180Hz', 35000000, 30000000, 1, '2025-07-02 11:04:02');
INSERT INTO `price_history` VALUES (6, 4, 'Card màn hình GIGABYTE GeForce RTX 4070 SUPER WINDFORCE OC 12G', 19000000, 1000000, 1, '2025-07-02 15:14:34');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `product_id` bigint NOT NULL,
  `product_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `product_dimensions` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `product_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `product_importprice` bigint NULL DEFAULT NULL,
  `product_marketprice` bigint NULL DEFAULT NULL,
  `product_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `product_price` bigint NULL DEFAULT NULL,
  `product_quantily` int NULL DEFAULT NULL,
  `product_quantity_sold` int NULL DEFAULT NULL,
  `product_connection` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `brand_id` bigint NOT NULL,
  `category_id` bigint NOT NULL,
  PRIMARY KEY (`product_id`) USING BTREE,
  INDEX `FK1mtsbur82frn64de7balymq9s`(`category_id` ASC) USING BTREE,
  CONSTRAINT `FK1mtsbur82frn64de7balymq9s` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, 'Tần số quét  180 Hz, độ phân giải Full HD(1920 x 1080), Thời gian phản hồi\r\n1ms', '25 inch', 'asus_vg259q3a.webp', 22000000, 27000000, 'Màn hình Asus TUF GAMING VG259Q3A 25“ Fast IPS 180Hz', 25000000, 10, 0, '2 x HDMI™ (2.0); 1 x DisplayPort (1.2)', 3, 2);
INSERT INTO `product` VALUES (2, 'Tần số quét 240 Hz, độ phân giải 2K (2560 x 1440), Thời gian phản hồi\r\n0.5 ms', '27 inch', 'msi_mag_274qf.webp', 18000000, 22000000, 'Màn hình MSI MAG 274QF X24 27\" Rapid IPS 2K 240Hz', 20000000, 12, 0, '\r\n2 x HDMI 2.0; 1 x DisplayPort', 2, 2);
INSERT INTO `product` VALUES (3, 'Tần số quét 180 Hz, độ phân giải 2K (3440 x 1440), thời gian phản hồi\r\n1 ms, ', ' 34 inch', 'giga34inch180hz.webp', 32000000, 38000000, 'Màn hình cong GIGABYTE G34WQCP 34\" 2K 180Hz', 30000000, 0, 0, '\r\n2 x HDMI 2.0; 1 x DisplayPort', 1, 2);
INSERT INTO `product` VALUES (4, '2505 MHz (Reference card: 2475 MHz), Nhân CUDA 7168,Giao thức bộ nhớ 192-bit, Độ phân giải	Độ phân giải tối đa 7680 x 4320,Số lượng màn hình tối đa hỗ trợ 4, ', '27inch', 'geforce_rtx__4070_super_windforce_oc_12g-02_cb8fbfeb315e480b8cf8698fee120280_master.webp', 17000000, 20000000, 'Card màn hình GIGABYTE GeForce RTX 4070 SUPER WINDFORCE OC 12G', 19000000, 90, 0, '2hdmi', 1, 1);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL,
  `user_email` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL,
  `user_pass` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL,
  `user_role` bit(1) NULL DEFAULT NULL,
  `user_phone` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1603337116139 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1488431413954, 'doquan', NULL, '123456', b'0', NULL);
INSERT INTO `users` VALUES (1488394819194, 'quang', NULL, '123456', b'1', NULL);
INSERT INTO `users` VALUES (1603337116135, 'test1', 'q2002@gmail.com', NULL, b'0', '12312313');
INSERT INTO `users` VALUES (1490249636892, 'a', 'a', 'a', b'0', 'a');

SET FOREIGN_KEY_CHECKS = 1;
