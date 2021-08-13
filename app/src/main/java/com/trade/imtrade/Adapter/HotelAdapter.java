package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trade.imtrade.databinding.CustomHotelLayoutBinding;
import com.trade.imtrade.databinding.CustomHotelLayoutBinding;

public class HotelAdapter  extends RecyclerView.Adapter<HotelAdapter.HotelViewHolder> {
    Context context;
     Integer brands_Image[];

    public HotelAdapter(Context context,  Integer brands_Image[]) {
        this.context = context;
         this.brands_Image = brands_Image;
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
        holder.binding.hotelImage.setImageResource(brands_Image[position]);

        // Glide.with(context).load(PrefConf.IMAGE_URL+brandsResponses.get(position).getLogo()).apply(new RequestOptions().circleCrop()).placeholder(R.drawable.ic_profile_image).into(holder.binding.brandsImage);
    }

    @Override
    public int getItemCount() {
        return brands_Image.length;
    }

    public class HotelViewHolder extends RecyclerView.ViewHolder {
        CustomHotelLayoutBinding binding;

        public HotelViewHolder(CustomHotelLayoutBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }
}
