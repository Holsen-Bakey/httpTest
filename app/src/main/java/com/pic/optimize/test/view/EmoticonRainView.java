package com.pic.optimize.test.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.pic.optimize.fresco.DensityUtils;
import com.pic.optimize.test.bean.EmoticonBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EmoticonRainView extends View {
    private static final String TAG = "EmoticonRainView";
    //是否正在下表情雨
    private boolean isRaining;

    //表情图高度
    private float mEmoticonHeight;
    private float mEmoticonWidth;

    private Random mRandom;
    private Matrix matrix;
    private Paint mPaint;

    //开始下表情雨的时间
    private long mStartTimestamp;

    private final List<EmoticonBean> mEmoticonList = new ArrayList<>();

    public EmoticonRainView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init(){
        mRandom = new Random();
        setVisibility(GONE);
        //如果没有这句话，onDraw不会被执行
        //自定义View中如果重写了onDraw()即自定义了绘制，那么就应该在构造函数中调用view的setWillNotDraw(false)，设置该flag标志。其实默认该标志就是false
        //setWillNotDraw(false);

        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setFilterBitmap(true);
        mPaint.setDither(true);
        mPaint.setColor(getResources().getColor(android.R.color.holo_red_light));
        matrix = new Matrix();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(!isRaining || mEmoticonList.size() == 0){
            return;
        }
        long currentTimestamp = System.currentTimeMillis();
        long totalTimeInterval = currentTimestamp - mStartTimestamp;

        for (int i = 0; i < mEmoticonList.size(); i++) {
            EmoticonBean emoticon = mEmoticonList.get(i);
            Bitmap bitmap = emoticon.getBitmap();
            if(bitmap.isRecycled() || isOutOfBottomBound(i) || totalTimeInterval < emoticon.getAppearTimestamp()){
                continue;
            }

            matrix.reset();

            float heightScale = mEmoticonHeight / bitmap.getHeight();
            float widthScale = mEmoticonWidth / bitmap.getWidth();
            matrix.setScale(widthScale, heightScale);

            emoticon.x = emoticon.x + emoticon.velocityX;
            emoticon.y = emoticon.y + emoticon.velocityY;

            matrix.postTranslate(emoticon.x, emoticon.y);

            canvas.drawBitmap(bitmap, matrix, mPaint);
        }
        postInvalidate();
    }

    /**
     * 某张图的位置是否超出下边界
     */
    private boolean isOutOfBottomBound(int position){
        return mEmoticonList.get(position).y > getHeight();
    }

    public void start(final List<Bitmap> list){
        if(list.size() == 0){
            throw new RuntimeException("EmoticonRainView bitmap list is 0!");
        }
        stop();
        setVisibility(VISIBLE);

        post(new Runnable() {
            @Override
            public void run() {
                initAndResetData(list);
                isRaining = true;
                invalidate();
            }
        });
    }

    private void initAndResetData(final List<Bitmap> list) {
        if(list.size() == 0){
            return;
        }
        this.mEmoticonWidth = mEmoticonHeight = DensityUtils.dip2px(getContext(),50);
        this.mStartTimestamp = System.currentTimeMillis();

        mEmoticonList.clear();

        //开始画表情的总时间
        int currentDuration = 0;

        int i = 0;
        int size = list.size();

        int maxDuration = 2000;
        //表情雨一共持续两秒,i表示屏幕上一共落下多少个bitmap,Math.ceil浮点数向上取整,为什么bean.y是负数呢，因为一开始要让bitmap在屏幕外落下来
        while(currentDuration < maxDuration){
            EmoticonBean bean = new EmoticonBean();
            //因为要出现重复的图标
            bean.bitmap = list.get(i%size);

            bean.x = mRandom.nextInt(getWidth());

            //即对浮点数向上取整
            bean.y = (int)(-Math.ceil(mEmoticonHeight));

            //该图标的持续时间，用于计算y轴是速率
            float duration = (mRandom.nextInt(500) + maxDuration);

            //为什么是*16呢，因为android系统每隔16秒刷新一次屏幕，里面所有的view必须在16秒内完成测量重绘，不然就会引起卡顿
            bean.velocityY = (int) (getHeight() * 16 / duration);

            //round() 方法可把一个数字舍入为最接近的整数
            bean.velocityX = Math.round(mRandom.nextFloat());

            bean.appearTimestamp = currentDuration;
            mEmoticonList.add(bean);
            currentDuration += mRandom.nextInt(250);
            i++;
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stop();
    }

    /**
     * 停止并考虑回收
     */
    public void stop(){
        isRaining = false;
        setVisibility(GONE);
    }

}
