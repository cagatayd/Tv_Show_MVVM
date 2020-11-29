package com.example.tv_app_mvvm.Repository;

import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;

import com.example.tv_app_mvvm.Local.dao.TvDao;
import com.example.tv_app_mvvm.Local.db.Appdatabase;
import com.example.tv_app_mvvm.Model.Response.BaseResponse;
import com.example.tv_app_mvvm.Model.Response.TvList;
import com.example.tv_app_mvvm.Model.Response.Tv_Detail;
import com.example.tv_app_mvvm.Service.ITvService;
import com.example.tv_app_mvvm.Utils.AppConstant;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class TvRepository {

    private  static  TvRepository tvrepoinstance;
    private final Appdatabase appDatabase;

    private ITvService iTvService;
    public MutableLiveData<BaseResponse> topratedtv = new MutableLiveData<>();
    public MutableLiveData<BaseResponse> populartv = new MutableLiveData<>(); //viewmodel dan view a herhangi bir veribütününü taşımak için mutablelivedatayı kullanırız.mutabledaye observer desingpatter mantığında çalışır yani kullanılan nesne bir yerde güncellendiğinde o nesnenin kullanıldıgı diğer tüm yerler güncellenir
    public MutableLiveData<BaseResponse> onairtv = new MutableLiveData<>();
    public MutableLiveData<BaseResponse> onairingtodaytv= new MutableLiveData<>();
    public MutableLiveData<Tv_Detail> tvdetail = new MutableLiveData<>();
    public MutableLiveData<BaseResponse>searchtv=new MutableLiveData<>();

    private TvRepository(final Appdatabase appDatabase)// singletan kullanıgımız için pria yaptık construc ırı
    {
        this.appDatabase = appDatabase;

        iTvService = new Retrofit.Builder()
                .baseUrl(AppConstant.SERVİCE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ITvService.class);
    }

    public static TvRepository getInstance(final Appdatabase appDatabase)
    {
        if (tvrepoinstance==null)
        {
            synchronized (TvRepository.class){
                tvrepoinstance=new TvRepository(appDatabase);
            }
        }
        return tvrepoinstance;
    }

    public MutableLiveData<BaseResponse>  getTopRatedTvList(int pageIndex) {
        iTvService.getTopRatedTvList(pageIndex)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseResponse>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull BaseResponse baseResponse) {

                        topratedtv.setValue(baseResponse);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return topratedtv;
    }

    public MutableLiveData<BaseResponse> getPopularTvList(int pageIndex) {
        iTvService.getPopularTvList(pageIndex)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseResponse>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull BaseResponse baseResponse) {
                        populartv.setValue(baseResponse);

                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return populartv;
    }

    public MutableLiveData<BaseResponse> getOnairtv(int pageIndex) {
        iTvService.getOnTvAiringTodayList(pageIndex)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<com.example.tv_app_mvvm.Model.Response.BaseResponse>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull com.example.tv_app_mvvm.Model.Response.BaseResponse baseResponse) {

                        onairtv.setValue(baseResponse);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return onairingtodaytv;
    }

    public MutableLiveData<BaseResponse> getOnTvAiringTodayList(int pageIndex) {
        iTvService.getOnTvAiringTodayList(pageIndex)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseResponse>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull BaseResponse baseResponse) {

                        onairingtodaytv.setValue(baseResponse);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return onairingtodaytv;

    }

    public MutableLiveData<Tv_Detail> getTvDetail(int tv_id) {
        iTvService.getTvDetail(tv_id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Tv_Detail>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull Tv_Detail tv_detail) {

                        tvdetail.setValue(tv_detail);


                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return tvdetail;
    }

    public MutableLiveData<BaseResponse> searcHTvShow(String query) {

        iTvService.getTvSearch(query )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseResponse>(){
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {


                    }

                    @Override
                    public void onNext(@NonNull BaseResponse baseResponse) {


                        searchtv.setValue(baseResponse);

                    }


                    @Override
                    public void onError(@NonNull Throwable e) {


                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return searchtv;

    }

    public void insert(TvList tvList) {
        new InsertTvAsyncTask(appDatabase.tvDao()).execute(tvList);
    }
    private static class InsertTvAsyncTask extends AsyncTask<TvList , Void, Void> {

        private TvDao tvDao;

        private InsertTvAsyncTask(TvDao tvDao){this.tvDao = tvDao;}

        @Override
        protected Void doInBackground(TvList... tvLists) {
            tvDao.insert(tvLists[0]);
            return null;
        }
    }
}
