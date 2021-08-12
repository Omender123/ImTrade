package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trade.imtrade.databinding.DetailsLayoutBinding;
import com.trade.imtrade.databinding.DiscountCategoriesLayoutBinding;

public class Discount_categoriesAdapter extends RecyclerView.Adapter<Discount_categoriesAdapter.Discount_categoriesViewHolder>  {
    Context context;
    String []cate_name;
    Integer []cate_Image;

    public Discount_categoriesAdapter(Context context, String[] cate_name, Integer[] cate_Image) {
        this.context = context;
        this.cate_name = cate_name;
        this.cate_Image = cate_Image;
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
        holder.binding.cateImg.setImageResource(cate_Image[position]);
        holder.binding.cateName.setText(cate_name[position]);

    }

    @Override
    public int getItemCount() {
        return cate_Image.length;
    }

    public class Discount_categoriesViewHolder extends RecyclerView.ViewHolder {
        DiscountCategoriesLayoutBinding binding;
        public Discount_categoriesViewHolder(DiscountCategoriesLayoutBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }
}
