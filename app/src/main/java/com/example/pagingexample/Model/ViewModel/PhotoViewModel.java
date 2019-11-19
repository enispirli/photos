package com.example.pagingexample.Model.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.example.pagingexample.DataSource.PhotoDataSource;
import com.example.pagingexample.DataSource.PhotoDataSourceFactory;
import com.example.pagingexample.Model.Photo;

public class PhotoViewModel extends ViewModel {
    public LiveData<PagedList<Photo>> photoPagedList;
    public LiveData<PageKeyedDataSource<Integer,Photo>> liveDataSource;

    public PhotoViewModel(){
        PhotoDataSourceFactory photoDataSourceFactory=new PhotoDataSourceFactory();
        liveDataSource=photoDataSourceFactory.getPhotoLiveDataSource();

        PagedList.Config config=(new PagedList.Config.Builder())
                .setEnablePlaceholders(false)
                .setPageSize(PhotoDataSource.PAGE_SIZE)
                .build();
        photoPagedList=(new LivePagedListBuilder(photoDataSourceFactory,config)).build();
    }
}
