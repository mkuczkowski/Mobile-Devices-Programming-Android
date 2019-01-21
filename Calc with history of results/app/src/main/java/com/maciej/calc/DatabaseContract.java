package com.maciej.calc;

import android.provider.BaseColumns;

public final class DatabaseContract {

    private DatabaseContract() {}

    public static class HistoryDatabase implements BaseColumns {
        public static final String TABLE_NAME = "history_table";
        public static final String COLUMN_1 = "expression";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + HistoryDatabase.TABLE_NAME + " (" +
                        HistoryDatabase._ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                        HistoryDatabase.COLUMN_1 + " TEXT)";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + HistoryDatabase.TABLE_NAME;
    }
}
