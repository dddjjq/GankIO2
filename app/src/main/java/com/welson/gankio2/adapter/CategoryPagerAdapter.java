package com.welson.gankio2.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.welson.gankio2.fragment.BaseFragment;

import java.util.ArrayList;

public class CategoryPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<BaseFragment> fragments;
    private String[] categoryString;

    public CategoryPagerAdapter(FragmentManager fm,ArrayList<BaseFragment> fragments,String[] categoryString) {
        super(fm);
        this.fragments = fragments;
        this.categoryString = categoryString;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return categoryString[position];
    }
}
