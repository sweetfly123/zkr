create table file_path
(
    id              int auto_increment
        primary key,
    organization_id int(32)      not null,
    path            varchar(128) null,
    gmt_create_time datetime default CURRENT_TIMESTAMP,
    gmt_update_time datetime
);