package com.trade.imtrade.Authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.irozon.sneaker.Sneaker;
import com.trade.imtrade.Model.request.ForgetPasswordBody;
import com.trade.imtrade.R;
import com.trade.imtrade.SharedPerfence.MyPreferences;
import com.trade.imtrade.SharedPerfence.PrefConf;
import com.trade.imtrade.databinding.ActivityChangePasswordBinding;
import com.trade.imtrade.utils.AppUtils;
import com.trade.imtrade.view_presenter.Forget_Password_Presenter;

import de.mateware.snacky.Snacky;

public class Change_Password extends AppCompatActivity implements View.OnClickListener, Forget_Password_Presenter.Change_PasswordView {
    ActivityChangePasswordBinding binding;
    private Context context;
    private Dialog dialog;
    private View view;
    private Forget_Password_Presenter presenter;
    String Email, Otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_change__password);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_change__password);
       
        view = binding.getRoot();
        context = Change_Password.this;
        dialog = AppUtils.hideShowProgress(context);

        presenter = new Forget_Password_Presenter(this);

        Email = MyPreferences.getInstance(getApplicationContext()).getString(PrefConf.EMAIL, "");
        Otp = MyPreferences.getInstance(getApplicationContext()).getString(PrefConf.OTP, "");
        binding.cardDone.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.card_Done:
               
                AppUtils.hideKeyboard(v, getApplicationContext());
                change_password();
                break;
        }
    }

    private void change_password() {
        String new_pass = binding.newPassword.getText().toString();
        String c_pass = binding.cPassword.getText().toString();
        if (new_pass.isEmpty()) {
            binding.newPassword.requestFocus();
            Snacky.builder()
                    .setActivity(Change_Password.this)
                    .setText("Please enter New Password !")
                    .setTextColor(getResources().getColor(R.color.white))
                    .warning()
                    .show();
        } else if (c_pass.isEmpty()) {
            binding.cPassword.requestFocus();
            Snacky.builder()
                    .setActivity(Change_Password.this)
                    .setText("Please enter Confirm Password !")
                    .setTextColor(getResources().getColor(R.color.white))
                    .warning()
                    .show();
        } else if (!c_pass.equals(new_pass)) {
            Snacky.builder()
                    .setActivity(Change_Password.this)
                    .setText("Password Not Match !")
                    .setTextColor(getResources().getColor(R.color.white))
                    .warning()
                    .show();
        } else {

            ForgetPasswordBody forgetPasswordBody = new ForgetPasswordBody(Email, Otp, new_pass);
            presenter.ForgetPassword(forgetPasswordBody);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
       
    }

    @Override
    public void showHideProgress(boolean isShow) {
        if (isShow) {
            dialog.show();
        } else {
            dialog.dismiss();
        }
    }

    @Override
    public void onError(String message) {
        Sneaker.with(this)
                .setTitle(message)
                .setMessage("")
                .setCornerRadius(4)
                .setDuration(1500)
                .sneakError();

    }

    @Override
    public void onSuccess(String message) {

        if (message.equalsIgnoreCase("ok")) {
            MyPreferences.getInstance(Change_Password.this).putString(PrefConf.TYPE, "Change_password");
            startActivity(new Intent(getApplicationContext(), Success_screen.class));

        }
    }

    @Override
    public void onFailure(Throwable t) {
        Sneaker.with(this)
                .setTitle(t.getLocalizedMessage())
                .setMessage("")
                .setCornerRadius(4)
                .setDuration(1500)
                .sneakError();

    }
}