package com.example.tv_app_mvvm.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tv_app_mvvm.Model.Response.BaseResponse;
import com.example.tv_app_mvvm.Model.Response.Tv_Detail;
import com.example.tv_app_mvvm.R;
import com.example.tv_app_mvvm.Utils.AppConstant;
import com.example.tv_app_mvvm.ViewModel.DetailViewModel;
import com.example.tv_app_mvvm.ViewModel.MainViewModel;

public class Detail_Activity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView txtfirst_air_date, txtorigin_country,textViewoverview,textViewpopularity,txtorginalname;
    private AppCompatImageView detailposter;

    DetailViewModel detailViewModel;
    Tv_Detail tv_detail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_);

        init();
        int tvId = getIntent().getIntExtra("tvId",0);
        detailViewModel= ViewModelProviders.of(this).get(DetailViewModel.class);
        detailViewModel.getTvDetail(tvId).observe(this, new Observer<Tv_Detail>() {
            @Override
            public void onChanged(Tv_Detail tv_detail) {
                setDataToView(tv_detail);
                hideProgressBar();
            }
        });


    }

    public void init()
    {
        progressBar = findViewById(R.id.progresbar);
        txtfirst_air_date=findViewById(R.id.first_air_date);
        txtorigin_country=findViewById(R.id.origin_country);
        textViewoverview=findViewById(R.id.overview);
        textViewpopularity=findViewById(R.id.popularity);
        txtorginalname=findViewById(R.id.original_name);
        detailposter=findViewById(R.id.backdrop_path);
        showProgressBar();
    }


    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }


    public void hideProgressBar() {
        if(progressBar!=null && progressBar.isShown()){
            progressBar.setVisibility(View.GONE);
        }
    }

    private void setDataToView(Tv_Detail tv_detail){

        txtfirst_air_date.setText(tv_detail.first_air_date);
        textViewoverview.setText(tv_detail.overview);
        textViewpopularity.setText(String.valueOf(tv_detail.popularity));
        txtorginalname.setText(tv_detail.original_name);

        Glide.with(getApplicationContext()).load(AppConstant.BASE_PATH_OF_IMAGE +tv_detail.getBackdrop_path()).into(detailposter);

        String countryFull = "";
        for (String country : tv_detail.origin_country){

            countryFull += country;
        }
        txtorigin_country.setText(countryFull);
    }
}