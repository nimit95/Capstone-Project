package com.capstone.imagefeed.models;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.capstone.imagefeed.database.ListColumns;

/**
 * Created by Nimit Agg on 27-12-2016.
 */

public class Image implements Parcelable{
    private int downloads;

    private String previewURL;
    private String webformatURL;
    private long id;
    public  Image(){}
    protected Image(Parcel in) {
        downloads = in.readInt();
        previewURL = in.readString();
        webformatURL = in.readString();
        id = in.readLong();
    }

    public void setDownloads(int downloads) {
        this.downloads = downloads;
    }

    public void setPreviewURL(String previewURL) {
        this.previewURL = previewURL;
    }

    public void setWebformatURL(String webformatURL) {
        this.webformatURL = webformatURL;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDownloads() {
        return downloads;
    }

    public String getPreviewURL() {
        return previewURL;
    }

    public String getWebformatURL() {
        return webformatURL;
    }

    public long getId() {
        return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(downloads);
        parcel.writeString(previewURL);
        parcel.writeString(webformatURL);
        parcel.writeLong(id);
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };

    public static Image fromCursor(Cursor cursor) {
        Image image = new Image();
        image.setId(cursor.getLong(cursor.getColumnIndex(ListColumns.ID)));
        image.setDownloads(cursor.getInt(cursor.getColumnIndex(ListColumns.Downloads)));
        image.setPreviewURL(cursor.getString(cursor.getColumnIndex(ListColumns.PreviewUrl)));
        image.setWebformatURL(cursor.getString(cursor.getColumnIndex(ListColumns.WebformatUrl)));
        return image;
    }
}
