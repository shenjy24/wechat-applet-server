package com.jonas.repository.mysql.mapper;

import com.jonas.repository.mysql.entity.WechatSecret;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 微信小程序应用信息表 Mapper 接口
 * </p>
 *
 * @author shenjy
 * @since 2022-06-25
 */
@Mapper
public interface WechatSecretMapper extends BaseMapper<WechatSecret> {

}
