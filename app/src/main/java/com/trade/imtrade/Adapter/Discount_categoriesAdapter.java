package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.trade.imtrade.Model.ResponseModel.AllCategoriesResponse;
import com.trade.imtrade.SharedPerfence.PrefConf;
import com.trade.imtrade.databinding.DetailsLayoutBinding;
import com.trade.imtrade.databinding.DiscountCategoriesLayoutBinding;

import java.util.List;

public class Discount_categoriesAdapter extends RecyclerView.Adapter<Discount_categoriesAdapter.Discount_categoriesViewHolder>  {
    Context context;
    List<AllCategoriesResponse> allCategoriesResponses;
    private OnCategoriesItemListener onCategoriesItemListener;


    public Discount_categoriesAdapter(Context context, List<AllCategoriesResponse> allCategoriesResponses, OnCategoriesItemListener onCategoriesItemListener) {
        this.context = context;
        this.allCategoriesResponses = allCategoriesResponses;
        this.onCategoriesItemListener = onCategoriesItemListener;

    }

    @NonNull
    @Override
    public Discount_categoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        DiscountCategoriesLayoutBinding binding = DiscountCategoriesLayoutBinding.inflate(inflater,parent,false);

        return new Discount_categoriesAdapter.Discount_categoriesViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Discount_categoriesViewHolder holder, int position) {
        holder.binding.cateName.setText(allCategoriesResponses.get(position).getName());
        Glide.with(context).load(PrefConf.IMAGE_URL+allCategoriesResponses.get(position).getImage()).into(holder.binding.cateImg);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCategoriesItemListener.onCategoriesItemClickListener(allCategoriesResponses,position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return allCategoriesResponses.size();
    }

    public class Discount_categoriesViewHolder extends RecyclerView.ViewHolder {
        DiscountCategoriesLayoutBinding binding;
        public Discount_categoriesViewHolder(DiscountCategoriesLayoutBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }


    public interface OnCategoriesItemListener {
        void onCategoriesItemClickListener(List<AllCategoriesResponse> data, int position);
    }
}
