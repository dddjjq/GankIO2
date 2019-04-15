package com.welson.gankio2.fragment;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.welson.gankio2.R;
import com.welson.gankio2.adapter.GirlRecyclerAdapter;
import com.welson.gankio2.contract.GirlContract;
import com.welson.gankio2.entity.GankEntity;
import com.welson.gankio2.presenter.GirlPresenter;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class GirlFragment extends BaseFragment implements GirlContract.View{

    private RecyclerView girlRecyclerView;
    private GirlRecyclerAdapter adapter;
    private ArrayList<GankEntity> entities;
    private GirlPresenter presenter;
    private int count = 20;
    private int page = 1;

    @Override
    public int setLayoutId() {
        return R.layout.fragment_girls;
    }

    @Override
    public void initView(View view) {
        girlRecyclerView = view.findViewById(R.id.girl_recycler_view);
        girlRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        girlRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
    }

    @Override
    public void initData() {
        entities = new ArrayList<>();
        presenter = new GirlPresenter(getContext());
        presenter.attachView(this);
        presenter.requestGirlData(count,page);
        adapter = new GirlRecyclerAdapter(getContext(),entities);
        girlRecyclerView.setAdapter(adapter);
    }

    @Override
    public void detach() {
        presenter.detachView();
    }

    @Override
    public void showDataSucceed(ArrayList<GankEntity> entities) {
        this.entities.addAll(entities);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void startRequest() {

    }

    @Override
    public void showError(String message) {

    }


}
