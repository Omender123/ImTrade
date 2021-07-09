package com.trade.imtrade.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.irozon.sneaker.Sneaker;
import com.trade.imtrade.R;
import com.trade.imtrade.SharedPrefernce.SharedPrefManager;
import com.trade.imtrade.SharedPrefernce.User_Data;
import com.trade.imtrade.databinding.ActivityReferralCodeBinding;
import com.trade.imtrade.utils.AppUtils;

public class Referral_Code extends AppCompatActivity implements View.OnClickListener {
    ActivityReferralCodeBinding binding;
    User_Data user_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_referral__code);



        AppUtils.setUpToolbar(this, binding.toolbar, true, true, "Referral Code");

        user_data = SharedPrefManager.getInstance(Referral_Code.this).getLoginDATA();
        binding.referral.setText(user_data.getReferral_code());

        binding.share.setOnClickListener(this);
        binding.textCopy.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_copy:
                ClipboardManager cm = (ClipboardManager) getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(binding.referral.getText().toString());
                Sneaker.with(Referral_Code.this)
                        .setTitle("Copied......")
                        .setMessage("")
                        .setCornerRadius(4)
                        .setDuration(1500)
                        .sneakSuccess();
                break;

            case R.id.share:
                AppUtils.shareApp(Referral_Code.this, user_data.getReferral_code());
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppUtils.FullScreen(this);
    }
}