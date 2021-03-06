package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;
import com.trade.imtrade.Model.ResponseModel.ProductResponse;
import com.trade.imtrade.R;
import com.trade.imtrade.SharedPerfence.PrefConf;
import com.trade.imtrade.databinding.CustomProductLayoutBinding;
import com.trade.imtrade.databinding.CustomProductLayoutBinding;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    Context context;
    ProductResponse productResponse;
    private ProductClickListener productClickListener;
    boolean Check = false;

    public ProductAdapter(Context context, ProductResponse productResponse, ProductClickListener productClickListener) {
        this.context = context;
        this.productResponse = productResponse;
        this.productClickListener = productClickListener;
    }

    public ProductAdapter() {
    }


    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CustomProductLayoutBinding binding = CustomProductLayoutBinding.inflate(inflater, parent, false);

        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.binding.productName.setText(productResponse.getResponse().get(position).getName());
       // holder.binding.textStock.setText(productResponse.getResponse().get(position).getName());

        if (productResponse.getResponse().get(position).getStockVisible()==true){
            holder.binding.textStock.setText("In Stock");
            holder.binding.textStock.setTextColor(context.getResources().getColor(R.color.green));
        }else {
            holder.binding.textStock.setText("Out of Stock");
            holder.binding.textStock.setTextColor(context.getResources().getColor(R.color.red_btn_bg_color));

        }

        // holder.binding.textRating.setRating(Float.parseFloat(productResponse.getResponse().get(position).getAverageRating()));
        SimpleRatingBar.AnimationBuilder builder = holder.binding.textRating.getAnimationBuilder()
                .setRatingTarget(Float.parseFloat(productResponse.getResponse().get(position).getAverageRating()))
                .setDuration(2000)
                .setRepeatMode(1)

                .setInterpolator(new BounceInterpolator());
        builder.start();
        Glide.with(context).load(PrefConf.IMAGE_URL + productResponse.getResponse().get(position).getImages().get(0)).into(holder.binding.productImg);

        if (productResponse.getResponse().get(position).getDiscount()!=null){
            holder.binding.productPrice.setText(productResponse.getResponse().get(position).getDiscount() + " Rs");
            holder.binding.productOffPrice.setText(productResponse.getResponse().get(position).getVariables().get(0).getPrice().getMargin() + " %OFF");
            holder.binding.productWorngPrice.setText(productResponse.getResponse().get(position).getVariables().get(0).getPrice().getMrp() + " Rs");
        }else{
            holder.binding.productPrice.setText(productResponse.getResponse().get(position).getVariables().get(0).getPrice().getMrp()+" Rs");
            holder.binding.productOffPrice.setVisibility(View.GONE);
            holder.binding.productWorngPrice.setVisibility(View.GONE);

        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productClickListener.ProductOnClickListener(productResponse, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productResponse.getResponse().size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        CustomProductLayoutBinding binding;

        public ProductViewHolder(CustomProductLayoutBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }

    public interface ProductClickListener {
        void ProductOnClickListener(ProductResponse productResponse, int Position);
    }
}
