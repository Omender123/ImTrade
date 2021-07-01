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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trade.imtrade.Adapter.All_Categories_Adapter;
import com.trade.imtrade.Adapter.ProductAdapter;
import com.trade.imtrade.R;
import com.trade.imtrade.databinding.FragmentProductBinding;
import com.trade.imtrade.utils.AppUtils;


public class Product_Fragemet extends Fragment {
    FragmentProductBinding binding;
    private View view;
    private Dialog dialog;
    NavController navController;
    String[] price = {"8,999 Rs", "8,999 Rs", "8,999 Rs", "8,999 Rs", "8,999 Rs", "8,999 Rs", "8,999 Rs", "8,999 Rs", "8,999 Rs", "8,999 Rs"};

    public Product_Fragemet() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      //  return inflater.inflate(R.layout.fragment_product, container, false);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product, container, false);

        view = binding.getRoot();
        dialog = AppUtils.hideShowProgress(getContext());

        getAllProduct();

        return binding.getRoot();
    }




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);


    }

    private void getAllProduct() {

        ProductAdapter productAdapter = new ProductAdapter(getContext(), price);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(),  RecyclerView.VERTICAL, false);
        binding.RecyclerView.setLayoutManager(mLayoutManager1);
        binding.RecyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.RecyclerView.setAdapter(productAdapter);
    }
}