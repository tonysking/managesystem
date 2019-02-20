CREATE TABLE `user_collection` (
  `c_id` int(11) NOT NULL AUTO_INCREMENT,
  `collect_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '收藏时间',
  `act_id` int(11) NOT NULL COMMENT '收藏的活动id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`c_id`),
  KEY `act_id` (`act_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户收藏表'