package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trade.imtrade.databinding.CustomReviewimageLayoutBinding;
import com.trade.imtrade.databinding.CustomReviewimageLayoutBinding;

public class ReviewImageAdapter extends RecyclerView.Adapter<ReviewImageAdapter.ReviewImageViewHolder>  {
    Context context;
    Integer[] Image;

    public ReviewImageAdapter(Context context, Integer[] Image) {
        this.context = context;
        this.Image = Image;
    }

    public ReviewImageAdapter() {
    }


    @NonNull
    @Override
    public ReviewImageAdapter.ReviewImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CustomReviewimageLayoutBinding binding = CustomReviewimageLayoutBinding.inflate(inflater,parent,false);

        return new ReviewImageAdapter.ReviewImageViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewImageAdapter.ReviewImageViewHolder holder, int position) {
        holder.binding.Image.setImageResource(Image[position]);
        //   Glide.with(context).load("https://picsum.photos/id/896/300/200").into(holder.binding.b);
    }

    @Override
    public int getItemCount() {
        return Image.length;
    }

    public class ReviewImageViewHolder extends RecyclerView.ViewHolder {
        CustomReviewimageLayoutBinding binding;
        public ReviewImageViewHolder(CustomReviewimageLayoutBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }


}
