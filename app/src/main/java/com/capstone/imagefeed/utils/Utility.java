package com.capstone.imagefeed.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.util.DisplayMetrics;
import android.util.Log;

import com.capstone.imagefeed.R;

public class Utility {
    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / 180);
        return noOfColumns;
    }

    public static boolean isStoragePermissionGranted(Activity activity) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (activity.checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("permission", "Permission is granted");
                return true;
            } else {

                Log.v("permission", "Permission is revoked");
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v("permision", "Permission is granted");
            return true;
        }
    }

    public static boolean isStoragePermissionGranted2(Activity activity) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (activity.checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("permission", "Permission is granted");
                return true;
            } else {

                Log.v("permission", "Permission is revoked");
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v("permision", "Permission is granted");
            return true;
        }
    }

    public static String[] getCategory(Context context) {
        return new String[]{context.getString(R.string.latest),
                context.getString(R.string.newyear),
                context.getString(R.string.birthday),
                context.getString(R.string.aniversary),
                context.getString(R.string.holi),
                context.getString(R.string.custom)};
    }

    public static String[] getSearchQuery(Context context) {
        return new String[]{"Latest",
                "New Year",
                "Birthday",
                "Anniversary",
                "Holi",
                ""};
    }

    public static int[] getImagethumbIds(Context context) {
        return new int[]{R.drawable.latest,
                R.drawable.newyear,
                R.drawable.birthday,
                R.drawable.anniversary,
                R.drawable.holi};
    }
}