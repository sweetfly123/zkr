drop table directory;
create table directory
(
    id           int auto_increment
        primary key,
    organization varchar(32) null,
    path         varchar(32) null,
    gmt_create_time datetime default CURRENT_TIMESTAMP,
    gmt_update_time datetime
);

