package com.pic.optimize.test.api;

import com.google.gson.Gson;
import com.pic.optimize.http.api.ApiUtil;
import com.pic.optimize.test.bean.QuestionInfo;

import org.json.JSONObject;


public class GetQuestionApi extends ApiUtil {

    public QuestionInfo mQuestionInfo;

    public GetQuestionApi(){
    }

    @Override
    protected boolean isBackInMainThread() {
        return true;
    }

    @Override
    protected String getUrl() {
        return "http://nick/getQuestion";
    }

    @Override
    protected void parseData(JSONObject jsonObject) throws Exception {
        try {
            JSONObject dataInfo = (JSONObject) jsonObject.get("data");
            JSONObject questionInfoStr = dataInfo.optJSONObject("info");
            mQuestionInfo = new Gson().fromJson(questionInfoStr.toString(), QuestionInfo.class);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }



}
