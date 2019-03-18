package com.welson.gankio2.base;

public interface BasePresenter<T extends BaseView> {
    void attachView(T t);
    void detachView();
}
