package com.example.book_library_app;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.book_library_app.adapters.CustomBookAdapter;
import com.example.book_library_app.helperclasses.BookDatabaseClass;
import com.example.book_library_app.modal.BookModal;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton addNewBook;
    private ArrayList<BookModal> bookList;
    private BookDatabaseClass bookDatabaseClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        RecyclerView rv_bookList = findViewById(R.id.rv_book_list);
        addNewBook = findViewById(R.id.fab_add_new_book);
        bookDatabaseClass = new BookDatabaseClass(this);
        bookList = new ArrayList<>();

        gotToFormBook();
        storeDataInArray();

        CustomBookAdapter customBookAdapter =
                new CustomBookAdapter(MainActivity.this, bookList);

        rv_bookList.setAdapter(customBookAdapter);
        rv_bookList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    private void storeDataInArray() {
        Cursor mCursor = bookDatabaseClass.readBookData();
        if (mCursor.getCount() != 0) {
            listBooks(mCursor);
            return;
        }
        Toast.makeText(this, "No data to list", Toast.LENGTH_SHORT).show();
    }

    private void listBooks(Cursor mCursor) {
        while (mCursor.moveToNext()) {
            bookList.add(
                    new BookModal(Integer.parseInt(mCursor.getString(0)),
                            mCursor.getString(1),
                            mCursor.getString(2),
                            Integer.parseInt(mCursor.getString(3))
                    )
            );
        }
    }

    private void gotToFormBook() {
        addNewBook.setOnClickListener(
                v -> startActivity(
                        new Intent(MainActivity.this, AddBookActivity.class)
                )
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.delete_menu) {
            Toast.makeText(this, "Delete All", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}