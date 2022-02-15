package com.example.cash_register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{


    ProductManager products;
    PurchaseManager purchase_history;

    Product m_selected_product;
    TextView prod_type;
    ListView product_list;

    //Quantity related
    int m_quantity;
    NumberPicker quantity_picker;
    TextView quantity;

    //cost related
    TextView total;
    double m_total_cost = 0;

    //Buy button
    Button buy_btn;

    //Manager Button
    Button manager_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //data
        products = ((MyApp)getApplication()).m_product_list;
        purchase_history = ((MyApp)getApplication()).m_purchase_list;

        if(products.product_list.isEmpty() == true)
        {
            //populate
            //populate products arraylist with dummy data
            Product prod_0 = new Product(0,0,"");
            Product prod_1 = new Product(10,10,"pants");
            Product prod_2 = new Product(20,10,"shirts");
            Product prod_3 = new Product(4,2,"errors");

            products.addProduct(prod_1);
            products.addProduct(prod_2);
            products.addProduct(prod_3);

            m_selected_product = prod_0;

        }



        //set up quantity picker
        quantity_picker = findViewById(R.id.quantity_picker);
        quantity_picker.setMinValue(0);
        quantity_picker.setMaxValue(10);

        quantity_picker.setOnValueChangedListener(
                new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal)
                    {
                        //Update the quantity
                        m_quantity = newVal;
                        UpdateDisplayQuantity();
                    }
                }
        );

        //set up product list
        product_list = findViewById(R.id.product_list);
        ArrayAdapter<Product> prodAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, products.product_list);
        product_list.setAdapter(prodAdapter);

        product_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                m_selected_product = (Product) adapterView.getItemAtPosition(position);
                UpdateDisplayProduct();
            }
        });

        //Buy button
        buy_btn = findViewById(R.id.buy_btn);

        buy_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                // Process order or send error
                if(m_quantity == 0)
                {
                    //send error
                    Toast.makeText(MainActivity.this, "Select a quantity", Toast.LENGTH_SHORT).show();
                }
                else if(m_selected_product.m_name == "")
                {
                    Toast.makeText(MainActivity.this, "Select a Product!", Toast.LENGTH_SHORT).show();
                }
                else if(m_selected_product.m_stock < m_quantity)
                {
                    //send error
                    Toast.makeText(MainActivity.this, "Insufficient stock!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //Process the order
                    m_selected_product.updateStock(m_quantity);
                    //send message
                    String s = "Thank you for your order!\nYour order was " +m_quantity + " " + m_selected_product.m_name + " for " + m_total_cost;
                    Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                    //add order to the History
                    History new_item = new History(m_selected_product.m_name, m_quantity, m_total_cost);
                    //purchase_history.add(new_item);
                    purchase_history.purchase_list.add(new_item);
                }
            }
        });

        //switch to manager
        manager_btn = findViewById(R.id.manager_btn);

        manager_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, ManagerMode.class);
                intent.putParcelableArrayListExtra("EXTRA_DATA", purchase_history.purchase_list);
                intent.putParcelableArrayListExtra("products", products.product_list);
                startActivity(intent);
            }
        });


    }


    void UpdateDisplayQuantity()
    {
        //set quantity value
        quantity = findViewById(R.id.quantity);
        String s=String.valueOf(m_quantity);
        quantity.setText(s);
        calculateCost();
    }
    void UpdateDisplayProduct()
    {

        //prod_type
        prod_type = findViewById(R.id.prod_type);
        String s = m_selected_product.getName();
        prod_type.setText(s);
        calculateCost();
    }

    void calculateCost()
    {
        total = findViewById(R.id.total);


        if(m_quantity > m_selected_product.m_stock)
        {
            //send error message
            total.setText("Insufficient stock!");
        }
        else
        {
            m_total_cost = m_quantity * m_selected_product.m_price;

            String s =String.valueOf(m_total_cost);
            total.setText(s);

        }

    }

}