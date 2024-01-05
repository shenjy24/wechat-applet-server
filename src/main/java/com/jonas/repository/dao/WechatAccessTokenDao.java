package com.jonas.repository.dao;

import com.jonas.repository.entity.WechatAccessToken;
import org.springframework.data.repository.CrudRepository;

/**
 * <p>
 * 微信小程序access_token表 服务实现类
 * </p>
 *
 * @author shenjy
 * @since 2022-06-25
 */
public interface WechatAccessTokenDao extends CrudRepository<WechatAccessToken, String> {
}
