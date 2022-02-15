package com.example.cash_register;
import java.util.ArrayList;

public class PurchaseManager
{
    ArrayList<History> purchase_list = new ArrayList<>(0);

    public void addPurchase(History d){
        purchase_list.add(d);
    }
}
