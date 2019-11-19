package com.example.pagingexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.pagingexample.Model.Photo;
import com.squareup.picasso.Picasso;

public class PhotoActivity extends AppCompatActivity {
   private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        imageView=findViewById(R.id.imgPhotoBig);

        Photo photo= getIntent().getExtras().getParcelable("photo");
        Picasso.get().load("https://farm" +photo.getFarm() +".staticflickr.com/" + photo.getServer() +"/" + photo.getId() +"_"+photo.getSecret() +".jpg").into(imageView);
    }
}
