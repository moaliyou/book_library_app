package com.example.book_library_app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.book_library_app.helperclasses.BookDatabaseClass;
import com.example.book_library_app.helperclasses.MyHelperClass;
import com.example.book_library_app.interfaces.BookRemover;

public class EditBookActivity extends AppCompatActivity {

    private EditText etBookTitle, etBookAuthor, etBookPages;
    private BookDatabaseClass bookDatabaseClass;
    private Button btnEditBook, btnDeleteBook;

    private String bookId, bookTitle, bookAuthor, bookPages;

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
        btnDeleteBook = findViewById(R.id.btn_delete_book);
        bookDatabaseClass = new BookDatabaseClass(this);

        getIntentData();
        setIntentDataToEditTexts();
        setTitleToActionBar(bookTitle);

        deleteBook();
        editBook();
    }

    private void deleteBook() {
        btnDeleteBook.setOnClickListener(view -> {

            BookRemover remover = () -> bookDatabaseClass.deleteBook(bookId);

            MyHelperClass.confirmDialog(this, "Are you sure to delete " + bookTitle + "?", remover);

        });
    }

    private void setTitleToActionBar(String title) {
        ActionBar actionBar = getSupportActionBar();
        if (title != null && actionBar != null && !title.isEmpty()) {
            actionBar.setTitle(title);
        }
    }

    private void setIntentDataToEditTexts() {
        etBookTitle.setText(bookTitle);
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
        bookTitle = getIntent().getStringExtra("title");
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
                bookDatabaseClass.editBookData(
                        bookId, bookTitle,
                        bookAuthor, Integer.parseInt(bookPagesInput)
                );

                MyHelperClass.refreshActivity(this, MainActivity.class);
            }

        });
    }

}