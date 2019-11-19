package com.example.pagingexample.ProjectInterface;

import com.example.pagingexample.Model.ResponsePhotosModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("services/rest/?method=flickr.photos.getRecent")
    Call<ResponsePhotosModel> getPhotos(@Query("api_key") String api_key
            ,@Query("per_page") int per_page,@Query("page") int page,@Query("format") String format
            ,@Query("nojsoncallback") int nojsoncallback);
}

