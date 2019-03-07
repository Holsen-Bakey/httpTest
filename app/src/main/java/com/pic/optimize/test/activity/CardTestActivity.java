package com.pic.optimize.test.activity;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
<<<<<<< HEAD
=======
import android.text.TextUtils;
import android.util.Log;
>>>>>>> fix bug
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pic.optimize.R;
import com.pic.optimize.test.ITestView;
import com.pic.optimize.test.adapter.CardFragmentPagerAdapter;
import com.pic.optimize.test.bean.QuestionInfo;
import com.pic.optimize.test.bean.RankInfo;
import com.pic.optimize.test.presenter.TestPresenter;
import com.pic.optimize.test.util.CardTransformer;

<<<<<<< HEAD
=======
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
>>>>>>> fix bug
import java.util.List;


public class CardTestActivity extends FragmentActivity implements View.OnClickListener, ITestView {

    public ImageView mBtnLeft;
    public RankInfo mRankInfo;
    public TextView mRankTextView1;
    public ViewPager mViewPager;


    private CardFragmentPagerAdapter mViewPagerAdapter;
<<<<<<< HEAD
    private CardTransformer mCardPageTransformer;
=======
    private static final String TAG = CardTestActivity.class.getSimpleName();
    public EmoticonRainView mEmoticonRainView;
>>>>>>> fix bug
    private TestPresenter mTestPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);
        initView();
        mTestPresenter = new TestPresenter(this);
        mTestPresenter.getDataFromLocal();
        mTestPresenter.getHistoryApi();

        copyDatabase();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                throw new NullPointerException("aa");
            }
        },3000);

    }

<<<<<<< HEAD
    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mRankTextView1 = (TextView) findViewById(R.id.rank_text_1);
        mBtnLeft = (ImageView) findViewById(R.id.btn_left);
        mBtnLeft.setOnClickListener(this);
    }

=======
    private void copyDatabase() {
        File file = new File("/data/data/com.pic.optimize/databases");
        String[] array = file.list();
        for(int i=0;i<array.length;i++) {
            Log.d(TAG,"=====array[i]="+array[i]);
        }
        File f = new File("/data/data/com.pic.optimize/databases/question.db");
        String sdcardPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        File o = new File(sdcardPath+"/question.db");
        if(f.exists()) {
            FileChannel outF;
            try {
                outF = new FileOutputStream(o).getChannel();
                new FileInputStream(f).getChannel().transferTo(0, f.length(),outF);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Toast.makeText(this, "完成", Toast.LENGTH_SHORT).show();
        }
    }



>>>>>>> fix bug

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_left) {
            throw new NullPointerException("aa");
            //finish();
        }
    }


<<<<<<< HEAD
    @Override
    public void setAdapter(List<QuestionInfo> mHistoryList) {
        mViewPagerAdapter = new CardFragmentPagerAdapter(getSupportFragmentManager(), mHistoryList);
        mViewPager.setAdapter(mViewPagerAdapter);
        //设置limit
        mViewPager.setOffscreenPageLimit(3);
=======
    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mEmoticonRainView = (EmoticonRainView)findViewById(R.id.emoticon_rain_view);
        mRankTextView1 = (TextView)findViewById(R.id.rank_text_1);
        mBtnLeft = (ImageView) findViewById(R.id.btn_left);
        mBtnLeft.setOnClickListener(this);
        mGuideView = (LinearLayout) findViewById(R.id.guide_view);
        mGuideView.setOnClickListener(this);
    }



     @Override
     public void setAdapter(List<QuestionInfo> mHistoryList) {

        Collections.reverse(mHistoryList);
        mViewPagerAdapter = new CardFragmentPagerAdapter(getSupportFragmentManager(), mHistoryList);
        mViewPager.setAdapter(mViewPagerAdapter);
        //mViewPager.setCurrentItem(mHistoryList.size()-1,false);
        //设置limit
        mViewPager.setOffscreenPageLimit(3);
        //设置transformer
        //mViewPager.setPageTransformer(true, mCardPageTransformer);
         mViewPager.setCurrentItem(mHistoryList.size()-1,false);
         mViewPager.setPageTransformer(true,new CardTransformer());



//         mViewPager.setPageMargin(20);//Page之间缩进20，作用是让页面之间空隙更大一点，根据自己需要而定。
//         mViewPager.setPageTransformer(true,new LoopTransformer());

>>>>>>> fix bug

    }

    /**
     * 设置底部view的提示
     */
    @Override
    public void setBottomTipView(String count) {
        mRankTextView1.setText("恭喜你已累计答对"+count+"题");
    }


}
