package com.lying.recyclerviewdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lying.recyclerviewdemo.itemdecoration.Evolution2ItemDecoration;
import com.lying.recyclerviewdemo.itemdecoration.EvolutionItemDecoration;
import com.lying.recyclerviewdemo.itemdecoration.SimpleItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        initRecyclerView();
    }

    private void initRecyclerView(){
        List<String> data = new ArrayList<>(50);
        for(int i = 0; i < 50 ; i++){
            data.add("第 " + (i+1) + " 项");
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new DataRecyclerViewAdapter(data));
        recyclerView.addItemDecoration(new Evolution2ItemDecoration());
    }

    public void into(View view) {
        Intent intent = new Intent(this,SecondActivity.class);
        startActivity(intent);
    }
}
