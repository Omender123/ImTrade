package com.trade.imtrade.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trade.imtrade.R;

public class Categories_Adapter extends RecyclerView.Adapter<Categories_Adapter.myViewHolder>  {

    Context context;
    String[] amount;

    public Categories_Adapter(Context context, String[] amount) {
        this.context = context;
        this.amount = amount;
    }

    public Categories_Adapter() {
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_layout, parent, false);

        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.product_name.setText(amount[position]);

    }

    @Override
    public int getItemCount() {
        return amount.length;
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView product_name;
        ImageView product_Image;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            product_name = itemView.findViewById(R.id.product_name);
            product_Image = itemView.findViewById(R.id.product_img);


        }
    }
}
