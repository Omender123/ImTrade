package com.trade.imtrade.Authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.irozon.sneaker.Sneaker;
import com.trade.imtrade.MainActivity;
import com.trade.imtrade.R;
import com.trade.imtrade.SharedPerfence.MyPreferences;
import com.trade.imtrade.SharedPerfence.PrefConf;
import com.trade.imtrade.databinding.ActivityReferralCodeSrceenBinding;
import com.trade.imtrade.utils.AppUtils;

public class Referral_Code_Srceen extends AppCompatActivity implements View.OnClickListener {
    ActivityReferralCodeSrceenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_referral__code__srceen);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_referral__code__srceen);

        binding.cardReady.setOnClickListener(this);
        binding.scan.setOnClickListener(this);
        binding.imgBack.setOnClickListener(this);
        binding.Skip.setOnClickListener(this);

        //

    }


    public void Skip_refferal_code(View view) {

        MyPreferences.getInstance(Referral_Code_Srceen.this).putString(PrefConf.REFERRAL_CODE, "");
        startActivity(new Intent(getApplicationContext(), SignUp.class));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.card_ready:
                String referral = binding.refferalCode.getText().toString();
                if (referral.isEmpty()) {
                    Sneaker.with(this)
                            .setTitle("Please enter your Referral Code !")
                            .setMessage("")
                            .setCornerRadius(4)
                            .setDuration(1000)
                            .sneakWarning();
                } else {
                    AppUtils.hideKeyboard(v, getApplicationContext());
                    MyPreferences.getInstance(Referral_Code_Srceen.this).putString(PrefConf.REFERRAL_CODE, referral);
                    startActivity(new Intent(getApplicationContext(), SignUp.class));

                }

                break;

            case R.id.scan:
                startActivity(new Intent(getApplicationContext(), Scan_Referral_code.class));
                break;

            case R.id.img_back:
                onBackPressed();
                break;

            case R.id.Skip:
                startActivity(new Intent(Referral_Code_Srceen.this, MainActivity.class));
                finish();
                MyPreferences.getInstance(getApplicationContext()).putBoolean(PrefConf.LOGINCHECK,false);
                break;
        }
    }


}