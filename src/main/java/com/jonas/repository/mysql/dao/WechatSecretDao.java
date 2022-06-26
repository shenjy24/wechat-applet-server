package com.jonas.repository.mysql.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jonas.repository.mysql.entity.WechatSecret;
import com.jonas.repository.mysql.mapper.WechatSecretMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 微信小程序应用信息表 服务实现类
 * </p>
 *
 * @author shenjy
 * @since 2022-06-25
 */
@Service
public class WechatSecretDao extends ServiceImpl<WechatSecretMapper, WechatSecret> implements IService<WechatSecret> {

}
