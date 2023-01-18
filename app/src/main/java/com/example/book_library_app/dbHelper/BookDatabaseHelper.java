package com.example.book_library_app.dbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BookDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "book_library.db";
    private static final int DATABASE_VERSION = 1;
    // ATTRIBUTES OF TABLE
    private static final String TABLE_NAME = "library_tbl";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_BOOK_TITLES = "book_titles";
    private static final String COLUMN_BOOK_AUTHORS = "book_authors";
    private static final String COLUMN_BOOK_PAGES = "book_pages";

    private Context mContext;

    public BookDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ( " +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_BOOK_TITLES + " TEXT, " +
                COLUMN_BOOK_AUTHORS + " TEXT, " +
                COLUMN_BOOK_PAGES + " INTEGER);";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
