package com.welson.gankio2.fragment;

import com.welson.gankio2.contract.TodayContract;
import com.welson.gankio2.entity.GankEntity;

import java.util.ArrayList;

public class NewsFragment extends BaseFragment implements TodayContract.View{

    @Override
    public int setLayoutId() {
        return 0;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void showDataSucceed(ArrayList<GankEntity> gankEntities) {
        
    }

    @Override
    public void showError() {

    }
}
