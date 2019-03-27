package com.welson.gankio2.retrofit;

import com.welson.gankio2.entity.HistoryEntity;
import com.welson.gankio2.entity.TodayEntity;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("today")
    Observable<TodayEntity> getTodayData();

    @GET("day/history")
    Observable<HistoryEntity> getHistoryDates();

    @GET("day/{date}")
    Observable<TodayEntity> getDateData(@Path("date")String date);
}
