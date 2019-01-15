#后台管理系统框架V1

### 建数据库
CREATE SCHEMA `bmzs` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;


### 编码规范
##### 数据库sql
    1，db/migration 里所有的sql都会上生产环境，不要放测试数据。
    2，db/migration-testdata 测试数据的sql都放这个目录，开发及测试环境会执行这个目录下的sql语句。
    3，如果不是必要，尽量增加sql文件而不是修改之前的sql文件。项目正式发布后禁止修改之前的sql文件。