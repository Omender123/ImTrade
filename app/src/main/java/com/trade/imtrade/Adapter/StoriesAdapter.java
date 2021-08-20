package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.trade.imtrade.Model.ResponseModel.StoriesResponse;
import com.trade.imtrade.R;
import com.trade.imtrade.SharedPerfence.PrefConf;
import com.trade.imtrade.databinding.CustomStoriesLayoutBinding;

import java.util.List;

public class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.StoriesViewHolder> {
    Context context;
    List<StoriesResponse> storiesResponses;

    public StoriesAdapter(Context context, List<StoriesResponse> storiesResponses) {
        this.context = context;
        this.storiesResponses = storiesResponses;
    }

    public StoriesAdapter() {
    }


    @NonNull
    @Override
    public StoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CustomStoriesLayoutBinding binding = CustomStoriesLayoutBinding.inflate(inflater, parent, false);

        return new StoriesViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull StoriesViewHolder holder, int position) {

        Glide.with(context).load(PrefConf.IMAGE_URL + storiesResponses.get(position).getImage()).apply(new RequestOptions().circleCrop()).placeholder(R.drawable.ic_profile_image).into(holder.binding.brandsImage);
    }

    @Override
    public int getItemCount() {
        return storiesResponses.size();
    }

    public class StoriesViewHolder extends RecyclerView.ViewHolder {
        CustomStoriesLayoutBinding binding;

        public StoriesViewHolder(CustomStoriesLayoutBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }

}
