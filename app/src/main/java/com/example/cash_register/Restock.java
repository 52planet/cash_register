package com.example.cash_register;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Restock extends AppCompatActivity {

    ArrayList<Product> productArrayList;

    Button cancel_btn;
    Button ok_btn;
    ListView product_list;
    Product selected_item = new Product(0,0,"");
    EditText input;
    int _position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restock);

        cancel_btn = findViewById(R.id.cancel_btn);
        ok_btn = findViewById(R.id.ok_btn);
        product_list = findViewById(R.id.product_list);
        input = findViewById(R.id.input);

        //get intent
        productArrayList = getIntent().getParcelableArrayListExtra("products");

        //attach passed in data to listview
        ArrayAdapter<Product> prodAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, productArrayList);
        product_list.setAdapter(prodAdapter);

        product_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                selected_item = (Product) adapterView.getItemAtPosition(position);
                _position = position;

            }
        });

        //set up input
        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = input.getText().toString();
                boolean result = isNumeric(data);
                if(selected_item.m_name == "")
                {
                    //send toast with error message
                    Toast.makeText(Restock.this, "Select a product!", Toast.LENGTH_SHORT).show();
                }
                else if(result == false)
                {
                    //send toast with error message
                    Toast.makeText(Restock.this, "Only numbers are allowed!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //convert to double and perform operation
                    int value = Integer.parseInt(data);
                    productArrayList.get(_position).addStock(value);
                    //update global variable
                    ((MyApp)getApplication()).m_product_list.product_list = productArrayList;
                    Toast.makeText(Restock.this, "Stock added!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Restock.this, "Operation aborted!", Toast.LENGTH_SHORT).show();
                input.setText("");
            }
        });
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}