CREATE TABLE `required_item_detail` (
  `required_item_detail_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户姓名',
  `sex` tinyint(4) DEFAULT NULL COMMENT '用户性别',
  `age` tinyint(4) DEFAULT NULL COMMENT '用户年龄',
  `address` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户地址',
  `phone` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户手机号',
  `wechat_number` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户微信号',
  `qq_number` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户qq号',
  `email` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户邮箱',
  `school` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户学校',
  `grade` varchar(8) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户年级',
  `class_number` varchar(8) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户班级',
  `student_id` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户学号',
  `work_place` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户工作单位',
  `department` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户部门',
  `position` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户职位',
  `job_number` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户工号',
  `province` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户省份',
  `city` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户城市',
  `field_one` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '自定义字段1',
  `field_two` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '自定义字段2',
  `field_three` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '自定义字段3',
  PRIMARY KEY (`required_item_detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='必填信息表'