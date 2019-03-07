package com.pic.optimize.test.presenter;

import android.app.Activity;
import android.content.Context;

import com.pic.optimize.db.QuestionDbController;
import com.pic.optimize.http.api.ApiListener;
import com.pic.optimize.http.api.ApiUtil;
import com.pic.optimize.test.ITestView;
import com.pic.optimize.test.api.GetQuestionApi;
import com.pic.optimize.test.api.HistoryQuestionGetApi;
import com.pic.optimize.test.bean.QuestionInfo;

import java.util.ArrayList;
import java.util.List;

import static com.pic.optimize.test.CardContants.HAVE_ANSWERED;


public class TestPresenter {

    private int mPage = 1;
    private List<QuestionInfo> mHistoryList = new ArrayList<>();
    private QuestionInfo mTodayQuestionInfo;
    private QuestionInfo mCurrentInfo;
    private ITestView mITestView;
    private Context mContext;

    public TestPresenter(Activity activity) {
        mITestView = (ITestView)activity;
        this.mContext = activity;
    }

    public void getDataFromLocal() {
        refreshData();
    }

    public void getHistoryApi() {
        new HistoryQuestionGetApi(mPage).get(mContext,new ApiListener() {
            @Override
            public void success(ApiUtil apiBase) {
                HistoryQuestionGetApi api = (HistoryQuestionGetApi) apiBase;
                mHistoryList = api.mQuestionInfoList;
                getTodayQuestion();
            }

            @Override
            public void failure(ApiUtil apiBase) {
            }
        });
    }


    private void getTodayQuestion() {
        new GetQuestionApi().get(this.mContext, new ApiListener() {
            @Override
            public void success(ApiUtil apiBase) {
                GetQuestionApi api = (GetQuestionApi) apiBase;
                mTodayQuestionInfo = api.mQuestionInfo;
                mHistoryList.add(0, mTodayQuestionInfo);
                handleDataBase();
                refreshData();
            }

            @Override
            public void failure(ApiUtil apiBase) {
            }
        });
    }

    private void refreshData() {

        mHistoryList = QuestionDbController.getInstance().queryAll();
        mCurrentInfo = mHistoryList.get(0);
        String count = mCurrentInfo.total_count;
        if (mCurrentInfo.type == HAVE_ANSWERED) {
            mITestView.setBottomTipView(count);
        }

        mITestView.setAdapter(mHistoryList);
    }


    private void handleDataBase() {
        List<QuestionInfo> deleteList = new ArrayList<>();
        ArrayList<QuestionInfo> paperList = new ArrayList();

        for (int i=0;i<mHistoryList.size();i++) {
            QuestionInfo info = mHistoryList.get(i);
            int status = info.status;
            if(status == -1) {
                deleteList.add(info);
            }else if(status == 0){
                paperList.add(info);
            }
        }


        QuestionDbController.getInstance().delete(deleteList);
        QuestionDbController.getInstance().insertOrReplace(paperList);
    }
}
