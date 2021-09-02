package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.trade.imtrade.Model.ResponseModel.AddressResponse;
import com.trade.imtrade.R;
import com.trade.imtrade.databinding.CustomAddressLayoutBinding;

import java.util.List;

public class My_Address_Adapter extends RecyclerView.Adapter<My_Address_Adapter.MyAddressViewHolder> {
    Context context;
    List<AddressResponse> addressResponses;
    private OnClickAddressListener onClickAddressListener;

    public My_Address_Adapter(Context context, List<AddressResponse> addressResponses, OnClickAddressListener onClickAddressListener) {
        this.context = context;
        this.addressResponses = addressResponses;
        this.onClickAddressListener = onClickAddressListener;
    }

    public My_Address_Adapter() {
    }


    @NonNull
    @Override
    public MyAddressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //   View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_address_layout, parent, false);

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        CustomAddressLayoutBinding binding = CustomAddressLayoutBinding.inflate(layoutInflater, parent, false);


        return new MyAddressViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAddressViewHolder holder, int position) {

        holder.binding.textName.setText(addressResponses.get(position).getName());

        if (addressResponses.get(position).getCity() == null) {
            holder.binding.textAddress.setText(addressResponses.get(position).getAddress()+" \n -"+addressResponses.get(position).getPinCode()+" "+addressResponses.get(position).getState()+", "+addressResponses.get(position).getCountry());
        } else {

            holder.binding.textAddress.setText(addressResponses.get(position).getAddress()+" \n"+addressResponses.get(position).getCity()+" -"+addressResponses.get(position).getPinCode()+", "+addressResponses.get(position).getState()+", "+addressResponses.get(position).getCountry());

        }
        holder.binding.textPhoneNo.setText("Phone Number : "+addressResponses.get(position).getPhoneNo());

        holder.binding.textEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickAddressListener.OnClickEditItemAddressListener(addressResponses.get(position).getId());
            }
        });

        holder.binding.imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickAddressListener.OnClickDeleteItemAddressListener(addressResponses.get(position).getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return addressResponses.size();
    }

    public class MyAddressViewHolder extends RecyclerView.ViewHolder {
        CustomAddressLayoutBinding binding;

        public MyAddressViewHolder(CustomAddressLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


    public interface OnClickAddressListener {
        void OnClickEditItemAddressListener(String id);
        void OnClickDeleteItemAddressListener(String id);
    }
}
