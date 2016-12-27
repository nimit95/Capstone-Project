package com.capstone.imagefeed.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nimit Agg on 27-12-2016.
 */

public class Image implements Parcelable{
    private int downloads;
    private String previewURL;
    private String webformatURL;

    private long id;

    protected Image(Parcel in) {
        downloads = in.readInt();
        previewURL = in.readString();
        webformatURL = in.readString();
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
}
