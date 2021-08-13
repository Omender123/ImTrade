package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trade.imtrade.databinding.CustomStoriesLayoutBinding;
import com.trade.imtrade.databinding.CustomStoriesLayoutBinding;

public class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.StoriesViewHolder>  {
    Context context;
    // List<BrandsResponse>brandsResponses;
    Integer brands_Image[];

    public StoriesAdapter(Context context, /*List<BrandsResponse>brandsResponses*/ Integer brands_Image[]) {
        this.context = context;
        //this.brandsResponses = brandsResponses;
        this.brands_Image = brands_Image;
    }

    public StoriesAdapter() {
    }


    @NonNull
    @Override
    public StoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CustomStoriesLayoutBinding binding = CustomStoriesLayoutBinding.inflate(inflater,parent,false);

        return new StoriesViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull StoriesViewHolder holder, int position) {
        holder.binding.brandsImage.setImageResource(brands_Image[position]);

        // Glide.with(context).load(PrefConf.IMAGE_URL+brandsResponses.get(position).getLogo()).apply(new RequestOptions().circleCrop()).placeholder(R.drawable.ic_profile_image).into(holder.binding.brandsImage);
    }

    @Override
    public int getItemCount() {
        return brands_Image.length;
    }

    public class StoriesViewHolder extends RecyclerView.ViewHolder {
        CustomStoriesLayoutBinding binding;
        public StoriesViewHolder(CustomStoriesLayoutBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }

}
