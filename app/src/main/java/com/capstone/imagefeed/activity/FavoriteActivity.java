package com.capstone.imagefeed.activity;


import android.database.Cursor;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.capstone.imagefeed.R;
import com.capstone.imagefeed.adapter.MyListCursorAdapter;
import com.capstone.imagefeed.database.ImageProvider;

public class FavoriteActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private RecyclerView recyclerView;
    private MyListCursorAdapter myListCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        getSupportLoaderManager().initLoader(1, null, this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader cursorLoader = new CursorLoader(getApplicationContext(), ImageProvider.Lists.LISTS, null, null, null, null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        myListCursorAdapter = new MyListCursorAdapter(getApplicationContext(), data);
        recyclerView.setAdapter(myListCursorAdapter);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        myListCursorAdapter.swapCursor(null);
    }
}
