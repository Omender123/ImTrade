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
import com.trade.imtrade.databinding.CustomRecomandedBinding;

public class Recommended_Adapter extends RecyclerView.Adapter<Recommended_Adapter.RecommendedViewHolder>  {
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
    public RecommendedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CustomRecomandedBinding binding = CustomRecomandedBinding.inflate(inflater,parent,false);

        return new RecommendedViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendedViewHolder holder, int position) {
        holder.binding.cateName.setText(amount[position]);
        Glide.with(context).load("https://picsum.photos/id/896/300/200").into(holder.binding.cateImg);
    }

    @Override
    public int getItemCount() {
        return amount.length;
    }

    public class RecommendedViewHolder extends RecyclerView.ViewHolder {
      CustomRecomandedBinding binding;
        public RecommendedViewHolder(CustomRecomandedBinding binding) {
            super(binding.getRoot());

          this.binding = binding;


        }
    }
}
