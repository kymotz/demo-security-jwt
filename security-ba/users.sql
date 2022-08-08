-- table structure
DROP  TABLE  IF  EXISTS  `user`;
CREATE  TABLE  `user`  (
`id`  bigint  NOT  NULL  AUTO_INCREMENT,
`username`  varchar(30)  NOT  NULL,
`password`  varchar(100)   DEFAULT  NULL,
`status`  integer(11)   NOT  NULL  DEFAULT  '0'  COMMENT  '0正常1停用',
`roles`  varchar(255)  DEFAULT  NULL  COMMENT  '多个角色用逗号间隔',
PRIMARY  KEY  (`id`)
)  ENGINE=InnoDB  AUTO_INCREMENT=4  DEFAULT  CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- records
INSERT  INTO  `user`  VALUES  ('1',  'admin',
'$2a$10$nNQI9Ij1rU5NG9JFLQphweTOteCX6O211Nysrg2V5rRSGDRmRWtm.',  '0',
'ROLE_ADMIN,ROLE_USER');
INSERT  INTO  `user`  VALUES  ('2',  'user',
'$2a$10$nNQI9Ij1rU5NG9JFLQphweTOteCX6O211Nysrg2V5rRSGDRmRWtm.',  '0',
'ROLE_USER');
INSERT  INTO  `user`  VALUES  ('3',  'alex',
'$2a$10$nNQI9Ij1rU5NG9JFLQphweTOteCX6O211Nysrg2V5rRSGDRmRWtm.',  '0',
'ROLE_ADMIN,ROLE_USER');