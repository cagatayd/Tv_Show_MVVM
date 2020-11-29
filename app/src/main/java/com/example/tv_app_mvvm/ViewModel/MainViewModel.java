package com.example.tv_app_mvvm.ViewModel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.tv_app_mvvm.App;
import com.example.tv_app_mvvm.Local.db.Appdatabase;
import com.example.tv_app_mvvm.Model.Response.BaseResponse;
import com.example.tv_app_mvvm.Model.Response.TvList;
import com.example.tv_app_mvvm.Repository.TvRepository;
import com.example.tv_app_mvvm.Service.ITvService;
import com.example.tv_app_mvvm.Utils.AppConstant;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainViewModel extends AndroidViewModel { // daha kapsamlı yapılacak olan işlerde viewmodeldan exten alınabilir


    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<BaseResponse> getTopRatedTvList(int pageIndex) {
        //return TvRepository.getInstance().getTopRatedTvList(pageIndex);
       return App.getInstance().getTvRepository().getTopRatedTvList(pageIndex);
    }

    public MutableLiveData<BaseResponse> getPopularTvList(int pageIndex) {
       return App.getInstance().getTvRepository().getPopularTvList(pageIndex);
    }

    public MutableLiveData<BaseResponse> getOnairtv(int pageIndex) {

        return App.getInstance().getTvRepository().getOnairtv(pageIndex);
    }

    public MutableLiveData<BaseResponse> getOnTvAiringTodayList(int pageIndex) {

        return App.getInstance().getTvRepository().getOnTvAiringTodayList(pageIndex);

    }

    public MutableLiveData<BaseResponse>SearchTvShow(String query)
    {
        return App.getInstance().getTvRepository().searcHTvShow(query);
    }

    public void insert(TvList tvList){
        App.getInstance().getTvRepository().insert(tvList);
    }

}