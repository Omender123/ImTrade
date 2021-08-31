package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.trade.imtrade.Model.ResponseModel.ContinueYourHuntResponse;
import com.trade.imtrade.SharedPerfence.PrefConf;
import com.trade.imtrade.databinding.CustomRecomandedBinding;

import java.util.List;

public class SuggestionProductAdapter extends RecyclerView.Adapter<SuggestionProductAdapter.SuggestionProductViewHolder> {
    Context context;
    ContinueYourHuntResponse RecommendedProductResponses;

    public SuggestionProductAdapter(Context context, ContinueYourHuntResponse RecommendedProductResponses) {
        this.context = context;
        this.RecommendedProductResponses = RecommendedProductResponses;

    }

    public SuggestionProductAdapter() {
    }

    @NonNull
    @Override
    public SuggestionProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CustomRecomandedBinding binding = CustomRecomandedBinding.inflate(inflater, parent, false);

        return new SuggestionProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SuggestionProductViewHolder holder, int position) {

        holder.binding.cateName.setText(RecommendedProductResponses.getProducts().get(position).getName());
        Glide.with(context).load(PrefConf.IMAGE_URL + RecommendedProductResponses.getProducts().get(position).getImages().get(0)).into(holder.binding.cateImg);

        if (RecommendedProductResponses.getProducts().get(position).getDiscount() != null) {
            holder.binding.catePrice.setText(RecommendedProductResponses.getProducts().get(position).getDiscount() + " Rs");
            holder.binding.productOffPrice.setText(RecommendedProductResponses.getProducts().get(position).getVariables().get(0).getPrice().getMargin() + " %OFF");
            holder.binding.productWorngPrice.setText(RecommendedProductResponses.getProducts().get(position).getVariables().get(0).getPrice().getMrp() + " Rs");
        } else {
            holder.binding.catePrice.setText(RecommendedProductResponses.getProducts().get(position).getVariables().get(0).getPrice().getMrp() + " Rs");
            holder.binding.productOffPrice.setVisibility(View.GONE);
            holder.binding.productWorngPrice.setVisibility(View.GONE);

        }


    }

    @Override
    public int getItemCount() {
        return RecommendedProductResponses.getProducts().size();
    }

    public class SuggestionProductViewHolder extends RecyclerView.ViewHolder {
        CustomRecomandedBinding binding;

        public SuggestionProductViewHolder(CustomRecomandedBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }


}
