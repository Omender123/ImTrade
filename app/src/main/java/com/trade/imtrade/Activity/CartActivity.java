package com.trade.imtrade.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.trade.imtrade.Adapter.CartItemAdapter;
import com.trade.imtrade.Adapter.GameAdapter;
import com.trade.imtrade.Adapter.Review_product_Adapter;
import com.trade.imtrade.R;
import com.trade.imtrade.databinding.ActivityCartBinding;
import com.trade.imtrade.utils.AppUtils;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    ActivityCartBinding binding;
    String[] price = {"8999 Rs", "8999 Rs", "8999 Rs"};
    String[] price1 = {"8999 Rs"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_cart);

        AppUtils.setUpToolbar(this, binding.toolbar, true, true, "Cart");

        getAllCartItem();
        getSaveCartItem();
        getAllRelated_Product();

    }

    private void getAllCartItem() {
        ArrayList<String> arrayList = new ArrayList<String>();
        for (int i = 1; i <= 15; i++) {
            arrayList.add(String.valueOf(i));
        }
        CartItemAdapter cartItemAdapter = new CartItemAdapter(CartActivity.this, price, arrayList,"Save For Later");
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        binding.cartReyclerView.setLayoutManager(mLayoutManager1);
        binding.cartReyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.cartReyclerView.setAdapter(cartItemAdapter);

    }

    private void getSaveCartItem() {
        ArrayList<String> arrayList = new ArrayList<String>();
        for (int i = 1; i <= 15; i++) {
            arrayList.add(String.valueOf(i));
        }
        CartItemAdapter cartItemAdapter = new CartItemAdapter(CartActivity.this, price1, arrayList,"Move To Cart");
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        binding.SaveReyclerView.setLayoutManager(mLayoutManager1);
        binding.SaveReyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.SaveReyclerView.setAdapter(cartItemAdapter);

    }

    private void getAllRelated_Product() {
        String price[] = {"7999", "8999", "10000"};
        Review_product_Adapter review_product_adapter = new Review_product_Adapter(this, price);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.relatedProductRcycler.setLayoutManager(mLayoutManager1);
        binding.relatedProductRcycler.setItemAnimator(new DefaultItemAnimator());
        binding.relatedProductRcycler.setAdapter(review_product_adapter);

    }
}