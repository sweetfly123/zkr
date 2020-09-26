create table user_role
(
    id  int auto_increment
        primary key,
    uid int null,
    rid int null
);

INSERT INTO zkr.user_role (id, uid, rid) VALUES (1, 1, 1);
INSERT INTO zkr.user_role (id, uid, rid) VALUES (2, 1, 2);
INSERT INTO zkr.user_role (id, uid, rid) VALUES (3, 2, 2);
INSERT INTO zkr.user_role (id, uid, rid) VALUES (4, 3, 3);