package com.welson.gankio2.fragment;

import android.animation.ObjectAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.welson.gankio2.R;
import com.welson.gankio2.adapter.CategoryEntityRecyclerAdapter;
import com.welson.gankio2.contract.CategoryContract;
import com.welson.gankio2.entity.GankEntity;
import com.welson.gankio2.presenter.CategoryPresenter;

import java.util.ArrayList;

public class CategoryEntityFragment extends BaseFragment implements CategoryContract.View{

    private CategoryPresenter presenter;
    private RecyclerView recyclerView;
    private CategoryEntityRecyclerAdapter adapter;
    private LinearLayout loadLayout;
    private ImageView loadIcon;
    private int currentItem;
    private int count = 20;
    private int page = 1;
    private ArrayList<GankEntity> entities;
    private ObjectAnimator rotate;

    @Override
    public int setLayoutId() {
        return R.layout.fragment_category_entity;
    }

    @Override
    public void initView(View view) {
        currentItem = getArguments().getInt("currentItem");
        recyclerView = view.findViewById(R.id.category_entity_recycler);
        loadLayout = view.findViewById(R.id.loading_layout);
        loadIcon = view.findViewById(R.id.loading_image);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }

    @Override
    public void initData() {
        presenter = new CategoryPresenter(getContext());
        presenter.attachView(this);
        presenter.requestCategoryData(currentItem,count,page);
        entities = new ArrayList<>();
        adapter = new CategoryEntityRecyclerAdapter(getContext(),entities);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void detach() {
        presenter.detachView();
    }

    @Override
    public void showDataSucceed(ArrayList<GankEntity> entities) {
        setLoadingShow(false);
        rotate.cancel();
        this.entities.clear();
        this.entities.addAll(entities);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void startRequest() {
        setLoadingShow(true);
        rotate = ObjectAnimator.ofFloat(loadIcon,"rotation",0,360f);
        rotate.setRepeatCount(-1);
        rotate.setDuration(700);
        rotate.start();
    }

    @Override
    public void showError(String message) {
        setLoadingShow(false);
        rotate.cancel();
    }

    private void setLoadingShow(boolean isLoadingShow){
        if (isLoadingShow){
            loadLayout.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }else {
            loadLayout.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }
}
