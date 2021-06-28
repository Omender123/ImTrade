package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.trade.imtrade.R;
import com.trade.imtrade.databinding.CustomRecomandedBinding;

public class DiscountByBrandsAdapter extends RecyclerView.Adapter<DiscountByBrandsAdapter.DiscountViewHolder>  {
    Context context;
    String[] amount;

    public DiscountByBrandsAdapter(Context context, String[] amount) {
        this.context = context;
        this.amount = amount;
    }

    public DiscountByBrandsAdapter() {
    }


    @NonNull
    @Override
    public DiscountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CustomRecomandedBinding binding = CustomRecomandedBinding.inflate(inflater,parent,false);

        return new DiscountViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscountViewHolder holder, int position) {
        holder.binding.cateName.setText(amount[position]);
      //  Glide.with(context).load("https://picsum.photos/id/896/300/200").into(holder.binding.cateImg);
        holder.binding.cateImg.setImageDrawable(context.getResources().getDrawable(R.mipmap.discount));
    }

    @Override
    public int getItemCount() {
        return amount.length;
    }

    public class DiscountViewHolder extends RecyclerView.ViewHolder {
        CustomRecomandedBinding binding;
        public DiscountViewHolder(CustomRecomandedBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }
}
