CREATE TABLE `user_signup_info` (
  `user_signup_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `act_id` int(11) NOT NULL COMMENT '活动id',
  `user_signup_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '用户报名状态，0表示正常报名 1表示取消报名',
  `required_item_detail_id` int(11) NOT NULL COMMENT '必填项详情id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`user_signup_id`),
  KEY `user_id` (`user_id`),
  KEY `act_id` (`act_id`),
  KEY `required_item_detail_id` (`required_item_detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户报名信息表'