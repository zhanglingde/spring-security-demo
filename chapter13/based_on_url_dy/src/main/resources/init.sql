create table menu
(
    menu_id int not null auto_increment primary key,
    url     varchar(60)
);
insert into menu(menu_id, url)
values (1, '/admin/**'),
       (2, '/user/**'),
       (3, '/guest/**');

create table role
(
    role_id int not null auto_increment primary key,
    name    varchar(60),
    name_zh varchar(60)
);
insert into role(role_id, name, name_zh)
values (1, 'ROLE_admin', '系统管理员'),
       (2, 'ROLE_user', '普通用户'),
       (3, 'ROLE_guest', '访客');

create table user
(
    user_id  int not null auto_increment primary key,
    username varchar(60),
    password varchar(255),
    enabled  tinyint(1) default 1,
    locked   tinyint(1) default 0
);
insert into user (user_id, username, password, enabled, locked)
values (1, 'admin', '{noop}123', 1, 0),
       (2, 'user', '{noop}123', 1, 0),
       (3, 'ling', '{noop}123', 1, 0);

create table user_role
(
    user_role_id int not null auto_increment primary key,
    user_id      int not null,
    role_id      int not null
);
insert into user_role
values (1,1,1),
       (2,2,2),
       (3,3,3);

create table menu_role
(
    menu_role_id int not null auto_increment primary key,
    menu_id      int not null,
    role_id      int not null
);
insert into menu_role(menu_role_id, menu_id, role_id)
values (1,1,1),
       (2,2,1),
       (3,3,1),
       (4,2,2),
       (5,3,2),
       (6,3,6);
