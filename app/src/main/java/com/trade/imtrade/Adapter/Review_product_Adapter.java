package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trade.imtrade.databinding.CustomRecomandedBinding;

public class Review_product_Adapter extends RecyclerView.Adapter<Review_product_Adapter.ReviewProductViewHolder>  {
    Context context;
    String[] amount;

    public Review_product_Adapter(Context context, String[] amount) {
        this.context = context;
        this.amount = amount;
    }

    public Review_product_Adapter() {
    }


    @NonNull
    @Override
    public ReviewProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CustomRecomandedBinding binding = CustomRecomandedBinding.inflate(inflater,parent,false);

        return new ReviewProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewProductViewHolder holder, int position) {
        holder.binding.catePrice.setText(amount[position]);
     
    }

    @Override
    public int getItemCount() {
        return amount.length;
    }

    public class ReviewProductViewHolder extends RecyclerView.ViewHolder {
        CustomRecomandedBinding binding;
        public ReviewProductViewHolder(CustomRecomandedBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }


}
