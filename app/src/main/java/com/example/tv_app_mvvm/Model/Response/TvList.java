package com.example.tv_app_mvvm.Model.Response;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "TvList")
public class TvList {

    @SerializedName("name")
    public String title;
    @SerializedName("poster_path")
    public String poster_path;

    @PrimaryKey
    @SerializedName("id")
    public int id;

    public TvList()
    {

    }

    public TvList(String name, String poster_path, int id) {
        this.title = name;
        this.poster_path = poster_path;
        this.id = id;
    }

    public String getName() {
        return title;
    }

    public void setName(String name) {
        this.title = name;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
