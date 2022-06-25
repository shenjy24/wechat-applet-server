CREATE TABLE `wechat_session` (
  `openid` varchar(128) NOT NULL COMMENT '微信小程序用户标识',
  `unionid` varchar(128) NOT NULL COMMENT '',
  `session_key` varchar(128) NOT NULL COMMENT '会话密钥',
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
