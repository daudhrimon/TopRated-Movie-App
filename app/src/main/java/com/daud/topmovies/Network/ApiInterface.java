package com.daud.topmovies.Network;

import com.daud.topmovies.Model.DetailsModel;
import com.daud.topmovies.Model.MovieModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("movie/top_rated?api_key=c37d3b40004717511adb2c1fbb15eda4&language=en-US")
    Call<MovieModel> getData();

    @GET("movie/{movie_id}?api_key=c37d3b40004717511adb2c1fbb15eda4&language=en-US")
    Call<DetailsModel> getDetails(@Path("movie_id") String movie_id);
}
