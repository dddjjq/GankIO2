package com.welson.gankio2.presenter;

import android.accounts.NetworkErrorException;

import com.welson.gankio2.constant.Category;
import com.welson.gankio2.contract.TodayContract;
import com.welson.gankio2.entity.GankEntity;
import com.welson.gankio2.entity.HistoryEntity;
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
                        LinkedHashMap<ArrayList<GankEntity>,String> data = parseData(todayEntity);
                        if (data == null){
                            onError(new NetworkErrorException());
                        }else {
                            view.showDataSucceed(data);
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
    public void requestHistoryDates() {
        RetrofitHelper.getInstance().getHistoryDates()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HistoryEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(HistoryEntity historyEntity) {
                        view.showHistoryDatesSucceed(historyEntity);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void requestDateData(String date) {
        view.startRequest();
        RetrofitHelper.getInstance().getDateData(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TodayEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(TodayEntity todayEntity) {
                        LinkedHashMap<ArrayList<GankEntity>,String> data = parseData(todayEntity);
                        if (data == null){
                            onError(new NetworkErrorException());
                        }else {
                            view.showDataSucceed(data);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError("");
                        e.printStackTrace();
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

    private LinkedHashMap<ArrayList<GankEntity>,String> parseData(TodayEntity todayEntity) {
        LinkedHashMap<ArrayList<GankEntity>,String> result = new LinkedHashMap<>();
        boolean isDataRight = !todayEntity.isError();
        if (isDataRight) {
            ArrayList<String> category = todayEntity.getCategory();
            TodayEntity.Result results = todayEntity.getResults();
            if(results.getAndroid() != null){
                result.put(results.getAndroid(),"Android");
            }
            if (results.getApp() != null){
                result.put(results.getApp(),"App");
            }
            if (results.getApp() != null){
                result.put(results.getApp(),"App");
            }
            if (results.getiOS() != null){
                result.put(results.getiOS(),"iOS");
            }
            if (results.getRistMovie() != null){
                result.put(results.getRistMovie(),"休息视频");
            }
            if (results.getFront() != null){
                result.put(results.getFront(),"前端");
            }
            if (results.getExpand() != null){
                result.put(results.getExpand(),"拓展资源");
            }
            if (results.getRecommendBlind() != null){
                result.put(results.getRecommendBlind(),"瞎推荐");
            }
            if (results.getFuli() != null){
                result.put(results.getFuli(),"福利");
            }
        }else {
            return null;
        }
        return result;
    }
}
