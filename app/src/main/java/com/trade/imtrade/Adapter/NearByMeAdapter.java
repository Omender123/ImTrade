package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trade.imtrade.databinding.CustomNearByMeBinding;

public class NearByMeAdapter extends RecyclerView.Adapter<NearByMeAdapter.NearByMeViewHolder> {
    Context context;
    String[] amount;
    private OnOrderItemListener listener;

    public NearByMeAdapter() {
    }

    public NearByMeAdapter(Context context, String[] amount, OnOrderItemListener listener) {
        this.context = context;
        this.amount = amount;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NearByMeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CustomNearByMeBinding customNearByMeBinding = CustomNearByMeBinding.inflate(layoutInflater, parent, false);

        return new NearByMeViewHolder(customNearByMeBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull NearByMeViewHolder holder, int position) {

        holder.binding.placeName.setText(amount[position]);

        holder.binding.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClickListener(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return amount.length;
    }

    class NearByMeViewHolder extends RecyclerView.ViewHolder {

        CustomNearByMeBinding binding;

        public NearByMeViewHolder(CustomNearByMeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;



        }
    }

    public interface OnOrderItemListener {
        void onItemClickListener(/*List<GetNewCoinRespinse> data,*/ int position);
    }

}
