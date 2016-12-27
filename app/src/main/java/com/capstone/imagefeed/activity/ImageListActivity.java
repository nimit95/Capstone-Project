package com.capstone.imagefeed.activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.capstone.imagefeed.R;
import com.capstone.imagefeed.adapter.ImageAdapter;
import com.capstone.imagefeed.models.ImageList;
import com.capstone.imagefeed.network.GetResponse;
import com.capstone.imagefeed.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

public class ImageListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        recyclerView.setHasFixedSize(true);
        new ShowImageList().execute();
    }
    private class ShowImageList extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... searchquery) {
            GetResponse getResponse = new GetResponse();
            try {
                return getResponse.run(Constants.apiBaseUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.e("yo", s );
            Gson gson = new GsonBuilder().create();
            ImageList imageList = gson.fromJson(s,ImageList.class);
            Log.e("inj", imageList.getHits().get(0).getPreviewURL());
            recyclerView.setAdapter(new ImageAdapter(getApplicationContext(),imageList.getHits()));
        }
    }
}
