package com.capstone.imagefeed.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.capstone.imagefeed.R;
import com.capstone.imagefeed.activity.ImageDetail;
import com.capstone.imagefeed.models.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nimit Agg on 28-12-2016.
 */

public class MyListCursorAdapter extends CursorRecyclerViewAdapter<MyListCursorAdapter.ViewHolder> {

    Context context;
    List<Image> imageList;

    public MyListCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor);
        this.context = context;
        imageList = new ArrayList<Image>();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;

        public ViewHolder(View view) {
            super(view);
            image = (ImageView) itemView.findViewById(R.id.preview_image);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ImageDetail.class)
                            .putExtra("image_detail", imageList.get(getAdapterPosition())).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_list_view, parent, false);
        ViewHolder vh = new ViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Cursor cursor) {
        Image imageDetail = Image.fromCursor(cursor);
        imageList.add(imageDetail);
        Glide.with(context)
                .load(imageDetail.getPreviewURL())
                .into(viewHolder.image);
    }
}