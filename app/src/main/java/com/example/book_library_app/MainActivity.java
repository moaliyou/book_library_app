package com.example.book_library_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.book_library_app.dbHelper.BookDatabaseHelper;
import com.example.book_library_app.modal.BookModal;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv_bookList;
    private FloatingActionButton addNewBook;
    private ArrayList<BookModal> bookList;
    private BookDatabaseHelper bookDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        rv_bookList = findViewById(R.id.rv_book_list);
        addNewBook = findViewById(R.id.fab_add_new_book);
        bookDatabaseHelper = new BookDatabaseHelper(this);
        bookList = new ArrayList<>();
        registerNewBook();
        storeDataInArray();
    }

    private void storeDataInArray() {
        Cursor mCursor = bookDatabaseHelper.readBookData();
        if(mCursor.getCount() != 0) {
            while (mCursor.moveToNext()) {
                bookList.add(new BookModal(mCursor.getString(0), mCursor.getString(1), Integer.parseInt(mCursor.getString(2))));
            }
            return;
        }
        Toast.makeText(this, "No data to list", Toast.LENGTH_SHORT).show();
    }

    private void registerNewBook() {
        addNewBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddBookActivity.class));
            }
        });
    }
}