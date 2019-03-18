package com.welson.gankio2.fragment;

import com.welson.gankio2.contract.TodayContract;
import com.welson.gankio2.entity.TodayEntity;

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
    public void showDataSucceed(TodayEntity todayEntity) {
        
    }

    @Override
    public void showError() {

    }
}
