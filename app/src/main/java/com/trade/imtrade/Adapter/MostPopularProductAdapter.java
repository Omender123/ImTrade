package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.trade.imtrade.R;
import com.trade.imtrade.databinding.CustomRecomandedBinding;

public class MostPopularProductAdapter extends RecyclerView.Adapter<MostPopularProductAdapter.MostPopularViewHolder>  {
    Context context;
    String[] amount;

    public MostPopularProductAdapter(Context context, String[] amount) {
        this.context = context;
        this.amount = amount;
    }

    public MostPopularProductAdapter() {
    }


    @NonNull
    @Override
    public MostPopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CustomRecomandedBinding binding = CustomRecomandedBinding.inflate(inflater,parent,false);

        return new MostPopularViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MostPopularViewHolder holder, int position) {
        holder.binding.cateName.setText(amount[position]);
        //Glide.with(context).load("https://picsum.photos/id/896/300/200").into(holder.binding.cateImg);
        holder.binding.cateImg.setImageDrawable(context.getResources().getDrawable(R.mipmap.most));
    }

    @Override
    public int getItemCount() {
        return amount.length;
    }

    public class MostPopularViewHolder extends RecyclerView.ViewHolder {
        CustomRecomandedBinding binding;
        public MostPopularViewHolder(CustomRecomandedBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }
}
