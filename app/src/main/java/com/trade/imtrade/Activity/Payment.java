package com.trade.imtrade.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.trade.imtrade.R;
import com.trade.imtrade.databinding.ActivityPaymentBinding;
import com.trade.imtrade.utils.AppUtils;

public class Payment extends AppCompatActivity {
ActivityPaymentBinding binding;
    private Context context;
    private Dialog dialog;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_payment);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_payment);

        AppUtils.setUpToolbar(this, binding.toolbar, true, true, "Payment");
        view = binding.getRoot();
        context = Payment.this;
    }
}