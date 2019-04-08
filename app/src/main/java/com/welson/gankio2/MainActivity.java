package com.welson.gankio2;

import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.welson.gankio2.fragment.BaseFragment;
import com.welson.gankio2.fragment.CategoryFragment;
import com.welson.gankio2.fragment.CollectFragment;
import com.welson.gankio2.fragment.GirlFragment;
import com.welson.gankio2.fragment.NewsFragment;
import com.welson.gankio2.view.BottomBar;
import com.welson.gankio2.view.GankToolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GankToolbar.OnLeftImageClickListener
        , GankToolbar.OnRightImageClickListener {

    private FrameLayout mainLayout;
    private GankToolbar toolbar;
    private ArrayList<BaseFragment> fragments;
    private BottomBar bottomBar;
    private int currentItem = 0;
    private String currentDate;
    private int[] leftIcons = {R.drawable.toolbar_calendar,R.drawable.toolbar_add,R.drawable.toolbar_change,R.drawable.toolbar_setting};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void initView() {
        mainLayout = findViewById(R.id.main_layout);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setOnLeftImageClickListener(this);
        toolbar.setOnRightImageClickListener(this);
        setSupportActionBar(toolbar);
        bottomBar = findViewById(R.id.bottom_bar);
    }

    private void initData() {
        fragments = new ArrayList<>();
        NewsFragment newsFragment = new NewsFragment();
        CategoryFragment categoryFragment = new CategoryFragment();
        GirlFragment girlFragment = new GirlFragment();
        CollectFragment collectFragment = new CollectFragment();
        fragments.add(newsFragment);
        fragments.add(categoryFragment);
        fragments.add(girlFragment);
        fragments.add(collectFragment);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        for (BaseFragment fragment : fragments) {
            fragmentTransaction.add(R.id.main_layout, fragment);
        }
        fragmentTransaction.hide(fragments.get(1)).hide(fragments.get(2)).hide(fragments.get(3)).show(fragments.get(0));
        fragmentTransaction.commit();
    }

    public void onBottomItemClick(int item) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        for (int i = 0; i < fragments.size(); i++) {
            if (i == item) {
                fragmentTransaction.show(fragments.get(i));
            } else {
                fragmentTransaction.hide(fragments.get(i));
            }
        }
        currentItem = item;
        fragmentTransaction.commit();
        toolbar.setLeftImage(leftIcons[item]);
        if (item != 0){
            setToolbarTitle(null);
        }else {
            setToolbarTitle(currentDate);
        }
    }

    @Override
    public void onLeftImageClick() {
        if (currentItem == 0){
            NewsFragment newsFragment = (NewsFragment) fragments.get(0);
            newsFragment.onCalendarClick();
        }
    }

    @Override
    public void onRightClickListener() {

    }

    public void setToolbarTitle(String title){
        toolbar.setTitle(title);
        if (title != null){
            currentDate = title;
        }
    }
}
