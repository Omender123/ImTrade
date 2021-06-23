package com.trade.imtrade.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.trade.imtrade.R;
import com.trade.imtrade.databinding.CategoriesLayoutBinding;

public class Categories_Adapter extends RecyclerView.Adapter<Categories_Adapter.CategoriesViewHolder>  {

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
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CategoriesLayoutBinding binding = CategoriesLayoutBinding.inflate(layoutInflater, parent, false);
        //  View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_layout, parent, false);

        return new CategoriesViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {
        holder.binding.productName.setText(amount[position]);

    }

    @Override
    public int getItemCount() {
        return amount.length;
    }

    public class CategoriesViewHolder extends RecyclerView.ViewHolder {
       CategoriesLayoutBinding binding;

        public CategoriesViewHolder( CategoriesLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


        }
    }
}
