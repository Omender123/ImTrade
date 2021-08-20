package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.trade.imtrade.Model.ResponseModel.AllCategoriesResponse;
import com.trade.imtrade.R;
import com.trade.imtrade.SharedPerfence.PrefConf;
import com.trade.imtrade.databinding.CustomBrandsLayoutBinding;

import java.util.List;

public class RoundAdapter extends RecyclerView.Adapter<RoundAdapter.BrandsViewHolder> {
    Context context;
    List<AllCategoriesResponse> allCategoriesResponses;
    private OnCategoriesItemListener onCategoriesItemListener;


    public RoundAdapter(Context context, List<AllCategoriesResponse> allCategoriesResponses, OnCategoriesItemListener onCategoriesItemListener) {
        this.context = context;
        this.allCategoriesResponses = allCategoriesResponses;
        this.onCategoriesItemListener = onCategoriesItemListener;

    }

    public RoundAdapter() {
    }


    @NonNull
    @Override
    public BrandsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CustomBrandsLayoutBinding binding = CustomBrandsLayoutBinding.inflate(inflater, parent, false);

        return new BrandsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BrandsViewHolder holder, int position) {
        holder.binding.brandsName.setText(allCategoriesResponses.get(position).getName());

        Glide.with(context).load(PrefConf.IMAGE_URL + allCategoriesResponses.get(position).getImage()).apply(new RequestOptions().circleCrop()).placeholder(R.drawable.ic_profile_image).into(holder.binding.brandsImage);

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

    public class BrandsViewHolder extends RecyclerView.ViewHolder {
        CustomBrandsLayoutBinding binding;

        public BrandsViewHolder(CustomBrandsLayoutBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }

    public interface OnCategoriesItemListener {
        void onCategoriesItemClickListener(List<AllCategoriesResponse> data, int position);
    }

}
