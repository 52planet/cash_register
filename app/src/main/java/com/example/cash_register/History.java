package com.example.cash_register;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Date;

public class History implements Parcelable {
    //

    String m_name;
    int m_quantity;
    double m_total_price;
    String m_purchase_date;

    History(String name, int quantity, double total)
    {
        m_name = name;
        m_quantity = quantity;
        m_total_price = total;
        m_purchase_date = new Date(System.currentTimeMillis()).toString();
    }

    History(String name, int quantity, double total, Date d)
    {
        m_name = name;
        m_quantity = quantity;
        m_total_price = total;
        m_purchase_date = d.toString();
    }


    protected History(Parcel in) {
        m_name = in.readString();
        m_quantity = in.readInt();
        m_total_price = in.readDouble();
        m_purchase_date = in.readString();
    }

    public static final Creator<History> CREATOR = new Creator<History>() {
        @Override
        public History createFromParcel(Parcel in) {
            return new History(in);
        }

        @Override
        public History[] newArray(int size) {
            return new History[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(m_name);
        parcel.writeInt(m_quantity);
        parcel.writeDouble(m_total_price);
        parcel.writeString(m_purchase_date);
    }
}
