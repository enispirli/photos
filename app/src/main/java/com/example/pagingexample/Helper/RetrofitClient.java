package com.example.pagingexample.Helper;

import com.example.pagingexample.ProjectInterface.Api;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL="https://www.flickr.com/";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;
    public OkHttpClient.Builder httpClient=new OkHttpClient.Builder();

    private RetrofitClient(){
        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(initiate().build())
                .build();
    }

    private OkHttpClient.Builder initiate() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(logging);
        httpClient.addInterceptor((chain) -> {
            Request request = chain.request().newBuilder()
                    .build();
            return chain.proceed(request);
        });
        return httpClient;
    }

    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    public static synchronized RetrofitClient getInstance(){
        if(mInstance==null){
            mInstance=new RetrofitClient();
        }
        return mInstance;
    }

    public Api getApi(){
        return retrofit.create(Api.class);
    }



}
