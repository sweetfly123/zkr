drop table record;
create table record
(
    id               int auto_increment
        primary key,
    user_id          int(32)           null,
    provider         varchar(32)       null,
    type             tinyint           null,
    is_secret        tinyint           null,
    file_name        varchar(64)       null,
    is_modify_header tinyint default 0 null comment '是否改动表头 0:否，1：是，默认值：0',
    commit_time      datetime          null,
    file_size        varchar(32)       null comment '文件大小，单位KB',
    file_format      varchar(32)       null,
    status           varchar(32)       null,
    detail           varchar(4096)     null,
    gmt_create_time datetime default CURRENT_TIMESTAMP,
    gmt_update_time datetime
);

