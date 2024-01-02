package com.jonas.common;

/**
 * @author shenjy
 * @createTime 2022/6/27 20:17
 * @description Constant
 */
public class Constant {
    public static final String AUTH_HEADER = "Authorization";

    public static final String REQ_ATT_USER = "attr-user";

    public static final String AVATAR_DEFAULT = "https://mmbiz.qpic.cn/mmbiz/icTdbqWNOwNRna42FI242Lcia07jQodd2FJGIYQfG0LAJGFxM4FbnQP6yfMxBgJ0F3YRqJCJ1aPAK2dQagdusBZg/0";

    // 距离5分钟就进行刷新，单位毫秒
    public static final int ACCESS_TOKEN_REFRESH_MS = 5 * 50 * 1000;
}
