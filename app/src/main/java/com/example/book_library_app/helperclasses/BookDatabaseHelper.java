package com.example.book_library_app.helperclasses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class BookDatabaseHelper {

    static final String DATABASE_NAME = "book_library.db";
    static final int DATABASE_VERSION = 1;

    // ATTRIBUTES OF TABLE
    static final String TABLE_NAME = "library_tbl";
    static final String COLUMN_ID = "_id";
    static final String COLUMN_BOOK_TITLES = "book_titles";
    static final String COLUMN_BOOK_AUTHORS = "book_authors";
    static final String COLUMN_BOOK_PAGES = "book_pages";

}
