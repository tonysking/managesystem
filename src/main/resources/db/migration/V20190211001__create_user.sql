CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_openid` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户微信openid',
  `user_nick_name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户昵称',
  `user_phone` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户电话',
  `user_address` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户地址',
  `user_status` tinyint(4) DEFAULT '0' COMMENT '用户状态，0表示正常，1表示被锁定',
  `user_role` tinyint(4) DEFAULT '0' COMMENT '用户等级，0表示正常，1管理员',
  PRIMARY KEY (`user_id`),
  KEY `idx_user_openid` (`user_openid`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表'