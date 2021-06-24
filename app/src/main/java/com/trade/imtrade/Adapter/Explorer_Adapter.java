package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trade.imtrade.databinding.CustomExplorerBinding;

public class Explorer_Adapter extends RecyclerView.Adapter<Explorer_Adapter.ExplorerViewHolder> {
    Context context;
    String[] amount;

    public Explorer_Adapter(Context context, String[] amount) {
        this.context = context;
        this.amount = amount;
    }

    public Explorer_Adapter() {
    }

    @NonNull
    @Override
    public ExplorerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        CustomExplorerBinding customExplorerBinding = CustomExplorerBinding.inflate(inflater,parent,false);

        return new ExplorerViewHolder(customExplorerBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ExplorerViewHolder holder, int position) {
        holder.binding.expDate.setText(amount[position]);

    }

    @Override
    public int getItemCount() {
        return amount.length;
    }

    public class ExplorerViewHolder extends RecyclerView.ViewHolder {
        CustomExplorerBinding binding;

        public ExplorerViewHolder(CustomExplorerBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }
    }
}
