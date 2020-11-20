-- table structure
DROP  TABLE  IF  EXISTS  `users`;
CREATE  TABLE  `users`  (
`user_id`  bigint  NOT  NULL  AUTO_INCREMENT,
`user_name`  varchar(30)  COLLATE  utf8mb4_general_ci  NOT  NULL,
`password`  varchar(100)  COLLATE  utf8mb4_general_ci  DEFAULT  NULL,
`status`  char(1)  COLLATE  utf8mb4_general_ci  NOT  NULL  DEFAULT  '0'  COMMENT  '0正常1停用',
`roles`  varchar(255)  COLLATE  utf8mb4_general_ci  DEFAULT  NULL  COMMENT  '多个角色用逗号间隔',
PRIMARY  KEY  (`user_id`)
)  ENGINE=InnoDB  AUTO_INCREMENT=4  DEFAULT  CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



-- records
INSERT  INTO  `users`  VALUES  ('1',  'admin',
'$2a$10$nNQI9Ij1rU5NG9JFLQphweTOteCX6O211Nysrg2V5rRSGDRmRWtm.',  '0',
'ROLE_ADMIN,ROLE_USER');
INSERT  INTO  `users`  VALUES  ('2',  'user',
'$2a$10$nNQI9Ij1rU5NG9JFLQphweTOteCX6O211Nysrg2V5rRSGDRmRWtm.',  '0',
'ROLE_USER');
INSERT  INTO  `users`  VALUES  ('3',  'alex',
'$2a$10$nNQI9Ij1rU5NG9JFLQphweTOteCX6O211Nysrg2V5rRSGDRmRWtm.',  '0',
'ROLE_ADMIN,ROLE_USER');