package com.example.book_library_app.helperclasses;

import android.content.Context;
import android.content.Intent;

import com.example.book_library_app.MainActivity;
import com.example.book_library_app.interfaces.BookRemover;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MyHelperClass {

    public static void showInfoMessage(Context mContext, String messageTitle, String message, String buttonText) {
        new MaterialAlertDialogBuilder(mContext)
                .setTitle(messageTitle)
                .setMessage(message)
                .setPositiveButton(buttonText, (dialogInterface, i) -> {}).show();
    }

    public static void confirmDialog(Context context, String messageBody, BookRemover remover) {

        new MaterialAlertDialogBuilder(context)
                .setTitle("Deleting")
                .setMessage(messageBody)
                .setPositiveButton("Yes", (dialogInterface, i) -> {
                    remover.deleteBook();
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(intent);
                })
                .setNegativeButton("No", (dialogInterface, i) -> {}).show();

    }

}
