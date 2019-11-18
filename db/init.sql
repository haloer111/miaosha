use miaosha;
drop table if exists `user`;
create table `user`
(
  id          bigint primary key auto_increment,
  name        varchar(32) not null comment '名字',
  create_time datetime    not null comment '创建时间',
  update_time datetime    not null comment '更新时间'
)
  engine = InnoDB
  default charset = utf8mb4
  comment '用户表';