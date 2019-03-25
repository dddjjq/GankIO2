package com.welson.gankio2.fragment;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;

import com.welson.gankio2.MainActivity;
import com.welson.gankio2.R;
import com.welson.gankio2.adapter.CalendarRecyclerAdapter;
import com.welson.gankio2.adapter.NewsRecyclerAdapter;
import com.welson.gankio2.contract.TodayContract;
import com.welson.gankio2.entity.GankEntity;
import com.welson.gankio2.entity.HistoryEntity;
import com.welson.gankio2.presenter.TodayPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class NewsFragment extends BaseFragment implements TodayContract.View {

    private TodayPresenter todayPresenter;
    private RecyclerView newsRecyclerView;
    private NewsRecyclerAdapter adapter;
    private RecyclerView calendarRecyclerView;
    private CalendarRecyclerAdapter calendarRecyclerAdapter;
    private MainActivity activity;

    @Override
    public int setLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    public void initView(View view) {
        newsRecyclerView = view.findViewById(R.id.news_recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        newsRecyclerView.setLayoutManager(manager);
        newsRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        calendarRecyclerView = view.findViewById(R.id.news_calendar_recycler);
        LinearLayoutManager horizontalManger = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        calendarRecyclerView.setLayoutManager(horizontalManger);
    }

    @Override
    public void initData() {
        activity = (MainActivity)getActivity();
        todayPresenter = new TodayPresenter();
        todayPresenter.attachView(this);
        todayPresenter.requestTodayData();
        todayPresenter.requestHistoryDates();
    }

    @Override
    public void detach() {
        todayPresenter.detachView();
    }

    @Override
    public void showDataSucceed(LinkedHashMap<String, ArrayList<GankEntity>> gankEntities) {
        adapter = new NewsRecyclerAdapter(getContext(), gankEntities);
        newsRecyclerView.setAdapter(adapter);
    }

    @Override
    public void showHistoryDatesSucceed(HistoryEntity historyEntity) {
        calendarRecyclerAdapter = new CalendarRecyclerAdapter(getContext(), historyEntity.getResults());
        calendarRecyclerView.setAdapter(calendarRecyclerAdapter);
    }

    @Override
    public void startRequest() {

    }

    @Override
    public void showError(String s) {

    }

    public void onCalendarClick() {
        if (calendarRecyclerView.getVisibility() == View.VISIBLE) {
            TranslateAnimation mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                    0.0f, Animation.RELATIVE_TO_SELF, -1.0f);
            mShowAction.setDuration(150);
            calendarRecyclerView.startAnimation(mShowAction);
            calendarRecyclerView.setVisibility(View.GONE);
        } else {
            TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                    -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
            mHiddenAction.setDuration(150);
            calendarRecyclerView.startAnimation(mHiddenAction);
            calendarRecyclerView.setVisibility(View.VISIBLE);
        }
    }
}
