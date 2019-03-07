package com.pic.optimize.test.bean;

import android.graphics.Bitmap;


public class EmoticonBean {
    //这个变量的意义是图标一个接一个的落下，因为我们是在ondraw中遍历list,绘制每个图标，我们给每个图标设置一个出现
    //的时间，如果还没到这个时间，就不能让图标落下
    public int appearTimestamp;
    //对应的Bitmap
    public Bitmap bitmap;
    //随机缩放比例
    public float scale = 1.0f;
    //位置
    public int x, y;
    //位移速度，我们怎么让表情雨移动呢，比方说我们上一秒在屏幕(10,200)这个位置，因为view的ondraw方法
    //会在执行，下次绘制的时候我们让表情雨的位置在(10+velocityX,200+velocityY)的位置，这样看起来
    //图标就在移动了
    public int velocityX, velocityY;


    public EmoticonBean() {

    }


    public int getAppearTimestamp() {
        return appearTimestamp;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }


}
