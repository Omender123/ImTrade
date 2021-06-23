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
import com.trade.imtrade.Adapter.NearByMeAdapter;
import com.trade.imtrade.R;
import com.trade.imtrade.databinding.FragmentMyAddressBinding;
import com.trade.imtrade.databinding.FragmentNearByMeBinding;
import com.trade.imtrade.utils.AppUtils;

public class Near_By_Me extends Fragment {
    FragmentNearByMeBinding binding;
    private View view;
    private Dialog dialog;

    String [] price = {"Grocery Store","Hostels","Parks","Club"};

    NavController navController;
    public Near_By_Me() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_near__by__me, container, false);

        view = binding.getRoot();
        dialog = AppUtils.hideShowProgress(getContext());


        getNearbyPlace();


        return binding.getRoot();

    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

    }
    private void getNearbyPlace() {

        NearByMeAdapter nearByMeAdapter = new NearByMeAdapter(getContext(),price);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL ,false);
        binding.RecyclerView.setLayoutManager(mLayoutManager1);
        binding.RecyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.RecyclerView.setAdapter(nearByMeAdapter);
    }
}