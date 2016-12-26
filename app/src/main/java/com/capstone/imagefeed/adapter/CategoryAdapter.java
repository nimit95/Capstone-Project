package com.capstone.imagefeed.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.capstone.imagefeed.R;
import com.capstone.imagefeed.activity.ImageListActivity;

/**
 * Created by Nimit Agg on 27-12-2016.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private String[] name,imageids;
    private Context context;
    public CategoryAdapter(Context context, String[] name,String[] imageids){
        this.context = context ;
        this.name = name;
        this.imageids = imageids;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_card_view,parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewholder, int position) {
        viewholder.headline.setText(name[position]);
      //  Glide.with(context)
        //        .load(imageids[position])
          //      .into(viewholder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return name.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView headline;
        ImageView thumbnail;
        ViewHolder(View itemView) {
            super(itemView);
            headline = (TextView) itemView.findViewById(R.id.name);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context, ImageListActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                }
            });
        }
    }
}
