package com.jonas.repository.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jonas.repository.mysql.entity.WechatUserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 微信小程序用户信息表 Mapper 接口
 * </p>
 *
 * @author shenjy
 * @since 2022-06-26
 */
@Mapper
public interface WechatUserInfoMapper extends BaseMapper<WechatUserInfo> {

}
