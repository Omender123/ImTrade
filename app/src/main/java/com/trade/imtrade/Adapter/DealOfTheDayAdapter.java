package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.trade.imtrade.R;
import com.trade.imtrade.databinding.CustomRecomandedBinding;

public class DealOfTheDayAdapter extends RecyclerView.Adapter<DealOfTheDayAdapter.DealViewHolder> {
    Context context;
    String[] amount;

    public DealOfTheDayAdapter(Context context, String[] amount) {
        this.context = context;
        this.amount = amount;
    }

    public DealOfTheDayAdapter() {
    }


    @NonNull
    @Override
    public DealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CustomRecomandedBinding binding = CustomRecomandedBinding.inflate(inflater, parent, false);

        return new DealViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DealViewHolder holder, int position) {
        holder.binding.cateName.setText(amount[position]);
        holder.binding.cateImg.setImageDrawable(context.getResources().getDrawable(R.mipmap.deal));
       // Glide.with(context).load("https://picsum.photos/id/896/300/200").into(holder.binding.cateImg);
    }

    @Override
    public int getItemCount() {
        return amount.length;
    }

    public class DealViewHolder extends RecyclerView.ViewHolder {
        CustomRecomandedBinding binding;

        public DealViewHolder(CustomRecomandedBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }
}
