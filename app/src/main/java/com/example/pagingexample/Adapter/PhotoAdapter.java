package com.example.pagingexample.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pagingexample.Model.Photo;
import com.example.pagingexample.R;
import com.squareup.picasso.Picasso;

public class PhotoAdapter extends PagedListAdapter<Photo,PhotoAdapter.PhotoViewHolder> {
    private OnPhotoListener mOnPhotoListener;
    private Context context;
    public PhotoAdapter(Context context,OnPhotoListener onPhotoListener) {
        super(DIFF_CALLBACK);
        this.context=context;
        this.mOnPhotoListener=onPhotoListener;
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(R.layout.photos_item,parent,false);
       return new PhotoViewHolder(view,mOnPhotoListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
      Photo photo=getItem(position);
      if(photo!=null){
          Picasso.get().load("https://farm" +photo.getFarm() +".staticflickr.com/" + photo.getServer() +"/" + photo.getId() +"_"+photo.getSecret() +".jpg").into(holder.imgPhoto);
      }
      else{
          Toast.makeText(context, "don't show data", Toast.LENGTH_SHORT).show();
      }
    }

    private  static DiffUtil.ItemCallback<Photo> DIFF_CALLBACK= new DiffUtil.ItemCallback<Photo>() {
        @Override
        public boolean areItemsTheSame(@NonNull Photo oldItem, @NonNull Photo newItem) {
            return oldItem.getId().equals(newItem.getId());
        }



        @Override
        public boolean areContentsTheSame(@NonNull Photo oldItem, @NonNull Photo newItem) {
            return oldItem.getId().equals(newItem.getId());
        }
    };

    class PhotoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imgPhoto;
        OnPhotoListener onPhotoListener;
        public PhotoViewHolder(@NonNull View itemView,OnPhotoListener onPhotoListener) {
            super(itemView);
            imgPhoto=itemView.findViewById(R.id.imgPhoto);
            this.onPhotoListener=onPhotoListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
         onPhotoListener.onPhotoClick(getAdapterPosition());
        }
    }
public interface OnPhotoListener{
        void onPhotoClick(int position);
}

}
