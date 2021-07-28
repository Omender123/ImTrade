package com.trade.imtrade.Fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.irozon.sneaker.Sneaker;
import com.trade.imtrade.Model.request.ChangeEmailBody;
import com.trade.imtrade.Model.request.SendNewEmailOtpBody;
import com.trade.imtrade.Model.request.SendOtpBody;
import com.trade.imtrade.R;
import com.trade.imtrade.SharedPrefernce.SharedPrefManager;
import com.trade.imtrade.SharedPrefernce.User_Data;
import com.trade.imtrade.databinding.FragmentChangeEmailBinding;
import com.trade.imtrade.utils.AppUtils;
import com.trade.imtrade.utils.Validation;
import com.trade.imtrade.view_presenter.ChangeEmailPresenter;
import com.trade.imtrade.view_presenter.UpdateProfile_Presenter;

import okhttp3.ResponseBody;

public class Change_Email extends Fragment implements View.OnClickListener, ChangeEmailPresenter.ChangeEmailView {
    FragmentChangeEmailBinding binding;
    private View view;
    private Dialog dialog;
    User_Data userData;
    private ChangeEmailPresenter presenter;
    String OldEmail, NewEmail,CNewEmail;
    private Dialog dialogbox;
    public Change_Email() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_change__email, container, false);

        view = binding.getRoot();
        dialog = AppUtils.hideShowProgress(getContext());
        userData = SharedPrefManager.getInstance(getContext()).getLoginDATA();

        binding.btnChange.setOnClickListener(this);
        presenter = new ChangeEmailPresenter(this);



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

    public void GetUserData() {
        OldEmail = binding.edUserOldEmail.getText().toString();
        NewEmail = binding.edUsernewEmail.getText().toString();
        CNewEmail = binding.edUserCEmail.getText().toString();

        if (OldEmail.isEmpty()) {
            Sneaker.with(getActivity())
                    .setTitle("Enter Old Email")
                    .setCornerRadius(4)
                    .setDuration(2500)
                    .sneakWarning();
            binding.edUserOldEmail.requestFocus();
        } else if (NewEmail.isEmpty()) {
            Sneaker.with(getActivity())
                    .setTitle("Enter New Email")
                    .setCornerRadius(4)
                    .setDuration(2500)
                    .sneakWarning();
            binding.edUsernewEmail.requestFocus();
        }else if (CNewEmail.isEmpty()) {
            Sneaker.with(getActivity())
                    .setTitle("Confirm New Email")
                    .setCornerRadius(4)
                    .setDuration(2500)
                    .sneakWarning();
            binding.edUserCEmail.requestFocus();
        }else if(!Validation.isValidEmail(OldEmail) ){
            binding.edUserOldEmail.requestFocus();
            Sneaker.with(getActivity())
                    .setTitle(" Enter a valid Old email!!!!")
                    .setCornerRadius(4)
                    .setDuration(2500)
                    .sneakWarning();
        }else if(!Validation.isValidEmail(NewEmail) ){
            binding.edUsernewEmail.requestFocus();
            Sneaker.with(getActivity())
                    .setTitle(" Enter a valid  New email!!!!")
                    .setCornerRadius(4)
                    .setDuration(2500)
                    .sneakWarning();
        }else if(!Validation.isValidEmail(CNewEmail)){
            binding.edUserCEmail.requestFocus();
            Sneaker.with(getActivity())
                    .setTitle(" Enter a valid Confirm email!!!!")
                    .setCornerRadius(4)
                    .setDuration(2500)
                    .sneakWarning();
        } else if (!NewEmail.equals(CNewEmail)) {
            Sneaker.with(getActivity())
                    .setTitle("New Email Not Match To Confirm New Email")
                    .setCornerRadius(4)
                    .setDuration(2500);

        } else {

            if (!OldEmail.equalsIgnoreCase(userData.getEmail())){

                Sneaker.with(getActivity())
                        .setTitle("Please Enter Correct Old Email")
                        .setCornerRadius(4)
                        .setDuration(2500)
                        .sneakError();
                binding.edUserOldEmail.requestFocus();
            }else{
                SendOtpBody sendOLDBody = new SendOtpBody(OldEmail);
                presenter.SendOldEmailOTP(getContext(),sendOLDBody);
                SendNewEmailOtpBody sendOtpBody = new SendNewEmailOtpBody(NewEmail);
                presenter.SendNewEmailOTP(getContext(),sendOtpBody);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showRightCustomDialog(OldEmail,NewEmail);
                    }
                },1000);

            }

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
    public void onOTPSendOldSuccess(ResponseBody responseBody, String message) {
        if (message.equalsIgnoreCase("ok")){
            Sneaker.with(getActivity())
                    .setTitle("Old Email OTP Send in this"+OldEmail)
                    .setMessage("")
                    .setCornerRadius(4)
                    .setDuration(1500)
                    .sneakSuccess();
        }

    }

    @Override
    public void onOTPSendNewSuccess(ResponseBody responseBody, String message) {
        if (message.equalsIgnoreCase("ok")){
            Sneaker.with(getActivity())
                    .setTitle("New Email OTP Send in this"+NewEmail)
                    .setMessage("")
                    .setCornerRadius(4)
                    .setDuration(1500)
                    .sneakSuccess();
        }
    }

    @Override
    public void onChangeEmailSuccess(ResponseBody responseBody, String message) {
        if (message.equalsIgnoreCase("ok")){
            binding.edUserOldEmail.setText("");
            binding.edUsernewEmail.setText("");
            binding.edUserCEmail.setText("");
            Sneaker.with(getActivity())
                    .setTitle("Email Change is SuccessFully")
                    .setMessage("")
                    .setCornerRadius(4)
                    .setDuration(1500)
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

    private void showRightCustomDialog(String oldEmail, String newEmail) {
        dialogbox = new Dialog(getContext());
        dialogbox.setContentView(R.layout.custom_verify_dialogbox);

        TextView text_email = (TextView) dialogbox.findViewById(R.id.text_email);
        TextView text_resend = (TextView) dialogbox.findViewById(R.id.text_resend);
        TextView text_resend1 = (TextView) dialogbox.findViewById(R.id.text_resend1);
        TextView text_cancel = (TextView) dialogbox.findViewById(R.id.text_cancel);
        TextView text_save = (TextView) dialogbox.findViewById(R.id.text_save);
        TextView text = (TextView) dialogbox.findViewById(R.id.text);
        PinView enter_otp = (PinView)  dialogbox.findViewById(R.id.enter_otp);
        PinView enter_otp1 = (PinView)  dialogbox.findViewById(R.id.enter_otp1);
        RelativeLayout relative1 = (RelativeLayout) dialogbox.findViewById(R.id.relative1);

        relative1.setVisibility(View.VISIBLE);
        enter_otp1.setVisibility(View.VISIBLE);
        text.setVisibility(View.VISIBLE);
        text_email.setText("OTP has sent to you at your old and new\n" + "email address ");
        text_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogbox.dismiss();
            }
        });
        text_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otp1 = enter_otp.getText().toString();
                String otp2 = enter_otp1.getText().toString();
                ChangeEmailBody body = new ChangeEmailBody(oldEmail,newEmail,otp2,otp1);
                presenter.ChangeEmail(getContext(),body);


            }
        });

        text_resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendOtpBody sendOtpBody = new SendOtpBody(oldEmail);
                presenter.SendOldEmailOTP(getContext(),sendOtpBody);
            }
        });
        text_resend1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendNewEmailOtpBody sendOtpBody = new SendNewEmailOtpBody(NewEmail);
                presenter.SendNewEmailOTP(getContext(),sendOtpBody);

            }
        });


        dialogbox.show();
        dialogbox.setCancelable(false);
        dialogbox.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
}