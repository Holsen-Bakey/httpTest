package com.pic.optimize.test.api;

import com.google.gson.Gson;
import com.pic.optimize.http.api.ApiUtil;
import com.pic.optimize.test.bean.QuestionInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



public class HistoryQuestionGetApi extends ApiUtil {

    public List<QuestionInfo> mQuestionInfoList = new ArrayList<>();
    public int mPage;

    public HistoryQuestionGetApi(int page){
        this.mPage = page;
    }

    @Override
    protected String getUrl() {
        return "http://nick/history";
    }



    @Override
    protected void parseData(JSONObject jsonObject) throws Exception {
        try {
            JSONObject dataInfo = (JSONObject) jsonObject.get("data");
            JSONArray historyInfoArray = (JSONArray)dataInfo.get("history_list");
            if(mQuestionInfoList != null) {
                mQuestionInfoList.clear();
            }

            for (int i=0;i<historyInfoArray.length();i++) {
                 QuestionInfo info = new Gson().fromJson(historyInfoArray.get(i).toString(), QuestionInfo.class);
                 mQuestionInfoList.add(info);
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
