create table in_girl(
                        id varchar(36) primary key comment '唯一主键',
                        role_type varchar(2) not null comment '身份类型',
                        people_type varchar(2) not null comment '人物类型',
                        name varchar(20) not null comment '姓名',
                        nickname varchar(20) default '' comment '称呼',
                        world varchar(20) not null comment '来自世界',
                        location varchar(2) not null comment '位于',
                        insert_time datetime comment '添加时间',
                        update_time datetime not null comment '更新时间',
                        relationship varchar(2) not null comment '与主角关系',
                        summary varchar(400) default '' comment '简介',
                        des varchar(1000) default '' comment '描述'
)