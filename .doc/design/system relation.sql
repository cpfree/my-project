


create table sys_module (
    guid varchar(36) primary key comment '全局唯一标识',
    path varchar(100) not null default '' comment '路径',
    level int(1) not null default 0 comment '级别',
    module varchar(36) not null default '' comment '模块',
    comment varchar(200) not null default '' comment '注释'
) comment '页面设计';



create table sys_page (
    guid varchar(36) primary key comment '全局唯一标识',
    path varchar(100) not null default '' comment '路径',
    module varchar(36) not null default '' comment '模块',
    comment varchar(200) not null default '' comment '注释'
) comment '页面设计';

create table sys_post_code (
    guid varchar(36) primary key comment '全局唯一标识',
    code varchar(30) not null comment '返回码',
    page_guid varchar(36) not null comment '页面guid',
    text varchar(30) not null comment '文本',
    `operate_guid` varchar(36) NOT NULL default '' COMMENT '操作人标识'
) comment '返回码';








create table `sys_parameter` (
    `key` varchar(30) primary key comment '名称, 唯一主键',
    value varchar(180) not null comment '参数值',
    type varchar(2) not null default '' comment '类型',
    ord int(4) default 0 comment '排序',
    `comment` varchar(200) not null default '' comment '备注',
    `add_time` datetime NOT NULL COMMENT '添加时间',
    `update_time` datetime NOT NULL COMMENT '更新时间',
    `operate_guid` varchar(36) NOT NULL default '' COMMENT '操作人标识',
    `state` char(1) NOT NULL COMMENT '{1:激活,2:禁用}'
) comment='参数表';




#################################################



