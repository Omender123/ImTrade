package com.trade.imtrade.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.trade.imtrade.Model.ResponseModel.AllCategoriesResponse;
import com.trade.imtrade.R;
import com.trade.imtrade.SharedPerfence.PrefConf;
import com.trade.imtrade.databinding.CategoriesLayoutBinding;

import java.util.List;

public class Categories_Adapter extends RecyclerView.Adapter<Categories_Adapter.CategoriesViewHolder>  {

    Context context;
    List<AllCategoriesResponse> allCategoriesResponses;
    private OnCategoriesItemListener onCategoriesItemListener;
    public Categories_Adapter(Context context,List<AllCategoriesResponse> allCategoriesResponses,OnCategoriesItemListener onCategoriesItemListener) {
        this.context = context;
        this.allCategoriesResponses = allCategoriesResponses;
        this.onCategoriesItemListener = onCategoriesItemListener;
    }

    public Categories_Adapter() {
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CategoriesLayoutBinding binding = CategoriesLayoutBinding.inflate(layoutInflater, parent, false);
        //  View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_layout, parent, false);

        return new CategoriesViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {
        holder.binding.productName.setText(allCategoriesResponses.get(position).getName());
        Glide.with(context).load(PrefConf.IMAGE_URL+allCategoriesResponses.get(position).getImage()).into(holder.binding.productImg);

        holder.binding.click.setOnClickListener(new View.OnClickListener() {
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

    public class CategoriesViewHolder extends RecyclerView.ViewHolder {
       CategoriesLayoutBinding binding;

        public CategoriesViewHolder( CategoriesLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


        }
    }

    public interface OnCategoriesItemListener {
        void onCategoriesItemClickListener(List<AllCategoriesResponse> data, int position);
    }
}
