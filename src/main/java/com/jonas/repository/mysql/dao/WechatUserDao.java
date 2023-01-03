package com.jonas.repository.mysql.dao;

import com.jonas.repository.mysql.entity.WechatUser;
import org.springframework.data.repository.CrudRepository;

/**
 * <p>
 * 微信小程序用户信息表 服务实现类
 * </p>
 *
 * @author shenjy
 * @since 2022-06-26
 */
public interface WechatUserDao extends CrudRepository<WechatUser, String> {

}
