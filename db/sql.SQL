-- 这里是所有的DDL
-- 创建数据库
create database pando_kola;

use pando_kola;

create table `system_setting` (
    `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
    `system_setting_key` varchar(32) NOT NULL DEFAULT '',
    `system_setting_value` varchar(32) NOT NULL DEFAULT '',
    PRIMARY KEY (`id`),
    UNIQUE KEY (`system_setting_key`),
    KEY `idx_key` (`system_setting_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统设置表';