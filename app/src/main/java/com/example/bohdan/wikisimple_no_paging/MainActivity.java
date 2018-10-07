package com.example.bohdan.wikisimple_no_paging;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recyclerlist23);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        //layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        ItemViewModel itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);
        final ModelAdapter modelAdapter1 = new ModelAdapter();
        //recyclerView.setAdapter(modelAdapter1);

        itemViewModel.modelPagedList.observe(this, new Observer<PagedList<Result>>() {
            @Override
            public void onChanged(@Nullable PagedList<Result> models) {
                modelAdapter1.submitList(models);
                System.out.println("tits");
                recyclerView.setAdapter(modelAdapter1);
            }
        });

    }
}
