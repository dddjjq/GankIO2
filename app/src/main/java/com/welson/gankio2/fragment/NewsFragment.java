package com.welson.gankio2.fragment;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.welson.gankio2.R;
import com.welson.gankio2.adapter.NewsRecyclerAdapter;
import com.welson.gankio2.contract.TodayContract;
import com.welson.gankio2.entity.GankEntity;
import com.welson.gankio2.presenter.TodayPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class NewsFragment extends BaseFragment implements TodayContract.View{

    private TodayPresenter todayPresenter;
    private RecyclerView newsRecyclerView;
    private NewsRecyclerAdapter adapter;

    @Override
    public int setLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    public void initView(View view) {
        newsRecyclerView = view.findViewById(R.id.news_recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        newsRecyclerView.setLayoutManager(manager);
        newsRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
    }

    @Override
    public void initData() {
        todayPresenter = new TodayPresenter();
        todayPresenter.attachView(this);
        todayPresenter.requestTodayData();
    }

    @Override
    public void detach() {
        todayPresenter.detachView();
    }

    @Override
    public void showDataSucceed(LinkedHashMap<String, ArrayList<GankEntity>> gankEntities) {
        adapter = new NewsRecyclerAdapter(getContext(),gankEntities);
        newsRecyclerView.setAdapter(adapter);
    }

    @Override
    public void startRequest() {

    }

    @Override
    public void showError(String s) {

    }
}
