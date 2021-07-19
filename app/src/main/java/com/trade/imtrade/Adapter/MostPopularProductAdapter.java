package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.trade.imtrade.Model.ResponseModel.PopularProductsResponse;
import com.trade.imtrade.R;
import com.trade.imtrade.SharedPerfence.PrefConf;
import com.trade.imtrade.databinding.CustomRecomandedBinding;

import java.util.List;

public class MostPopularProductAdapter extends RecyclerView.Adapter<MostPopularProductAdapter.MostPopularViewHolder> {
    Context context;
    List<PopularProductsResponse> popularProductsResponses;

    public MostPopularProductAdapter(Context context,List<PopularProductsResponse> popularProductsResponses) {
        this.context = context;
        this.popularProductsResponses = popularProductsResponses;
    }

    public MostPopularProductAdapter() {
    }


    @NonNull
    @Override
    public MostPopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CustomRecomandedBinding binding = CustomRecomandedBinding.inflate(inflater, parent, false);

        return new MostPopularViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MostPopularViewHolder holder, int position) {
        holder.binding.cateName.setText(popularProductsResponses.get(position).getName());
        holder.binding.catePrice.setText(popularProductsResponses.get(position).getDiscount());
      Glide.with(context).load(PrefConf.IMAGE_URL+popularProductsResponses.get(position).getImages().get(0)).into(holder.binding.cateImg);
       // holder.binding.cateImg.setImageDrawable(context.getResources().getDrawable(R.mipmap.most));
    }

    @Override
    public int getItemCount() {
        return popularProductsResponses.size();
    }

    public class MostPopularViewHolder extends RecyclerView.ViewHolder {
        CustomRecomandedBinding binding;

        public MostPopularViewHolder(CustomRecomandedBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }
}
