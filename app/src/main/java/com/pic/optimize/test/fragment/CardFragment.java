package com.pic.optimize.test.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pic.optimize.R;
import com.pic.optimize.http.api.ApiListener;
import com.pic.optimize.http.api.ApiUtil;
import com.pic.optimize.test.activity.CardTestActivity;
import com.pic.optimize.test.api.QuestionSaveApi;
import com.pic.optimize.test.bean.QuestionInfo;

import java.util.ArrayList;
import java.util.List;

import static com.pic.optimize.test.CardContants.CHOOSE_OPTION_ONE;
import static com.pic.optimize.test.CardContants.CHOOSE_OPTION_TWO;
import static com.pic.optimize.test.CardContants.HAVE_ANSWERED;
import static com.pic.optimize.test.CardContants.NOT_ANSWERED;

public class CardFragment extends Fragment {

    public View mRootView;
    public TextView contentTv;
    public TextView TipContentTv;
    public ImageView first_btn_right_icon;
    public ImageView second_btn_right_icon;
    public LinearLayout tip_layout;
    public TextView optionTv1;
    public TextView optionTv2;

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
            int type = itemData.card_type;
            int answer = 0;
            int user_option = 0;
            if (!TextUtils.isEmpty(itemData.answer)) {
                answer = Integer.valueOf(itemData.answer);
            }
            if (!TextUtils.isEmpty(itemData.option)) {
                user_option = Integer.valueOf(itemData.option);
            }

            if (type == NOT_ANSWERED) {
                //说明是答题页
                tip_layout.setVisibility(View.GONE);
            } else if (type == HAVE_ANSWERED) {
                //说明是历史页
                tip_layout.setVisibility(View.VISIBLE);
                TipContentTv.setText(itemData.explain);
            }

            if (type == HAVE_ANSWERED && answer == 1) {
                first_btn_right_icon.setVisibility(View.VISIBLE);
                second_btn_right_icon.setVisibility(View.VISIBLE);
                first_btn_right_icon.setImageResource(R.drawable.img_test_right);
                second_btn_right_icon.setImageResource(R.drawable.img_test_worn);
            } else if (type == HAVE_ANSWERED && answer == 2) {
                first_btn_right_icon.setVisibility(View.VISIBLE);
                second_btn_right_icon.setVisibility(View.VISIBLE);
                first_btn_right_icon.setImageResource(R.drawable.img_test_worn);
                second_btn_right_icon.setImageResource(R.drawable.img_test_right);
            }

            if (type == HAVE_ANSWERED && user_option == 1) {
                optionTv1.setSelected(true);
                optionTv2.setSelected(false);
            } else if (type == HAVE_ANSWERED && user_option == 2) {
                optionTv1.setSelected(false);
                optionTv2.setSelected(true);
            }
            if (type == HAVE_ANSWERED) {
                optionTv1.setEnabled(false);
                optionTv2.setEnabled(false);
            }

            optionTv1.setText(itemData.options.get(0));
            optionTv2.setText(itemData.options.get(1));



            tip_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }catch (Exception ex) {
            ex.printStackTrace();
        }

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
        optionTv1 = (TextView) view.findViewById(R.id.first_btn);
        optionTv2 = (TextView) view.findViewById(R.id.second_btn);
        tip_layout = (LinearLayout) view.findViewById(R.id.tip_layout);

        first_btn_right_icon = (ImageView) view.findViewById(R.id.first_btn_right_icon);
        second_btn_right_icon = (ImageView) view.findViewById(R.id.second_btn_right_icon);
        optionTv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveQuestionInfo(CHOOSE_OPTION_ONE);
            }
        });
        optionTv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    first_btn_right_icon.setVisibility(View.VISIBLE);
                    second_btn_right_icon.setVisibility(View.VISIBLE);
                    boolean isCorrect = mActivity.mRankInfo.is_correct.equals("1");
                    int rightOption = 1;
                    if (option == CHOOSE_OPTION_ONE) {
                        if (isCorrect) {
                            rightOption = 1;
                        } else {
                            rightOption = 2;
                        }
                        optionTv1.setSelected(true);
                        optionTv2.setSelected(false);
                        optionTv1.setEnabled(false);
                        optionTv2.setEnabled(false);

                    } else {
                        optionTv1.setEnabled(false);
                        optionTv2.setEnabled(false);
                        optionTv1.setSelected(false);
                        optionTv2.setSelected(true);
                        if (isCorrect) {
                            rightOption = 2;
                        } else {
                            rightOption = 1;
                        }
                    }
                    if (rightOption == 1) {
                        first_btn_right_icon.setImageResource(R.drawable.img_test_right);
                        second_btn_right_icon.setImageResource(R.drawable.img_test_worn);
                    } else {
                        first_btn_right_icon.setImageResource(R.drawable.img_test_worn);
                        second_btn_right_icon.setImageResource(R.drawable.img_test_right);
                    }

                    //如何已经答了题目，我们把当前的info设置成已答题的info
                    mCurrentInfo.card_type = HAVE_ANSWERED;
                    mCurrentInfo.type = String.valueOf(HAVE_ANSWERED);
                    mCurrentInfo.total_count = mActivity.mRankInfo.total_count;
                    mCurrentInfo.correct_count = mActivity.mRankInfo.correct_count;
                    mCurrentInfo.option = String.valueOf(option);

                    tip_layout.setVisibility(View.VISIBLE);
                    TipContentTv.setText(mCurrentInfo.explain);

                    mCurrentInfo.card_type = HAVE_ANSWERED;
                    mCurrentInfo.type = String.valueOf(HAVE_ANSWERED);

                }catch (Exception ex) {
                    ex.printStackTrace();
                }

            }

            @Override
            public void failure(ApiUtil apiBase) {
                optionTv1.setEnabled(true);
                optionTv2.setEnabled(true);
            }
        });

    }


    public List<Bitmap> getBitmaps(){
        List<Bitmap> bitmaps = new ArrayList<>();
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.pic1));
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.pic2));
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.pic3));

        return bitmaps;
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
