package com.welson.gankio2.presenter;

import android.accounts.NetworkErrorException;

import com.welson.gankio2.constant.Category;
import com.welson.gankio2.contract.TodayContract;
import com.welson.gankio2.entity.GankEntity;
import com.welson.gankio2.entity.TodayEntity;
import com.welson.gankio2.retrofit.RetrofitHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TodayPresenter extends AbstractPresenter implements TodayContract.Presenter {

    private TodayContract.View view;

    @Override
    public void requestTodayData() {
        view.startRequest();
        RetrofitHelper.getInstance().getTodayData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TodayEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(TodayEntity todayEntity) {
                        HashMap<String, ArrayList<GankEntity>> data = parseData(todayEntity);
                        if (data == null){
                            onError(new NetworkErrorException());
                        }else {
                            view.showDataSucceed(parseData(todayEntity));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError("");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void attachView(TodayContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        if (view != null) {
            view = null;
        }
    }

    private LinkedHashMap<String, ArrayList<GankEntity>> parseData(TodayEntity todayEntity) {
        LinkedHashMap<String, ArrayList<GankEntity>> result = new LinkedHashMap<>();
        boolean isDataRight = !todayEntity.isError();
        if (isDataRight) {
            String key = null;
            ArrayList<GankEntity> value = null;
            ArrayList<String> category = todayEntity.getCategory();
            TodayEntity.Result results = todayEntity.getResults();
            for (int i = 0; i < todayEntity.getCategory().size(); i++) {
                switch (i){
                    case Category.IOS:
                        key = category.get(0);
                        value = results.getiOS();
                        break;
                    case Category.EXPAND:
                        key = category.get(1);
                        value = results.getExpand();
                        break;
                    case Category.RECOMMEND:
                        key = category.get(2);
                        value = results.getRecommendBlind();
                        break;
                    case Category.ANDROID:
                        key = category.get(3);
                        value = results.getAndroid();
                        break;
                    case Category.APP:
                        key = category.get(4);
                        value = results.getApp();
                        break;
                    case Category.RIST_MOVIE:
                        key = category.get(5);
                        value = results.getRistMovie();
                        break;
                    case Category.FULI:
                        key = category.get(6);
                        value = results.getFuli();
                        break;
                    case Category.FRONT:
                        key = category.get(7);
                        value = results.getFront();
                        break;
                }
                result.put(key,value);
            }
        }else {
            return null;
        }
        return result;
    }
}
