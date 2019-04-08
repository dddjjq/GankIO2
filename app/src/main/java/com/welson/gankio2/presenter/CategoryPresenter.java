package com.welson.gankio2.presenter;

import android.content.Context;

import com.welson.gankio2.R;
import com.welson.gankio2.contract.CategoryContract;
import com.welson.gankio2.entity.CategoryEntity;
import com.welson.gankio2.retrofit.RetrofitHelper;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CategoryPresenter extends AbstractPresenter implements CategoryContract.Presenter {

    private CategoryContract.View view;
    private Context context;

    public CategoryPresenter(Context context){
        this.context = context;
    }
    @Override
    public void requestCategoryData(int category, int count, int page) {
        view.startRequest();
        String[] categoryStrings = context.getResources().getStringArray(R.array.category_item);
        RetrofitHelper.getInstance().getCategoryData(categoryStrings[category],count,page)
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
    public void attachView(CategoryContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        if(view != null){
            view = null;
        }
    }
}
