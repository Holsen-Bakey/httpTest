package com.pic.optimize.test.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QuestionInfo implements Serializable {

    /**
     * 题目ID
     */
    public String question_id = "";

    /**
     * 题目
     */
    public String title = "";


    /**
     * 题目答案（题目选项数组KEY+1）
     */
    public String answer = "";

    /**
     * 解释
     */
    public String explain = "";


    /**
     * 今日是否答题：1-已答题、2-未答题,-1是网络异常type
     */
    public String type = "1";

    /**
     * 今日是否答题：1-已答题、2-未答题
     */
    public int card_type;

    /**
     * 答题结果：0-错误、1-正确,3-完成所有题目
     */
    public String is_correct = "";


    public String correct_count = "";

    public String total_count = "";


    public List<String> options = new ArrayList<>();

    /**
     * 用户选择的选项,从1开始，选项A就是1
     */
    public String option = "";


}
