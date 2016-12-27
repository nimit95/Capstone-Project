package com.capstone.imagefeed.network;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Nimit Agg on 26-12-2016.
 */

public class GetResponse {
    private OkHttpClient client=new OkHttpClient();
    public String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        if(response.isSuccessful())
            return response.body().string();
        else
            return null ;
    }

}
