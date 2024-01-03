package com.jonas.repository.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shenjy
 * @createTime 2022/6/27 14:06
 * @description UserProfile
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {
    private String openId;
    private String unionId;
    private String nickName;
    private int gender;
    private String language;
    private String city;
    private String province;
    private String country;
    private String avatarUrl;
    private Watermark watermark;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Watermark {
        private String appid;
        private int timestamp;
    }
}
