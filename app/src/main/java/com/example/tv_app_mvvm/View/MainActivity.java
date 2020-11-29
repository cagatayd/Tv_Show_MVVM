package com.example.tv_app_mvvm.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

import com.example.tv_app_mvvm.Adapter.TvAdapter;
import com.example.tv_app_mvvm.Listener.OnTvItemClickListener;
import com.example.tv_app_mvvm.Listener.PaginationScrollListener;
import com.example.tv_app_mvvm.Model.Response.BaseResponse;
import com.example.tv_app_mvvm.Model.Response.TvList;
import com.example.tv_app_mvvm.R;
import com.example.tv_app_mvvm.ViewModel.MainViewModel;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnTvItemClickListener  {

    private ProgressBar progressBar;
    private ActionBarDrawerToggle mToggle;
    private RecyclerView recyclerView;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private SearchView searchView;
    private static final int PAGE_START=1;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int currentPage = PAGE_START;
    GridLayoutManager gridLayoutManager;
    MainViewModel mainViewModel;
    byte type=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.navigationView);
        progressBar=findViewById(R.id.progresbar);
        searchView=findViewById(R.id.searchView);

        mToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.Open,R.string.Close);
        mToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mainViewModel=ViewModelProviders.of(this).get(MainViewModel.class);
        showProgressBar();
        mainViewModel.getTopRatedTvList(1).observe(this, new Observer<BaseResponse>() {
            @Override
            public void onChanged(BaseResponse baseResponse) {
                loadTvList(baseResponse.getResults());
                insertTvList(baseResponse.getResults());
                hideProgressBar();
            }
        });

        recyclerView.addOnScrollListener(new PaginationScrollListener(gridLayoutManager) {

            @Override
            public void loadMoreItems() {

                isLoading = true;
                currentPage +=1;


                switch (type){

                   case 1:

                       mainViewModel.getTopRatedTvList(currentPage).observe(MainActivity.this, new Observer<BaseResponse>() {
                           @Override
                           public void onChanged(BaseResponse baseResponse) {
                               loadTvList(baseResponse.getResults());
                               insertTvList(baseResponse.getResults());
                               hideProgressBar();
                               isLoading=false;
                           }
                       });
                      break;
                  case 2:
                      mainViewModel.getPopularTvList(currentPage).observe(MainActivity.this, new Observer<BaseResponse>() {
                          @Override
                          public void onChanged(BaseResponse baseResponse) {
                              loadTvList(baseResponse.getResults());
                              insertTvList(baseResponse.getResults());
                              hideProgressBar();
                              isLoading=false;
                          }
                      });


                   break;

                   case 3:

                       mainViewModel.getOnairtv(currentPage).observe(MainActivity.this, new Observer<BaseResponse>() {
                           @Override
                           public void onChanged(BaseResponse baseResponse) {
                               loadTvList(baseResponse.getResults());
                               insertTvList(baseResponse.getResults());
                               hideProgressBar();
                               isLoading=false;
                           }
                       });
                       break;
                    case 4:
                        mainViewModel.getOnTvAiringTodayList(currentPage).observe(MainActivity.this, new Observer<BaseResponse>() {
                            @Override
                            public void onChanged(BaseResponse baseResponse) {
                                loadTvList(baseResponse.getResults());
                                insertTvList(baseResponse.getResults());
                                hideProgressBar();
                                isLoading=false;
                            }
                        });
                        break;
                }
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getResultOfSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                getResultOfSearch(newText);
                return false;
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.tvtoprated:

                        type=1;
                       mainViewModel.getTopRatedTvList(1).observe(MainActivity.this, new Observer<BaseResponse>() {
                           @Override
                           public void onChanged(BaseResponse baseResponse) {
                               loadTvList(baseResponse.getResults());
                               hideProgressBar();

                           }
                       });
                        drawerLayout.closeDrawers();
                        return false;
                    case R.id.tvpopular:
                        type=2;
                        mainViewModel.getPopularTvList(1).observe(MainActivity.this, new Observer<BaseResponse>() {
                            @Override
                            public void onChanged(BaseResponse baseResponse) {
                                loadTvList(baseResponse.getResults());
                                hideProgressBar();
                            }
                        });
                        drawerLayout.closeDrawers();
                        return false;
                    case R.id.tvonair:
                        type=3;
                        mainViewModel.getOnairtv(1).observe(MainActivity.this, new Observer<BaseResponse>() {
                            @Override
                            public void onChanged(BaseResponse baseResponse) {
                                loadTvList(baseResponse.getResults());
                                hideProgressBar();
                            }
                        });
                        drawerLayout.closeDrawers();
                        return false;
                    case R.id.tvairing:
                        type=4;
                        mainViewModel.getOnTvAiringTodayList(1).observe(MainActivity.this, new Observer<BaseResponse>() {
                            @Override
                            public void onChanged(BaseResponse baseResponse) {
                                loadTvList(baseResponse.getResults());
                                hideProgressBar();
                            }
                        });
                        drawerLayout.closeDrawers();
                        return false;
                    case R.id.exit:
                        Toast.makeText(MainActivity.this,"naberrr",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawers();
                        return false;
                }
                return false;
            }
        });
    }



    private void getResultOfSearch(String query){
        mainViewModel.SearchTvShow(query).observe(MainActivity.this, new Observer<BaseResponse>() {
            @Override
            public void onChanged(BaseResponse baseResponse) {
                loadTvList(baseResponse.getResults());
            }
        });
    }

    private void insertTvList(List<TvList> tvLists){

        mainViewModel.insert(tvLists.get(0));
//        for(int i = 0 ; i <= tvLists.size() ; i++){
//            mainViewModel.insert(tvLists.get(i));
//        }
//        for(TvList tvList : tvLists){
//            mainViewModel.insert(getApplicationContext() , tvList);
//        }
    }


    private void loadTvList(List<TvList> tvLists){

        TvAdapter tvAdapter = new TvAdapter((ArrayList<TvList>) tvLists,this, getApplicationContext());

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(tvAdapter);
        tvAdapter.notifyDataSetChanged();
//        showProgressBar();
    }

    @Override
    public void onClick(int tvId) {
        Intent intent = new Intent(this, Detail_Activity.class);
        intent.putExtra("tvId", tvId);
        startActivity(intent);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (mToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }
    public void showProgressBar() {
        if (progressBar!=null && !progressBar.isShown())
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        if(progressBar!=null && progressBar.isShown()){
            progressBar.setVisibility(View.GONE);
        }
    }


}