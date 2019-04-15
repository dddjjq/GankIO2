package com.welson.gankio2.presenter;

import android.content.Context;

import com.welson.gankio2.R;
import com.welson.gankio2.base.BaseView;
import com.welson.gankio2.contract.GirlContract;
import com.welson.gankio2.entity.CategoryEntity;
import com.welson.gankio2.retrofit.RetrofitHelper;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class GirlPresenter extends AbstractPresenter implements GirlContract.Presenter{

    private Context context;
    private GirlContract.View view;

    public GirlPresenter(Context context){
        this.context = context;
    }

    @Override
    public void requestGirlData(int count, int page) {
        view.startRequest();
        RetrofitHelper.getInstance().getCategoryData(context.getResources().getString(R.string.girl_category),count,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CategoryEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(CategoryEntity categoryEntity) {
                        view.showDataSucceed(categoryEntity.getResults());
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
    public void attachView(BaseView baseView) {
        view = (GirlContract.View) baseView;
    }

    @Override
    public void detachView() {
        if (view != null){
            view = null;
        }
    }
}
