alter table activity_info modify column `act_run_status` tinyint(3) NOT NULL DEFAULT 0 COMMENT '活动进行状态，0正常 1表示被禁止(失效)';
alter table activity_info modify column `act_address` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '活动地址';



alter table activity_info add `is_limitNum` Boolean COMMENT '是否有最大人数限制';
alter table activity_info add `max_num` integer COMMENT '设置最大人数';
alter table activity_info add `is_private` Boolean COMMENT '是否需要密码访问';
alter table activity_info add `act_password` varchar(64) COMMENT '设置密码';