package com.example.cash_register;
//https://guides.codepath.com/android/using-the-recyclerview#creating-the-custom-row-layout
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder> {

    private ArrayList<History> purchase_history;

    public HistoryAdapter(ArrayList<History> purchase_history)
    {
        this.purchase_history = purchase_history;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView nameTextView;
        public TextView quantity;
        public TextView total_price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.product_name);
            quantity = itemView.findViewById(R.id.product_quantity);
            total_price = itemView.findViewById(R.id.total_cost);

        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        History item = purchase_history.get(position);

        // Set item views based on your views and data model
        TextView nameTextView = holder.nameTextView;
        nameTextView.setText(item.m_name);
        //
        TextView quantityTextView = holder.quantity;
        String s = String.valueOf(item.m_quantity);
        quantityTextView.setText(s);
        //
        TextView costTextView = holder.total_price;
        s = String.valueOf(item.m_total_price);
        costTextView.setText(s);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), History_Details.class);
                intent.putExtra("EXTRA_DATA", item);
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return purchase_history.size();
    }


}
