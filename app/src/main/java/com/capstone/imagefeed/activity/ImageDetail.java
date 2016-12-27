package com.capstone.imagefeed.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.capstone.imagefeed.R;
import com.capstone.imagefeed.models.Image;

public class ImageDetail extends AppCompatActivity {
    private Image imageDetail;
    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        Intent intent = getIntent();
        imageDetail = intent.getParcelableExtra("image_detail");
        image = (ImageView) findViewById(R.id.image);
        Glide.with(getApplicationContext())
                .load(imageDetail.getWebformatURL())
                .into(image);

    }
}
