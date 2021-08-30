package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.trade.imtrade.Model.ResponseModel.ReviewResponse;
import com.trade.imtrade.SharedPerfence.PrefConf;
import com.trade.imtrade.databinding.CustomReviewimageLayoutBinding;
import com.trade.imtrade.databinding.CustomReviewimageLayoutBinding;

import java.util.List;

public class ReviewImageAdapter extends RecyclerView.Adapter<ReviewImageAdapter.ReviewImageViewHolder>  {
    Context context;
    List<ReviewResponse> reviewResponse;

    public ReviewImageAdapter(Context context, List<ReviewResponse> reviewResponse) {
        this.context = context;
        this.reviewResponse = reviewResponse;
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

         Glide.with(context).load(PrefConf.IMAGE_URL + reviewResponse.get(position).getImage()).into(holder.binding.Image);
    }

    @Override
    public int getItemCount() {
        return reviewResponse.size();
    }

    public class ReviewImageViewHolder extends RecyclerView.ViewHolder {
        CustomReviewimageLayoutBinding binding;
        public ReviewImageViewHolder(CustomReviewimageLayoutBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }


}
