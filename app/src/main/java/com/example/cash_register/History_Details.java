package com.example.cash_register;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class History_Details extends AppCompatActivity{

    History item;
    ArrayList<History> historyArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_details);


        //get text views
        TextView product_name;
        product_name = findViewById(R.id.product_name);
        TextView product_price;
        product_price = findViewById(R.id.product_price);
        TextView date_purchased;
        date_purchased = findViewById(R.id.date_purchased);



        //populate item with intent
        item = getIntent().getParcelableExtra("EXTRA_DATA");
        historyArrayList = getIntent().getParcelableExtra("arraylist");
        //set text
        product_name.setText(item.m_name);
        String s =String.valueOf(item.m_total_price);
        product_price.setText(s);
        date_purchased.setText(item.m_purchase_date);


    }
}