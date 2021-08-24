package com.trade.imtrade.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.trade.imtrade.Model.ResponseModel.CustomerQuestionsResponse;
import com.trade.imtrade.databinding.CustomQuesAnsLayoutBinding;
import com.trade.imtrade.databinding.CustomQuesAnsLayoutBinding;

import java.util.ArrayList;
import java.util.List;

public class Customer_Questions_Adapter  extends RecyclerView.Adapter<Customer_Questions_Adapter.Customer_QuestionsrViewHolder> {

    Context context;
    List<CustomerQuestionsResponse>customerQuestionsResponses;
    Boolean count;


    public Customer_Questions_Adapter(Context context, List<CustomerQuestionsResponse> customerQuestionsResponses, Boolean count) {
        this.context = context;
        this.customerQuestionsResponses = customerQuestionsResponses;
        this.count = count;
    }

    public Customer_Questions_Adapter() {
    }
    @NonNull
    @Override
    public Customer_QuestionsrViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CustomQuesAnsLayoutBinding binding = CustomQuesAnsLayoutBinding.inflate(inflater, parent, false);

        return new Customer_QuestionsrViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Customer_QuestionsrViewHolder holder, int position) {
        holder.binding.textQ.setText("Q : "+customerQuestionsResponses.get(position).getQsn());
        holder.binding.textA.setText(customerQuestionsResponses.get(position).getAnswer());

    }

    @Override
    public int getItemCount() {
        int size = 0;
        if (count==false){
            if (customerQuestionsResponses.size()<=2){
                size = customerQuestionsResponses.size();
            }else{

                size = 2;
            }
        }else{
            size = customerQuestionsResponses.size();
        }
        return size;
    }

    public class Customer_QuestionsrViewHolder extends RecyclerView.ViewHolder {
        CustomQuesAnsLayoutBinding binding;

        public Customer_QuestionsrViewHolder(CustomQuesAnsLayoutBinding binding) {
            super(binding.getRoot());

            this.binding = binding;

        }
    }

}
