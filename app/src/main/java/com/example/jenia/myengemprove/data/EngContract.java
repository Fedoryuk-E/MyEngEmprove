package com.example.jenia.myengemprove.data;

import android.provider.BaseColumns;

/**
 * Created by Jenia on 22.01.2017.
 */

public  final class EngContract  {
    private EngContract(){
    };
    public static final class NewWord implements BaseColumns {
        public final static String TABLE_NAME = "words";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_WORD = "word";
        public final static String COLUMN_TRANSLATION = "translation";
        public final static String COLUMN_GUESS = "guess";
        public final static String COLUMN_COUNT = "count";
        public final static String COLUMN_UNIT = "unit";

    }


}
