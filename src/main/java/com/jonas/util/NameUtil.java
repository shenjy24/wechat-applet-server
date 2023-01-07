package com.jonas.common;

import java.util.Random;

/**
 * 命名工具类
 *
 * @author shenjy 2019/03/22
 */
public class NameUtil {

    private static String BOY = "梁、栋、维、启、克、伦、峰、旭、鹏、泽、" +
            "晨、辰、士、乐、建、杰、致、树、逸、盛、" +
            "雄、顺、冠、策、腾、茂、榕、风、航、弘、" +
            "义、兴、良、飞、彬、富、和、鸣、朋、斌、" +
            "光、时、泰、博、林、民、友、志、清、坚、" +
            "庆、天、星、思、群、豪、心、伟、浩、勇、" +
            "强、平、东、文、辉、业、名、永、信、彦、" +
            "健、世、广、海、山、敬、安、武、生、涛、" +
            "龙、元、全、善、胜、学、祥、才、毅、俊、" +
            "仁、义、礼、智、信、新、功、耀、成、康";

    public static String getName(Integer num) {
        String[] names = BOY.split("、");
        int max = names.length;
        Random random = new Random();
        StringBuffer userName = new StringBuffer("喵星人·");
        for (int i = 0; i < num; i++) {
            int s = random.nextInt(max);
            if (s == 0) {
                System.out.println(s);
            }
            if (s == max - 1) {
                System.out.println(s);
            }
            userName.append(names[s]);
        }
        return userName.toString();
    }

    public static void main(String[] args) {
        System.out.println(getName(1));
    }
}
