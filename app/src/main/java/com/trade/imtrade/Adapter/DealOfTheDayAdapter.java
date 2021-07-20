package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.trade.imtrade.Model.ResponseModel.AllCategoriesResponse;
import com.trade.imtrade.Model.ResponseModel.DealOfTheDayResponse;
import com.trade.imtrade.R;
import com.trade.imtrade.SharedPerfence.PrefConf;
import com.trade.imtrade.databinding.CustomRecomandedBinding;

import java.util.List;

public class DealOfTheDayAdapter extends RecyclerView.Adapter<DealOfTheDayAdapter.DealViewHolder> {
    Context context;
    List<DealOfTheDayResponse>dealOfTheDayResponses;
    DealOfTheDayClickListener dealOfTheDayClickListener;

    public DealOfTheDayAdapter(Context context, List<DealOfTheDayResponse>dealOfTheDayResponses,DealOfTheDayClickListener dealOfTheDayClickListener) {
        this.context = context;
        this.dealOfTheDayResponses = dealOfTheDayResponses;
        this.dealOfTheDayClickListener = dealOfTheDayClickListener;

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
        holder.binding.cateName.setText(dealOfTheDayResponses.get(position).getName());
      //  holder.binding.catePrice.setText(dealOfTheDayResponses.get(position).getDiscount());

        // holder.binding.cateImg.setImageDrawable(context.getResources().getDrawable(R.mipmap.deal));
      Glide.with(context).load(PrefConf.IMAGE_URL+dealOfTheDayResponses.get(position).getImages().get(0)).into(holder.binding.cateImg);

        if (dealOfTheDayResponses.get(position).getDiscount()!=null){
            holder.binding.catePrice.setText(dealOfTheDayResponses.get(position).getDiscount()+" Rs");
            holder.binding.productOffPrice.setText(dealOfTheDayResponses.get(position).getVariables().get(0).getPrice().getMargin()+" %OFF");
            holder.binding.productWorngPrice.setText(dealOfTheDayResponses.get(position).getVariables().get(0).getPrice().getMrp()+" Rs");
        }else{
            holder.binding.catePrice.setText(dealOfTheDayResponses.get(position).getVariables().get(0).getPrice().getMrp()+" Rs");
            holder.binding.productOffPrice.setVisibility(View.GONE);
            holder.binding.productWorngPrice.setVisibility(View.GONE);

        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dealOfTheDayClickListener.onDealOfTheDayItemClickListener(dealOfTheDayResponses,position);
        }
    });
    }

    @Override
    public int getItemCount() {
        return dealOfTheDayResponses.size();
    }

    public class DealViewHolder extends RecyclerView.ViewHolder {
        CustomRecomandedBinding binding;

        public DealViewHolder(CustomRecomandedBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }

    public interface DealOfTheDayClickListener{
        void onDealOfTheDayItemClickListener(List<DealOfTheDayResponse> data, int position);
    }
}
