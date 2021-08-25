package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trade.imtrade.Model.ResponseModel.ProductDetailsResponse;
import com.trade.imtrade.R;
import com.trade.imtrade.databinding.ReviewLayoutBinding;
import com.trade.imtrade.databinding.ReviewLayoutBinding;
import com.trade.imtrade.utils.AppUtils;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {
    Context context;
    ProductDetailsResponse productDetailsResponse;

    public ReviewAdapter(Context context,ProductDetailsResponse productDetailsResponse) {
        this.context = context;
        this.productDetailsResponse = productDetailsResponse;
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
        holder.binding.name.setText(productDetailsResponse.getReviews().get(position).getReview().getUserDetail());
       /* holder.binding.textRating.setText("4.0");
        holder.binding.textReview.setText(productDetailsResponse.getReviews().get(position).getReview().getReview());
        holder.binding.textDislike.setText(productDetailsResponse.getReviews().get(position).getReview().getDislike());
        holder.binding.textLike.setText(productDetailsResponse.getReviews().get(position).getReview().getLike());
        holder.binding.textTime.setText(AppUtils.getDate(productDetailsResponse.getReviews().get(position).getReview().getCreatedAt()));
*/


    }

    @Override
    public int getItemCount() {
        return productDetailsResponse.getReviews().size();
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder {
        ReviewLayoutBinding binding;

        public ReviewViewHolder(ReviewLayoutBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }
}