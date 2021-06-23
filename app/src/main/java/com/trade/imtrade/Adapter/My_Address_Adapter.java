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
import com.trade.imtrade.databinding.CustomAddressLayoutBinding;

public class My_Address_Adapter extends RecyclerView.Adapter<My_Address_Adapter.MyAddressViewHolder> {
    Context context;
    String[] amount;

    public My_Address_Adapter(Context context, String[] amount) {
        this.context = context;
        this.amount = amount;
    }

    public My_Address_Adapter() {
    }


    @NonNull
    @Override
    public MyAddressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     //   View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_address_layout, parent, false);
        
      LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
      
      CustomAddressLayoutBinding binding = CustomAddressLayoutBinding.inflate(layoutInflater,parent,false);


        return new MyAddressViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAddressViewHolder holder, int position) {

        holder.binding.textName.setText(amount[position]);

    }

    @Override
    public int getItemCount() {
        return amount.length;
    }

    public class MyAddressViewHolder extends RecyclerView.ViewHolder {
      CustomAddressLayoutBinding binding;
        public MyAddressViewHolder(CustomAddressLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


}
