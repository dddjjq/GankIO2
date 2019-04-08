package com.welson.gankio2.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.welson.gankio2.R;
import com.welson.gankio2.adapter.CategoryPagerAdapter;
import com.welson.gankio2.contract.CategoryContract;
import com.welson.gankio2.entity.GankEntity;
import com.welson.gankio2.presenter.CategoryPresenter;

import java.util.ArrayList;

public class CategoryFragment extends BaseFragment{

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private CategoryPagerAdapter pagerAdapter;
    private ArrayList<BaseFragment> fragments;
    private String[] categoryString;

    @Override
    public int setLayoutId() {
        return R.layout.fragment_category;
    }

    @Override
    public void initView(View view) {
        tabLayout = view.findViewById(R.id.category_tab_layout);
        viewPager = view.findViewById(R.id.category_viewpager);
    }

    @Override
    public void initData() {
        fragments = new ArrayList<>();
        initTabLayout();
        initViewPager();
        tabLayout.setupWithViewPager(viewPager,false);
    }

    @Override
    public void detach() {

    }

    private void initTabLayout(){
        categoryString = getResources().getStringArray(R.array.category_string);
        for (String s : categoryString){
            tabLayout.addTab(tabLayout.newTab().setText(s));
        }
    }

    private void initViewPager(){
        for (int i=0;i<categoryString.length;i++){
            CategoryEntityFragment categoryEntityFragment = new CategoryEntityFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("currentItem",i);
            categoryEntityFragment.setArguments(bundle);
            fragments.add(categoryEntityFragment);
        }
        pagerAdapter = new CategoryPagerAdapter(getFragmentManager(),fragments,categoryString);
        viewPager.setAdapter(pagerAdapter);
    }
}
