

CREATE TABLE `sys_dict` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `key` varchar(50) NOT NULL COMMENT '数据类别',
    `par_code` varchar(12) NOT NULL DEFAULT '0' COMMENT '父ID',
    `code` varchar(20) NOT NULL COMMENT '数据编码',
    `text` varchar(200) NOT NULL COMMENT '数据名称',
    `sort` int(11) NOT NULL DEFAULT '50' COMMENT '顺序',
    `level` int(1) NOT NULL comment '等级',
    `remark` varchar(400) NOT NULL DEFAULT '' COMMENT '数据描述',
    `add_time` datetime NOT NULL COMMENT '添加时间',
    `update_time` datetime NOT NULL COMMENT '更新时间',
    `state` int(11) NOT NULL COMMENT '1正常,0删除',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统字典表';

CREATE TABLE `sys_dict_key` (
    `name` varchar(50) primary key COMMENT '名称, 唯一主键',
    `text` varchar(200) NOT NULL COMMENT '数据名称',
    `sort` int(11) NOT NULL DEFAULT '50' COMMENT '顺序',
    `max_level` int(1) NOT NULL comment '最大级别',
    `remark` varchar(400) NOT NULL DEFAULT '' COMMENT '数据描述',
    `add_time` datetime NOT NULL COMMENT '添加时间',
    `update_time` datetime NOT NULL COMMENT '更新时间',
    `state` char(1) NOT NULL COMMENT '{1:激活,2:禁用}'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='系统字典键表';


create table `sys_parameter` (
    `key` varchar(30) primary key comment '名称, 唯一主键',
    value varchar(180) not null comment '参数值',
    type varchar(2) not null default '' comment '类型',
    ord int(4) default 0 comment '排序',
    remark varchar(200) not null default '' comment '备注',
    `add_time` datetime NOT NULL COMMENT '添加时间',
    `update_time` datetime NOT NULL COMMENT '更新时间',
    `state` char(1) NOT NULL COMMENT '{1:激活,2:禁用}'
) comment='参数表';




#################################################



