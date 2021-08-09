package com.trade.imtrade.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.trade.imtrade.R;
import com.trade.imtrade.SharedPerfence.MyPreferences;
import com.trade.imtrade.SharedPerfence.PrefConf;
import com.trade.imtrade.databinding.ActivityOrderSummaryBinding;
import com.trade.imtrade.utils.AppUtils;

public class OrderSummary extends AppCompatActivity {
ActivityOrderSummaryBinding binding;
    private Context context;
    private Dialog dialog;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_order_summary);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_order_summary);

        AppUtils.setUpToolbar(this, binding.toolbar, true, true, "Order Summary");
        view = binding.getRoot();
        context = OrderSummary.this;


    }
}