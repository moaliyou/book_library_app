package com.example.book_library_app.modal;

public class BookModal {

    private int bookId;
    private String bookTitle;
    private String bookAuthor;
    private int bookPages;

    public BookModal() {
    }

    public BookModal(int bookId, String bookTitle, String bookAuthor, int bookPages) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookPages = bookPages;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public int getBookId() {
        return bookId;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public int getBookPages() {
        return bookPages;
    }
}
