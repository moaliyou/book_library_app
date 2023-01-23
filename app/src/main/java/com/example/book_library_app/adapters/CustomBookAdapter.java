package com.example.book_library_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.book_library_app.R;
import com.example.book_library_app.modal.BookModal;

import java.util.ArrayList;

public class CustomBookAdapter extends RecyclerView.Adapter<CustomBookAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<BookModal> bookList;

    public CustomBookAdapter(Context context, ArrayList<BookModal> bookList) {
        this.mContext = context;
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public CustomBookAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.book_list_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomBookAdapter.MyViewHolder holder, int position) {
        holder.tvBookId.setText(String.valueOf(bookList.get(position).getBookId()));
        holder.tvBookTitle.setText(String.valueOf(bookList.get(position).getBookTitle()));
        holder.tvBookAuthor.setText(String.valueOf(bookList.get(position).getBookAuthor()));
        holder.tvBookPage.setText(String.valueOf(bookList.get(position).getBookPages()));
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvBookId, tvBookTitle, tvBookAuthor, tvBookPage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvBookId = itemView.findViewById(R.id.tv_id);
            tvBookTitle = itemView.findViewById(R.id.tv_book_title);
            tvBookAuthor = itemView.findViewById(R.id.tv_book_author);
            tvBookPage = itemView.findViewById(R.id.tv_book_pages);
        }
    }
}
