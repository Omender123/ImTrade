package com.trade.imtrade.Authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ProgressBar;

import com.irozon.sneaker.Sneaker;
import com.trade.imtrade.Model.request.SendOtpBody;
import com.trade.imtrade.Model.request.VerifyOTP_Body;
import com.trade.imtrade.R;
import com.trade.imtrade.SharedPerfence.MyPreferences;
import com.trade.imtrade.SharedPerfence.PrefConf;
import com.trade.imtrade.databinding.ActivityEmailVerifyBinding;
import com.trade.imtrade.utils.AppUtils;
import com.trade.imtrade.view_presenter.Send_OTP_Presenter;
import com.trade.imtrade.view_presenter.SignUpPresenter;
import com.trade.imtrade.view_presenter.VerifyOtp_Presenter;

import java.util.concurrent.TimeUnit;

import de.mateware.snacky.Snacky;

public class Email_Verify extends AppCompatActivity implements View.OnClickListener, VerifyOtp_Presenter.OTP_VerifyView, Send_OTP_Presenter.OTP_SendView {
ActivityEmailVerifyBinding binding;
    private Context context;
    private Dialog dialog;
    private VerifyOtp_Presenter presenter;
    private Send_OTP_Presenter presenter1;
    private View view;
    private long timeCountInMilliSeconds = 1 * 60000;

    private enum TimerStatus {
        STARTED,
        STOPPED
    }

    private TimerStatus timerStatus = TimerStatus.STOPPED;


    Boolean click=false;
    private static CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   setContentView(R.layout.activity_email__verify);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_email__verify);
        AppUtils.FullScreen(this);
        view = binding.getRoot();
        context = Email_Verify.this;
        presenter = new VerifyOtp_Presenter(this);
        presenter1 = new Send_OTP_Presenter(this);
        dialog = AppUtils.hideShowProgress(context);


        binding.cardDone.setOnClickListener(this);
        startCountDownTimer();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.card_done:
                AppUtils.FullScreen(this);
                AppUtils.hideKeyboard(v,getApplicationContext());

               VerifyOtp();
                 break;
        }
    }

    private void VerifyOtp() {
        String Email = MyPreferences.getInstance(getApplicationContext()).getString(PrefConf.EMAIL,"");
        String otp = binding.enterOtp.getText().toString();
        if (otp.isEmpty()){
            Snacky.builder()
                    .setActivity(Email_Verify.this)
                    .setText("Please enter OTP !")
                    .setTextColor(getResources().getColor(R.color.white))
                    .warning()
                    .show();
        }else{
             VerifyOTP_Body verifyOTP_body = new VerifyOTP_Body(Email,otp);
             presenter.VerifyUser(verifyOTP_body);
        }
    }

    public void Resend_OTP(View view) {
        String Email = MyPreferences.getInstance(getApplicationContext()).getString(PrefConf.EMAIL,"");

        AppUtils.FullScreen(this);
        AppUtils.hideKeyboard(view,getApplicationContext());
        SendOtpBody sendOtpBody = new SendOtpBody(Email);
        presenter1.SendOTP(sendOtpBody);

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
        Sneaker.with(this)
                .setTitle(message)
                .setMessage("")
                .setCornerRadius(4)
                .setDuration(1500)
                .sneakError();

    }

    @Override
    public void onSuccess(String message) {
        if (message.equalsIgnoreCase("ok")){
            MyPreferences.getInstance(Email_Verify.this).putString(PrefConf.TYPE,"SignUP");
            startActivity(new Intent(getApplicationContext(),Success_screen.class));
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
            Sneaker.with(this)
                    .setTitle("OTP Resend in Your Email")
                    .setMessage("")
                    .setCornerRadius(4)
                    .setDuration(1500)
                    .sneakSuccess();

            reset();
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
    private void reset() {
        stopCountDownTimer();
        startCountDownTimer();

    }

    private void startCountDownTimer() {

        countDownTimer = new CountDownTimer(timeCountInMilliSeconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
               binding.resend.setVisibility(View.GONE);
               binding.timer.setVisibility(View.VISIBLE);
                binding.timer.setText("Time left "+hmsTimeFormatter(millisUntilFinished)+"s");



            }

            @Override
            public void onFinish() {

                binding.timer.setText("Time left 60s");
                timerStatus = TimerStatus.STOPPED;
                binding.resend.setVisibility(View.VISIBLE);
                binding.timer.setVisibility(View.GONE);


                binding.resend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Resend_OTP(v);
                    }
                });
            }

        }.start();
        countDownTimer.start();
    }

    private void stopCountDownTimer() {
        countDownTimer.cancel();
    }


    private String hmsTimeFormatter(long milliSeconds) {

        String hms = String.format("%02d",  TimeUnit.MILLISECONDS.toSeconds(milliSeconds) );
        return hms;


    }


}