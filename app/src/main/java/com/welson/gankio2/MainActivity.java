package com.welson.gankio2;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.welson.gankio2.fragment.NewsFragment;

public class MainActivity extends AppCompatActivity {

    private FrameLayout mainLayout;
    private NewsFragment newsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initView();
        initData();
    }

    private void initView(){
        mainLayout = findViewById(R.id.main_layout);

    }

    private void initData(){
        newsFragment = new NewsFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_layout,newsFragment);
        fragmentTransaction.commit();
    }
}
