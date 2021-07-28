package com.trade.imtrade.Fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.irozon.sneaker.Sneaker;
import com.trade.imtrade.Model.request.ChangePasswordBody;
import com.trade.imtrade.Model.request.SendOtpBody;
import com.trade.imtrade.Model.request.UpdateProfileBody;
import com.trade.imtrade.R;
import com.trade.imtrade.SharedPrefernce.SharedPrefManager;
import com.trade.imtrade.SharedPrefernce.User_Data;
import com.trade.imtrade.databinding.FragmentChangePasswordBinding;
import com.trade.imtrade.utils.AppUtils;
import com.trade.imtrade.view_presenter.ChangeEmailPresenter;
import com.trade.imtrade.view_presenter.ChangePassword_Presenter;

import okhttp3.ResponseBody;


public class ChangePassword_Fragment extends Fragment implements View.OnClickListener, ChangePassword_Presenter.ChangePasswordView {
FragmentChangePasswordBinding binding;
    private View view;
    private Dialog dialog;
    private ChangePassword_Presenter presenter;
    String OldPassword, NewPassword,CNewPassword,Email;
    private Dialog dialogbox;
    User_Data user_data;

    public ChangePassword_Fragment() {
        // Required empty public constructor
    }
   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_change_password, container, false);
       binding = DataBindingUtil.inflate(inflater, R.layout.fragment_change_password, container, false);

       user_data = SharedPrefManager.getInstance(getContext()).getLoginDATA();
       Email = user_data.getEmail();
       view = binding.getRoot();
       dialog = AppUtils.hideShowProgress(getContext());

       binding.btnChange.setOnClickListener(this);
       presenter = new ChangePassword_Presenter(this);



       return binding.getRoot();


   }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_change:
                GetUserData();
                break;
        }
    }

    private void GetUserData() {
        OldPassword = binding.edUserOldPassword.getText().toString();
        NewPassword = binding.edUsernewPassword.getText().toString();
        CNewPassword = binding.edUserCPassword.getText().toString();


        if (OldPassword.isEmpty()){
            Sneaker.with(getActivity())
                    .setTitle("Enter Old Password")
                    .setCornerRadius(4)
                    .setDuration(2500)
                    .sneakWarning();
            binding.edUserOldPassword.requestFocus();
        }else if(NewPassword.isEmpty()){
            Sneaker.with(getActivity())
                    .setTitle("Enter New Password")
                    .setCornerRadius(4)
                    .setDuration(2500)
                    .sneakWarning();
            binding.edUsernewPassword.requestFocus();
        }else if(CNewPassword.isEmpty()){
            Sneaker.with(getActivity())
                    .setTitle("Enter  Confirm New password")
                    .setCornerRadius(4)
                    .setDuration(2500)
                    .sneakWarning();
            binding.edUserCPassword.requestFocus();
        }else if (!NewPassword.equals(CNewPassword)){
            Sneaker.with(getActivity())
                    .setTitle(" New Password Not Match To Confirm New Password")
                    .setCornerRadius(4)
                    .setDuration(2500)
                    .sneakWarning();
        }else {
          /*  SendOtpBody sendOtpBody = new SendOtpBody(Email);
            presenter.SendOTP(getContext(),sendOtpBody);*/

            Sneaker.with(getActivity())
                    .setTitle("Api lagani baki h ")
                    .setCornerRadius(4)
                    .setDuration(2500)
                    .sneakSuccess();
        }

    }

    @Override
    public void showHideProgress(boolean isShow) {
        if (isShow){
            dialog.show();
        }else{
            dialog.dismiss();
        }
    }

    @Override
    public void onError(String message) {
        Sneaker.with(getActivity())
                .setTitle(message)
                .setMessage("")
                .setCornerRadius(4)
                .setDuration(1500)
                .sneakError();

    }

    @Override
    public void onOTPSendSuccess(ResponseBody responseBody, String message) {
        Sneaker.with(getActivity())
                .setTitle("OTP Send in Your Email")
                .setCornerRadius(4)
                .setDuration(2500)
                .sneakSuccess();
        showRightCustomDialog();

    }

    @Override
    public void onChangePasswordSuccess(ResponseBody responseBody, String message) {
        if (message.equalsIgnoreCase("ok")){
            binding.edUserCPassword.setText("");
            binding.edUsernewPassword.setText("");
            binding.edUserOldPassword.setText("");


            Sneaker.with(getActivity())
                    .setTitle("Successfully Update ")
                    .setCornerRadius(4)
                    .setDuration(2500)
                    .sneakSuccess();
            dialogbox.dismiss();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        Sneaker.with(getActivity())
                .setTitle(t.getLocalizedMessage())
                .setMessage("")
                .setCornerRadius(4)
                .setDuration(1500)
                .sneakError();

    }

    private void showRightCustomDialog() {
        dialogbox = new Dialog(getContext());
        dialogbox.setContentView(R.layout.custom_verify_dialogbox);

        TextView text_email = (TextView) dialogbox.findViewById(R.id.text_email);
        TextView text_resend = (TextView) dialogbox.findViewById(R.id.text_resend);
        TextView text_cancel = (TextView) dialogbox.findViewById(R.id.text_cancel);
        TextView text_save = (TextView) dialogbox.findViewById(R.id.text_save);
        PinView enter_otp = (PinView)  dialogbox.findViewById(R.id.enter_otp);

        text_email.setText("OTP has sent to you at your email address \n"+Email);
        text_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogbox.dismiss();
            }
        });
        text_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otp = enter_otp.getText().toString();
                ChangePasswordBody body = new ChangePasswordBody(OldPassword,NewPassword,otp);
                presenter.ChangePassword(getContext(),body);

            }
        });

        text_resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendOtpBody sendOtpBody = new SendOtpBody(Email);
                presenter.SendOTP(getContext(),sendOtpBody);
            }
        });


        dialogbox.show();
        dialogbox.setCancelable(false);
        dialogbox.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

}