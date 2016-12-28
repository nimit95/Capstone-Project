package com.capstone.imagefeed.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.capstone.imagefeed.R;
import com.capstone.imagefeed.utils.Utility;

/**
 * Created by Nimit Agg on 28-12-2016.
 */



public class ItemWidgetList extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new OtherWidgetList(this.getApplicationContext(), intent);
    }
}

class OtherWidgetList implements RemoteViewsService.RemoteViewsFactory {
    String[] name;
    private Context context = null;
    private int appWidgetId;
    RemoteViews row;

    public OtherWidgetList(Context context, Intent intent) {

        this.context = context;
        appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
    }

    @Override
    public void onCreate() {
        Log.e("Item_Widget_Oncreate", "is running");
    }

    @Override
    public void onDataSetChanged() {
        Log.e("Item_Widget_onDataSEt", "is running");
        initData();
    }

    private void initData() {
        name = Utility.getCategory(context);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return name.length;
    }

    @Override
    public RemoteViews getViewAt(int position) {

        Log.e("Item_Widget_getview", "is running");
        String url = "";
        String title = "";
        title = name[position];
        Log.e("getViewAt: ", "hi " + title);
        row = new RemoteViews(context.getPackageName(), R.layout.list_category);
        //Picasso.with(context).load(url).into(row,R.id.thumbnail,appWidgetId[position]);
        row.setTextViewText(R.id.heading, title);
        //Picasso.with(context).load(url).into(row,R.id.thumbnail,new int[]{appWidgetId});
        /*
        try {
            Bitmap b=Picasso.with(context).load(url).get();
            row.setImageViewBitmap(R.id.thumbnail,b);
        } catch (IOException e) {
            e.printStackTrace();
        }
*/

        return row;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}