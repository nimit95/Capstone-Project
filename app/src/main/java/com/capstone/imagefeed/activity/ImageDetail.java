package com.capstone.imagefeed.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.capstone.imagefeed.R;
import com.capstone.imagefeed.database.ImageProvider;
import com.capstone.imagefeed.database.ListColumns;
import com.capstone.imagefeed.models.Image;

import static android.R.drawable.btn_star_big_off;
import static android.R.drawable.btn_star_big_on;

public class ImageDetail extends AppCompatActivity {
    private Image imageDetail;
    private ImageView image;
    private FloatingActionButton addFavorite;
    private boolean favState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        Intent intent = getIntent();
        imageDetail = intent.getParcelableExtra("image_detail");
        image = (ImageView) findViewById(R.id.image);
        addFavorite = (FloatingActionButton) findViewById(R.id.favorite);
        if(getContentResolver().query(ImageProvider.Lists.LISTS,
                new String[]{"id"},
                ListColumns.ID+"=?", new String[]{String.valueOf(imageDetail.getId())}, null).getCount() == 0) {
            addFavorite.setImageResource(btn_star_big_off);
            favState = false;
        }
        else {
            addFavorite.setImageResource(btn_star_big_on);
            favState = true;
        }
     //   Log.e("jn",imageDetail.getId()+"");
        Glide.with(getApplicationContext())
                .load(imageDetail.getWebformatURL())
                .into(image);
        addFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!favState) {
                    addFavorite.setImageResource(btn_star_big_on);
                    Snackbar.make(findViewById(R.id.activity_image_detail),getString(R.string.add_fav),Snackbar.LENGTH_SHORT).show();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("id", imageDetail.getId());
                    contentValues.put("downloads", imageDetail.getDownloads());
                    contentValues.put("previewurl", imageDetail.getPreviewURL());
                    contentValues.put("webformaturl", imageDetail.getWebformatURL());
                    getContentResolver().insert(ImageProvider.Lists.LISTS, contentValues);
                    Cursor cursor = getContentResolver().query(ImageProvider.Lists.LISTS, null, null,null,null);
                    Log.e("jnvdv",cursor.getCount()+"");
                    favState = true;
                }
                else{
                    favState = false;
                    addFavorite.setImageResource(btn_star_big_off);
                    getContentResolver().delete(ImageProvider.Lists.LISTS, ListColumns.ID + " = " + imageDetail.getId(),null);
                    Cursor cursor = getContentResolver().query(ImageProvider.Lists.LISTS, null, null,null,null);
                    Log.e("jnvdv",cursor.getCount()+"");
                }
            }
        });
    }
}
