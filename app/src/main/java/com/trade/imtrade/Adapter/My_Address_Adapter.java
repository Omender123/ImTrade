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
import com.trade.imtrade.R;

public class My_Address_Adapter extends RecyclerView.Adapter<My_Address_Adapter.myViewHolder> {
    Context context;
    String[] amount;

    public My_Address_Adapter(Context context, String[] amount) {
        this.context = context;
        this.amount = amount;
    }

    public My_Address_Adapter() {
    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_address_layout, parent, false);

        return new My_Address_Adapter.myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        holder.text_name.setText(amount[position]);

    }

    @Override
    public int getItemCount() {
        return amount.length;
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView text_name, text_address, text_phone_no, text_edit;
        ImageView img_cancel;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            text_name = itemView.findViewById(R.id.text_name);
            text_address = itemView.findViewById(R.id.text_address);
            text_phone_no = itemView.findViewById(R.id.text_phone_no);
            text_edit = itemView.findViewById(R.id.text_edit);
            img_cancel = itemView.findViewById(R.id.img_cancel);


        }
    }


}
