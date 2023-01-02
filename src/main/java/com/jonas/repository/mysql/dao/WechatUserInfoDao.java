package com.jonas.repository.mysql.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jonas.repository.mysql.entity.WechatUserInfo;
import com.jonas.repository.mysql.mapper.WechatUserInfoMapper;
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
public class WechatUserInfoDao extends ServiceImpl<WechatUserInfoMapper, WechatUserInfo> implements IService<WechatUserInfo> {

}
