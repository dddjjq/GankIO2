package com.welson.gankio2.retrofit;

import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.welson.gankio2.constant.Constants;
import com.welson.gankio2.entity.HistoryEntity;
import com.welson.gankio2.entity.TodayEntity;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {
    private OkHttpClient client = new OkHttpClient();
    private GsonConverterFactory factory = GsonConverterFactory.create(new GsonBuilder().create());
    private Retrofit retrofit = null;
    private ApiService apiService;
    private static RetrofitHelper instance = null;

    public static RetrofitHelper getInstance() {
        if (instance == null){
            instance = new RetrofitHelper();
        }
        return instance;
    }

    private RetrofitHelper(){
        init();
    }

    private void init(){
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public Observable<TodayEntity> getTodayData(){
        return apiService.getTodayData();
    }

    public Observable<HistoryEntity> getHistoryDates(){
        return apiService.getHistoryDates();
    }

    public Observable<TodayEntity> getDateData(String date){
        return apiService.getDateData(date);
    }
}
