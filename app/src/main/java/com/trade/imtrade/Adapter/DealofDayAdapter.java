package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.trade.imtrade.Model.ResponseModel.DealofDayResponse;
import com.trade.imtrade.Model.ResponseModel.HomeProductResponse;
import com.trade.imtrade.SharedPerfence.PrefConf;
import com.trade.imtrade.databinding.DealofdayLayoutBinding;
import com.trade.imtrade.databinding.DealofdayLayoutBinding;

import java.util.List;

public class DealofDayAdapter extends RecyclerView.Adapter<DealofDayAdapter.DealOfDayViewHolder> {
    Context context;
    List<DealofDayResponse> dealofDayResponses;
    private DealOfDaytClickListener dealOfDaytClickListener;

    public DealofDayAdapter(Context context, List<DealofDayResponse> dealofDayResponses, DealOfDaytClickListener dealOfDaytClickListener) {
        this.context = context;
        this.dealofDayResponses = dealofDayResponses;
        this.dealOfDaytClickListener = dealOfDaytClickListener;
    }

    @NonNull
    @Override
    public DealOfDayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        DealofdayLayoutBinding binding = DealofdayLayoutBinding.inflate(inflater, parent, false);

        return new DealOfDayViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DealOfDayViewHolder holder, int position) {


        holder.binding.cateName.setText(dealofDayResponses.get(position).getName());

        if (position==0){
            Glide.with(context).load(PrefConf.IMAGE_URL + dealofDayResponses.get(0).getImages().get(0)).into(holder.binding.cateImg0);
            holder.binding.cateImg0.setVisibility(View.VISIBLE);
            holder.binding.cateImg1.setVisibility(View.GONE);
        }else{
            Glide.with(context).load(PrefConf.IMAGE_URL + dealofDayResponses.get(position).getImages().get(0)).into(holder.binding.cateImg1);
            holder.binding.cateImg0.setVisibility(View.GONE);
            holder.binding.cateImg1.setVisibility(View.VISIBLE);
        }

        if (dealofDayResponses.get(position).getDiscount() != null) {
            holder.binding.catePrice.setText(dealofDayResponses.get(position).getDiscount() + " Rs");
            holder.binding.productOffPrice.setText(dealofDayResponses.get(position).getVariables().get(0).getPrice().getMargin() + " %OFF");
            holder.binding.productWorngPrice.setText(dealofDayResponses.get(position).getVariables().get(0).getPrice().getMrp() + " Rs");
        } else {
            holder.binding.catePrice.setText(dealofDayResponses.get(position).getVariables().get(0).getPrice().getMrp() + " Rs");
            holder.binding.productOffPrice.setVisibility(View.GONE);
            holder.binding.productWorngPrice.setVisibility(View.GONE);

        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dealOfDaytClickListener.onDealOfDayItemClickListener(dealofDayResponses, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        int size=0;
        if (dealofDayResponses.size() <= 3) {
            size = dealofDayResponses.size();
        } else {
            size = 3;
        }
        return size;
    }

    public class DealOfDayViewHolder extends RecyclerView.ViewHolder {
        DealofdayLayoutBinding binding;

        public DealOfDayViewHolder(DealofdayLayoutBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }

    public interface DealOfDaytClickListener {
        void onDealOfDayItemClickListener(List<DealofDayResponse> data, int position);
    }
}
