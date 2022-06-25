package com.jonas.data.mysql.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jonas.data.mysql.entity.WechatSecret;
import com.jonas.data.mysql.mapper.WechatSecretMapper;
import com.jonas.data.mysql.dao.IWechatSecretService;
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
public class WechatSecretServiceImpl extends ServiceImpl<WechatSecretMapper, WechatSecret> implements IWechatSecretService {

}
