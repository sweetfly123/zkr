drop table role;
create table role
(
    id     int auto_increment
        primary key,
    name   varchar(32) null,
    nameZh varchar(32) null,
    gmt_create_time datetime default CURRENT_TIMESTAMP,
    gmt_update_time datetime
);

INSERT INTO zkr.role (id, name, nameZh) VALUES (1, 'dba', '数据库管理员');
INSERT INTO zkr.role (id, name, nameZh) VALUES (2, 'admin', '系统管理员');
INSERT INTO zkr.role (id, name, nameZh) VALUES (3, 'user', '用户');