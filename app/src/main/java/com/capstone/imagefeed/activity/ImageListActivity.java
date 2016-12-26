package com.capstone.imagefeed.activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.capstone.imagefeed.R;
import com.capstone.imagefeed.network.GetResponse;
import com.capstone.imagefeed.utils.Constants;

import java.io.IOException;

public class ImageListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);

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
        }
    }
}
