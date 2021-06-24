package com.trade.imtrade.Fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.trade.imtrade.R;
import com.trade.imtrade.databinding.FragmentSearchNearByBinding;
import com.trade.imtrade.utils.AppUtils;


public class Search_NearBy extends Fragment {
    FragmentSearchNearByBinding binding;
    private View view;
    View view1;
    private Dialog dialog;

    NavController navController;

    public Search_NearBy() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search__near_by, container, false);

        view = binding.getRoot();
        dialog = AppUtils.hideShowProgress(getContext());




        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
    }

    }
