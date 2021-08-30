package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;
import com.trade.imtrade.Model.ResponseModel.ProductDetailsResponse;
import com.trade.imtrade.Model.ResponseModel.ReviewResponse;
import com.trade.imtrade.R;
import com.trade.imtrade.SharedPerfence.PrefConf;
import com.trade.imtrade.databinding.ReviewLayoutBinding;
import com.trade.imtrade.databinding.ReviewLayoutBinding;
import com.trade.imtrade.utils.AppUtils;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {
    Context context;
    List<ReviewResponse> productDetailsResponse;

    public ReviewAdapter(Context context,List<ReviewResponse> productDetailsResponse) {
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
        if (productDetailsResponse.get(position).getUser().getLastName()!=null){
            holder.binding.name.setText(productDetailsResponse.get(position).getUser().getFirstName()+" "+productDetailsResponse.get(position).getUser().getLastName());

        }else {
            holder.binding.name.setText(productDetailsResponse.get(position).getUser().getFirstName());

        }

        SimpleRatingBar.AnimationBuilder builder = holder.binding.textRating.getAnimationBuilder()
                .setRatingTarget(Float.parseFloat(productDetailsResponse.get(position).getRating()))
                .setDuration(2000)
                .setRepeatMode(1)

                .setInterpolator(new BounceInterpolator());
        builder.start(); 
        
        if (productDetailsResponse.get(position).getUser().getProfileImage()!=null){
            Glide.with(context).load(PrefConf.IMAGE_URL+productDetailsResponse.get(position).getUser().getProfileImage()).apply(new RequestOptions().circleCrop()).placeholder(R.drawable.ic_profile_image).into(holder.binding.imgProfile);
        }else{
            Glide.with(context).load("https://stargazeevents.s3.ap-south-1.amazonaws.com/pfiles/profile.png").apply(new RequestOptions().circleCrop()).placeholder(R.drawable.ic_profile_image).into(holder.binding.imgProfile);
        }
        holder.binding.textReview.setText(productDetailsResponse.get(position).getReview());
        holder.binding.textDislike.setText(productDetailsResponse.get(position).getDislike());
        holder.binding.textLike.setText(productDetailsResponse.get(position).getLike());

        if (productDetailsResponse.get(position).getImage() ==null && productDetailsResponse.get(position).getThumbnail() ==null && productDetailsResponse.get(position).getVideo() ==null){
            holder.binding.card.setVisibility(View.GONE);
        }else if (productDetailsResponse.get(position).getImage() ==null){
            Glide.with(context).load(PrefConf.IMAGE_URL+productDetailsResponse.get(position).getThumbnail()).into(holder.binding.videoImage);
            holder.binding.play.setVisibility(View.VISIBLE);
        }else {
            Glide.with(context).load(PrefConf.IMAGE_URL+productDetailsResponse.get(position).getImage()).into(holder.binding.videoImage);
            holder.binding.play.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        int size=0;

            if (productDetailsResponse.size()<=2){
                size =productDetailsResponse.size();
            }else{
                size =2;
            }

        return size;
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder {
        ReviewLayoutBinding binding;

        public ReviewViewHolder(ReviewLayoutBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }
}