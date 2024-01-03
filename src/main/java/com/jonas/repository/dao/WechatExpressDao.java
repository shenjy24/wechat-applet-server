package com.jonas.repository.dao;

import com.jonas.repository.entity.WechatExpress;
import org.springframework.data.repository.CrudRepository;

/**
 * <p>
 * 快递100信息查询类
 * </p>
 *
 * @author shenjy
 * @since 2022-06-26
 */
public interface WechatExpressDao extends CrudRepository<WechatExpress, Integer> {

}
