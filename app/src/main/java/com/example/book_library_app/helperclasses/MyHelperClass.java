package com.example.book_library_app.helperclasses;

import android.content.Context;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MyHelperClass {

    public static void showInfoMessage(Context mContext, String messageTitle, String message, String buttonText) {
        new MaterialAlertDialogBuilder(mContext)
                .setTitle(messageTitle)
                .setMessage(message)
                .setPositiveButton(buttonText, (dialogInterface, i) -> {}).show();
    }

}
