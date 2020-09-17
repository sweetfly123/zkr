create table user
(
    id       int auto_increment
        primary key,
    username varchar(32)  null,
    password varchar(255) null,
    enabled  tinyint(1)   null,
    locked   tinyint(1)   null
)
    charset = utf8;

INSERT INTO lf.user (id, username, password, enabled, locked) VALUES (1, 'root', '$2a$10$RMuFXGQ5AtH4wOvkUqyvuecpqUSeoxZYqilXzbz50dceRsga.WYiq', 1, 0);
INSERT INTO lf.user (id, username, password, enabled, locked) VALUES (2, 'admin', '$2a$10$RMuFXGQ5AtH4wOvkUqyvuecpqUSeoxZYqilXzbz50dceRsga.WYiq', 1, 0);
INSERT INTO lf.user (id, username, password, enabled, locked) VALUES (3, 'sang', '$2a$10$RMuFXGQ5AtH4wOvkUqyvuecpqUSeoxZYqilXzbz50dceRsga.WYiq', 1, 0);