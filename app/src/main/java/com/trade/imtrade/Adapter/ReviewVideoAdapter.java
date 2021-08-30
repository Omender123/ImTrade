package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.trade.imtrade.Model.ResponseModel.ReviewResponse;
import com.trade.imtrade.SharedPerfence.PrefConf;
import com.trade.imtrade.databinding.CustomReviewVideoLayoutBinding;
import com.trade.imtrade.databinding.CustomReviewVideoLayoutBinding;

import java.util.List;

public class ReviewVideoAdapter extends RecyclerView.Adapter<ReviewVideoAdapter.ReviewVideoViewHolder>  {
    Context context;
    List<ReviewResponse> reviewResponse;

    public ReviewVideoAdapter(Context context, List<ReviewResponse> reviewResponse) {
        this.context = context;
        this.reviewResponse = reviewResponse;
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

         Glide.with(context).load(PrefConf.IMAGE_URL + reviewResponse.get(position).getThumbnail()).into(holder.binding.videoImage);
    }

    @Override
    public int getItemCount() {
        return reviewResponse.size();
    }

    public class ReviewVideoViewHolder extends RecyclerView.ViewHolder {
        CustomReviewVideoLayoutBinding binding;
        public ReviewVideoViewHolder(CustomReviewVideoLayoutBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }


}
