package com.example.cash_register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import java.util.Date;

import java.util.ArrayList;

public class ManagerMode extends AppCompatActivity {

    //
    private ArrayList<History> historyArrayList;
    private ArrayList<Product> productArrayList;
    Button home_btn;
    Button history_btn;
    Button restock_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_mode);

        home_btn = findViewById(R.id.home_btn);
        history_btn = findViewById(R.id.history_btn);
        restock_btn= findViewById(R.id.restock_btn);

        //get data from main regarding history
        historyArrayList = getIntent().getParcelableArrayListExtra("EXTRA_DATA");
        productArrayList= getIntent().getParcelableArrayListExtra("products");

        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ManagerMode.this, MainActivity.class));
            }
        });

        history_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManagerMode.this, HistoryList.class);
                intent.putParcelableArrayListExtra("EXTRA_DATA", historyArrayList);
                startActivity(intent);
            }
        });

        restock_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go to restock activity
                Intent intent = new Intent(ManagerMode.this, Restock.class);
                intent.putParcelableArrayListExtra("products", productArrayList);
                startActivity(intent);

            }
        });

    }

}