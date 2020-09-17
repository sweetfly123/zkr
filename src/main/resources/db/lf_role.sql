create table role
(
    id     int auto_increment
        primary key,
    name   varchar(32) null,
    nameZh varchar(32) null
)
    charset = utf8;

INSERT INTO lf.role (id, name, nameZh) VALUES (1, 'dba', '数据库管理员');
INSERT INTO lf.role (id, name, nameZh) VALUES (2, 'admin', '系统管理员');
INSERT INTO lf.role (id, name, nameZh) VALUES (3, 'user', '用户');