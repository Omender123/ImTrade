package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.trade.imtrade.R;
import com.trade.imtrade.databinding.CustomRecomandedBinding;

public class ProductToSeasonAdapter extends RecyclerView.Adapter<ProductToSeasonAdapter.ProductViewHolder>  {
    Context context;
    String[] amount;

    public ProductToSeasonAdapter(Context context, String[] amount) {
        this.context = context;
        this.amount = amount;
    }

    public ProductToSeasonAdapter() {
    }


    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CustomRecomandedBinding binding = CustomRecomandedBinding.inflate(inflater,parent,false);

        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.binding.cateName.setText(amount[position]);
       // Glide.with(context).load("https://picsum.photos/id/896/300/200").into(holder.binding.cateImg);
        holder.binding.cateImg.setImageDrawable(context.getResources().getDrawable(R.mipmap.season));
    }

    @Override
    public int getItemCount() {
        return amount.length;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        CustomRecomandedBinding binding;
        public ProductViewHolder(CustomRecomandedBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }

}
