package com.pic.optimize.test;

import com.pic.optimize.test.bean.QuestionInfo;
import java.util.List;

public interface ITestView {

    void setBottomTipView(String count);

    void setAdapter(List<QuestionInfo> mHistoryList);
}
