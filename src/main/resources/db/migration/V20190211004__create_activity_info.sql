CREATE TABLE `activity_info` (
  `act_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `required_item_id` int(11) NOT NULL COMMENT '必填项id',
  `act_title` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '活动标题',
  `act_detail_info` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '活动详细信息',
  `act_signup_deadline` datetime NOT NULL COMMENT '报名截止时间',
  `act_start_time` datetime NOT NULL COMMENT '活动开始时间',
  `act_reminder` tinyint(1) NOT NULL COMMENT '是否开启活动提醒',
  `category_type` int(11) NOT NULL COMMENT '活动类型编号',
  `act_address` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '活动地址',
  `longitude` decimal(15,12) NOT NULL COMMENT '活动经度',
  `latitude` decimal(15,12) NOT NULL COMMENT '活动纬度',
  `participants_number` int(11) NOT NULL COMMENT '活动参与人数',
  `act_heat` int(11) NOT NULL COMMENT '活动热度',
  `act_like` int(11) NOT NULL COMMENT '活动点赞人数',
  `act_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '活动状态，默认0审核中，1表示审核通过，2审核未通过，3活动被禁止',
  `act_run_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '活动进行状态，0正常 1表示失效',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_limit_num` tinyint(1) DEFAULT NULL COMMENT '是否有最大人数限制',
  `max_num` int(11) DEFAULT NULL COMMENT '设置最大人数',
  `is_private` tinyint(1) DEFAULT NULL COMMENT '是否需要密码访问',
  `act_password` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '设置密码',
  `is_delete` tinyint(1) DEFAULT 0 COMMENT '是否被发起者删除 0未被删除，1已被删除',
  PRIMARY KEY (`act_id`),
  KEY `user_id` (`user_id`),
  KEY `required_item_id` (`required_item_id`),
  KEY `idx_category_type` (`category_type`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='活动信息表'