package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.trade.imtrade.Model.ResponseModel.DealOfTheDayResponse;
import com.trade.imtrade.R;
import com.trade.imtrade.SharedPerfence.PrefConf;
import com.trade.imtrade.databinding.CustomRecomandedBinding;

import java.util.List;

public class DiscountByBrandsAdapter extends RecyclerView.Adapter<DiscountByBrandsAdapter.DiscountViewHolder> {
    Context context;
    List<DealOfTheDayResponse> DiscountForYouResponses;
    DiscountForYouClickListener DiscountForYouClickListener;


    public DiscountByBrandsAdapter(Context context, List<DealOfTheDayResponse> DiscountForYouResponses, DiscountForYouClickListener DiscountForYouClickListener) {
        this.context = context;
        this.DiscountForYouResponses = DiscountForYouResponses;
        this.DiscountForYouClickListener = DiscountForYouClickListener;

    }

    public DiscountByBrandsAdapter() {
    }


    @NonNull
    @Override
    public DiscountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CustomRecomandedBinding binding = CustomRecomandedBinding.inflate(inflater, parent, false);

        return new DiscountViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscountViewHolder holder, int position) {
        holder.binding.cateName.setText(DiscountForYouResponses.get(position).getName());
        //  holder.binding.catePrice.setText(DiscountForYouResponses.get(position).getDiscount());

        // holder.binding.cateImg.setImageDrawable(context.getResources().getDrawable(R.mipmap.deal));
        Glide.with(context).load(PrefConf.IMAGE_URL+DiscountForYouResponses.get(position).getImages().get(0)).into(holder.binding.cateImg);

        if (DiscountForYouResponses.get(position).getDiscount()!=null){
            holder.binding.catePrice.setText(DiscountForYouResponses.get(position).getDiscount()+" Rs");
            holder.binding.productOffPrice.setText(DiscountForYouResponses.get(position).getVariables().get(0).getPrice().getMargin()+" %OFF");
            holder.binding.productWorngPrice.setText(DiscountForYouResponses.get(position).getVariables().get(0).getPrice().getMrp()+" Rs");
        }else{
            holder.binding.catePrice.setText(DiscountForYouResponses.get(position).getVariables().get(0).getPrice().getMrp()+" Rs");
            holder.binding.productOffPrice.setVisibility(View.GONE);
            holder.binding.productWorngPrice.setVisibility(View.GONE);

        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiscountForYouClickListener.onDiscountForYouItemClickListener(DiscountForYouResponses,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return DiscountForYouResponses.size();
    }

    public class DiscountViewHolder extends RecyclerView.ViewHolder {
        CustomRecomandedBinding binding;

        public DiscountViewHolder(CustomRecomandedBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }

    public interface DiscountForYouClickListener{
        void onDiscountForYouItemClickListener(List<DealOfTheDayResponse> data, int position);
    }
}
