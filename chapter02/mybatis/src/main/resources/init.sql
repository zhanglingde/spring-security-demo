create table role
(
    role_id int not null auto_increment primary key,
    name    VARCHAR(32) DEFAULT (NULL),
    name_zh VARCHAR(32) DEFAULT (NULL)
);

create table user
(
    user_id               INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username              VARCHAR(32),
    password              VARCHAR(32),
    enabled               TINYINT(1),
    accountNonExpired     tinyint(1) default null,
    accountNonLocked      tinyint(1) default null,
    credentialsNonExpired tinyint(1) default null
);

create table user_role
(
    id      int not null auto_increment primary key,
    user_id int default null,
    role_id int default null,
    key user_id (user_id),
    key role_id (role_id)
);

insert into role(role_id, name, name_zh)
values (1, 'ROLE_dba', '数据库管理员'),
       (2, 'ROLE_admin', '系统管理员'),
       (3, 'ROLE_user', '用户');

insert into user(user_id, username, password, enabled, accountNonExpired, accountNonLocked, credentialsNonExpired)
values (1, 'root', '{noop}123', 1, 1, 1, 1),
       (2, 'admin', '{noop}123', 1, 1, 1, 1),
       (3, 'ling', '{noop}123', 1, 1, 1, 1);

insert into user_role(id, user_id, role_id)
values (1, 1, 1),
       (2, 1, 2),
       (3, 2, 2),
       (4, 3, 3);