package com.capstone.imagefeed.database;

import net.simonvt.schematic.annotation.AutoIncrement;
import net.simonvt.schematic.annotation.DataType;
import net.simonvt.schematic.annotation.NotNull;
import net.simonvt.schematic.annotation.PrimaryKey;

import static net.simonvt.schematic.annotation.DataType.Type.*;

/**
 * Created by Nimit Agg on 27-12-2016.
 */
public interface ListColumns {
    @DataType(INTEGER)
    @PrimaryKey
    @AutoIncrement
    String _ID = "_id";

    @DataType(INTEGER)
    String ID = "id";

    @DataType(INTEGER)
    @NotNull
    String Downloads = "downloads";

    @DataType(TEXT)
    @NotNull
    String PreviewUrl = "previewurl";

    @DataType(TEXT)
    @NotNull
    String WebformatUrl = "webformaturl";
}
