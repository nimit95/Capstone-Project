package com.capstone.imagefeed.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.capstone.imagefeed.R;
import com.capstone.imagefeed.adapter.CategoryAdapter;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private String[] name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name= new String[]{getString(R.string.latest), getString(R.string.newyear), getString(R.string.birthday)};

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new CategoryAdapter(getApplicationContext(),name,name));
    }
}
