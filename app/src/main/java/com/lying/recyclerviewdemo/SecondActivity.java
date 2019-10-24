package com.lying.recyclerviewdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lying.recyclerviewdemo.fenzu.FenzuActivity;
import com.lying.recyclerviewdemo.itemdecoration.Evolution3ItemDecoration;
import com.lying.recyclerviewdemo.nianxingfenzu.NianxingFenzuActivity;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private List<BookBean> bookData;
    private RecyclerView secondRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initData();
        secondRecyclerView = findViewById(R.id.rv_second_recycler_view);
        secondRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        secondRecyclerView.setAdapter(new SecondViewAdapter(bookData));
        secondRecyclerView.addItemDecoration(new Evolution3ItemDecoration(this));
    }

    private void initData() {
        bookData = new ArrayList<>(50);
        for(int i = 1; i <= 50; i++){
            BookBean bookBean = new BookBean(String.valueOf(i),"xiaocaiyidie " + i);
            bookData.add(bookBean);
        }
    }

    public void fenzu(View view) {
        Intent intent = new Intent(this, FenzuActivity.class);
        startActivity(intent);
    }

    public void nianxingfenzu(View view) {
        Intent intent = new Intent(this, NianxingFenzuActivity.class);
        startActivity(intent);
    }
}
