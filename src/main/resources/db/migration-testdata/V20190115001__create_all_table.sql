
CREATE TABLE `wxapp_user`(
	`id` BIGINT NOT NULL,
	`nick_name` VARCHAR(64) NOT NULL COMMENT '微信名',
	`province` VARCHAR(64)  COMMENT '省份',
	`city` VARCHAR(64)  COMMENT '城市',
	`open_id` VARCHAR(64) COMMENT '用户open_id',
	`avatar_url` VARCHAR(64)  COMMENT '用户头像',
	PRIMARY KEY(`id`)
)COMMENT '微信授权登录用户表';

CREATE TABLE `wxapp_user_session`(
	`id` BIGINT NOT NULL,
	`session_key` VARCHAR(256) NOT NULL COMMENT '会话key',
	`open_id` VARCHAR(64) NOT NULL COMMENT '用户微信openid',
	`expire_date` DATETIME NOT NULL COMMENT '过期时间',
	`client_key` VARCHAR(64) NOT NULL COMMENT '',
	PRIMARY KEY(`id`)
)COMMENT '用户会话表';



CREATE TABLE `activity_category`
(
		`category_id`		SMALLINT NOT NULL auto_increment,
		`category_name`		VARCHAR(64) NOT NULL COMMENT '活动类型名称',
		`category_type`     SMALLINT NOT NULL COMMENT '类目编号',
		PRIMARY KEY(`category_id`)
)COMMENT '活动类型表';


CREATE TABLE `user`
(
    `user_id`         INT    NOT NULL auto_increment,
	`user_openid`	  VARCHAR(64) NOT NULL COMMENT '用户微信openid',
    `user_nick_name`    	  VARCHAR(64) NOT NULL COMMENT '用户昵称',
    `user_phone`      VARCHAR(64) COMMENT '用户电话',
    `user_address`    VARCHAR(64) COMMENT '用户地址',
	`user_status`     TINYINT DEFAULT 0 COMMENT '用户状态，0表示正常，1表示被锁定',
	`user_role`		  TINYINT DEFAULT 0 COMMENT '用户等级，0表示正常，1管理员',
	PRIMARY KEY (`user_id`),
	key `idx_user_openid` (`user_openid`)
) COMMENT '用户表';

CREATE TABLE `required_item_detail`
(
		`required_item_detail_id`  INT NOT NULL auto_increment,
		`name`					   VARCHAR(16) NOT NULL COMMENT '用户姓名',
		`sex` 					   TINYINT COMMENT '用户性别',
		`age`  						TINYINT COMMENT '用户年龄',
		`address` 					VARCHAR(32) COMMENT '用户地址',
		`phone` 					VARCHAR(10) COMMENT '用户手机号',
		`wechat_number` 			VARCHAR(16) COMMENT '用户微信号',
		`qq_number` 				VARCHAR(16) COMMENT '用户qq号',
		`email` 					VARCHAR(16) COMMENT '用户邮箱',
		`school` 					VARCHAR(16) COMMENT '用户学校',
		`grade` 					VARCHAR(8) COMMENT '用户年级',
		`class_number`				 VARCHAR(8) COMMENT '用户班级',
		`studentId` 				VARCHAR(16) COMMENT '用户学号',
		`work_place` 				VARCHAR(16) COMMENT '用户工作单位',
		`department`				 VARCHAR(16) COMMENT '用户部门',
		`position`					 VARCHAR(16) COMMENT '用户职位',
		`job_number`				 VARCHAR(16) COMMENT '用户工号',
		`province` 					VARCHAR(16) COMMENT '用户省份',
		`city` 						VARCHAR(16) COMMENT '用户城市',
		`field_one`					 VARCHAR(16) COMMENT  '自定义字段1',
		`field_two`					 VARCHAR(16) COMMENT  '自定义字段2',
		`field_three` 				VARCHAR(16) COMMENT  '自定义字段3',
		PRIMARY KEY(`required_item_detail_id`)
)COMMENT '必填信息表';


CREATE TABLE `required_item`
(
		`required_item_id`  INT NOT NULL auto_increment,
		`name` BOOLEAN NOT NULL DEFAULT TRUE COMMENT '用户姓名',
		`sex`  BOOLEAN COMMENT '用户性别',
		`age`  BOOLEAN COMMENT '用户年龄',
		`address` BOOLEAN COMMENT '用户地址',
		`phone` BOOLEAN COMMENT '用户手机号',
		`wechat_number` BOOLEAN COMMENT '用户微信号',
		`qq_number` BOOLEAN COMMENT '用户qq号',
		`email` BOOLEAN COMMENT '用户邮箱',
		`school` BOOLEAN COMMENT '用户学校',
		`grade` BOOLEAN COMMENT '用户年级',
		`class_number` BOOLEAN COMMENT '用户班级',
		`studentId` BOOLEAN COMMENT '用户学号',
		`work_place` BOOLEAN COMMENT '用户工作单位',
		`department` BOOLEAN COMMENT '用户部门',
		`position` BOOLEAN COMMENT '用户职位',
		`job_number` BOOLEAN COMMENT '用户工号',
		`province` BOOLEAN COMMENT '用户省份',
		`city` BOOLEAN COMMENT '用户城市',
		`field_one` BOOLEAN COMMENT  '自定义字段1',
		`field_two` BOOLEAN COMMENT  '自定义字段2',
		`field_three` BOOLEAN COMMENT  '自定义字段3',
		PRIMARY KEY(`required_item_id`)
)COMMENT '必填信息表';

