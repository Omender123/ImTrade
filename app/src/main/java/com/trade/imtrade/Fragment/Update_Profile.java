package com.trade.imtrade.Fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trade.imtrade.R;
import com.trade.imtrade.databinding.FragmentUpdateProfileBinding;
import com.trade.imtrade.utils.AppUtils;

public class Update_Profile extends Fragment implements View.OnClickListener {
    FragmentUpdateProfileBinding binding;
    private View view;
    private Dialog dialog;


    public Update_Profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_update__profile, container, false);

        view = binding.getRoot();
        dialog = AppUtils.hideShowProgress(getContext());

        binding.imageEditName.setOnClickListener(this);
        binding.imageEditEmail.setOnClickListener(this);
        binding.imageEditPhone.setOnClickListener(this);
        binding.btnDone.setOnClickListener(this);

        return binding.getRoot();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.image_edit_name:
                binding.edName.setText(" ");
                binding.edName.setEnabled(true);
                break;

            case R.id.image_edit_email:
                binding.edEmail.setText(" ");
                binding.edEmail.setEnabled(true);

                break;

            case R.id.image_edit_phone:
                binding.edPhone.setText(" ");
                binding.edPhone.setEnabled(true);

                break;
            case R.id.btn_done:

                binding.edName.setEnabled(false);
                binding.edEmail.setEnabled(false);
                binding.edPhone.setEnabled(false);
                break;


        }

    }
}