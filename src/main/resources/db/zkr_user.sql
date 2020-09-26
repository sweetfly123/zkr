create table user
(
    id           int auto_increment
        primary key,
    username     varchar(32)  null,
    password     varchar(255) null,
    enabled      tinyint(1)   null,
    locked       tinyint(1)   null,
    organization varchar(128) null
);

INSERT INTO zkr.user (id, username, password, enabled, locked, organization) VALUES (1, 'root', '$2a$10$RMuFXGQ5AtH4wOvkUqyvuecpqUSeoxZYqilXzbz50dceRsga.WYiq', 1, 0, null);
INSERT INTO zkr.user (id, username, password, enabled, locked, organization) VALUES (2, 'admin', '$2a$10$RMuFXGQ5AtH4wOvkUqyvuecpqUSeoxZYqilXzbz50dceRsga.WYiq', 1, 0, null);
INSERT INTO zkr.user (id, username, password, enabled, locked, organization) VALUES (3, 'sang', '$2a$10$RMuFXGQ5AtH4wOvkUqyvuecpqUSeoxZYqilXzbz50dceRsga.WYiq', 1, 0, null);