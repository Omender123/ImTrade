package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.trade.imtrade.Model.ResponseModel.SaveForLaterResponse;
import com.trade.imtrade.R;
import com.trade.imtrade.SharedPerfence.PrefConf;
import com.trade.imtrade.databinding.CustomCartLayoutBinding;

import java.util.ArrayList;
public class SaveForLaterAdapter extends RecyclerView.Adapter<SaveForLaterAdapter.CartItemViewHolder> {
    Context context;
    SaveForLaterResponse saveForLaterResponse;
    ArrayList<String> arrayList;
    String string;
    private OnGetCartItemListener onGetCartItemListener;

    public SaveForLaterAdapter(Context context, SaveForLaterResponse saveForLaterResponse, ArrayList<String> arrayList, String string,OnGetCartItemListener onGetCartItemListener) {
        this.context = context;
        this.saveForLaterResponse = saveForLaterResponse;
        this.arrayList = arrayList;
        this.string = string;
        this.onGetCartItemListener = onGetCartItemListener;
    }

    public SaveForLaterAdapter() {
    }


    @NonNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CustomCartLayoutBinding binding = CustomCartLayoutBinding.inflate(inflater, parent, false);

        return new CartItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemViewHolder holder, int position) {
        Glide.with(context).load(PrefConf.IMAGE_URL + saveForLaterResponse.getProducts().get(position).getImages().get(0)).into(holder.binding.productImg);
        holder.binding.productName.setText(saveForLaterResponse.getProducts().get(position).getName());
        holder.binding.textRating.setText(saveForLaterResponse.getProducts().get(position).getAverageRating());
        holder.binding.productPrice.setText(saveForLaterResponse.getProducts().get(position).getDiscount() + " Rs");
        holder.binding.productWorngPrice.setText(saveForLaterResponse.getProducts().get(position).getVariables().get(0).getPrice().getMrp() + " Rs");
        holder.binding.productOffPrice.setText(saveForLaterResponse.getProducts().get(position).getVariables().get(0).getPrice().getMargin() + " %OFF");

        holder.binding.save.setText(string);

        // holder.binding.spinner.setId(Integer.parseInt(saveForLaterResponse.getProducts().get(position).getQuantity()));
        ArrayAdapter<String> adp = new ArrayAdapter<String>(context, R.layout.spinner_list, arrayList);
        holder.binding.spinner.setAdapter(adp);
      /*  if (saveForLaterResponse.getProducts().get(position).getQuantity() != null) {
            int spinnerPosition = adp.getPosition(saveForLaterResponse.getProducts().get(position).getQuantity());
            holder.binding.spinner.setSelection(spinnerPosition);

        }*/

        holder.binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int positions, long id) {
                int Quantity = Integer.parseInt(arrayList.get(positions));
                onGetCartItemListener.onIncreaseQuantityItemClickListener(saveForLaterResponse.getProducts().get(position).getId(),Quantity,string);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        holder.binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onGetCartItemListener.onSaveLaterItemClickListener(saveForLaterResponse.getProducts().get(position).getId(),string);

            }
        });

        holder.binding.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onGetCartItemListener.onDeleteItemClickListener(saveForLaterResponse.getProducts().get(position).getId(),string);

            }
        });


    }

    @Override
    public int getItemCount() {
        return saveForLaterResponse.getProducts().size();
    }

    public class CartItemViewHolder extends RecyclerView.ViewHolder {
        CustomCartLayoutBinding binding;

        public CartItemViewHolder(CustomCartLayoutBinding binding) {
            super(binding.getRoot());

            this.binding = binding;


        }
    }

    public interface OnGetCartItemListener {
        void onIncreaseQuantityItemClickListener(String ProductId, int Quantity,String Type);

        void onSaveLaterItemClickListener(String ProductId,String Type);

        void onDeleteItemClickListener(String ProductId,String Type);
    }
}
