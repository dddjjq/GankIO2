package com.welson.gankio2.contract;

import com.welson.gankio2.base.BasePresenter;
import com.welson.gankio2.base.BaseView;
import com.welson.gankio2.entity.GankEntity;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class TodayContract {

    public interface Presenter extends BasePresenter<View>{
        void requestTodayData();
    }

    public interface View extends BaseView{
        void showDataSucceed(LinkedHashMap<String, ArrayList<GankEntity>> gankEntities);
    }
}
