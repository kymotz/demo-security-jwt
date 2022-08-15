-- table structure
DROP  TABLE  IF  EXISTS  `user`;

Create Table: CREATE TABLE `user` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `password` varchar(255) NOT NULL DEFAULT '' COMMENT '密码',
    `roles` varchar(1024) NOT NULL DEFAULT '' COMMENT '用户角色（多角色用英文半角逗号(,)间隔）',
    `status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '帐号状态（0正常  1停用）',
    `username` varchar(255) NOT NULL DEFAULT '' COMMENT '用户账号',
    PRIMARY KEY (`id`),
    UNIQUE KEY `username_idx_unique` (`username`),
    KEY `password_idx` (`password`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4  COLLATE=utf8mb4_general_ci;

-- records
INSERT  INTO  `user`  (id, username, password, status, roles) VALUES  (1,  'admin',
'$2a$10$nNQI9Ij1rU5NG9JFLQphweTOteCX6O211Nysrg2V5rRSGDRmRWtm.',  0,
'ROLE_ADMIN,ROLE_USER');
INSERT  INTO  `user` (id, username, password, status, roles)  VALUES  (2,  'user',
'$2a$10$nNQI9Ij1rU5NG9JFLQphweTOteCX6O211Nysrg2V5rRSGDRmRWtm.',  0,
'ROLE_USER');
INSERT  INTO  `user` (id, username, password, status, roles)  VALUES  (3,  'ada',
'$2a$10$nNQI9Ij1rU5NG9JFLQphweTOteCX6O211Nysrg2V5rRSGDRmRWtm.',  0,
'ROLE_ADMIN,ROLE_USER');