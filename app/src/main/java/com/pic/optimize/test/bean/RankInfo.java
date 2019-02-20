package com.pic.optimize.test.bean;

public class RankInfo {

    /**
     * 答题结果：0-错误、1-正确
     */
    public String is_correct = "";

    /**
     * 用户答题总数（当答题总数在6题及6题以上时展示排名)
     */
    public String total_count = "";


    /**
     * 用户排名（当答题总数在6题及6题以上时展示排名）默认为0
     */
    public String rank = "";


    /**
     * 用户作答正确总数
     */
    public String correct_count = "";
}
