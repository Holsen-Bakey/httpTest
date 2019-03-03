package com.pic.optimize.test.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pic.optimize.R;
import com.pic.optimize.test.ITestView;
import com.pic.optimize.test.adapter.CardFragmentPagerAdapter;
import com.pic.optimize.test.bean.QuestionInfo;
import com.pic.optimize.test.bean.RankInfo;
import com.pic.optimize.test.presenter.TestPresenter;
import com.pic.optimize.test.util.CardTransformer;

import java.util.List;


public class CardTestActivity extends FragmentActivity implements View.OnClickListener, ITestView {

    public ImageView mBtnLeft;
    public RankInfo mRankInfo;
    public TextView mRankTextView1;
    public ViewPager mViewPager;


    private CardFragmentPagerAdapter mViewPagerAdapter;
    private CardTransformer mCardPageTransformer;
    private TestPresenter mTestPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);
        initView();
        mTestPresenter = new TestPresenter(this);
        mTestPresenter.getHistoryApi();

    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mRankTextView1 = (TextView) findViewById(R.id.rank_text_1);
        mBtnLeft = (ImageView) findViewById(R.id.btn_left);
        mBtnLeft.setOnClickListener(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_left) {
            finish();
        }
    }


    @Override
    public void setAdapter(List<QuestionInfo> mHistoryList) {
        mViewPagerAdapter = new CardFragmentPagerAdapter(getSupportFragmentManager(), mHistoryList);
        mViewPager.setAdapter(mViewPagerAdapter);
        //设置limit
        mViewPager.setOffscreenPageLimit(3);

    }

    /**
     * 设置底部view的提示
     */
    @Override
    public void setBottomTipView(String count) {
        mRankTextView1.setText("恭喜你已累计答对"+count+"题");
    }


}
