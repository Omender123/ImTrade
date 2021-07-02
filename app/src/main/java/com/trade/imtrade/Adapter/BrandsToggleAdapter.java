package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.trade.imtrade.R;
import com.trade.imtrade.databinding.CustomBrandsToggleLayoutBinding;
import com.trade.imtrade.databinding.CustomBrandsToggleLayoutBinding;

public class BrandsToggleAdapter extends RecyclerView.Adapter<BrandsToggleAdapter.BrandsToggleViewHolder>  {
    Context context;
    String[] amount;
    private  OnToggleItemListener onToggleItemListener;

    public BrandsToggleAdapter(Context context, String[] amount,OnToggleItemListener onToggleItemListener) {
        this.context = context;
        this.amount = amount;
         this.onToggleItemListener =onToggleItemListener;
    }

    public BrandsToggleAdapter() {
    }


    @NonNull
    @Override
    public BrandsToggleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CustomBrandsToggleLayoutBinding binding = CustomBrandsToggleLayoutBinding.inflate(inflater,parent,false);

        return new BrandsToggleViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BrandsToggleViewHolder holder, int position) {
        holder.binding.textShow.setText(amount[position]);
        holder.binding.textUnshow.setText(amount[position]);

        holder.binding.textUnshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.binding.textShow.setVisibility(View.VISIBLE);
                holder.binding.textUnshow.setVisibility(View.GONE);
                onToggleItemListener.onToggleItemClickListener(position,true);

            }
        });

        holder.binding.textShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.binding.textShow.setVisibility(View.GONE);
                holder.binding.textUnshow.setVisibility(View.VISIBLE);
                onToggleItemListener.onToggleItemClickListener(position,false);

            }
        });
         }

    @Override
    public int getItemCount() {
        return amount.length;
    }

    public class BrandsToggleViewHolder extends RecyclerView.ViewHolder {
        CustomBrandsToggleLayoutBinding binding;
        public BrandsToggleViewHolder(CustomBrandsToggleLayoutBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }
    public interface OnToggleItemListener {
        void onToggleItemClickListener(/*List<GetNewCoinRespinse> data,*/ int position,boolean Checked);
    }


}
