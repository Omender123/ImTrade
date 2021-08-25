package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trade.imtrade.databinding.CustomReviewVideoLayoutBinding;
import com.trade.imtrade.databinding.CustomReviewVideoLayoutBinding;

public class ReviewVideoAdapter extends RecyclerView.Adapter<ReviewVideoAdapter.ReviewVideoViewHolder>  {
    Context context;
    Integer[] Image;

    public ReviewVideoAdapter(Context context, Integer[] Image) {
        this.context = context;
        this.Image = Image;
    }

    public ReviewVideoAdapter() {
    }


    @NonNull
    @Override
    public ReviewVideoAdapter.ReviewVideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CustomReviewVideoLayoutBinding binding = CustomReviewVideoLayoutBinding.inflate(inflater,parent,false);

        return new ReviewVideoAdapter.ReviewVideoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewVideoAdapter.ReviewVideoViewHolder holder, int position) {
        holder.binding.videoImage.setImageResource(Image[position]);
        //   Glide.with(context).load("https://picsum.photos/id/896/300/200").into(holder.binding.b);
    }

    @Override
    public int getItemCount() {
        return Image.length;
    }

    public class ReviewVideoViewHolder extends RecyclerView.ViewHolder {
        CustomReviewVideoLayoutBinding binding;
        public ReviewVideoViewHolder(CustomReviewVideoLayoutBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }


}
