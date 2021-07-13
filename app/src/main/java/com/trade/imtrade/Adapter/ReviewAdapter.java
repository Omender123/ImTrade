package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trade.imtrade.R;
import com.trade.imtrade.databinding.ReviewLayoutBinding;
import com.trade.imtrade.databinding.ReviewLayoutBinding;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {
    Context context;
    String[] amount;

    public ReviewAdapter(Context context, String[] amount) {
        this.context = context;
        this.amount = amount;
    }

    public ReviewAdapter() {
    }


    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ReviewLayoutBinding binding = ReviewLayoutBinding.inflate(inflater, parent, false);

        return new ReviewViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        holder.binding.textName.setText(amount[position]);

    }

    @Override
    public int getItemCount() {
        return amount.length;
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder {
        ReviewLayoutBinding binding;

        public ReviewViewHolder(ReviewLayoutBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }
}