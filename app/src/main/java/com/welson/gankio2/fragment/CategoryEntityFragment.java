package com.welson.gankio2.fragment;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.welson.gankio2.R;
import com.welson.gankio2.contract.CategoryContract;
import com.welson.gankio2.entity.GankEntity;
import com.welson.gankio2.presenter.CategoryPresenter;

import java.util.ArrayList;

public class CategoryEntityFragment extends BaseFragment implements CategoryContract.View{

    private CategoryPresenter presenter;
    private RecyclerView recyclerView;

    @Override
    public int setLayoutId() {
        return R.layout.fragment_category_entity;
    }

    @Override
    public void initView(View view) {
        recyclerView = view.findViewById(R.id.category_entity_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }

    @Override
    public void initData() {
        presenter = new CategoryPresenter(getContext());
    }

    @Override
    public void detach() {

    }

    @Override
    public void showDataSucceed(ArrayList<GankEntity> entities) {

    }

    @Override
    public void startRequest() {

    }

    @Override
    public void showError(String message) {

    }
}
