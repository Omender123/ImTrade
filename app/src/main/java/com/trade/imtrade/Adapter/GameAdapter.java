package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trade.imtrade.databinding.CustomGameLayoutBinding;
import com.trade.imtrade.databinding.CustomGameLayoutBinding;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder>  {
    Context context;
   Integer[] Image;

    public GameAdapter(Context context, Integer[] Image) {
        this.context = context;
        this.Image = Image;
    }

    public GameAdapter() {
    }


    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CustomGameLayoutBinding binding = CustomGameLayoutBinding.inflate(inflater,parent,false);

        return new GameViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        holder.binding.gameImg.setImageResource(Image[position]);
        //   Glide.with(context).load("https://picsum.photos/id/896/300/200").into(holder.binding.b);
    }

    @Override
    public int getItemCount() {
        return Image.length;
    }

    public class GameViewHolder extends RecyclerView.ViewHolder {
        CustomGameLayoutBinding binding;
        public GameViewHolder(CustomGameLayoutBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }


}
