package com.pic.optimize.test.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pic.optimize.R;
import com.pic.optimize.db.MessageContactDbController;
import com.pic.optimize.test.ITestView;
import com.pic.optimize.test.adapter.CardFragmentPagerAdapter;
import com.pic.optimize.test.bean.MessageContact;
import com.pic.optimize.test.bean.QuestionInfo;
import com.pic.optimize.test.bean.RankInfo;
import com.pic.optimize.test.presenter.TestPresenter;
import com.pic.optimize.test.util.CardTransformer;
import com.pic.optimize.test.view.EmoticonRainView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class CardTestActivity extends FragmentActivity implements View.OnClickListener, ITestView {

    public ImageView mBtnLeft;
    public RankInfo mRankInfo;
    public TextView mRankTextView1;
    public ViewPager mViewPager;


    private CardFragmentPagerAdapter mViewPagerAdapter;
    private CardTransformer mCardPageTransformer;
    public EmoticonRainView mEmoticonRainView;
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
        mEmoticonRainView = (EmoticonRainView) findViewById(R.id.emoticon_rain_view);
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
        Collections.reverse(mHistoryList);
        mViewPagerAdapter = new CardFragmentPagerAdapter(getSupportFragmentManager(), mHistoryList);
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.setCurrentItem(mHistoryList.size() - 1, false);
        mCardPageTransformer = new CardTransformer();
        //设置limit
        mViewPager.setOffscreenPageLimit(3);
        //设置transformer
        mViewPager.setPageTransformer(true, mCardPageTransformer);


        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int position) {

            }
        });

    }

    /**
     * 设置底部view的提示
     */
    @Override
    public void setBottomTipView(String count) {
        String content = "";
        int totalCount = 0;
        if (!TextUtils.isEmpty(count)) {
            totalCount = Integer.valueOf(count);
            if (totalCount >= 6) {
                mRankTextView1.setVisibility(View.VISIBLE);
                content = getString(R.string.ca_rank_info_with_correct_count, count);
                mRankTextView1.setText(content);
            }
        }
    }

    public void startRain() {
        mEmoticonRainView.start(getBitmaps());
    }

    public List<Bitmap> getBitmaps() {
        List<Bitmap> bitmaps = new ArrayList<>();
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.pic1));
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.pic2));
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.pic3));

        return bitmaps;
    }


}
