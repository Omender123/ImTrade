package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trade.imtrade.Model.ResponseModel.ProductDetailsResponse;
import com.trade.imtrade.databinding.CustomBrandsToggleLayoutBinding;

public class StorageAdapter  extends RecyclerView.Adapter<StorageAdapter.BrandsToggleViewHolder>  {
    Context context;
    ProductDetailsResponse productDetailsResponse;
    private OnToggleItemListener onToggleItemListener;
    Boolean aBoolean=false;

    public StorageAdapter(Context context, ProductDetailsResponse productDetailsResponse,OnToggleItemListener onToggleItemListener) {
        this.context = context;
        this.productDetailsResponse = productDetailsResponse;
        this.onToggleItemListener =onToggleItemListener;
    }

    public StorageAdapter() {
    }


    @NonNull
    @Override
    public BrandsToggleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CustomBrandsToggleLayoutBinding binding = CustomBrandsToggleLayoutBinding.inflate(inflater,parent,false);

        return new BrandsToggleViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BrandsToggleViewHolder holder, int position) {
        holder.binding.textShow.setText(productDetailsResponse.getStorage().get(position).getSize());
        holder.binding.textUnshow.setText(productDetailsResponse.getStorage().get(position).getSize());


        if (productDetailsResponse.getStorage().get(position).getSize().equalsIgnoreCase(productDetailsResponse.getStorage().get(0).getSize())){
            holder.binding.textShow.setVisibility(View.VISIBLE);
        }

        holder.binding.textUnshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aBoolean=true;
                holder.binding.textShow.setVisibility(View.VISIBLE);
                holder.binding.textUnshow.setVisibility(View.GONE);
                onToggleItemListener.onToggleItemClickListener(productDetailsResponse,position,true);

            }
        });

        holder.binding.textShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aBoolean=false;
                holder.binding.textShow.setVisibility(View.GONE);
                holder.binding.textUnshow.setVisibility(View.VISIBLE);
                onToggleItemListener.onToggleItemClickListener(productDetailsResponse,position,false);

            }
        });
    }

    @Override
    public int getItemCount() {
        return productDetailsResponse.getStorage().size();
    }

    public class BrandsToggleViewHolder extends RecyclerView.ViewHolder {
        CustomBrandsToggleLayoutBinding binding;
        public BrandsToggleViewHolder(CustomBrandsToggleLayoutBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }
    public interface OnToggleItemListener {
        void onToggleItemClickListener(ProductDetailsResponse productDetailsResponse, int position,boolean Checked);
    }


}
