package com.example.book_library_app.helperclasses;

public class BookDatabaseHelper {

    static final String DATABASE_NAME = "book_library.db";
    static final int DATABASE_VERSION = 1;

    // ATTRIBUTES OF TABLE
    public static final String TABLE_NAME = "library_tbl";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_BOOK_TITLES = "book_titles";
    public static final String COLUMN_BOOK_AUTHORS = "book_authors";
    public static final String COLUMN_BOOK_PAGES = "book_pages";

    public static final String CREATE_BOOK_TABLE = "CREATE TABLE " + BookDatabaseHelper.TABLE_NAME + " ( " +
            BookDatabaseHelper.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            BookDatabaseHelper.COLUMN_BOOK_TITLES + " TEXT, " +
            BookDatabaseHelper.COLUMN_BOOK_AUTHORS + " TEXT, " +
            BookDatabaseHelper.COLUMN_BOOK_PAGES + " INTEGER);";

}
