package com.pic.optimize.test.api;

import com.google.gson.Gson;
import com.pic.optimize.http.api.ApiUtil;
import com.pic.optimize.test.bean.QuestionInfo;

import org.json.JSONObject;


public class GetQuestionApi extends ApiUtil {

    public QuestionInfo mQuestionInfo;
    public int mStatus = 2;
    public static final int NO_MORE_QUESTION = 0;
    public static final int GET_NEW_QUESTION = 2;
    public static final int HAVE_ANSWERED_QUESTION = 1;


    public GetQuestionApi(){
    }

    @Override
    protected boolean isBackInMainThread() {
        return true;
    }

    @Override
    protected String getUrl() {
        return "http://127.0.0.1/examine/question";
    }

    @Override
    protected void parseData(JSONObject jsonObject) throws Exception {
        try {
            JSONObject dataInfo = (JSONObject) jsonObject.get("data");
            JSONObject questionInfoStr = dataInfo.optJSONObject("info");
            mQuestionInfo = new Gson().fromJson(questionInfoStr.toString(), QuestionInfo.class);
            //今日是否答题：1-已答题、2-未答题
            mStatus = Integer.valueOf(mQuestionInfo.type);
            mQuestionInfo.card_type = mStatus;
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }



}
