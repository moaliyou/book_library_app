package com.example.book_library_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.book_library_app.helperclasses.BookDatabaseClass;
import com.example.book_library_app.helperclasses.MyHelperClass;

public class EditBookActivity extends AppCompatActivity {

    private EditText etBookTitle, etBookAuthor, etBookPages;
    private BookDatabaseClass bookDatabaseClass;
    private Button btnEditBook;

    private String bookId, bookTile, bookAuthor, bookPages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        initViews();
    }

    private void initViews() {
        etBookTitle = findViewById(R.id.et_book_title);
        etBookAuthor = findViewById(R.id.et_book_author);
        etBookPages = findViewById(R.id.et_book_pages);
        btnEditBook = findViewById(R.id.btn_edit_book);
        bookDatabaseClass = new BookDatabaseClass(this);

        getIntentData();
        setIntentDataToEditTexts();

        editBook();
    }

    private void setIntentDataToEditTexts() {
        etBookTitle.setText(bookTile);
        etBookAuthor.setText(bookAuthor);
        etBookPages.setText(bookPages);
    }

    private void getIntentData() {

        if (!hasData()) {
            MyHelperClass.showInfoMessage(
                    this, "Info",
                    "No data has been found", "Ok");
            return;
        }

        bookId = getIntent().getStringExtra("id");
        bookTile = getIntent().getStringExtra("title");
        bookAuthor = getIntent().getStringExtra("author");
        bookPages = getIntent().getStringExtra("pages");

    }

    private boolean hasData() {
        return getIntent().hasExtra("id") &&
                getIntent().hasExtra("title") &&
                getIntent().hasExtra("author") &&
                getIntent().hasExtra("pages");
    }

    private void editBook() {
        btnEditBook.setOnClickListener(view -> {
            String bookTitle = etBookTitle.getText().toString().trim();
            String bookAuthor = etBookAuthor.getText().toString().trim();
            String bookPagesInput = etBookPages.getText().toString().trim();

            if (!(bookTitle.isEmpty()) && !(bookAuthor.isEmpty()) && !(bookPagesInput.isEmpty())) {
                bookDatabaseClass.editBookData(bookId, bookTitle, bookAuthor, bookPages);
                startActivity(new Intent(EditBookActivity.this, MainActivity.class));
            }

        });
    }

}