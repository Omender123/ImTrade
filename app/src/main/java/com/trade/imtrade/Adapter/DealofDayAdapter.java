package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trade.imtrade.databinding.DealofdayLayoutBinding;
import com.trade.imtrade.databinding.DealofdayLayoutBinding;

public class DealofDayAdapter extends RecyclerView.Adapter<DealofDayAdapter.DealOfDayViewHolder>  {
    Context context;
    Integer []cate_Image;

    public DealofDayAdapter(Context context,  Integer[] cate_Image) {
        this.context = context;
         this.cate_Image = cate_Image;
    }

    @NonNull
    @Override
    public DealOfDayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        DealofdayLayoutBinding binding = DealofdayLayoutBinding.inflate(inflater,parent,false);

        return new DealOfDayViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DealOfDayViewHolder holder, int position) {
        holder.binding.cateImg.setImageResource(cate_Image[position]);


    }

    @Override
    public int getItemCount() {
        return cate_Image.length;
    }

    public class DealOfDayViewHolder extends RecyclerView.ViewHolder {
        DealofdayLayoutBinding binding;
        public DealOfDayViewHolder(DealofdayLayoutBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }
}
