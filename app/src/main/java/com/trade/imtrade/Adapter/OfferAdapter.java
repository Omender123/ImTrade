package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trade.imtrade.Model.ResponseModel.OfferResponse;
import com.trade.imtrade.databinding.CustomOfferLayoutBinding;
import com.trade.imtrade.databinding.DetailsLayoutBinding;

import java.util.ArrayList;
import java.util.List;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.OfferViewHolder> {
    Context context;
    List<OfferResponse>offerResponses;

    public OfferAdapter(Context context, List<OfferResponse>offerResponse) {
        this.context = context;
        this.offerResponses = offerResponse;
    }

    public OfferAdapter() {
    }

    @NonNull
    @Override
    public OfferViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CustomOfferLayoutBinding binding = CustomOfferLayoutBinding.inflate(inflater, parent, false);

        return new OfferViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OfferViewHolder holder, int position) {
        holder.binding.textOffer.setText(offerResponses.get(position).getTitle());
        holder.binding.textOfferDes.setText(offerResponses.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return offerResponses.size();
    }

    public class OfferViewHolder extends RecyclerView.ViewHolder {
        CustomOfferLayoutBinding binding;

        public OfferViewHolder(CustomOfferLayoutBinding binding) {
            super(binding.getRoot());

            this.binding = binding;

        }
    }
}
