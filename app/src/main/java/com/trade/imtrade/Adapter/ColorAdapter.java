package com.trade.imtrade.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.trade.imtrade.Model.ResponseModel.AllCategoriesResponse;
import com.trade.imtrade.Model.ResponseModel.ProductDetailsResponse;
import com.trade.imtrade.R;
import com.trade.imtrade.SharedPerfence.PrefConf;
import com.trade.imtrade.databinding.CustomColorLayoutBinding;
import com.trade.imtrade.databinding.CustomColorLayoutBinding;

import java.util.ArrayList;
import java.util.List;

public class ColorAdapter  extends RecyclerView.Adapter<ColorAdapter.ColorViewHolder> {
    Context context;
    ProductDetailsResponse productDetailsResponse;
    private OnColorItemListener onColorItemListener;
    public ColorAdapter(Context context, ProductDetailsResponse productDetailsResponse,OnColorItemListener onColorItemListener) {
        this.context = context;
        this.productDetailsResponse = productDetailsResponse;
        this.onColorItemListener = onColorItemListener;
    }

    public ColorAdapter() {
    }

    @NonNull
    @Override
    public ColorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CustomColorLayoutBinding binding = CustomColorLayoutBinding.inflate(layoutInflater, parent, false);
        //  View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_layout, parent, false);

        return new ColorViewHolder(binding);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ColorViewHolder holder, int position) {
      //  String color ="#000000";
      holder.binding.cardColor.setCardBackgroundColor(Color.parseColor(productDetailsResponse.getColor().get(position).getCode()));
        holder.binding.colorName.setText(productDetailsResponse.getColor().get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onColorItemListener.onOnColorClickListener(productDetailsResponse,position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return productDetailsResponse.getColor().size();
    }

    public class ColorViewHolder extends RecyclerView.ViewHolder {
        CustomColorLayoutBinding binding;

        public ColorViewHolder( CustomColorLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


        }
    }

    public interface OnColorItemListener {
        void onOnColorClickListener(ProductDetailsResponse productDetailsResponse, int position);
    }

}
