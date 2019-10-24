package com.lying.recyclerviewdemo.fenzu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.lying.recyclerviewdemo.R;

import java.util.ArrayList;
import java.util.List;

public class FenzuActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fenzu);

        final List<String> fenzuData = new ArrayList<>(50);
        for(int i = 1; i <= 50; i++){
            fenzuData.add("第 " + i + "条数据");
        }

        recyclerView = findViewById(R.id.rv_fenzu);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new FenzuAdapter(fenzuData));
        recyclerView.addItemDecoration(new FenzuDecoration(this, new FenzuDecoration.FenzuCallBack() {
            @Override
            public Fenzu getFenzu(int position) {
                int id = position/5+1;
                String name = fenzuData.get(position);
                int pos = position%5;
                return new Fenzu(id,name,pos);
            }
        }));
    }
}
