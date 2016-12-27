package com.capstone.imagefeed.database;

import android.net.Uri;

import net.simonvt.schematic.annotation.ContentProvider;
import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.TableEndpoint;

/**
 * Created by Nimit Agg on 27-12-2016.
 */

@ContentProvider(authority = ImageProvider.AUTHORITY, database = DatabaseDefinition.class)
public final class ImageProvider {

    public static final String AUTHORITY = "com.capstone.imagefeed.database.ImageProvider";

    @TableEndpoint(table = DatabaseDefinition.LISTS)
    public static class Lists {

        @ContentUri(
                path = "lists",
                type = "vnd.android.cursor.dir/list",
                defaultSort = ListColumns.ID + " ASC")
        public static final Uri LISTS = Uri.parse("content://" + AUTHORITY + "/lists");
    }
}