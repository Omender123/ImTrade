package com.trade.imtrade.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.irozon.sneaker.Sneaker;
import com.trade.imtrade.Model.ResponseModel.BuyNowResponse;
import com.trade.imtrade.Model.ResponseModel.DealofDayResponse;
import com.trade.imtrade.Model.request.BuyNowRequest;
import com.trade.imtrade.R;
import com.trade.imtrade.SharedPerfence.MyPreferences;
import com.trade.imtrade.SharedPerfence.PrefConf;
import com.trade.imtrade.databinding.ActivityOrderSummaryBinding;
import com.trade.imtrade.utils.AppUtils;
import com.trade.imtrade.view_presenter.OrderSummary_Presenter;

import java.util.ArrayList;

public class OrderSummary extends AppCompatActivity implements View.OnClickListener, OrderSummary_Presenter.OrderSummaryView {
    ActivityOrderSummaryBinding binding;
    private Context context;
    private Dialog dialog;
    private View view;
    String check, ProductID;
    private OrderSummary_Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_order_summary);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_order_summary);

        AppUtils.setUpToolbar(this, binding.toolbar, true, true, "Order Summary");

        view = binding.getRoot();
        context = OrderSummary.this;

        presenter = new OrderSummary_Presenter(this);
        dialog = AppUtils.hideShowProgress(context);
        binding.btnContinue.setOnClickListener(this);


        try {
            ArrayList<BuyNowRequest.Product> productArrayList = new ArrayList<BuyNowRequest.Product>();
            Bundle bundle = getIntent().getExtras();
            for (int i = 0; i < bundle.getStringArrayList("productId").size(); i++) {
                BuyNowRequest.Product product = new BuyNowRequest.Product(bundle.getStringArrayList("productId").get(i), bundle.getIntegerArrayList("Quantity").get(i));
                productArrayList.add(product);
            }
            BuyNowRequest buyNowRequest = new BuyNowRequest(productArrayList);
            presenter.BuyNowOrderProduct(OrderSummary.this, buyNowRequest);
        } catch (Exception e) {
            Toast.makeText(context, "" + e.toString(), Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_Continue:
                startActivity(new Intent(OrderSummary.this, Payment.class));
                break;
        }
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
    public void onBuyNowOneProductSuccess(BuyNowResponse buyNowResponse, String message) {

        if (message.equalsIgnoreCase("ok")) {
            binding.textTotalItem.setText("Price (1 items)");
            binding.textTotalPriceItem.setText(buyNowResponse.getTotalAmount() + ".00 Rs");
            binding.textTaxPrice.setText("(+) " + buyNowResponse.getTax() + ".00 Rs");
            binding.textOtherPriceCharges.setText("(+) " + buyNowResponse.getShippingCharges() + ".00 Rs");
            binding.textTotalPrice.setText(buyNowResponse.getTotalPayableAmount() + ".00 Rs");
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