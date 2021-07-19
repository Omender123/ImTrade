package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trade.imtrade.Model.ResponseModel.ProductDetailsResponse;
import com.trade.imtrade.databinding.CustomStorageLayoutBinding;
import com.trade.imtrade.databinding.CustomStorageLayoutBinding;

public class StorageAdapter  extends RecyclerView.Adapter<StorageAdapter.BrandsToggleViewHolder>  {
    Context context;
    ProductDetailsResponse productDetailsResponse;
    private OnToggleItemListener onToggleItemListener;
    private int lastSelectedPosition = 0;

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
        CustomStorageLayoutBinding binding = CustomStorageLayoutBinding.inflate(inflater,parent,false);

        return new BrandsToggleViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BrandsToggleViewHolder holder, int position) {
        holder.binding.radioButton.setText(productDetailsResponse.getStorage().get(position).getSize());


             holder.binding.radioButton.setChecked(lastSelectedPosition==position);



        holder.binding.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                    lastSelectedPosition = position;
                    notifyDataSetChanged();

                    onToggleItemListener.onToggleItemClickListener(productDetailsResponse,position);

            }
        });


    }

    @Override
    public int getItemCount() {
        return productDetailsResponse.getStorage().size();
    }

    public class BrandsToggleViewHolder extends RecyclerView.ViewHolder {
        CustomStorageLayoutBinding binding;
        public BrandsToggleViewHolder(CustomStorageLayoutBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }
    public interface OnToggleItemListener {
        void onToggleItemClickListener(ProductDetailsResponse productDetailsResponse, int position);
    }


}
