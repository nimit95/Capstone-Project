package com.capstone.imagefeed.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.capstone.imagefeed.R;
import com.capstone.imagefeed.activity.ImageDetail;
import com.capstone.imagefeed.models.Image;

import java.util.List;

/**
 * Created by Nimit Agg on 27-12-2016.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder>{

    private List<Image> imageData;
    private Context context;

    public ImageAdapter(Context context, List<Image> imageData) {
        this.context = context;
        this.imageData = imageData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_list_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context)
                .load(imageData.get(position).getPreviewURL())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return imageData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView image;
        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.preview_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                 //   Log.e("jn",imageData.get(getAdapterPosition()).getId() + "");
                    Intent intent = new Intent(context, ImageDetail.class)
                            .putExtra("image_detail",imageData.get(getAdapterPosition())).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }
}
