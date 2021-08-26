package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.trade.imtrade.Model.ResponseModel.ProductDetailsResponse;
import com.trade.imtrade.databinding.ProfileItemLayoutBinding;

import java.util.ArrayList;

public class Other_Info_Adapter  extends RecyclerView.Adapter<Other_Info_Adapter.OtherInfo_itemViewHolder> {
    ProductDetailsResponse ItemList;
    Context context;
    private OnOtherInfoItemListener onOtherInfoItemListener;

    public Other_Info_Adapter(ProductDetailsResponse itemList, Context context, OnOtherInfoItemListener onOtherInfoItemListener) {
        this.ItemList = itemList;
        this.context = context;
        this.onOtherInfoItemListener = onOtherInfoItemListener;
    }

    public Other_Info_Adapter() {
    }

    @NonNull
    @Override
    public Other_Info_Adapter.OtherInfo_itemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ProfileItemLayoutBinding binding = ProfileItemLayoutBinding.inflate(layoutInflater, parent, false);

        return new Other_Info_Adapter.OtherInfo_itemViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull Other_Info_Adapter.OtherInfo_itemViewHolder holder, int position) {

        holder.binding.text.setPadding(0,5,0,5);
        holder.binding.text.setTextSize(12);
        holder.binding.text.setText(ItemList.getOtherInfo().get(position).getDetail());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOtherInfoItemListener.onOtherInfoItemClickListener(ItemList,position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return ItemList.getOtherInfo().size();
    }

    public class OtherInfo_itemViewHolder extends RecyclerView.ViewHolder {
        ProfileItemLayoutBinding binding;

        public OtherInfo_itemViewHolder(ProfileItemLayoutBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }

    public interface OnOtherInfoItemListener {
        void onOtherInfoItemClickListener(ProductDetailsResponse ItemList, int position);
    }
}
