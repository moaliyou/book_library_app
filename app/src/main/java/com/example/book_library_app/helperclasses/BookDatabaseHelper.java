package com.example.book_library_app.helperclasses;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class BookDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "book_library.db";
    private static final int DATABASE_VERSION = 1;
    // ATTRIBUTES OF TABLE
    private static final String TABLE_NAME = "library_tbl";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_BOOK_TITLES = "book_titles";
    private static final String COLUMN_BOOK_AUTHORS = "book_authors";
    private static final String COLUMN_BOOK_PAGES = "book_pages";

    private final Context mContext;

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

    public void registerNewBook(String bookTitle, String bookAuthor, int bookPage) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues mContentValues = new ContentValues();

        mContentValues.put(COLUMN_BOOK_TITLES, bookTitle);
        mContentValues.put(COLUMN_BOOK_AUTHORS, bookAuthor);
        mContentValues.put(COLUMN_BOOK_PAGES, bookPage);

        long codeResult = db.insert(TABLE_NAME, null, mContentValues);

        if (!(codeResult == -1)) {
            Toast.makeText(mContext, "Added new book successfully", Toast.LENGTH_SHORT).show();
            return;
        }

        MyHelperClass.showInfoMessage(
                mContext,
                "Warning",
                "Failed to add new book :(",
                "Try again"
        );
    }

    public Cursor readBookData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = null;

        if (db != null) {
            mCursor = db.rawQuery(query, null);
        }

        return mCursor;
    }

}
