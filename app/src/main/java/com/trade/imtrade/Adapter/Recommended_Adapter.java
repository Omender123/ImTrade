package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.trade.imtrade.R;

public class Recommended_Adapter extends RecyclerView.Adapter<Recommended_Adapter.myViewHolder>  {
    Context context;
    String[] amount;

    public Recommended_Adapter(Context context, String[] amount) {
        this.context = context;
        this.amount = amount;
    }

    public Recommended_Adapter() {
    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_recomanded, parent, false);

        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.product_name.setText(amount[position]);
        Glide.with(context).load("https://picsum.photos/id/896/300/200").into(holder.product_Image);
    }

    @Override
    public int getItemCount() {
        return amount.length;
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView product_name,product_price;
        ImageView product_Image;
        CheckBox checkBox;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            product_name = itemView.findViewById(R.id.cate_name);
            product_price = itemView.findViewById(R.id.cate_price);
            product_Image = itemView.findViewById(R.id.cate_img);
            checkBox = itemView.findViewById(R.id.checkbox);



        }
    }
}
