package com.example.book_library_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.book_library_app.helperclasses.BookDatabaseHelper;

public class AddBookActivity extends AppCompatActivity {

    private EditText etBookTitle, etBookAuthor, etBookPages;
    private BookDatabaseHelper bookDatabaseHelper;
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
        bookDatabaseHelper = new BookDatabaseHelper(this);

        addBook();
    }

    private void addBook() {
        btnAddNewBook.setOnClickListener(view -> {
            String bookTitle = etBookTitle.getText().toString().trim();
            String bookAuthor = etBookAuthor.getText().toString().trim();
            String bookPagesInput = etBookPages.getText().toString().trim();

            if (!(bookTitle.isEmpty()) && !(bookAuthor.isEmpty()) && !(bookPagesInput.isEmpty())) {
                int bookPages = Integer.parseInt(etBookPages.getText().toString().trim());
                bookDatabaseHelper.registerNewBook(bookTitle, bookAuthor, bookPages);
                startActivity(new Intent(AddBookActivity.this, MainActivity.class));
            }

        });
    }

}