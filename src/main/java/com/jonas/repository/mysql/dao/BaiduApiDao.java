package com.jonas.repository.mysql.dao;

import com.jonas.repository.mysql.entity.BaiduApi;
import com.jonas.repository.mysql.entity.WechatUserInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * @author shenjy
 * @since 2022-06-25
 */
public interface BaiduApiDao extends CrudRepository<BaiduApi, Integer> {

}
