CREATE TABLE `activity_category` (
  `category_id` smallint(6) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '活动类型名称',
  `category_type` smallint(6) NOT NULL COMMENT '类目编号',
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='活动类型表'