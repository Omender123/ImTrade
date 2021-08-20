package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.trade.imtrade.Model.ResponseModel.HotelAndCoffeeResponse;
import com.trade.imtrade.SharedPerfence.PrefConf;
import com.trade.imtrade.databinding.CustomHotelLayoutBinding;
import com.trade.imtrade.databinding.CustomHotelLayoutBinding;

import java.util.List;

public class HotelAdapter  extends RecyclerView.Adapter<HotelAdapter.HotelViewHolder> {
    Context context;
    List<HotelAndCoffeeResponse> hotelAndCoffeeResponses;

    public HotelAdapter(Context context, List<HotelAndCoffeeResponse> hotelAndCoffeeResponses) {
        this.context = context;
        this.hotelAndCoffeeResponses = hotelAndCoffeeResponses;
    }

    public HotelAdapter() {
    }


    @NonNull
    @Override
    public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CustomHotelLayoutBinding binding = CustomHotelLayoutBinding.inflate(inflater, parent, false);

        return new HotelViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelViewHolder holder, int position) {
        holder.binding.hotelName.setText(hotelAndCoffeeResponses.get(position).getName());
       // holder.binding.hotelPrice.setText(hotelAndCoffeeResponses.get(position).getMenu().);


        Glide.with(context).load(PrefConf.IMAGE_URL+hotelAndCoffeeResponses.get(position).getImages().get(0)).into(holder.binding.hotelImage);
    }

    @Override
    public int getItemCount() {
        return hotelAndCoffeeResponses.size();
    }

    public class HotelViewHolder extends RecyclerView.ViewHolder {
        CustomHotelLayoutBinding binding;

        public HotelViewHolder(CustomHotelLayoutBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }
}
