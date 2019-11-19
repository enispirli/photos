package com.example.pagingexample.DataSource;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;


import com.example.pagingexample.Model.Photo;

public class PhotoDataSourceFactory extends DataSource.Factory {
    private MutableLiveData<PageKeyedDataSource<Integer, Photo>> photoLiveDataSource= new MutableLiveData<>();
    @Override
    public DataSource create() {
        PhotoDataSource photoDataSource=new PhotoDataSource();
        photoLiveDataSource.postValue(photoDataSource);
        return photoDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Photo>> getPhotoLiveDataSource() {
        return photoLiveDataSource;
    }
}
