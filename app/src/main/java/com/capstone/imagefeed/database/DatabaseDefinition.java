package com.capstone.imagefeed.database;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.Table;

/**
 * Created by Nimit Agg on 27-12-2016.
 */
@Database(version = DatabaseDefinition.VERSION)
public class DatabaseDefinition {
    public static final int VERSION = 1;

    @Table(ListColumns.class)
    public static final String LISTS = "lists";

}
