package com.lying.recyclerviewdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DataRecyclerViewAdapter extends RecyclerView.Adapter<DataRecyclerViewAdapter.DataViewHolder> {

    private List<String> dataList;

    public DataRecyclerViewAdapter(List<String> dataList){
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public DataRecyclerViewAdapter.DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataRecyclerViewAdapter.DataViewHolder holder, int position) {

        holder.textView.setText(dataList.get(position));

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_item);
        }
    }
}
