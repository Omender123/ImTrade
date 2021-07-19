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
import com.trade.imtrade.databinding.CustomBrandsLayoutBinding;

import java.util.List;

public class BrandsAdapter extends RecyclerView.Adapter<BrandsAdapter.BrandsViewHolder>  {
    Context context;
    List<BrandsResponse>brandsResponses;

    public BrandsAdapter(Context context,List<BrandsResponse>brandsResponses) {
        this.context = context;
        this.brandsResponses = brandsResponses;
    }

    public BrandsAdapter() {
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
        holder.binding.brandsName.setText(brandsResponses.get(position).getName());

     Glide.with(context).load(PrefConf.IMAGE_URL+brandsResponses.get(position).getLogo()).apply(new RequestOptions().circleCrop()).placeholder(R.mipmap.profile1).into(holder.binding.brandsImage);
    }

    @Override
    public int getItemCount() {
        return brandsResponses.size();
    }

    public class BrandsViewHolder extends RecyclerView.ViewHolder {
        CustomBrandsLayoutBinding binding;
        public BrandsViewHolder(CustomBrandsLayoutBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }

}
