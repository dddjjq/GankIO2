package com.welson.gankio2.retrofit;

import com.welson.gankio2.entity.TodayEntity;


import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {

    @GET("today")
    Observable<TodayEntity> getTodayData();


}
