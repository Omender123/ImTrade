package com.trade.imtrade.Fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trade.imtrade.Adapter.My_Address_Adapter;
import com.trade.imtrade.R;
import com.trade.imtrade.databinding.FragmentMyAddressBinding;
import com.trade.imtrade.utils.AppUtils;


public class My_Address extends Fragment implements View.OnClickListener {
FragmentMyAddressBinding binding;
    private View view;
    private Dialog dialog;

    String [] price = {"Full Name","Full Name","Full Name","Full Name","Full Name","Full Name","Full Name","Full Name","Full Name","Full Name","Full Name","Full Name"};

    NavController navController;
    public My_Address() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_my__address, container, false);

        view = binding.getRoot();
        dialog = AppUtils.hideShowProgress(getContext());

     
        GetAddress();

        binding.textAdd.setOnClickListener(this);

        return binding.getRoot();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

    }

    private void GetAddress() {
        My_Address_Adapter  my_address_adapter = new My_Address_Adapter(getContext(),price);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL ,false);
        binding.RecyclerView.setLayoutManager(mLayoutManager1);
        binding.RecyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.RecyclerView.setAdapter(my_address_adapter);
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()){

            case R.id.text_add:
                navController.navigate(R.id.address);

                break;
        }

    }
}