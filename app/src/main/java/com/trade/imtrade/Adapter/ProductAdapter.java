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
import com.trade.imtrade.R;
import com.trade.imtrade.databinding.CustomProductLayoutBinding;
import com.trade.imtrade.databinding.CustomProductLayoutBinding;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>  {
    Context context;
    String[] amount;
    boolean Check=false;

    public ProductAdapter(Context context, String[] amount) {
        this.context = context;
        this.amount = amount;
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
        holder.binding.productPrice.setText(amount[position]);

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
          }

    @Override
    public int getItemCount() {
        return amount.length;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        CustomProductLayoutBinding binding;
        public ProductViewHolder(CustomProductLayoutBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }

}
