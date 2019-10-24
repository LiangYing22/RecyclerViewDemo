package com.lying.recyclerviewdemo.fenzu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.lying.recyclerviewdemo.R;

import java.util.List;

public class FenzuAdapter extends RecyclerView.Adapter<FenzuAdapter.FenzuViewHolde> {

    private List<String> fenzuData;

    public FenzuAdapter(List<String> fenzuData){
        this.fenzuData = fenzuData;
    }

    @NonNull
    @Override
    public FenzuAdapter.FenzuViewHolde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        return new FenzuViewHolde(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FenzuAdapter.FenzuViewHolde holder, int position) {
        holder.textView.setText(fenzuData.get(position));
    }

    @Override
    public int getItemCount() {
        return fenzuData.size();
    }

    public class FenzuViewHolde extends RecyclerView.ViewHolder{
        private TextView textView;
        public FenzuViewHolde(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_item);
        }
    }
}
