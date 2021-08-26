package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;
import com.trade.imtrade.Model.ResponseModel.ProductDetailsResponse;
import com.trade.imtrade.R;
import com.trade.imtrade.SharedPerfence.PrefConf;
import com.trade.imtrade.databinding.CustomBuyitwithLayoutBinding;
import com.trade.imtrade.databinding.CustomBuyitwithLayoutBinding;
import com.trade.imtrade.databinding.DealofdayLayoutBinding;

import java.util.ArrayList;

public class BuyItWithAdapter extends RecyclerView.Adapter<BuyItWithAdapter.BuyitwithViewHolder> {
    Context context;
    ProductDetailsResponse productDetailsResponse;
    BuyitwithClickListener buyitwithClickListener;
     public  static  ArrayList<String>ProductId = new ArrayList<String >();

    public BuyItWithAdapter(Context context, ProductDetailsResponse productDetailsResponse, BuyitwithClickListener buyitwithClickListener) {
        this.context = context;
        this.productDetailsResponse = productDetailsResponse;
        this.buyitwithClickListener = buyitwithClickListener;
    }

    public BuyItWithAdapter() {
    }

    @NonNull
    @Override
    public BuyitwithViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CustomBuyitwithLayoutBinding binding = CustomBuyitwithLayoutBinding.inflate(inflater, parent, false);

        return new BuyitwithViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull BuyitwithViewHolder holder, int position) {

        holder.binding.productName.setText(productDetailsResponse.getAddOn().get(position).getName());
        // holder.binding.textStock.setText(productDetailsResponse.getAddOn().get(position).getName());

        SimpleRatingBar.AnimationBuilder builder = holder.binding.textRating.getAnimationBuilder()
                .setRatingTarget(Float.parseFloat(productDetailsResponse.getAddOn().get(position).getAverageRating()))
                .setDuration(2000)
                .setRepeatMode(1)

                .setInterpolator(new BounceInterpolator());
        builder.start();
        Glide.with(context).load(PrefConf.IMAGE_URL + productDetailsResponse.getAddOn().get(position).getImages().get(0)).into(holder.binding.productImg);

        if (productDetailsResponse.getAddOn().get(position).getDiscount()!=null){
            holder.binding.productPrice.setText(productDetailsResponse.getAddOn().get(position).getDiscount() + " Rs");
            holder.binding.productOffPrice.setText(productDetailsResponse.getAddOn().get(position).getVariables().get(0).getPrice().getMargin() + " %OFF");
            holder.binding.productWorngPrice.setText(productDetailsResponse.getAddOn().get(position).getVariables().get(0).getPrice().getMrp() + " Rs");
        }else{
            holder.binding.productPrice.setText(productDetailsResponse.getAddOn().get(position).getVariables().get(0).getPrice().getMrp()+" Rs");
            holder.binding.productOffPrice.setVisibility(View.GONE);
            holder.binding.productWorngPrice.setVisibility(View.GONE);

        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buyitwithClickListener.onBuyitwithOnClickListener(productDetailsResponse, position);
            }
        });

        ProductId.add(productDetailsResponse.getAddOn().get(position).getId());

        holder.binding.checked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked==true){
                    ProductId.add(productDetailsResponse.getAddOn().get(position).getId());
                    if (productDetailsResponse.getAddOn().get(position).getDiscount()!=null){
                        buyitwithClickListener.OnCheckBoxClickListener(ProductId,productDetailsResponse.getAddOn().get(position).getDiscount(),true);
                    }else{
                        buyitwithClickListener.OnCheckBoxClickListener(ProductId,productDetailsResponse.getAddOn().get(position).getVariables().get(0).getPrice().getMrp(),true);
                    }
                  }else{
                    ProductId.remove(productDetailsResponse.getAddOn().get(position).getId());
                    if (productDetailsResponse.getAddOn().get(position).getDiscount()!=null){
                        buyitwithClickListener.OnCheckBoxClickListener(ProductId,productDetailsResponse.getAddOn().get(position).getDiscount(),false);
                    }else{
                        buyitwithClickListener.OnCheckBoxClickListener(ProductId,productDetailsResponse.getAddOn().get(position).getVariables().get(0).getPrice().getMrp(),false);
                    }
                     }
            }
        });
    }

    @Override
    public int getItemCount() {
        return productDetailsResponse.getAddOn().size();
    }

    public class BuyitwithViewHolder extends RecyclerView.ViewHolder {
        CustomBuyitwithLayoutBinding binding;

        public BuyitwithViewHolder(CustomBuyitwithLayoutBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }

    public interface BuyitwithClickListener {
        void onBuyitwithOnClickListener(ProductDetailsResponse productDetailsResponse, int Position);
        void OnCheckBoxClickListener(ArrayList<String>ProductId,String totalPrice ,Boolean checked );
    }
}
