package com.lying.recyclerviewdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SecondViewAdapter extends RecyclerView.Adapter<SecondViewAdapter.SecondViewHolder> {

    private List<BookBean> bookData;

    public SecondViewAdapter(List<BookBean> bookData){
        this.bookData = bookData;
    }

    @NonNull
    @Override
    public SecondViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_second,parent,false);

        return new SecondViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SecondViewHolder holder, int position) {
        holder.tvId.setText(bookData.get(position).getId());
        holder.tvName.setText(bookData.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return bookData.size();
    }

    public class SecondViewHolder extends RecyclerView.ViewHolder {

        private TextView tvId;
        private TextView tvName;

        public SecondViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }
}
