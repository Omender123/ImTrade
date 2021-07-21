package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.trade.imtrade.Model.ResponseModel.HomeProductResponse;
import com.trade.imtrade.SharedPerfence.PrefConf;
import com.trade.imtrade.databinding.CustomRecomandedBinding;

import java.util.List;

public class HomeProduct_Adapter extends RecyclerView.Adapter<HomeProduct_Adapter.HomeProductViewHolder> {
    Context context;
    List<HomeProductResponse> RecommendedProductResponses;
    HomeProductClickListener homeProductClickListener;

    public HomeProduct_Adapter(Context context, List<HomeProductResponse> RecommendedProductResponses, HomeProductClickListener homeProductClickListener) {
        this.context = context;
        this.RecommendedProductResponses = RecommendedProductResponses;
        this.homeProductClickListener = homeProductClickListener;

    }

    public HomeProduct_Adapter() {
    }


    @NonNull
    @Override
    public HomeProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CustomRecomandedBinding binding = CustomRecomandedBinding.inflate(inflater, parent, false);

        return new HomeProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeProductViewHolder holder, int position) {

        holder.binding.cateName.setText(RecommendedProductResponses.get(position).getName());
        //  holder.binding.catePrice.setText(RecommendedProductResponses.get(position).getDiscount());

        // holder.binding.cateImg.setImageDrawable(context.getResources().getDrawable(R.mipmap.deal));
        Glide.with(context).load(PrefConf.IMAGE_URL+RecommendedProductResponses.get(position).getImages().get(0)).into(holder.binding.cateImg);

        if (RecommendedProductResponses.get(position).getDiscount()!=null){
            holder.binding.catePrice.setText(RecommendedProductResponses.get(position).getDiscount()+" Rs");
            holder.binding.productOffPrice.setText(RecommendedProductResponses.get(position).getVariables().get(0).getPrice().getMargin()+" %OFF");
            holder.binding.productWorngPrice.setText(RecommendedProductResponses.get(position).getVariables().get(0).getPrice().getMrp()+" Rs");
        }else{
            holder.binding.catePrice.setText(RecommendedProductResponses.get(position).getVariables().get(0).getPrice().getMrp()+" Rs");
            holder.binding.productOffPrice.setVisibility(View.GONE);
            holder.binding.productWorngPrice.setVisibility(View.GONE);

        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeProductClickListener.onHomeProductItemClickListener(RecommendedProductResponses,position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return RecommendedProductResponses.size();
    }

    public class HomeProductViewHolder extends RecyclerView.ViewHolder {
        CustomRecomandedBinding binding;

        public HomeProductViewHolder(CustomRecomandedBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }

    public interface HomeProductClickListener{
        void onHomeProductItemClickListener(List<HomeProductResponse> data, int position);
    }


}
