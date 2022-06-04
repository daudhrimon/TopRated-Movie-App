package com.daud.topmovies.Network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInit {
    public static String BASE_URL = "https://api.themoviedb.org/3/";
    public static Gson gson = new GsonBuilder().setLenient().create();

    public static Retrofit getRetro() {
        return new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
    }
}
