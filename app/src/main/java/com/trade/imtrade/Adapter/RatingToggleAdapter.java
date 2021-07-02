package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trade.imtrade.databinding.CustomFilterRatingLayoutBinding;
import com.trade.imtrade.databinding.CustomFilterRatingLayoutBinding;

public class RatingToggleAdapter extends RecyclerView.Adapter<RatingToggleAdapter.RatingToggleViewHolder>  {
    Context context;
    String[] amount;
    private OnRatingItemListener onRatingItemListener;

    public RatingToggleAdapter(Context context, String[] amount, OnRatingItemListener onRatingItemListener) {
        this.context = context;
        this.amount = amount;
        this.onRatingItemListener =onRatingItemListener;
    }

    public RatingToggleAdapter() {
    }


    @NonNull
    @Override
    public RatingToggleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CustomFilterRatingLayoutBinding binding = CustomFilterRatingLayoutBinding.inflate(inflater,parent,false);

        return new RatingToggleViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RatingToggleViewHolder holder, int position) {
        holder.binding.textShow.setText(amount[position]);
        holder.binding.textUnshow.setText(amount[position]);

        holder.binding.textUnshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.binding.textShow.setVisibility(View.VISIBLE);
                holder.binding.textUnshow.setVisibility(View.GONE);
                onRatingItemListener.onRatingItemClickListener(position,true);

            }
        });

        holder.binding.textShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.binding.textShow.setVisibility(View.GONE);
                holder.binding.textUnshow.setVisibility(View.VISIBLE);
                onRatingItemListener.onRatingItemClickListener(position,false);
            }
        });
    }

    @Override
    public int getItemCount() {
        return amount.length;
    }

    public class RatingToggleViewHolder extends RecyclerView.ViewHolder {
        CustomFilterRatingLayoutBinding binding;
        public RatingToggleViewHolder(CustomFilterRatingLayoutBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }
    public interface OnRatingItemListener {
        void onRatingItemClickListener(/*List<GetNewCoinRespinse> data,*/ int position,boolean Checked);
    }

}
