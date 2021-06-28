package com.trade.imtrade.Authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.trade.imtrade.R;
import com.trade.imtrade.SharedPerfence.MyPreferences;
import com.trade.imtrade.SharedPerfence.PrefConf;
import com.trade.imtrade.databinding.ActivitySuccessScreenBinding;
import com.trade.imtrade.utils.AppUtils;

public class Success_screen extends AppCompatActivity {
ActivitySuccessScreenBinding binding;
String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_success_screen);
       // FullScreen();
        AppUtils.FullScreen(this);
        type = MyPreferences.getInstance(Success_screen.this).getString(PrefConf.TYPE,"");

        if (type.equals("Change_password")){
            binding.createAccount.setText("Password Changed");
        }else{
            binding.createAccount.setText("Account is created");
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MyPreferences.getInstance(Success_screen.this).deletePreference(PrefConf.User_ID);
                MyPreferences.getInstance(Success_screen.this).deletePreference(PrefConf.EMAIL);
              MyPreferences.getInstance(Success_screen.this).deletePreference(PrefConf.OTP);
                MyPreferences.getInstance(Success_screen.this).deletePreference(PrefConf.REFERRAL_CODE);

                startActivity(new Intent(getApplicationContext(),Login.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK));
                finish();
            }
        },2000);


    }

    @Override
    protected void onResume() {
        super.onResume();
        AppUtils.FullScreen(this);
    }
}