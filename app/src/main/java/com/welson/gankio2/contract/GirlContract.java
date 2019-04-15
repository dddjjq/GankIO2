package com.welson.gankio2.contract;

import com.welson.gankio2.base.BasePresenter;
import com.welson.gankio2.base.BaseView;
import com.welson.gankio2.entity.GankEntity;

import java.util.ArrayList;

public class GirlContract {

    public interface Presenter extends BasePresenter{
        void requestGirlData(int count,int page);
    }

    public interface View extends BaseView{
        void showDataSucceed(ArrayList<GankEntity> entities);
    }
}
