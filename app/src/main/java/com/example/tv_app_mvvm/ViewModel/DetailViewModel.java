package com.example.tv_app_mvvm.ViewModel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.tv_app_mvvm.App;
import com.example.tv_app_mvvm.Local.db.Appdatabase;
import com.example.tv_app_mvvm.Model.Response.BaseResponse;
import com.example.tv_app_mvvm.Model.Response.Tv_Detail;
import com.example.tv_app_mvvm.Repository.TvRepository;
import com.example.tv_app_mvvm.Service.ITvService;
import com.example.tv_app_mvvm.Utils.AppConstant;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailViewModel extends AndroidViewModel {

    public DetailViewModel(@NonNull Application application) {
        super(application);
    }
    public MutableLiveData<Tv_Detail> getTvDetail(int tv_id) {
        return App.getInstance().getTvRepository().getTvDetail(tv_id);
    }
}






