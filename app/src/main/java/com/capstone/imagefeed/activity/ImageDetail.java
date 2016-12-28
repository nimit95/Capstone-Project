package com.capstone.imagefeed.activity;

import android.app.DownloadManager;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
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
import com.capstone.imagefeed.utils.Utility;

import java.util.concurrent.ExecutionException;

import static android.R.drawable.btn_star_big_off;
import static android.R.drawable.btn_star_big_on;

public class ImageDetail extends AppCompatActivity {
    private Image imageDetail;
    private ImageView image;
    private FloatingActionButton addFavorite,download,share;
    private boolean favState;
    final Bitmap[] bitmap = new Bitmap[1];
    private String path;
    private DownloadManager downloadManager;
    private DownloadManager.Request request;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        Intent intent = getIntent();
        imageDetail = intent.getParcelableExtra("image_detail");
        image = (ImageView) findViewById(R.id.image);
        addFavorite = (FloatingActionButton) findViewById(R.id.favorite);
        download = (FloatingActionButton) findViewById(R.id.download);
        share = (FloatingActionButton) findViewById(R.id.share);
        downloadManager = (DownloadManager) getSystemService(getApplicationContext().DOWNLOAD_SERVICE);
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
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(imageDetail.getPreviewURL());
                request = new DownloadManager.Request(uri);
                request.setVisibleInDownloadsUi(true);
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.allowScanningByMediaScanner();
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,uri.getLastPathSegment());
               if(Utility.isStoragePermissionGranted(ImageDetail.this))
                    downloadManager.enqueue(request);
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                   // Drawable drawable = image.getDrawable();

                    Thread t = new Thread(){
                        @Override
                        public void run() {
                            try {
                                bitmap[0] = Glide.with(getApplicationContext())
                                        .load(imageDetail.getWebformatURL())
                                        .asBitmap()
                                        .into(500,500)
                                        .get();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    t.start();
                    if(Utility.isStoragePermissionGranted2(ImageDetail.this)) {
                        path = MediaStore.Images.Media.insertImage(getContentResolver(),
                                bitmap[0], "Image Description", null);

                        Uri uri = Uri.parse(path);


                        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                        sharingIntent.setType("image/*");
                        String shareBody = "Here is the share content body";
                        sharingIntent.putExtra(android.content.Intent.EXTRA_STREAM, uri);
                        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.subj) + "\n\n");
                        //  sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, article.getDescription()
                        //        );
                        //if(Utility.isStoragePermissionGranted(ImageDetail.this))
                        startActivity(Intent.createChooser(sharingIntent, "Share via").addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                    }
                }catch (Exception e){
                    Log.e("Error in sharing image",e.getMessage().toString()+" ");
                }
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED ){
            Log.v("permission","Permission: "+permissions[0]+ "was "+grantResults[0]);
            if(requestCode==1)
                downloadManager.enqueue(request);
            else
                path = MediaStore.Images.Media.insertImage(getContentResolver(),
                        bitmap[0], "Image Description", null);
            //resume tasks needing this permission
        }
        if(grantResults[0]== PackageManager.PERMISSION_DENIED){
            Snackbar.make(findViewById(R.id.activity_image_detail),getString(R.string.permission),Snackbar.LENGTH_SHORT).show();
        }
    }
}
