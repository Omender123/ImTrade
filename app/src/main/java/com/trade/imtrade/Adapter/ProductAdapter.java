package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.trade.imtrade.Model.ResponseModel.ProductResponse;
import com.trade.imtrade.R;
import com.trade.imtrade.SharedPerfence.PrefConf;
import com.trade.imtrade.databinding.CustomProductLayoutBinding;
import com.trade.imtrade.databinding.CustomProductLayoutBinding;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>  {
    Context context;
    ProductResponse productResponse;
    private  ProductClickListener productClickListener;
    boolean Check=false;

    public ProductAdapter(Context context, ProductResponse productResponse,ProductClickListener productClickListener) {
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
        CustomProductLayoutBinding binding = CustomProductLayoutBinding.inflate(inflater,parent,false);

        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.binding.productName.setText(productResponse.getResponse().get(position).getName());
        holder.binding.textRating.setText(productResponse.getResponse().get(position).getAverageRating());
      Glide.with(context).load(PrefConf.IMAGE_URL+productResponse.getResponse().get(position).getImages().get(0)).into(holder.binding.productImg);

      //  Toast.makeText(context, ""+productResponse.getResponse().get(position).getImages().get(0), Toast.LENGTH_SHORT).show();
        holder.binding.productPrice.setText(productResponse.getResponse().get(position).getDiscount()+" Rs");
        holder.binding.productOffPrice.setText(productResponse.getResponse().get(position).getVariables().get(0).getPrice().getMargin()+" %OFF");
        holder.binding.productWorngPrice.setText(productResponse.getResponse().get(position).getVariables().get(0).getPrice().getMrp()+" Rs");

        holder.binding.imageUnselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Check =true;
                holder.binding.imageSelect.setVisibility(View.VISIBLE);
                holder.binding.imageUnselect.setVisibility(View.GONE);
                Toast.makeText(context, position+""+Check, Toast.LENGTH_SHORT).show();
            }
        });
        holder.binding.imageSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Check =false;
                holder.binding.imageUnselect.setVisibility(View.VISIBLE);
                holder.binding.imageSelect.setVisibility(View.GONE);
                Toast.makeText(context, position+""+Check, Toast.LENGTH_SHORT).show();
            }

        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productClickListener.ProductOnClickListener(productResponse,position);
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

    public interface ProductClickListener{
        void ProductOnClickListener(ProductResponse productResponse,int Position);
    }
}
