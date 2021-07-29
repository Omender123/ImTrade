package com.trade.imtrade.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trade.imtrade.R;
import com.trade.imtrade.databinding.CustomCartLayoutBinding;
import com.trade.imtrade.databinding.CustomCartLayoutBinding;

import java.util.ArrayList;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.CartItemViewHolder>  {
    Context context;
    String[] price;
    ArrayList<String> arrayList;


    public CartItemAdapter(Context context, String[] price,ArrayList<String>arrayList) {
        this.context = context;
        this.price = price;
        this.arrayList=arrayList;
    }

    public CartItemAdapter() {
    }


    @NonNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CustomCartLayoutBinding binding = CustomCartLayoutBinding.inflate(inflater,parent,false);

        return new CartItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemViewHolder holder, int position) {

        holder.binding.productPrice.setText(price[position]);

        ArrayAdapter<String> adp = new ArrayAdapter<String>(context, R.layout.spinner_list, arrayList);
        holder.binding.spinner.setAdapter(adp);

        //  adp.setDropDownViewResource(R.layout.spinner_list);

        holder.binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String Quantity = arrayList.get(position);
               Toast.makeText(parent.getContext(), Quantity, Toast.LENGTH_LONG).show();
                Log.d("count",Quantity);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return price.length;
    }

    public class CartItemViewHolder extends RecyclerView.ViewHolder {
        CustomCartLayoutBinding binding;
        public CartItemViewHolder(CustomCartLayoutBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }



}
