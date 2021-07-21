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

public class MostPopularProductAdapter extends RecyclerView.Adapter<MostPopularProductAdapter.MostPopularViewHolder> {
    Context context;
    List<HomeProductResponse> homeProductRespons;
    HomeProductClickListener homeProductClickListener;

    public MostPopularProductAdapter(Context context, List<HomeProductResponse> homeProductRespons, HomeProductClickListener homeProductClickListener) {
        this.context = context;
        this.homeProductRespons = homeProductRespons;
        this.homeProductClickListener = homeProductClickListener;

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
        holder.binding.cateName.setText(homeProductRespons.get(position).getName());
       Glide.with(context).load(PrefConf.IMAGE_URL+ homeProductRespons.get(position).getImages().get(0)).into(holder.binding.cateImg);

      if (homeProductRespons.get(position).getDiscount()!=null){
          holder.binding.catePrice.setText(homeProductRespons.get(position).getDiscount()+" Rs");
          holder.binding.productOffPrice.setText(homeProductRespons.get(position).getVariables().get(0).getPrice().getMargin()+" %OFF");
          holder.binding.productWorngPrice.setText(homeProductRespons.get(position).getVariables().get(0).getPrice().getMrp()+" Rs");
      }else{
          holder.binding.catePrice.setText(homeProductRespons.get(position).getVariables().get(0).getPrice().getMrp()+" Rs");
          holder.binding.productOffPrice.setVisibility(View.GONE);
          holder.binding.productWorngPrice.setVisibility(View.GONE);

      }

      // holder.binding.cateImg.setImageDrawable(context.getResources().getDrawable(R.mipmap.most));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeProductClickListener.onHomeProductItemClickListener(homeProductRespons,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return homeProductRespons.size();
    }

    public class MostPopularViewHolder extends RecyclerView.ViewHolder {
        CustomRecomandedBinding binding;

        public MostPopularViewHolder(CustomRecomandedBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }

    public interface HomeProductClickListener{
        void onHomeProductItemClickListener(List<HomeProductResponse> data, int position);
    }
}
