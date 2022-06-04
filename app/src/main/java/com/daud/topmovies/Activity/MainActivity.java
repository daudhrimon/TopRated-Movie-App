package com.daud.topmovies.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.daud.topmovies.Adapter.TopMovieAdapter;
import com.daud.topmovies.Model.MovieModel;
import com.daud.topmovies.Model.Result;
import com.daud.topmovies.Network.ApiInterface;
import com.daud.topmovies.Network.RetrofitInit;
import com.daud.topmovies.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mvRecycler;
    private ApiInterface apiInterface;
    private List<Result> rList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initial();

        getResult();
    }

    private void getResult(){
        apiInterface.getData().enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                if (response.isSuccessful()){
                    rList = response.body().getResults();

                    if (rList.size()>0){
                        mvRecycler.setAdapter(new TopMovieAdapter(MainActivity.this,rList));
                    }
                }
            }
            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) { }
        });
    }

    private void initial() {
        mvRecycler = findViewById(R.id.mvRecycler);
        mvRecycler.setHasFixedSize(true);
        mvRecycler.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
        apiInterface = RetrofitInit.getRetro().create(ApiInterface.class);
        rList = new ArrayList<>();
    }
}