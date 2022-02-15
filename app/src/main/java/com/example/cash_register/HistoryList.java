package com.example.cash_register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class HistoryList extends AppCompatActivity {

    ArrayList<History> historyArrayList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list);

        recyclerView = findViewById(R.id.recyclerView);

        // populate arraylist
        //get data from main regarding history
        historyArrayList = getIntent().getParcelableArrayListExtra("EXTRA_DATA");

        //
        HistoryAdapter adapter = new HistoryAdapter(historyArrayList);

        //Attach the adapter to the recyclerview to populate items

        recyclerView.setAdapter(adapter);

        // Set layout manager to position the items
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}