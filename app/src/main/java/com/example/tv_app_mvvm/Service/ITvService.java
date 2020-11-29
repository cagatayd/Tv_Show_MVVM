package com.example.tv_app_mvvm.Service;

import com.example.tv_app_mvvm.Model.Response.BaseResponse;
import com.example.tv_app_mvvm.Model.Response.Tv_Detail;
import com.example.tv_app_mvvm.Utils.AppConstant;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ITvService {


    @GET(AppConstant.TOP_RATED_TV+AppConstant.API_KEY)
    Observable<BaseResponse> getTopRatedTvList(@Query("page") int pageIndex);

    @GET(AppConstant.POPULAR_TV+AppConstant.API_KEY)
    Observable<BaseResponse> getPopularTvList(@Query("page") int pageIndex);

    @GET(AppConstant.ONAIR_TV+AppConstant.API_KEY)
    Call<BaseResponse> getOnAirTvList(@Query("page") int pageIndex);

    @GET(AppConstant.TV_AIRING+AppConstant.API_KEY)
    Observable<BaseResponse> getOnTvAiringTodayList(@Query("page") int pageIndex);



    @GET(AppConstant.TV_DETAIL+AppConstant.API_KEY)
    Observable<Tv_Detail> getTvDetail(@Path("tv_id") int tv_id);

    @GET(AppConstant.TV_SEARCH+AppConstant.API_KEY)
    Observable<BaseResponse> getTvSearch(@Query("query") String query);
}
