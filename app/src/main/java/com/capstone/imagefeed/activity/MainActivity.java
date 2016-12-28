package com.capstone.imagefeed.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.bumptech.glide.util.Util;
import com.capstone.imagefeed.R;
import com.capstone.imagefeed.adapter.CategoryAdapter;
import com.capstone.imagefeed.utils.Utility;
import com.google.android.gms.appinvite.AppInviteInvitation;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private String[] name,queryName;
    private int[] imageids;
    private String TAG="MainActivity";
    public static final int REQUEST_INVITE=420;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name= Utility.getCategory(getApplicationContext());
        queryName = Utility.getSearchQuery(getApplicationContext());
        imageids = Utility.getImagethumbIds(getApplicationContext());

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new CategoryAdapter(getApplicationContext(),name,imageids,queryName));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.favorite:
                startActivity(new Intent(MainActivity.this,FavoriteActivity.class));
                return true;
            case R.id.share:
                onInviteClicked();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void onInviteClicked() {
        Intent intent = new AppInviteInvitation.IntentBuilder(getString(R.string.invitation_title))
                .setMessage(getString(R.string.invitation_message))
                .build();
        startActivityForResult(intent, REQUEST_INVITE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: requestCode=" + requestCode + ", resultCode=" + resultCode);

        if (requestCode == REQUEST_INVITE) {
            if (resultCode == RESULT_OK) {
                // Get the invitation IDs of all sent messages
                String[] ids = AppInviteInvitation.getInvitationIds(resultCode, data);
                for (String id : ids) {
                    Log.d(TAG, "onActivityResult: sent invitation " + id);
                }
            } else {
                // Sending failed or it was canceled, show failure message to the user
                // ...
            }
        }
    }
}
