package com.pic.optimize.test.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pic.optimize.R;
import com.pic.optimize.http.api.ApiListener;
import com.pic.optimize.http.api.ApiUtil;
import com.pic.optimize.test.activity.CardTestActivity;
import com.pic.optimize.test.api.QuestionSaveApi;
import com.pic.optimize.test.bean.QuestionInfo;
import com.pic.optimize.test.view.ButtonSelectView;

import static com.pic.optimize.test.CardContants.CHOOSE_OPTION_ONE;
import static com.pic.optimize.test.CardContants.CHOOSE_OPTION_TWO;
import static com.pic.optimize.test.CardContants.HAVE_ANSWERED;
import static com.pic.optimize.test.CardContants.NOT_ANSWERED;

public class CardFragment extends Fragment {

    public View mRootView;
    public TextView contentTv;
    public TextView TipContentTv;
    public LinearLayout tip_layout;

    private ButtonSelectView mButtonSelectView1;
    private ButtonSelectView mButtonSelectView2;


    private CardTestActivity mActivity;
    private QuestionInfo mCurrentInfo;


    public static CardFragment newInstance(QuestionInfo info) {
        CardFragment fragment = new CardFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("info",info);
        fragment.setArguments(bundle);
        return fragment;
    }

    public CardFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (CardTestActivity)context;
    }

    public void bindData(final QuestionInfo itemData) {
        try {
            if (itemData == null) {
                return;
            }

            contentTv.setText(itemData.title);
            int type = itemData.type;
            int answer = Integer.valueOf(itemData.answer);

            int user_option = 1;
            if(TextUtils.isEmpty(itemData.option)) {
                user_option = Integer.valueOf(itemData.option);
            }
            bindButtonSelectView1(type,answer,user_option,itemData);
            if (type == NOT_ANSWERED) {
                //说明是答题页
                tip_layout.setVisibility(View.GONE);
            } else if (type == HAVE_ANSWERED) {
                //说明是历史页
                tip_layout.setVisibility(View.VISIBLE);
                TipContentTv.setText(itemData.explain);
            }

        }catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void bindButtonSelectView1(int type,int answer,int user_option,final QuestionInfo itemData) {
        if(type == HAVE_ANSWERED) {
            if(answer == 1) {
                mButtonSelectView1.setIcon(R.drawable.img_test_right);
                mButtonSelectView2.setIcon(R.drawable.img_test_worn);
            }else if(answer == 2) {
                mButtonSelectView1.setIcon(R.drawable.img_test_worn);
                mButtonSelectView2.setIcon(R.drawable.img_test_right);
            }

            if(user_option == 1) {
                mButtonSelectView1.setSelect(true);
                mButtonSelectView2.setSelect(false);
            }else{
                mButtonSelectView1.setSelect(false);
                mButtonSelectView2.setSelect(true);
            }
        }
        mButtonSelectView1.setText(itemData.options.get(0));
        mButtonSelectView2.setText(itemData.options.get(1));
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCurrentInfo = (QuestionInfo) getArguments().getSerializable("info");
        initView(mRootView);
        bindData(mCurrentInfo);
    }

    private void initView(View view) {
        contentTv = (TextView) view.findViewById(R.id.card_text);
        TipContentTv = (TextView) view.findViewById(R.id.tip_text);
        tip_layout = (LinearLayout) view.findViewById(R.id.tip_layout);

        mButtonSelectView1 = (ButtonSelectView)view.findViewById(R.id.first_option_layout);
        mButtonSelectView2 = (ButtonSelectView)view.findViewById(R.id.second_option_layout);

        Log.d("TAG","<<<<<mButtonSelectView1="+mButtonSelectView1+"mButtonSelectView2="+mButtonSelectView2);

        mButtonSelectView1.setListener(new ButtonSelectView.onButtonSelectClickListener() {
            @Override
            public void onclick() {
                saveQuestionInfo(CHOOSE_OPTION_ONE);
            }
        });

        mButtonSelectView2.setListener(new ButtonSelectView.onButtonSelectClickListener() {
            @Override
            public void onclick() {
                saveQuestionInfo(CHOOSE_OPTION_TWO);
            }
        });


    }

    private void saveQuestionInfo(final int option) {

        new QuestionSaveApi(mCurrentInfo.question_id, String.valueOf(option)).post(new ApiListener() {
            @Override
            public void success(ApiUtil apiBase) {
                try {
                    QuestionSaveApi questionSaveApi = (QuestionSaveApi) apiBase;
                    Log.d("TAG", "<<<<<questionSaveApi=" + questionSaveApi);
                    mActivity.mRankInfo = questionSaveApi.mRankInfo;
                    boolean isCorrect = mActivity.mRankInfo.is_correct.equals("1");

                    bindButtonSelectView2(option,isCorrect);

                    tip_layout.setVisibility(View.VISIBLE);
                    TipContentTv.setText(mCurrentInfo.explain);

                    mActivity.setBottomTipView(questionSaveApi.mRankInfo.correct_count);

                }catch (Exception ex) {
                    ex.printStackTrace();
                }

            }

            @Override
            public void failure(ApiUtil apiBase) {
                mButtonSelectView1.setEnable(true);
                mButtonSelectView2.setEnable(true);
            }
        });

    }


    /**
     * option是用户的答案选择，isCorrect是是否正确
     * 这个函数的逻辑是找出哪道题是正确答案，
     */
    private void bindButtonSelectView2(int option,boolean isCorrect) {
        int rightOption;
        if (option == 1) {
            if (isCorrect) {
                rightOption = 1;
            } else {
                rightOption = 2;
            }
            mButtonSelectView1.setSelect(true);
            mButtonSelectView2.setSelect(false);

        } else {
            mButtonSelectView1.setSelected(false);
            mButtonSelectView2.setSelected(true);
            if (isCorrect) {
                rightOption = 2;
            } else {
                rightOption = 1;
            }
        }
        if (rightOption == 1) {
            mButtonSelectView1.setIcon(R.drawable.img_test_right);
            mButtonSelectView2.setIcon(R.drawable.img_test_worn);
        } else {
            mButtonSelectView1.setIcon(R.drawable.img_test_worn);
            mButtonSelectView2.setIcon(R.drawable.img_test_right);
        }

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView =  inflater.inflate(R.layout.test_item_layout,container,false);
        return mRootView;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onResume() {
        super.onResume();
    }
}
