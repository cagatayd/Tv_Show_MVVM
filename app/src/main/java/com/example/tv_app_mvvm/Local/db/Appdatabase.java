package com.example.tv_app_mvvm.Local.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.tv_app_mvvm.AppExecutors;
import com.example.tv_app_mvvm.Local.dao.TvDao;
import com.example.tv_app_mvvm.Model.Response.TvList;

@Database(entities = {TvList.class}, version = 1) //Database anonation'ını bu sınıfın db sınıfı olduğu belirtir.İçerisindeki entities ise veritabanı içerisinde yer alacak olan tabloları belirler.
public abstract class Appdatabase extends RoomDatabase {

    @VisibleForTesting
    public static final String DATABASE_NAME = "app_db";

    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    public abstract TvDao tvDao();

    private static Appdatabase sInstance;

    public static synchronized Appdatabase getInstance(Context context, final AppExecutors executors) {

        if (sInstance == null) {
            synchronized (Appdatabase.class) {
                if (sInstance == null) {
                    sInstance = buildDatabase(context.getApplicationContext() , executors);
                    sInstance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated();
        }
    }

    private static Appdatabase buildDatabase(final Context appContext,
                                             final AppExecutors executors) {
        return Room.databaseBuilder(appContext, Appdatabase.class, DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        executors.diskIO().execute(() -> {
                            // Add a delay to simulate a long-running operation
                            addDelay();
                            // Generate the data for pre-population
                            Appdatabase database = Appdatabase.getInstance(appContext, executors);

                            database.setDatabaseCreated();
                        });
                    }
                })
                .build();
    }

    private static void addDelay() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ignored) {
        }
    }

    private void setDatabaseCreated(){
        mIsDatabaseCreated.postValue(true);
    }

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
}
