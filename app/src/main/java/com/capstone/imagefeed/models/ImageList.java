package com.capstone.imagefeed.models;



import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nimit Agg on 27-12-2016.
 */

public class ImageList {
    private List<Image> hits;
    public ImageList() {
        hits = new ArrayList<Image>();
    }
    public List<Image> getHits() {
        return hits;
    }
}
