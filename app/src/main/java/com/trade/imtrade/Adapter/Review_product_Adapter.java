package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.trade.imtrade.Model.ResponseModel.RelatedResponse;
import com.trade.imtrade.SharedPerfence.PrefConf;
import com.trade.imtrade.databinding.CustomRecomandedBinding;

public class Review_product_Adapter extends RecyclerView.Adapter<Review_product_Adapter.ReviewProductViewHolder>  {
    Context context;
   RelatedResponse relatedResponse;

    public Review_product_Adapter(Context context, RelatedResponse relatedResponse) {
        this.context = context;
        this.relatedResponse = relatedResponse;
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
        holder.binding.cateName.setText(relatedResponse.getRelated().getSimilar().get(position).getName());
        //  holder.binding.catePrice.setText(relatedResponse.getRelated().getSimilar().get(position).getDiscount());

        // holder.binding.cateImg.setImageDrawable(context.getResources().getDrawable(R.mipmap.deal));
        Glide.with(context).load(PrefConf.IMAGE_URL+relatedResponse.getRelated().getSimilar().get(position).getImages().get(0)).into(holder.binding.cateImg);

        if (relatedResponse.getRelated().getSimilar().get(position).getDiscount()!=null){
            holder.binding.catePrice.setText(relatedResponse.getRelated().getSimilar().get(position).getDiscount()+" Rs");
            holder.binding.productOffPrice.setText(relatedResponse.getRelated().getSimilar().get(position).getVariables().get(0).getPrice().getMargin()+" %OFF");
            holder.binding.productWorngPrice.setText(relatedResponse.getRelated().getSimilar().get(position).getVariables().get(0).getPrice().getMrp()+" Rs");
        }else{
            holder.binding.catePrice.setText(relatedResponse.getRelated().getSimilar().get(position).getVariables().get(0).getPrice().getMrp()+" Rs");
            holder.binding.productOffPrice.setVisibility(View.GONE);
            holder.binding.productWorngPrice.setVisibility(View.GONE);

        }


    }

    @Override
    public int getItemCount() {
        return relatedResponse.getRelated().getSimilar().size();
    }

    public class ReviewProductViewHolder extends RecyclerView.ViewHolder {
        CustomRecomandedBinding binding;
        public ReviewProductViewHolder(CustomRecomandedBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }


}
