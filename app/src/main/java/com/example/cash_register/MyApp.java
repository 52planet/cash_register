package com.example.cash_register;

import android.app.Application;

public class MyApp extends Application
{
    ProductManager m_product_list = new ProductManager();
    PurchaseManager m_purchase_list = new PurchaseManager();

}
