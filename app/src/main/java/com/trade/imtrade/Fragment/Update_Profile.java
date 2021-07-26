package com.trade.imtrade.Fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.service.autofill.UserData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chaos.view.PinView;
import com.irozon.sneaker.Sneaker;
import com.trade.imtrade.Authentication.SignUp;
import com.trade.imtrade.R;
import com.trade.imtrade.SharedPrefernce.SharedPrefManager;
import com.trade.imtrade.SharedPrefernce.User_Data;
import com.trade.imtrade.databinding.FragmentUpdateProfileBinding;
import com.trade.imtrade.utils.AppUtils;
import com.trade.imtrade.utils.Validation;

import de.mateware.snacky.Snacky;

public class Update_Profile extends Fragment implements View.OnClickListener {
    FragmentUpdateProfileBinding binding;
    private View view;
    private Dialog dialog;
    User_Data userData;


    public Update_Profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_update__profile, container, false);

        view = binding.getRoot();
        dialog = AppUtils.hideShowProgress(getContext());

        binding.btnDone.setOnClickListener(this);

        userData = SharedPrefManager.getInstance(getContext()).getLoginDATA();

        if (userData.getUserName()!=null){
            binding.edUserPhone.setText(userData.getPhoneNo());
            binding.edUserName.setText(userData.getUserName());
            binding.edUserEmail.setText(userData.getEmail());
        }else {
            binding.edUserPhone.setHint("Phone No.");
            binding.edUserName.setHint("Full Name ");
            binding.edUserEmail.setText(userData.getEmail());
        }

        return binding.getRoot();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_done:
              setUserData();
                 break;


        }

    }

    public void setUserData() {
        String FullName = binding.edUserName.getText().toString();
        String Email = binding.edUserEmail.getText().toString();
        String Phone = binding.edUserPhone.getText().toString();

        if (FullName.isEmpty()) {
            Sneaker.with(getActivity())
                    .setTitle("Enter Full Name")
                    .setCornerRadius(4)
                    .setDuration(2500)
                    .sneakWarning();
            binding.edUserName.requestFocus();
        } else if (Email.isEmpty()) {
            Sneaker.with(getActivity())
                    .setTitle("Enter Email")
                    .setCornerRadius(4)
                    .setDuration(2500)
                    .sneakWarning();
            binding.edUserEmail.requestFocus();
        }else if(!Validation.isValidEmail(Email)){
            binding.edUserEmail.requestFocus();
            Sneaker.with(getActivity())
                    .setTitle(" Enter a valid email!!!!")
                    .setCornerRadius(4)
                    .setDuration(2500)
                    .sneakWarning();
        } else if (Phone.isEmpty()) {
            Sneaker.with(getActivity())
                    .setTitle("Enter Phone No.")
                    .setCornerRadius(4)
                    .setDuration(2500)
                    .sneakWarning();
            binding.edUserPhone.requestFocus();
        } else {
            showRightCustomDialog(FullName, Email,Phone);

        }


    }

    private void showRightCustomDialog(String fullName, String email, String phone) {
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.custom_verify_dialogbox);

        TextView text_email = (TextView) dialog.findViewById(R.id.text_email);
        TextView text_resend = (TextView) dialog.findViewById(R.id.text_resend);
        TextView text_cancel = (TextView) dialog.findViewById(R.id.text_cancel);
        TextView text_save = (TextView) dialog.findViewById(R.id.text_save);
        PinView enter_otp = (PinView)  dialog.findViewById(R.id.enter_otp);

        text_email.setText("OTP has sent to you at your email address \n"+email);
        text_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        text_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User_Data user_data = new User_Data(userData.getId(),email,userData.getToken(),userData.getReferral_code(),fullName,phone);
                SharedPrefManager.getInstance(getContext()).SetLoginData(user_data);

                Sneaker.with(getActivity())
                        .setTitle("Successfully Update ")
                        .setCornerRadius(4)
                        .setDuration(2500)
                        .sneakSuccess();
                dialog.dismiss();
            }
        });

        text_resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sneaker.with(getActivity())
                        .setTitle("Successfully OTP Resend ")
                        .setCornerRadius(4)
                        .setDuration(2500)
                        .sneakSuccess();
            }
        });


        dialog.show();
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }


}