CREATE TABLE `activity_info`
(
		`act_id`				INT NOT NULL auto_increment,
		`user_id`				INT NOT NULL COMMENT '用户id',
		`required_item_id`      INT NOT NULL COMMENT '必填项id',
		`act_title` 			VARCHAR(32) NOT NULL COMMENT '活动标题',
		`act_detail_info`		VARCHAR(256) NOT NULL COMMENT '活动详细信息',
		`act_signup_deadline`	DATETIME NOT NULL COMMENT '报名截止时间',
		`act_start_time` 		DATETIME NOT NULL COMMENT '活动开始时间',
	#	`act_end_time` 			DATETIME NOT NULL COMMENT '活动结束时间',
	#	`act_number_limit`  	SMALLINT NOT NULL COMMENT '活动最大报名人数',
		`act_reminder` 			BOOLEAN NOT NULL COMMENT '是否开启活动提醒',
		`category_type`			INT NOT NULL COMMENT '活动类型编号',
		`act_address`              VARCHAR(16) NOT NULL COMMENT '活动地址',
		`longitude`             DECIMAL(15,12) NOT NULL COMMENT '活动经度',
		`latitude`             DECIMAL(15,12) NOT NULL COMMENT '活动纬度',
		`participants_number`	INT NOT NULL COMMENT '活动参与人数',
		`act_heat`				INT NOT NULL COMMENT '活动热度',
		`act_like`				INT NOT NULL COMMENT '活动点赞人数',
		`act_status`			TINYINT(3) NOT NULL DEFAULT '0' COMMENT '活动状态，默认0审核中，1表示审核通过，2审核未通过，3活动被禁止',
		`act_run_status`		TINYINT(3) NOT NULL DEFAULT '0' COMMENT '活动进行状态，0报名未截止，1报名截止，2失效',
		`create_time` 			TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
 		`update_time`			TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
		PRIMARY KEY (`act_id`),
		FOREIGN KEY (`user_id`) REFERENCES `user`(`user_id`),
		FOREIGN KEY (`required_item_id`) REFERENCES `required_item`(`required_item_id`),
		KEY `idx_category_type` (`category_type`)
)COMMENT '活动信息表';

CREATE TABLE `user_browsing_history`
(
	`h_id` INT NOT NULL auto_increment,
	`browsing_time` 			TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '浏览时间',
	`act_id`				INT NOT NULL COMMENT '浏览的活动id',
	`user_id`				INT NOT NULL COMMENT '用户id',
	FOREIGN KEY (`act_id`) REFERENCES `activity_info`(`act_id`),
	FOREIGN KEY (`user_id`) REFERENCES `user`(`user_id`),
	PRIMARY KEY(`h_id`)
)COMMENT '用户浏览历史表';

CREATE TABLE `user_collection`
(
	`c_id` INT NOT NULL auto_increment,
	`collect_time` 			TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '收藏时间',
	`act_id`				INT NOT NULL COMMENT '收藏的活动id',
	`user_id`				INT NOT NULL COMMENT '用户id',
	FOREIGN KEY (`act_id`) REFERENCES `activity_info`(`act_id`),
	FOREIGN KEY (`user_id`) REFERENCES `user`(`user_id`),
	PRIMARY KEY(`c_id`)
)COMMENT '用户收藏表';


CREATE TABLE `user_signup_info`
(
		`user_signup_id` 		INT NOT NULL auto_increment,
		`user_id`				INT NOT NULL COMMENT '用户id',
		`act_id`				INT NOT NULL COMMENT '活动id',
		`user_signup_status`	TINYINT(3) NOT NULL DEFAULT '0' COMMENT '用户报名状态，默认0未完成报名',
		`required_item_detail_id`  INT NOT NULL COMMENT '必填项详情id',
		`create_time` 			TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
 		`update_time`			TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
		PRIMARY KEY(`user_signup_id`),
		FOREIGN KEY (`user_id`) REFERENCES `user`(`user_id`),
		FOREIGN KEY (`act_id`) REFERENCES `activity_info`(`act_id`),
		FOREIGN KEY (`required_item_detail_id`) REFERENCES `required_item_detail`(`required_item_detail_id`)
) COMMENT '用户报名信息表';




