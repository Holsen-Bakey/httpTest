package com.pic.optimize.test.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pic.optimize.R;

public class ButtonSelectView extends RelativeLayout {

    private String mText;
    private Drawable mDrawable;
    private TextView textView;
    private ImageView leftImg;
    public onButtonSelectClickListener listener;

    public ButtonSelectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context,R.layout.button_select_layout,this);
        TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.ButtonSelectUtil);

        mText = t.getString(R.styleable.ButtonSelectUtil_text);
        mDrawable =  t.getDrawable(R.styleable.ButtonSelectUtil_icon_left);

        textView = findViewById(R.id.textView);
        leftImg = findViewById(R.id.leftImg);
        textView.setText(mText);
        leftImg.setImageDrawable(mDrawable);
        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null) {
                    listener.onclick();
                }
            }
        });
    }

    public void setEnable(boolean enable) {
        textView.setEnabled(enable);
    }

    public void setSelect(boolean select) {
        textView.setSelected(select);
        setEnable(false);
    }


    public void setListener(onButtonSelectClickListener listener){
        this.listener = listener;
    }


    public void setButtonState(String text,int resource) {
        setText(text);
        setIcon(resource);
    }

    public void setText(String text) {
        textView.setText(text);
    }


    public void setIcon(int resource) {
        leftImg.setImageResource(resource);
    }

    public interface onButtonSelectClickListener {
        void onclick();
    }
}
