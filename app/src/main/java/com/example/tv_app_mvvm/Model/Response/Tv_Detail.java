package com.example.tv_app_mvvm.Model.Response;

import java.util.List;

public class Tv_Detail {

    public String backdrop_path;
    public int id;
    public double popularity;
    public int vote_count;
    public double vote_average;
    public String first_air_date;
    public String original_language;
    public String overview;
    public String original_name;
    public List<String> origin_country;

    public Tv_Detail(String backdrop_path, int id, double popularity, int vote_count, double vote_average, String first_air_date, String original_language, String overview, String original_name, List<String> origin_country) {
        this.backdrop_path = backdrop_path;
        this.id = id;
        this.popularity = popularity;
        this.vote_count = vote_count;
        this.vote_average = vote_average;
        this.first_air_date = first_air_date;
        this.original_language = original_language;
        this.overview = overview;
        this.original_name = original_name;
        this.origin_country = origin_country;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public List<String> getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(List<String> origin_country) {
        this.origin_country = origin_country;
    }
}
