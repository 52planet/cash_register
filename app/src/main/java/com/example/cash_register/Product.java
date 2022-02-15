package com.example.cash_register;

import android.os.Parcel;
import android.os.Parcelable;

//logcat to debug
public class Product implements Parcelable {
    String m_name;
    double m_price;
    int m_stock;

    Product()
    {
        m_price = 0;
        m_stock = 0;
        m_name = "";
    }

    Product(double price, int stock, String name)
    {
        m_stock = stock;
        m_price = price;
        m_name = name;
    }
    //get methods

    protected Product(Parcel in) {
        m_name = in.readString();
        m_price = in.readDouble();
        m_stock = in.readInt();
    }

    public void addStock(int s)
    {
        m_stock += s;
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    String getName()
    {
     return m_name;
    }

    //update methods
    void updateStock(int stock)
    {
        m_stock = m_stock - stock;
    }

    @Override
    public String toString() {
        return m_name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(m_name);
        parcel.writeDouble(m_price);
        parcel.writeInt(m_stock);
    }
}
