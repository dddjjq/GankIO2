package com.welson.gankio2.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.welson.gankio2.R;

public class GankToolbar extends Toolbar implements View.OnClickListener{

    private ImageView leftImage;
    private ImageView rightImage;
    private TextView title;
    private OnLeftImageClickListener onLeftImageClickListener;
    private OnRightImageClickListener onRightImageClickListener;

    public GankToolbar(Context context) {
        super(context);
    }

    public GankToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context,AttributeSet attrs){
        LayoutInflater.from(context).inflate(R.layout.gank_toolbar_layout,this,true);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.GankToolbar);
        int leftImageSrc = ta.getResourceId(R.styleable.GankToolbar_leftImageSrc,0);
        int rightImageSrc = ta.getResourceId(R.styleable.GankToolbar_rightImageSrc,0);
        ta.recycle();
        leftImage = findViewById(R.id.toolbar_left_image);
        rightImage = findViewById(R.id.toolbar_right_image);
        title = findViewById(R.id.toolbar_title);
        leftImage.setImageResource(leftImageSrc);
        rightImage.setImageResource(rightImageSrc);
        leftImage.setOnClickListener(this);
        rightImage.setOnClickListener(this);
    }

    public void setTitle(String s){
        title.setText(s);
    }

    public void setLeftImage(int resId){
        leftImage.setImageResource(resId);
    }

    public void setRightImage(int resId){
        rightImage.setImageResource(resId);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.toolbar_left_image:
                if (onLeftImageClickListener != null){
                    onLeftImageClickListener.onLeftImageClick();
                }
                break;
            case R.id.toolbar_right_image:
                if (onRightImageClickListener != null){
                    onRightImageClickListener.onRightClickListener();
                }
                break;
        }
    }

    public interface OnLeftImageClickListener{
        void onLeftImageClick();
    }

    public interface OnRightImageClickListener{
        void onRightClickListener();
    }

    public void setOnLeftImageClickListener(OnLeftImageClickListener onLeftImageClickListener){
        this.onLeftImageClickListener = onLeftImageClickListener;
    }

    public void setOnRightImageClickListener(OnRightImageClickListener onRightImageClickListener){
        this.onRightImageClickListener = onRightImageClickListener;
    }
}
