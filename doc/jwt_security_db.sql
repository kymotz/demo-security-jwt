/*
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : jwt_security_db
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '0正常1停用',
  `roles` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '多个角色用逗号间隔',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'admin', '$2a$10$nNQI9Ij1rU5NG9JFLQphweTOteCX6O211Nysrg2V5rRSGDRmRWtm.', '0', 'ROLE_ADMIN,ROLE_USER');
INSERT INTO `users` VALUES (2, 'user', '$2a$10$nNQI9Ij1rU5NG9JFLQphweTOteCX6O211Nysrg2V5rRSGDRmRWtm.', '0', 'ROLE_USER');
INSERT INTO `users` VALUES (3, 'alex', '$2a$10$nNQI9Ij1rU5NG9JFLQphweTOteCX6O211Nysrg2V5rRSGDRmRWtm.', '0', 'ROLE_ADMIN,ROLE_USER');

SET FOREIGN_KEY_CHECKS = 1;
