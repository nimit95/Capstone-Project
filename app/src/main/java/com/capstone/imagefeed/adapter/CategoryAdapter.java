package com.capstone.imagefeed.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.capstone.imagefeed.R;
import com.capstone.imagefeed.activity.CustomSearchActivity;
import com.capstone.imagefeed.activity.ImageListActivity;
import com.google.firebase.analytics.FirebaseAnalytics;

/**
 * Created by Nimit Agg on 27-12-2016.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private FirebaseAnalytics mFirebaseAnalytics;
    private String[] name, queryName;
    private int[] thumbnailImageId;
    private Context context;

    public CategoryAdapter(Context context, String[] name, int[] thumbnailImageId, String[] queryName) {
        this.context = context;
        this.name = name;
        this.thumbnailImageId = thumbnailImageId;
        this.queryName = queryName;
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_card_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewholder, int position) {
        viewholder.headline.setText(name[position]);
        Glide.with(context)
                .load(thumbnailImageId[position])
                .into(viewholder.thumbnail);
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
                    Bundle bundle = new Bundle();
                    bundle.putString(FirebaseAnalytics.Param.ITEM_ID, getAdapterPosition() + "");
                    bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name[getAdapterPosition()]);
                    bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
                    mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
                    if (getAdapterPosition() != getItemCount() - 1)
                        context.startActivity(new Intent(context, ImageListActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                .putExtra("category", queryName[getAdapterPosition()]));
                    else
                        context.startActivity(new Intent(context, CustomSearchActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                }
            });
        }
    }
}
