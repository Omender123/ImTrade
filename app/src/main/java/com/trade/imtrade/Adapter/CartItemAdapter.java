package com.trade.imtrade.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;
import com.trade.imtrade.Model.ResponseModel.AllCategoriesResponse;
import com.trade.imtrade.Model.ResponseModel.CartProductResponse;
import com.trade.imtrade.R;
import com.trade.imtrade.SharedPerfence.PrefConf;
import com.trade.imtrade.databinding.CustomCartLayoutBinding;
import com.trade.imtrade.databinding.CustomCartLayoutBinding;

import java.util.ArrayList;
import java.util.List;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.CartItemViewHolder> {
    Context context;
    CartProductResponse cartProductResponse;
    String string;
    private OnGetCartItemListener onGetCartItemListener;

    public CartItemAdapter(Context context, CartProductResponse cartProductResponse,String string,OnGetCartItemListener onGetCartItemListener) {
        this.context = context;
        this.cartProductResponse = cartProductResponse;
        this.string = string;
        this.onGetCartItemListener = onGetCartItemListener;
    }

    public CartItemAdapter() {
    }


    @NonNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CustomCartLayoutBinding binding = CustomCartLayoutBinding.inflate(inflater, parent, false);

        return new CartItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemViewHolder holder, int position) {
        Glide.with(context).load(PrefConf.IMAGE_URL + cartProductResponse.getProducts().get(position).getProductId().getImages().get(0)).into(holder.binding.productImg);
        holder.binding.productName.setText(cartProductResponse.getProducts().get(position).getProductId().getName());
        SimpleRatingBar.AnimationBuilder builder = holder.binding.textRating.getAnimationBuilder()
                .setRatingTarget(Float.parseFloat(cartProductResponse.getProducts().get(position).getProductId().getAverageRating()))
                .setDuration(2000)
                .setRepeatMode(1)

                .setInterpolator(new BounceInterpolator());
        builder.start();

        holder.binding.productPrice.setText(cartProductResponse.getProducts().get(position).getProductId().getDiscount() + " Rs");
        holder.binding.productWorngPrice.setText(cartProductResponse.getProducts().get(position).getProductId().getVariables().get(0).getPrice().getMrp() + " Rs");
        holder.binding.productOffPrice.setText(cartProductResponse.getProducts().get(position).getProductId().getVariables().get(0).getPrice().getMargin() + " %OFF");

        holder.binding.textSave.setText(string);


        holder.binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onGetCartItemListener.onSaveLaterItemClickListener(cartProductResponse.getProducts().get(position).getProductId().getId(),string);

            }
        });

        holder.binding.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onGetCartItemListener.onDeleteItemClickListener(cartProductResponse.getProducts().get(position).getId(),string);

            }
        });


    }

    @Override
    public int getItemCount() {
        return cartProductResponse.getProducts().size();
    }

    public class CartItemViewHolder extends RecyclerView.ViewHolder {
        CustomCartLayoutBinding binding;

        public CartItemViewHolder(CustomCartLayoutBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }

    public interface OnGetCartItemListener {
        void onIncreaseQuantityItemClickListener(String ProductId, int Quantity,String Type);

        void onSaveLaterItemClickListener(String ProductId,String Type);

        void onDeleteItemClickListener(String ProductId,String Type);
    }
}
