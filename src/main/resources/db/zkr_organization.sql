create table organization
(
    id              int auto_increment
        primary key,
    name            varchar(32) null,
    gmt_create_time datetime default CURRENT_TIMESTAMP,
    gmt_update_time datetime
);