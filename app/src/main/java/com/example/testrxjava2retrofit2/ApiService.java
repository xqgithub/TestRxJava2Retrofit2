package com.example.testrxjava2retrofit2;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by admin on 2018/1/16.
 */
public interface ApiService {


    @GET("/api/client/v3/commons/routes?platform=android")
    Observable<ApiResponse<RoutesResponse>> routesv3();
}
