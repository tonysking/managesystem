CREATE TABLE `user_browsing_history` (
  `h_id` int(11) NOT NULL AUTO_INCREMENT,
  `browsing_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '浏览时间',
  `act_id` int(11) NOT NULL COMMENT '浏览的活动id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`h_id`),
  KEY `act_id` (`act_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户浏览历史表'