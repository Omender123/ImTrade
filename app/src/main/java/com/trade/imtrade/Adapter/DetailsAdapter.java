package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.trade.imtrade.R;
import com.trade.imtrade.databinding.DetailsLayoutBinding;
import com.trade.imtrade.databinding.DetailsLayoutBinding;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder>  {
    Context context;
    String[] detailsType;
    String[] details;
    Boolean count;

    public DetailsAdapter(Context context,String[] detailsType,String[] details,Boolean count) {
        this.context = context;
        this.detailsType = detailsType;
        this.details = details;
        this.count = count;
    }

    public DetailsAdapter() {
    }


    @NonNull
    @Override
    public DetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        DetailsLayoutBinding binding = DetailsLayoutBinding.inflate(inflater,parent,false);

        return new DetailsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsViewHolder holder, int position) {
        holder.binding.detailsType.setText(detailsType[position]);
        holder.binding.details.setText(details[position]);

     }

    @Override
    public int getItemCount() {
        int size =0;
        if (count==true){

            size= details.length;
        }else{
           size =4;
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
