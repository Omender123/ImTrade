package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trade.imtrade.Model.ResponseModel.AllCategoriesResponse;
import com.trade.imtrade.databinding.CustomCategorieslayoutfragmentBinding;
import com.trade.imtrade.databinding.CustomGameLayoutBinding;
import com.trade.imtrade.databinding.ProfileItemLayoutBinding;

import java.util.ArrayList;
import java.util.List;

public class Profile_itemAdapter extends RecyclerView.Adapter<Profile_itemAdapter.Profile_itemViewHolder> {
    ArrayList<String> ItemList;
    Context context;

    private OnProfileItemListener onProfileItemListener;

    public Profile_itemAdapter(ArrayList<String> itemList, Context context, OnProfileItemListener onProfileItemListener) {
        this.ItemList = itemList;
        this.context = context;
        this.onProfileItemListener = onProfileItemListener;
    }

    public Profile_itemAdapter() {
    }

    @NonNull
    @Override
    public Profile_itemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ProfileItemLayoutBinding binding = ProfileItemLayoutBinding.inflate(layoutInflater, parent, false);

        return new Profile_itemViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull Profile_itemViewHolder holder, int position) {

        holder.binding.text.setText(ItemList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onProfileItemListener.onProfileItemClickListener(ItemList,position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return ItemList.size();
    }

    public class Profile_itemViewHolder extends RecyclerView.ViewHolder {
        ProfileItemLayoutBinding binding;

        public Profile_itemViewHolder(ProfileItemLayoutBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }

    public interface OnProfileItemListener {
        void onProfileItemClickListener(ArrayList<String> ItemList, int position);
    }
}
