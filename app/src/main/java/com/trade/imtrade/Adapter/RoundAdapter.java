package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.trade.imtrade.Model.ResponseModel.BrandsResponse;
import com.trade.imtrade.R;
import com.trade.imtrade.SharedPerfence.PrefConf;
import com.trade.imtrade.databinding.CustomBrandsLayoutBinding;

import java.util.List;

public class RoundAdapter extends RecyclerView.Adapter<RoundAdapter.BrandsViewHolder>  {
    Context context;
   // List<BrandsResponse>brandsResponses;
    String brands_Name[];
    Integer brands_Image[];

    public RoundAdapter(Context context, /*List<BrandsResponse>brandsResponses*/ String brands_Name[], Integer brands_Image[]) {
        this.context = context;
        //this.brandsResponses = brandsResponses;
        this.brands_Image = brands_Image;
        this.brands_Name=brands_Name;
    }

    public RoundAdapter() {
    }


    @NonNull
    @Override
    public BrandsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CustomBrandsLayoutBinding binding = CustomBrandsLayoutBinding.inflate(inflater,parent,false);

        return new BrandsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BrandsViewHolder holder, int position) {
        holder.binding.brandsName.setText(brands_Name[position]);
        holder.binding.brandsImage.setImageResource(brands_Image[position]);

    // Glide.with(context).load(PrefConf.IMAGE_URL+brandsResponses.get(position).getLogo()).apply(new RequestOptions().circleCrop()).placeholder(R.drawable.ic_profile_image).into(holder.binding.brandsImage);
    }

    @Override
    public int getItemCount() {
        return brands_Image.length;
    }

    public class BrandsViewHolder extends RecyclerView.ViewHolder {
        CustomBrandsLayoutBinding binding;
        public BrandsViewHolder(CustomBrandsLayoutBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }

}
