package com.jonas.repository.mysql.dao;

import com.jonas.repository.mysql.entity.WechatUserInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * <p>
 * 微信小程序应用信息表 服务实现类
 * </p>
 *
 * @author shenjy
 * @since 2022-06-25
 */
public interface WechatUserInfoDao extends CrudRepository<WechatUserInfo, String> {

}
