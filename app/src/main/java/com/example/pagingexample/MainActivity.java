package com.example.pagingexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;


import com.example.pagingexample.Adapter.PhotoAdapter;
import com.example.pagingexample.Model.Photo;
import com.example.pagingexample.Model.ViewModel.PhotoViewModel;

public class MainActivity extends AppCompatActivity implements PhotoAdapter.OnPhotoListener {

    private RecyclerView recyclerView;
    PhotoViewModel photoViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     recyclerView=findViewById(R.id.rcPhotos);
     recyclerView.setLayoutManager(new LinearLayoutManager(this));
     recyclerView.setLayoutManager(new GridLayoutManager(this,3));
     recyclerView.setHasFixedSize(true);


        photoViewModel= ViewModelProviders.of(this).get(PhotoViewModel.class);
        final PhotoAdapter adapter=new PhotoAdapter(this,this);


        photoViewModel.photoPagedList.observe(this, new Observer<PagedList<Photo>>() {
            @Override
            public void onChanged(PagedList<Photo> photos) {
                adapter.submitList(photos);
            }
        });
        recyclerView.setAdapter(adapter);

    }


    @Override
    public void onPhotoClick(int position) {
      Photo photo=photoViewModel.photoPagedList.getValue().get(position);
        startActivity(new Intent(this,PhotoActivity.class).putExtra("photo",photo));
    }
}
