package com.trade.imtrade.Authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.irozon.sneaker.Sneaker;
import com.trade.imtrade.Model.request.SendOtpBody;
import com.trade.imtrade.R;
import com.trade.imtrade.SharedPerfence.MyPreferences;
import com.trade.imtrade.SharedPerfence.PrefConf;
import com.trade.imtrade.databinding.ActivityForgetPasswordBinding;
import com.trade.imtrade.utils.AppUtils;
import com.trade.imtrade.utils.Validation;
import com.trade.imtrade.view_presenter.Send_OTP_Presenter;
import com.trade.imtrade.view_presenter.VerifyOtp_Presenter;

import de.mateware.snacky.Snacky;

public class
ForgetPassword extends AppCompatActivity implements View.OnClickListener, Send_OTP_Presenter.OTP_SendView {
ActivityForgetPasswordBinding  binding;
    private Context context;
    private Dialog dialog;
    private View view;
    private Send_OTP_Presenter presenter1;
    String Email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_forget_password);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_forget_password);

        AppUtils.FullScreen(this);
        view = binding.getRoot();
        context = ForgetPassword.this;
        presenter1 = new Send_OTP_Presenter(this);
        dialog = AppUtils.hideShowProgress(context);

        binding.cardNext.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.card_next:
                AppUtils.FullScreen(this);
                AppUtils.hideKeyboard(v,getApplicationContext());
               // startActivity(new Intent(getApplicationContext(),Change_Password.class));
              SendOTP();
                break;
        }
    }

    private void SendOTP() {
       Email = binding.email.getText().toString();
        if(Email.isEmpty()){
            binding.email.requestFocus();
            Snacky.builder()
                    .setActivity(ForgetPassword.this)
                    .setText("Please enter Email !")
                    .setTextColor(getResources().getColor(R.color.white))
                    .warning()
                    .show();

        }else if(!Validation.isValidEmail(Email)){
            binding.email.requestFocus();
            Snacky.builder()
                    .setActivity(ForgetPassword.this)
                    .setText("Enter a valid email!")
                    .setTextColor(getResources().getColor(R.color.white))
                    .warning()
                    .show();
        }else{
            SendOtpBody sendOtpBody = new SendOtpBody(Email);
            presenter1.SendOTP(sendOtpBody);
        }
    }

    @Override
    public void showOTPHideProgress(boolean isShow) {
        if (isShow){
            dialog.show();
        }else{
            dialog.dismiss();
        }

    }

    @Override
    public void onOTPError(String message) {
        Sneaker.with(this)
                .setTitle(message)
                .setMessage("")
                .setCornerRadius(4)
                .setDuration(1500)
                .sneakError();
    }

    @Override
    public void onOTPSuccess(String message) {
        if (message.equalsIgnoreCase("ok")){
            MyPreferences.getInstance(ForgetPassword.this).putString(PrefConf.EMAIL,Email);
            startActivity(new Intent(getApplicationContext(),Change_Password.class));
            Toast.makeText(context, "Otp send in Your Email", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onOTPFailure(Throwable t) {
        Sneaker.with(this)
                .setTitle(t.getLocalizedMessage())
                .setMessage("")
                .setCornerRadius(4)
                .setDuration(1500)
                .sneakError();
    }
    @Override
    protected void onResume() {
        super.onResume();
        AppUtils.FullScreen(this);
    }
}