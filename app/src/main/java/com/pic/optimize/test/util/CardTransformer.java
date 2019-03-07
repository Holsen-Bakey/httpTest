package com.pic.optimize.test.util;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * 卡片效果Transformer
 * @author:gaoyoujian
 */

public class CardTransformer implements ViewPager.PageTransformer {

    private int mOffset = 40;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void transformPage(View page, float position) {

        if (position <=0) {
            //我们把当前view的位置设置到了viewPager的最右边
            //我们把-1位置的view,向右移动的距离是一个屏幕的宽度，这样就叠在position=0的位置，
            //同样道理，我们把位置为-1的view,向右移动两个屏幕的宽度，也叠在position=0的位置
            page.setTranslationX(-position * page.getWidth());

            //缩放卡片并调整位置
            float scale = (page.getWidth() + mOffset * position) / page.getWidth();
            page.setScaleX(scale);
            page.setScaleY(scale);

            //移动Y轴坐标,setTranslationX和setTranslationY，api版本为11，是设置view相对原始位置的偏移量
            page.setTranslationY(-position * mOffset);

            //setTranslationZ是层级，值越大，view越靠上面
            page.setTranslationZ(position);
        }


    }

}
