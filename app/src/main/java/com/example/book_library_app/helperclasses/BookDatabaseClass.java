package com.example.book_library_app.helperclasses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

public class BookDatabaseClass extends SQLiteOpenHelper {

    private final Context mContext;

    public BookDatabaseClass(@Nullable Context context) {
        super(
                context,
                BookDatabaseHelper.DATABASE_NAME,
                null, BookDatabaseHelper.DATABASE_VERSION
        );
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BookDatabaseHelper.CREATE_BOOK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BookDatabaseHelper.TABLE_NAME);
        onCreate(db);
    }

    public void insertData(String tableName, HashMap<String, String> dataList) {

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        try {
            database.beginTransaction();

            if (dataList != null && dataList.size() > 0) {

                for (Map.Entry<String, String> data : dataList.entrySet())
                    contentValues.put(data.getKey(), data.getValue());

                long codeResult = database.insert(tableName, null, contentValues);

                if (codeResult != -1) {
                    database.setTransactionSuccessful();
                    MyHelperClass.showLongToastMessage(mContext, "New book has been added");
                }

            }

        } catch (Exception e) {
            database.endTransaction();
            e.printStackTrace();
        } finally {
            database.endTransaction();
        }

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

    public void updateData(String tableName, String dataId, HashMap<String, String> dataList) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        try {
            database.beginTransaction();

            if (dataList != null && dataList.size() > 0) {

                for (Map.Entry<String, String> data : dataList.entrySet())
                    contentValues.put(data.getKey(), data.getValue());

                long codeResult = database.update(tableName, contentValues, "_id=?", new String[]{dataId});

                if (codeResult != -1) {
                    database.setTransactionSuccessful();
                    MyHelperClass.showLongToastMessage(mContext, "The book has been modified");
                }

            }

        } catch (Exception e) {
            database.endTransaction();
            e.printStackTrace();
        } finally {
            database.endTransaction();
        }

    }

    public void deleteBookById(String bookId) {
        SQLiteDatabase database = this.getWritableDatabase();


        long resultCode = database.delete(BookDatabaseHelper.TABLE_NAME, "_id=?", new String[]{bookId});

        if (resultCode != -1) {
            Toast.makeText(mContext, "Deleted successfully", Toast.LENGTH_SHORT).show();
            return;
        }

        MyHelperClass.showInfoMessage(
                mContext,
                "Error",
                "Failed to delete. Something went wrong :(",
                "Try again"
        );

    }

    public void deleteAllBooks() {
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DELETE FROM " + BookDatabaseHelper.TABLE_NAME);
    }

}
