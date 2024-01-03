package com.jonas.repository.dao;

import com.jonas.repository.entity.WechatSecret;
import org.springframework.data.repository.CrudRepository;

/**
 * <p>
 * 微信小程序应用信息表 服务实现类
 * </p>
 *
 * @author shenjy
 * @since 2022-06-25
 */
public interface WechatSecretDao extends CrudRepository<WechatSecret, String> {

}
