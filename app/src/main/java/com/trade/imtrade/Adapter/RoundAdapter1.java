package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.trade.imtrade.Model.ResponseModel.AllCategoriesResponse;
import com.trade.imtrade.Model.ResponseModel.HomeProductResponse;
import com.trade.imtrade.Model.ResponseModel.HotelAndCoffeeResponse;
import com.trade.imtrade.R;
import com.trade.imtrade.SharedPerfence.PrefConf;
import com.trade.imtrade.databinding.CustomBrandsLayoutBinding;

import java.util.List;

public class RoundAdapter1 extends RecyclerView.Adapter<RoundAdapter1.BrandsViewHolder> {
    Context context;
    List<HotelAndCoffeeResponse> hotelAndCoffeeResponses;

    public RoundAdapter1(Context context, List<HotelAndCoffeeResponse> hotelAndCoffeeResponses) {
        this.context = context;
        this.hotelAndCoffeeResponses = hotelAndCoffeeResponses;
    }

    public RoundAdapter1() {
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
        holder.binding.brandsName.setText(hotelAndCoffeeResponses.get(position).getName());
        Glide.with(context).load(PrefConf.IMAGE_URL + hotelAndCoffeeResponses.get(position).getImages().get(0)).apply(new RequestOptions().circleCrop()).placeholder(R.drawable.ic_profile_image).into(holder.binding.brandsImage);

    }

    @Override
    public int getItemCount() {
        return hotelAndCoffeeResponses.size();
    }

    public class BrandsViewHolder extends RecyclerView.ViewHolder {
        CustomBrandsLayoutBinding binding;

        public BrandsViewHolder(CustomBrandsLayoutBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }

}