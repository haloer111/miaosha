use miaosha;
drop table if exists `user`;
create table `user`
(
  id              bigint primary key auto_increment,
  name            varchar(32) not null comment '名字',
  password        varchar(32) comment 'md5加密候的密码',
  head            varchar(128) comment '头像',
  last_login_time datetime    not null comment '上次登录时间',
  login_count     int default '0' comment '登录次数',
  create_time     datetime    not null comment '创建时间',
  update_time     datetime    not null comment '更新时间'
)
  engine = InnoDB
  default charset = utf8mb4
  comment '用户表';