package com.example.pagingexample.DataSource;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;


import com.example.pagingexample.Helper.RetrofitClient;
import com.example.pagingexample.Model.Photo;
import com.example.pagingexample.Model.ResponsePhotosModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoDataSource extends PageKeyedDataSource<Integer, Photo> {
    public static final String API_KEY="3d0d253d472b7fa40bfe8fc6ac9ea0e8";
    public static final int PER_PAGE=20;
    public static final int FIRST_PAGE=1;
    public static final String FORMAT="json";
    public static final int NOJSON_CALLBACK=1;
    public static final int PAGE_SIZE=50;


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Photo> callback) {
        System.out.println("Atom "+ params.requestedLoadSize);
        RetrofitClient.getInstance()
                .getApi()
                .getPhotos(API_KEY,PER_PAGE,FIRST_PAGE,FORMAT,NOJSON_CALLBACK)
                .enqueue(new Callback<ResponsePhotosModel>() {
                    @Override
                    public void onResponse(Call<ResponsePhotosModel> call, Response<ResponsePhotosModel> response) {
                        if(response.body()!=null){
                            callback.onResult(response.body().getPhotos().getPhoto(),null,FIRST_PAGE +1);

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsePhotosModel> call, Throwable t) {

                    }
                });

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Photo> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Photo> callback) {
        System.out.println("Atom2 "+ params.requestedLoadSize + " " +params.key);
        RetrofitClient.getInstance()
                .getApi()
                .getPhotos(API_KEY,PER_PAGE,params.key,FORMAT,NOJSON_CALLBACK)
                .enqueue(new Callback<ResponsePhotosModel>() {
                    @Override
                    public void onResponse(Call<ResponsePhotosModel> call, Response<ResponsePhotosModel> response) {
                        if(response.body()!=null){
                            Integer key=response.body().getPhotos().getPages()!=params.key? params.key+1:null;
                            callback.onResult(response.body().getPhotos().getPhoto(),key);

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsePhotosModel> call, Throwable t) {

                    }
                });
    }
}
