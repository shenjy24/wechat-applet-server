CREATE TABLE `wechat_user` (
  `openid` varchar(64) NOT NULL COMMENT '微信小程序用户标识',
  `unionid` varchar(64) DEFAULT NULL COMMENT '微信开放平台帐号用户标识',
  `session_key` varchar(64) NOT NULL COMMENT '微信服务端的会话密钥',
  `token` varchar(128) NOT NULL COMMENT '开发服务端的通信凭证',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='微信小程序用户信息表';

CREATE TABLE `wechat_secret` (
  `appid` varchar(128) NOT NULL COMMENT '微信小程序应用标识',
  `secret` varchar(128) NOT NULL COMMENT '微信小程序应用密钥',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`appid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='微信小程序应用信息表';

CREATE TABLE `wechat_user_info` (
  `openid` varchar(64) NOT NULL COMMENT '微信小程序用户标识',
  `unionid` varchar(64) DEFAULT NULL COMMENT '微信开放平台帐号用户标识',
  `avatar` text NOT NULL COMMENT '头像',
  `nickname` varchar(32) NOT NULL COMMENT '昵称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
PRIMARY KEY (`openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='微信小程序用户信息表';

CREATE TABLE `wechat_express` (
  `id` int NOT NULL COMMENt '逻辑id',
  `userid` varchar(128) NOT NULL COMMENt '快递100用户id',
  `customer` char(32) NOT NULL COMMENT '快递100授权码',
  `key` char(12) DEFAULT NULL COMMENT '快递100授权key',
  `secret` varchar(32) NOT NULL COMMENT '快递100密码',
  `create_time` datetime DEFAULT now() COMMENT '创建时间',
  `update_time` datetime DEFAULT now() COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='快递100信息表';

CREATE TABLE `baidu_api` (
  `appid` int NOT NULL COMMENt '百度开放平台应用编号',
  `app_name` varchar(128) NOT NULL COMMENt '应用名称',
  `ak` varchar(32) NOT NULL COMMENT '应用授权码',
  `type` varchar(32) COMMENT '应用类型',
  `create_time` datetime DEFAULT now() COMMENT '创建时间',
  `update_time` datetime DEFAULT now() COMMENT '更新时间',
  PRIMARY KEY (`appid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='百度开放平台信息表';
