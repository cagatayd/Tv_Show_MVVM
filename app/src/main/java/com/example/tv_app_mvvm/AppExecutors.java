package com.example.tv_app_mvvm;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutors {

    private final Executor mDiskIO;

    private final Executor mNetworkIO;

    private final Executor mMainThread;

    public AppExecutors(Executor mDiskIO, Executor mNetworkIO, Executor mMainThread) {
        this.mDiskIO = mDiskIO;
        this.mNetworkIO = mNetworkIO;
        this.mMainThread = mMainThread;
    }

    public AppExecutors(){
        this(Executors.newSingleThreadExecutor(), Executors.newFixedThreadPool(3), new MainThreadExecutor());
    }

    public Executor diskIO(){return  mDiskIO;}

    public Executor networkIO(){return mNetworkIO;}

    public Executor mainThread(){return mMainThread;}
}
