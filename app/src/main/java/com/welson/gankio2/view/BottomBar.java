package com.welson.gankio2.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.welson.gankio2.MainActivity;
import com.welson.gankio2.R;

import java.util.ArrayList;

public class BottomBar extends LinearLayout implements View.OnClickListener {

    private BottomItemView bottomItemNew;
    private BottomItemView bottomItemCategory;
    private BottomItemView bottomItemGirls;
    private BottomItemView bottomItemCollect;
    private MainActivity activity;
    private ArrayList<BottomItemView> bottomItemViews;
    private int currentItem = 0;

    public BottomBar(Context context) {
        super(context);
    }

    public BottomBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        activity = (MainActivity)context;
        bottomItemViews = new ArrayList<>();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.bottom_bar_layout, this, true);
        bottomItemNew = view.findViewById(R.id.bottom_item_new);
        bottomItemCategory = view.findViewById(R.id.bottom_item_category);
        bottomItemGirls = view.findViewById(R.id.bottom_item_girls);
        bottomItemCollect = view.findViewById(R.id.bottom_item_collect);
        bottomItemViews.add(bottomItemNew);
        bottomItemViews.add(bottomItemCategory);
        bottomItemViews.add(bottomItemGirls);
        bottomItemViews.add(bottomItemCollect);
        for (BottomItemView bottomItemView : bottomItemViews) {
            bottomItemView.setOnClickListener(this);
        }
        bottomItemNew.setFocus(true); //初始化
    }


    @Override
    public void onClick(View v) {
        int oldItem = currentItem;
        switch (v.getId()) {
            case R.id.bottom_item_new:
                currentItem = 0;
                break;
            case R.id.bottom_item_category:
                currentItem = 1;
                break;
            case R.id.bottom_item_girls:
                currentItem = 2;
                break;
            case R.id.bottom_item_collect:
                currentItem = 3;
                break;
        }
        for (int i = 0; i < bottomItemViews.size(); i++) {
            if (i == currentItem) {
                bottomItemViews.get(i).setFocus(true);
            } else if (i == oldItem){
                if (oldItem != currentItem){
                    bottomItemViews.get(i).setFocus(false);
                }
            }
        }
        activity.onBottomItemClick(currentItem);
    }
}
