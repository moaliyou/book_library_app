package com.example.book_library_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv_bookList;
    FloatingActionButton addNewBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        rv_bookList = findViewById(R.id.rv_book_list);
        addNewBook = findViewById(R.id.fab_add_new_book);
        registerNewBook();
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