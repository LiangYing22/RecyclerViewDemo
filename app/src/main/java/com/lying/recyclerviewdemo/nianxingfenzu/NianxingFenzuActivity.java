package com.lying.recyclerviewdemo.nianxingfenzu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.lying.recyclerviewdemo.R;
import com.lying.recyclerviewdemo.fenzu.Fenzu;
import com.lying.recyclerviewdemo.fenzu.FenzuAdapter;

import java.util.ArrayList;
import java.util.List;

public class NianxingFenzuActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nianxing_fenzu);

        final List<String> fenzuData = new ArrayList<>(50);
        for(int i = 1; i <= 50; i++){
            fenzuData.add("第 " + i + "条数据");
        }

        recyclerView = findViewById(R.id.rv_nianxingfenzu);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new FenzuAdapter(fenzuData));
        recyclerView.addItemDecoration(new NianxingFenzuItemDecoration(new NianxingFenzuItemDecoration.NianxingFenzuCallBack() {
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
