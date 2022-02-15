package com.example.cash_register;

import java.util.ArrayList;

public class ProductManager
{
    ArrayList<Product>product_list = new ArrayList<>(0);

    public void addProduct(Product d){
        product_list.add(d);
    }

    public ArrayList<Product> getProduct_list()
    {
        return product_list;
    }
}
