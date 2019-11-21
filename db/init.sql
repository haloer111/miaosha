use miaosha;

-- 用户表
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

-- 商品表
drop table if exists `goods`;
create table `goods`
(
  id           bigint primary key auto_increment,
  goods_name   varchar(32) comment '名字',
  goods_title  varchar(32) comment '标题',
  goods_img    varchar(128) comment '图片',
  goods_detail longtext comment '商品详情介绍',
  goods_price  decimal(10, 2) default '0.00' comment '商品价格',
  goods_stock  int            default '0' comment '商品库存，-1没有限制',
  create_time  datetime not null comment '创建时间',
  update_time  datetime not null comment '更新时间'
)
  engine = InnoDB
  default charset = utf8mb4
  comment '商品表';

insert into goods
values (1, 'iphone', '标题', '/img/img1.jpg', '详情', 50, 100, now(), now())
    ,
       (2, '安卓', '标题', '/img/img2.jpg', '详情', 25, 200, now(), now())
;

-- 秒杀商品表
drop table if exists `miaosha_goods`;
create table `miaosha_goods`
(
  id            bigint primary key auto_increment,
  goods_id      bigint comment '商品id',
  miaosha_price decimal(10, 2) default '0.00' comment '秒杀价格',
  stock_count   int comment '库存数量',
  start_time    datetime comment '秒杀开始时间',
  end_time      datetime comment '秒杀结束时间',
  create_time   datetime not null comment '创建时间',
  update_time   datetime not null comment '更新时间'
)
  engine = InnoDB
  default charset = utf8mb4
  comment '秒杀商品表';

insert into miaosha_goods
values (1, 1, 0.01, 50, now(), now(), now(), now())
;

-- 订单表
drop table if exists `order_info`;
create table `order_info`
(
  id               bigint primary key auto_increment,
  user_id          bigint comment '用户id',
  goods_id         bigint comment '商品id',
  delivery_addr_id bigint comment '收货地址id',
  goods_name       varchar(32) comment '商品名称',
  goods_count      int            default '0' comment '商品数量',
  goods_price      decimal(10, 2) default '0.00' comment '商品价格',
  order_channel    tinyint        default '0' comment '1-pc,2-android,3-ios',
  status           tinyint        default '0' comment '0-新建，1-已支付，2-已发货，3-已收货，4-已退款，5-已完成',
  pay_time         datetime comment '支付时间',
  create_time      datetime not null comment '创建时间',
  update_time      datetime not null comment '更新时间'
)
  engine = InnoDB
  default charset = utf8mb4
  comment '订单表';

-- 秒杀订单表
drop table if exists `miaosha_order`;
create table `miaosha_order`
(
  id          bigint primary key auto_increment,
  user_id     bigint comment '用户id',
  order_id    bigint comment '订单id',
  goods_id    bigint comment '商品id',
  create_time datetime not null comment '创建时间',
  update_time datetime not null comment '更新时间'
)
  engine = InnoDB
  default charset = utf8mb4
  comment '秒杀订单表';