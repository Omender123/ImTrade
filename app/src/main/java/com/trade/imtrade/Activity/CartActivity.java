package com.trade.imtrade.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.trade.imtrade.Adapter.CartItemAdapter;
import com.trade.imtrade.Adapter.GameAdapter;
import com.trade.imtrade.R;
import com.trade.imtrade.databinding.ActivityCartBinding;
import com.trade.imtrade.utils.AppUtils;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
ActivityCartBinding binding;
String [] price = {"8999 Rs","8999 Rs","8999 Rs"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_cart);

        AppUtils.setUpToolbar(this, binding.toolbar, true, true, "Cart");

        getAllCartItem();

    }

    private void getAllCartItem() {
        ArrayList<String>arrayList = new ArrayList<String>();
        for (int i=1;i<=15;i++){
            arrayList.add(String.valueOf(i));
        }
        CartItemAdapter cartItemAdapter = new CartItemAdapter(CartActivity.this, price,arrayList);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        binding.cartReyclerView.setLayoutManager(mLayoutManager1);
        binding.cartReyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.cartReyclerView.setAdapter(cartItemAdapter);

    }
}