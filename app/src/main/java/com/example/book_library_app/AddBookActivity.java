package com.example.book_library_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.book_library_app.dbHelper.BookDatabaseHelper;

public class AddBookActivity extends AppCompatActivity {

    private EditText etBookTitle, etBookAuthor, etBookPages;
    private Button btnAddNewBook;
    private BookDatabaseHelper bookDatabaseHelper;

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

        btnAddNewBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bookTitle = etBookTitle.getText().toString().trim();
                String bookAuthor = etBookAuthor.getText().toString().trim();

                if (!(bookTitle.isEmpty()) && !(bookAuthor.isEmpty()) && !etBookPages.getText().toString().trim().isEmpty()) {
                    int bookPages = Integer.parseInt(etBookPages.getText().toString().trim());
                    bookDatabaseHelper.registerNewBook(bookTitle, bookAuthor, bookPages);
                }

            }
        });
    }
}