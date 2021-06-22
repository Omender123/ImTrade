package com.trade.imtrade.Fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trade.imtrade.R;
import com.trade.imtrade.databinding.FragmentProfileBinding;
import com.trade.imtrade.databinding.FragmentProfileBindingImpl;
import com.trade.imtrade.utils.AppUtils;


public class Profile_Fragment extends Fragment implements View.OnClickListener {
    FragmentProfileBinding binding;
    private View view;
    private Dialog dialog;

    NavController navController;

    public Profile_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_profile, container, false);

        view = binding.getRoot();
        dialog = AppUtils.hideShowProgress(getContext());

        binding.cardMyAddress.setOnClickListener(this);

        return binding.getRoot();

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.card_MyAddress:
                navController.navigate(R.id.action_profile_to_My_address);
                break;
        }
    }
}