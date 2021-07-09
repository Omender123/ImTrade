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
import com.trade.imtrade.databinding.CustomCategorieslayoutfragmentBinding;

import java.util.List;

public class All_Categories_Adapter extends RecyclerView.Adapter<All_Categories_Adapter.AllCategoriesViewHolder>  {

    Context context;
   List<AllCategoriesResponse>allCategoriesResponses;
    private  OnAllCategoriesItemListener onAllCategoriesItemListener;
    public All_Categories_Adapter(Context context, List<AllCategoriesResponse>allCategoriesResponses,OnAllCategoriesItemListener onAllCategoriesItemListener) {
        this.context = context;
        this.allCategoriesResponses = allCategoriesResponses;
        this.onAllCategoriesItemListener = onAllCategoriesItemListener;
    }

    public All_Categories_Adapter() {
    }

    @NonNull
    @Override
    public AllCategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CustomCategorieslayoutfragmentBinding binding = CustomCategorieslayoutfragmentBinding.inflate(layoutInflater, parent, false);
        //  View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_layout, parent, false);

        return new AllCategoriesViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AllCategoriesViewHolder holder, int position) {
        holder.binding.productName.setText(allCategoriesResponses.get(position).getName());
        Glide.with(context).load(PrefConf.IMAGE_URL+allCategoriesResponses.get(position).getImage()).into(holder.binding.productImg);

        holder.binding.categories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAllCategoriesItemListener.onAllCategoriesItemClickListener(allCategoriesResponses,position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return allCategoriesResponses.size();
    }

    public class AllCategoriesViewHolder extends RecyclerView.ViewHolder {
        CustomCategorieslayoutfragmentBinding binding;

        public AllCategoriesViewHolder( CustomCategorieslayoutfragmentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


        }
    }

    public interface OnAllCategoriesItemListener {
        void onAllCategoriesItemClickListener(List<AllCategoriesResponse> data, int position);
    }

}
