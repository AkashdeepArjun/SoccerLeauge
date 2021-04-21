package com.akkayameva.soccerLeauge.model.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class TeamAPIService {
    public static final String BASE_URL = "https://raw.githubusercontent.com/";


    private static Retrofit retrofit = null;

    public static Retrofit getClient() {


        if (retrofit == null) {

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient oktHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            retrofit = new Retrofit.Builder()

                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .client(oktHttpClient)
                    .build();
        }
        return retrofit;
    }

    public static TeamAPI api = getClient().create(TeamAPI.class);



}