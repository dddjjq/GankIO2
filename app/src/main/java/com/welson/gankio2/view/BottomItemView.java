package com.welson.gankio2.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.welson.gankio2.R;

public class BottomItemView extends LinearLayout {

    private ImageView bottomImage;
    private TextView bottomText;
    private static int focusColor = Color.parseColor("#00ACC1");
    private static int normalColor = Color.parseColor("#757575");
    private boolean isCurrentFocus = false;

    public BottomItemView(Context context) {
        super(context);
    }

    public BottomItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context,AttributeSet attrs){
        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.BottomItemView);
        int imageSrc = ta.getResourceId(R.styleable.BottomItemView_imageSrc,0);
        String bottomTextString = ta.getString(R.styleable.BottomItemView_name);
        ta.recycle();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.bottom_item_layout, this, true);
        bottomImage = view.findViewById(R.id.bottom_image);
        bottomText = view.findViewById(R.id.bottom_text);
        bottomImage.setImageResource(imageSrc);
        bottomText.setText(bottomTextString);
    }

    public void setFocus(boolean isFocus){
        isCurrentFocus = isFocus;
        if (isFocus){
            bottomImage.setColorFilter(focusColor);
            bottomText.setTextColor(focusColor);
        }else {
            bottomImage.setColorFilter(normalColor);
            bottomText.setTextColor(normalColor);
        }
        scale();
    }

    private void scale(){
        ObjectAnimator scaleBigX = ObjectAnimator.ofFloat(this,"scaleX",1f,1.02f);
        ObjectAnimator scaleBigY = ObjectAnimator.ofFloat(this,"scaleY",1f,1.02f);

        ObjectAnimator scaleSmallX = ObjectAnimator.ofFloat(this,"scaleX",1.02f,1f);
        ObjectAnimator scaleSmallY = ObjectAnimator.ofFloat(this,"scaleY",1.02f,1f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(50);
        animatorSet.setInterpolator(new LinearInterpolator());
        if (isCurrentFocus){
            animatorSet.play(scaleBigX).with(scaleBigY);
        }else {
            animatorSet.play(scaleSmallX).with(scaleSmallY);
        }
        animatorSet.start();
    }

}
