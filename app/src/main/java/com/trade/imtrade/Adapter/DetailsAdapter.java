package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.trade.imtrade.Model.ResponseModel.ProductDetailsResponse;
import com.trade.imtrade.R;
import com.trade.imtrade.databinding.DetailsLayoutBinding;
import com.trade.imtrade.databinding.DetailsLayoutBinding;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder> {
    Context context;
    ProductDetailsResponse productDetailsResponse;
    Boolean count;

    public DetailsAdapter(Context context, ProductDetailsResponse productDetailsResponse, Boolean count) {
        this.context = context;
        this.productDetailsResponse = productDetailsResponse;
        this.count = count;
    }

    public DetailsAdapter() {
    }


    @NonNull
    @Override
    public DetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        DetailsLayoutBinding binding = DetailsLayoutBinding.inflate(inflater, parent, false);

        return new DetailsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsViewHolder holder, int position) {
        holder.binding.details.setText(productDetailsResponse.getDetails().get(position).getDetail());

    }

    @Override
    public int getItemCount() {
        int size = 0;
        if (count == false) {

            if (productDetailsResponse.getDetails().size() <= 4) {
                size = productDetailsResponse.getDetails().size();
            } else {
                size = 4;
            }
        } else {
            size = productDetailsResponse.getDetails().size();
        }
        return size;
    }

    public class DetailsViewHolder extends RecyclerView.ViewHolder {
        DetailsLayoutBinding binding;

        public DetailsViewHolder(DetailsLayoutBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }


}
