package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.trade.imtrade.Model.ResponseModel.ContinueYourHuntResponse;
import com.trade.imtrade.SharedPerfence.PrefConf;
import com.trade.imtrade.databinding.ContinueYourHuntLayoutBinding;
import com.trade.imtrade.databinding.ContinueYourHuntLayoutBinding;

import java.util.List;

public class Continue_HuntAdapter extends RecyclerView.Adapter<Continue_HuntAdapter.Continue_HuntViewHolder>  {
    Context context;
    ContinueYourHuntResponse continueYourHuntResponse;
    ContinueYourHuntClickListener ContinueYourHuntClickListener;
    Boolean check;

    public Continue_HuntAdapter(Context context, ContinueYourHuntResponse continueYourHuntResponse, ContinueYourHuntClickListener ContinueYourHuntClickListener,Boolean check) {
        this.context = context;
        this.continueYourHuntResponse = continueYourHuntResponse;
        this.ContinueYourHuntClickListener = ContinueYourHuntClickListener;
        this.check = check;

    }

    public Continue_HuntAdapter() {
    }


    @NonNull
    @Override
    public Continue_HuntViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ContinueYourHuntLayoutBinding binding = ContinueYourHuntLayoutBinding.inflate(inflater, parent, false);

        return new Continue_HuntViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Continue_HuntViewHolder holder, int position) {

        holder.binding.cateName.setText(continueYourHuntResponse.getProducts().get(position).getName());
        //  holder.binding.catePrice.setText(continueYourHuntResponse.getProducts().get(position).getDiscount());

        // holder.binding.cateImg.setImageDrawable(context.getResources().getDrawable(R.mipmap.deal));
        Glide.with(context).load(PrefConf.IMAGE_URL+continueYourHuntResponse.getProducts().get(position).getImages().get(0)).into(holder.binding.cateImg);

        if (continueYourHuntResponse.getProducts().get(position).getDiscount()!=null){
            holder.binding.catePrice.setText(continueYourHuntResponse.getProducts().get(position).getDiscount()+" Rs");
            holder.binding.productOffPrice.setText(continueYourHuntResponse.getProducts().get(position).getVariables().get(0).getPrice().getMargin()+" %OFF");
            holder.binding.productWorngPrice.setText(continueYourHuntResponse.getProducts().get(position).getVariables().get(0).getPrice().getMrp()+" Rs");
        }else{
            holder.binding.catePrice.setText(continueYourHuntResponse.getProducts().get(position).getVariables().get(0).getPrice().getMrp()+" Rs");
            holder.binding.productOffPrice.setVisibility(View.GONE);
            holder.binding.productWorngPrice.setVisibility(View.GONE);

        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContinueYourHuntClickListener.onContinueYourHuntItemClickListener(continueYourHuntResponse,position);
            }
        });


    }

    @Override
    public int getItemCount() {
        int size=0;
        if (check==false){
            if (continueYourHuntResponse.getProducts().size()<=4){
                size =continueYourHuntResponse.getProducts().size();
            }else{
                size =4;
            }
        }else{
            size=continueYourHuntResponse.getProducts().size();
        }
        return size;
    }

    public class Continue_HuntViewHolder extends RecyclerView.ViewHolder {
        ContinueYourHuntLayoutBinding  binding;

        public Continue_HuntViewHolder(ContinueYourHuntLayoutBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }

    public interface ContinueYourHuntClickListener{
        void onContinueYourHuntItemClickListener(ContinueYourHuntResponse data, int position);
    }


}
