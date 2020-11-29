package com.example.tv_app_mvvm;

import android.app.Application;
import android.util.Log;

import com.example.tv_app_mvvm.Local.db.Appdatabase;
import com.example.tv_app_mvvm.Repository.TvRepository;

public class App extends Application {


    private static App sInstance;

    private AppExecutors appExecutors;

    public static synchronized App getInstance(){ return  sInstance == null ? sInstance = new App() : sInstance; }

    @Override
    public void onCreate() {
        super.onCreate();

        appExecutors = new AppExecutors();
        sInstance = this;
    }

    public Appdatabase getDatabase(){ return Appdatabase.getInstance(this, appExecutors);}

    public TvRepository getTvRepository(){
        return TvRepository.getInstance(getDatabase());
    }
}
