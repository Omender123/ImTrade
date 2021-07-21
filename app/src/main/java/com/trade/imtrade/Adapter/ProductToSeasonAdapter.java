package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.trade.imtrade.Model.ResponseModel.HomeProductResponse;
import com.trade.imtrade.R;
import com.trade.imtrade.SharedPerfence.PrefConf;
import com.trade.imtrade.databinding.CustomRecomandedBinding;

import java.util.List;

public class ProductToSeasonAdapter extends RecyclerView.Adapter<ProductToSeasonAdapter.ProductViewHolder>  {
    Context context;
    List<HomeProductResponse> ProductToSeasonResponses;
    HomeProductClickListener homeProductClickListener;


    public ProductToSeasonAdapter(Context context, List<HomeProductResponse> ProductToSeasonResponses,HomeProductClickListener homeProductClickListener) {
        this.context = context;
        this.ProductToSeasonResponses = ProductToSeasonResponses;
        this.homeProductClickListener=homeProductClickListener;
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
        holder.binding.cateName.setText(ProductToSeasonResponses.get(position).getName());
        Glide.with(context).load(PrefConf.IMAGE_URL+ProductToSeasonResponses.get(position).getImages().get(0)).into(holder.binding.cateImg);

        if (ProductToSeasonResponses.get(position).getDiscount()!=null){
            holder.binding.catePrice.setText(ProductToSeasonResponses.get(position).getDiscount()+" Rs");
            holder.binding.productOffPrice.setText(ProductToSeasonResponses.get(position).getVariables().get(0).getPrice().getMargin()+" %OFF");
            holder.binding.productWorngPrice.setText(ProductToSeasonResponses.get(position).getVariables().get(0).getPrice().getMrp()+" Rs");
        }else{
            holder.binding.catePrice.setText(ProductToSeasonResponses.get(position).getVariables().get(0).getPrice().getMrp()+" Rs");
            holder.binding.productOffPrice.setVisibility(View.GONE);
            holder.binding.productWorngPrice.setVisibility(View.GONE);

        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeProductClickListener.onHomeProductItemClickListener(ProductToSeasonResponses,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ProductToSeasonResponses.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        CustomRecomandedBinding binding;
        public ProductViewHolder(CustomRecomandedBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }
    public interface HomeProductClickListener{
        void onHomeProductItemClickListener(List<HomeProductResponse> data, int position);
    }
    
}
