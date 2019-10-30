# 表信息
# 字段信息


# mapping信息, api调用, 一个mapping, 同时有调用url, 是否事务, controller位置, 是否需要登录, 权限, 成功返回信息介绍
# baseCode 存放基本类型
# funCode 存放返回类型, 同时与mapping关联, 显示本文, 与mapping应该是一对多关系,






# 模块
# 页面信息 页面
# 按钮信息


create table design_table (
   guid varchar(36) primary key comment '全局唯一标识',
   name varchar(50) not null comment '表名',
   remark varchar(200) not null comment '备注'
);

create table design_field (
    guid varchar(36) primary key comment '全局唯一标识',
    name varchar(30) not null default '' comment '字段名称',
    type varchar(10) not null default '' comment '数据类型',
    length int(3) not null default 0 comment '长度',
    label varchar(20) not null default '' comment '显示文本',
    comment varchar(100) not null default '' comment '备注',
    codeItem_guid varchar(36) not null default '' comment '代码项',
    n_search boolean not null default false comment '是否需要搜索',
    n_list boolean not null default false comment '是否需要列表',
    n_add boolean not null default false comment '是否需要新增',
    n_edit boolean not null default false comment '是否需要变价'
) comment '字段设计';

create table design_module (
    guid varchar(36) primary key comment '全局唯一标识',
    path varchar(100) not null default '' comment '路径',
    level int(1) not null default 0 comment '级别',
    module varchar(36) not null default '' comment '模块',
    comment varchar(200) not null default '' comment '注释'
) comment '页面设计';


create table design_page (
    guid varchar(36) primary key comment '全局唯一标识',
    path varchar(100) not null default '' comment '路径',
    module varchar(36) not null default '' comment '模块',
    comment varchar(200) not null default '' comment '注释'
) comment '页面设计';

create table design_post_code (
    guid varchar(36) primary key comment '全局唯一标识',
    page_guid varchar(36) not null comment '页面guid',
    code varchar(30) not null comment '代码',
    text varchar(30) not null comment '文本',

) comment '返回码'

