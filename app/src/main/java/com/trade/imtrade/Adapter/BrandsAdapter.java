package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.trade.imtrade.R;
import com.trade.imtrade.databinding.CustomBrandsLayoutBinding;
import com.trade.imtrade.databinding.CustomBrandsLayoutBinding;

public class BrandsAdapter extends RecyclerView.Adapter<BrandsAdapter.BrandsViewHolder>  {
    Context context;
    String[] amount;

    public BrandsAdapter(Context context, String[] amount) {
        this.context = context;
        this.amount = amount;
    }

    public BrandsAdapter() {
    }


    @NonNull
    @Override
    public BrandsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CustomBrandsLayoutBinding binding = CustomBrandsLayoutBinding.inflate(inflater,parent,false);

        return new BrandsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BrandsViewHolder holder, int position) {
        holder.binding.brandsName.setText(amount[position]);
       Glide.with(context).load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT9TpFjWFwDHIQSxfrSoGEGQRkjSvSWwBgKtA&usqp=CAU").apply(new RequestOptions().circleCrop()).placeholder(R.mipmap.profile1).into(holder.binding.brandsImage);
    }

    @Override
    public int getItemCount() {
        return amount.length;
    }

    public class BrandsViewHolder extends RecyclerView.ViewHolder {
        CustomBrandsLayoutBinding binding;
        public BrandsViewHolder(CustomBrandsLayoutBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }

}
