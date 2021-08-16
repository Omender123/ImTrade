package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
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

public class RoundAdapter1 extends RecyclerView.Adapter<RoundAdapter1.BrandsViewHolder> {
    Context context;
    String[] cate_name;
    Integer[] cate_Image;

    public RoundAdapter1(Context context,String[] cate_name,Integer[] cate_Image) {
        this.context = context;
        this.cate_name = cate_name;
        this.cate_Image = cate_Image;
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
        holder.binding.brandsName.setText(cate_name[position]);
        holder.binding.brandsImage.setImageResource(cate_Image[position]);
      //  Glide.with(context).load(PrefConf.IMAGE_URL + allCategoriesResponses.get(position).getImage()).apply(new RequestOptions().circleCrop()).placeholder(R.drawable.ic_profile_image).into(holder.binding.brandsImage);
    }

    @Override
    public int getItemCount() {
        return cate_Image.length;
    }

    public class BrandsViewHolder extends RecyclerView.ViewHolder {
        CustomBrandsLayoutBinding binding;

        public BrandsViewHolder(CustomBrandsLayoutBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }

}