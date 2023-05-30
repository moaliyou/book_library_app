package com.example.book_library_app.helperclasses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class BookDatabaseClass extends SQLiteOpenHelper {

    private final Context mContext;

    public BookDatabaseClass(@Nullable Context context) {
        super(context, BookDatabaseHelper.DATABASE_NAME, null, BookDatabaseHelper.DATABASE_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + BookDatabaseHelper.TABLE_NAME + " ( " +
                BookDatabaseHelper.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                BookDatabaseHelper.COLUMN_BOOK_TITLES + " TEXT, " +
                BookDatabaseHelper.COLUMN_BOOK_AUTHORS + " TEXT, " +
                BookDatabaseHelper.COLUMN_BOOK_PAGES + " INTEGER);";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BookDatabaseHelper.TABLE_NAME);
        onCreate(db);
    }

    public void registerNewBook(String bookTitle, String bookAuthor, int bookPage) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues mContentValues = new ContentValues();

        mContentValues.put(BookDatabaseHelper.COLUMN_BOOK_TITLES, bookTitle);
        mContentValues.put(BookDatabaseHelper.COLUMN_BOOK_AUTHORS, bookAuthor);
        mContentValues.put(BookDatabaseHelper.COLUMN_BOOK_PAGES, bookPage);

        long codeResult = db.insert(
                BookDatabaseHelper.TABLE_NAME, null, mContentValues
        );

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
        String query = "SELECT * FROM " + BookDatabaseHelper.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = null;

        if (db != null) {
            mCursor = db.rawQuery(query, null);
        }

        return mCursor;
    }

    public void editBookData(String bookId, String bookTitle, String bookAuthor, String bookPages) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(BookDatabaseHelper.COLUMN_BOOK_TITLES, bookTitle);
        contentValues.put(BookDatabaseHelper.COLUMN_BOOK_AUTHORS, bookAuthor);
        contentValues.put(BookDatabaseHelper.COLUMN_BOOK_PAGES, bookPages);

        long resultCode = database.update(
                BookDatabaseHelper.TABLE_NAME, contentValues,
                "_id=?", new String[]{bookId}
        );

        if (!(resultCode == -1)) {
            Toast.makeText(mContext, "Updated successfully", Toast.LENGTH_SHORT).show();
            return;
        }

        MyHelperClass.showInfoMessage(
                mContext,
                "Warning",
                "Failed to save changes. Something went wrong :(",
                "Try again"
        );

    }

}
