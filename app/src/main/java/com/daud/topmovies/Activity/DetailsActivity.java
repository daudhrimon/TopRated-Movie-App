package com.daud.topmovies.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daud.topmovies.Model.DetailsModel;
import com.daud.topmovies.Network.ApiInterface;
import com.daud.topmovies.Network.RetrofitInit;
import com.daud.topmovies.R;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends AppCompatActivity {
    private ImageButton backBtn;
    private ImageView poster;
    private TextView titileTv, reDateTv, popularityTv, voteTv, ratingTv, overViewTv, statusTv, budgetTv, oLanguageTv, oTitleTv, runTimeTv, adultTv;
    private LinearLayout detailsLay;
    private ApiInterface apiInterface;
    private String movie_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getSupportActionBar().hide();

        initial();

        if (movie_id != null && !movie_id.isEmpty()) {
            getMovieDetails(movie_id);
        }

        backBtn.setOnClickListener(view -> {
            onBackPressed();
            finish();
        });
    }

    private void getMovieDetails(String movie_id) {
        apiInterface.getDetails(movie_id).enqueue(new Callback<DetailsModel>() {
            @Override
            public void onResponse(Call<DetailsModel> call, Response<DetailsModel> response) {
                if (response.isSuccessful()) {
                    detailsLay.setVisibility(View.VISIBLE);
                    Picasso.get().load("https://image.tmdb.org/t/p/original/" + response.body().getBackdropPath()).into(poster);
                    titileTv.setText(response.body().getTitle());
                    reDateTv.setText("Release : " + response.body().getReleaseDate());
                    popularityTv.setText("Popularity : " + response.body().getPopularity());
                    voteTv.setText("Total Vote : " + response.body().getVoteCount());
                    ratingTv.setText("Rating : " + response.body().getVoteAverage());
                    overViewTv.setText(response.body().getOverview());
                    statusTv.setText("Status : " + response.body().getStatus());
                    budgetTv.setText("Budget : " + response.body().getBudget());
                    oTitleTv.setText("Original Title : " + response.body().getOriginalTitle());
                    oLanguageTv.setText("Original Language : " + response.body().getOriginalLanguage());
                    runTimeTv.setText("Runtime : " + response.body().getRuntime());
                    setAdultTv(adultTv, response.body().getAdult());
                }
            }

            @Override
            public void onFailure(Call<DetailsModel> call, Throwable t) {
                Log.d("ERROR", t.getMessage().toString());
            }
        });
    }

    private void setAdultTv(TextView adultTv, Boolean adult) {
        if (adult) {
            adultTv.setText("Adult : Yes");
        } else {
            adultTv.setText("Adult : No");
        }
    }

    private void initial() {
        poster = findViewById(R.id.poster);
        backBtn = findViewById(R.id.backBtn);
        detailsLay = findViewById(R.id.detailsLay);
        apiInterface = RetrofitInit.getRetro().create(ApiInterface.class);
        Intent intent = getIntent();
        movie_id = intent.getStringExtra("movie_id");
        titileTv = findViewById(R.id.titileTv);
        reDateTv = findViewById(R.id.reDateTv);
        popularityTv = findViewById(R.id.popularityTv);
        voteTv = findViewById(R.id.voteTv);
        ratingTv = findViewById(R.id.ratingTv);
        overViewTv = findViewById(R.id.overViewTv);
        statusTv = findViewById(R.id.statusTv);
        budgetTv = findViewById(R.id.budgetTv);
        oTitleTv = findViewById(R.id.oTitleTv);
        oLanguageTv = findViewById(R.id.oLanguageTv);
        runTimeTv = findViewById(R.id.runTimeTv);
        adultTv = findViewById(R.id.adultTv);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}