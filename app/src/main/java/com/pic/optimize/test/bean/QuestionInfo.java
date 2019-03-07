package com.pic.optimize.test.bean;

import android.support.annotation.Keep;

import com.pic.optimize.test.util.StringConverter;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Keep
@Entity(nameInDb = "QuestionBean")
public class QuestionInfo implements Serializable {

    public static final long serialVersionUID = 3243243243243L;
    /**
     * 题目ID
     */
    @Id
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
     * 题目排序,客户端没用
     */
    public String sort = "";
    /**
     * 今日是否答题：1-已答题、2-未答题,-1是网络异常type
     */
    public int type = 1;



    /**
     * 答题结果：0-错误、1-正确,3-完成所有题目
     */
    public String is_correct = "";


    public String correct_count = "";

    public String total_count = "";


    public String rank = "";

    /**
     * 0表示数据被删除了，1表示数据是正常的
     */
    public int status = 0;

    /**
     * 题目选项,["选项A","选项B"]
     */
    @Convert(converter = StringConverter.class , columnType = String.class)
    public List<String> options = new ArrayList<>();

    /**
     * 用户选择的选项,从1开始，选项A就是1
     */
    public String option = "";

    @Generated(hash = 113213755)
    public QuestionInfo(String question_id, String title, String answer,
            String explain, String sort, int type, String is_correct,
            String correct_count, String total_count, String rank, int status,
            List<String> options, String option) {
        this.question_id = question_id;
        this.title = title;
        this.answer = answer;
        this.explain = explain;
        this.sort = sort;
        this.type = type;
        this.is_correct = is_correct;
        this.correct_count = correct_count;
        this.total_count = total_count;
        this.rank = rank;
        this.status = status;
        this.options = options;
        this.option = option;
    }

    @Generated(hash = 1065106606)
    public QuestionInfo() {
    }

    public String getQuestion_id() {
        return this.question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getExplain() {
        return this.explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getSort() {
        return this.sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getIs_correct() {
        return this.is_correct;
    }

    public void setIs_correct(String is_correct) {
        this.is_correct = is_correct;
    }

    public String getCorrect_count() {
        return this.correct_count;
    }

    public void setCorrect_count(String correct_count) {
        this.correct_count = correct_count;
    }

    public String getTotal_count() {
        return this.total_count;
    }

    public void setTotal_count(String total_count) {
        this.total_count = total_count;
    }

    public String getRank() {
        return this.rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<String> getOptions() {
        return this.options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getOption() {
        return this.option;
    }

    public void setOption(String option) {
        this.option = option;
    }


}
