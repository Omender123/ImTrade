package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trade.imtrade.databinding.CustomNearByMeBinding;

public class NearByMeAdapter extends RecyclerView.Adapter<NearByMeAdapter.NearByMeViewHolder> {
    Context context;
    String[] amount;

    public NearByMeAdapter() {
    }

    public NearByMeAdapter(Context context, String[] amount) {
        this.context = context;
        this.amount = amount;
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

}
