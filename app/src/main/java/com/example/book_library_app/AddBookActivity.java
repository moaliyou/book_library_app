package com.example.book_library_app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.book_library_app.helperclasses.BookDatabaseClass;
import com.example.book_library_app.helperclasses.BookDatabaseHelper;
import com.example.book_library_app.helperclasses.MyHelperClass;

import java.util.HashMap;

public class AddBookActivity extends AppCompatActivity {

    private EditText etBookTitle, etBookAuthor, etBookPages;
    private BookDatabaseClass bookDatabaseClass;
    private Button btnAddNewBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        initViews();
    }

    private void initViews() {
        etBookTitle = findViewById(R.id.et_book_title);
        etBookAuthor = findViewById(R.id.et_book_author);
        etBookPages = findViewById(R.id.et_book_pages);
        btnAddNewBook = findViewById(R.id.btn_add_new_book);
        bookDatabaseClass = new BookDatabaseClass(this);

        addNewBook();
    }

    private void addNewBook() {
        btnAddNewBook.setOnClickListener(view -> {
            String bookTitle = etBookTitle.getText().toString().trim();
            String bookAuthor = etBookAuthor.getText().toString().trim();
            String bookPagesInput = etBookPages.getText().toString().trim();

            if (!(bookTitle.isEmpty()) && !(bookAuthor.isEmpty()) && !(bookPagesInput.isEmpty())) {

                HashMap<String, String> dataList = new HashMap<>();
                dataList.put(BookDatabaseHelper.COLUMN_BOOK_TITLES, bookTitle);
                dataList.put(BookDatabaseHelper.COLUMN_BOOK_AUTHORS, bookAuthor);
                dataList.put(BookDatabaseHelper.COLUMN_BOOK_PAGES, bookPagesInput);

                bookDatabaseClass.insertData(BookDatabaseHelper.TABLE_NAME, dataList);

                MyHelperClass.refreshActivity(this, MainActivity.class);
            }

        });
    }

}