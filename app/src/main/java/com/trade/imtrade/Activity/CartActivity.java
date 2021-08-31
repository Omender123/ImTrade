package com.trade.imtrade.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.irozon.sneaker.Sneaker;
import com.trade.imtrade.Adapter.CartItemAdapter;
import com.trade.imtrade.Adapter.GameAdapter;
import com.trade.imtrade.Adapter.Review_product_Adapter;
import com.trade.imtrade.Adapter.SaveForLaterAdapter;
import com.trade.imtrade.Adapter.SuggestionProductAdapter;
import com.trade.imtrade.Model.ResponseModel.CartProductResponse;
import com.trade.imtrade.Model.ResponseModel.ContinueYourHuntResponse;
import com.trade.imtrade.Model.ResponseModel.SaveForLaterResponse;
import com.trade.imtrade.Model.request.AddToCartBody;
import com.trade.imtrade.R;
import com.trade.imtrade.SharedPerfence.MyPreferences;
import com.trade.imtrade.SharedPerfence.PrefConf;
import com.trade.imtrade.databinding.ActivityCartBinding;
import com.trade.imtrade.utils.AppUtils;
import com.trade.imtrade.view_presenter.CartPresenter;
import com.trade.imtrade.view_presenter.ProductDetails_Presenter;

import java.util.ArrayList;

import okhttp3.ResponseBody;

public class CartActivity extends AppCompatActivity implements CartPresenter.CartPresenterView, CartItemAdapter.OnGetCartItemListener, SaveForLaterAdapter.OnGetCartItemListener, View.OnClickListener {
    ActivityCartBinding binding;
    CartPresenter presenter;
    private Context context;
    private Dialog dialog;
    private View view;
    String TotalItem;
    Boolean check = false;
    TextView text_cart_Count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_cart);

        AppUtils.setUpToolbar(this, binding.toolbar, true, true, "Cart");
        view = binding.getRoot();
        context = CartActivity.this;
        presenter = new CartPresenter(this);
        dialog = AppUtils.hideShowProgress(context);



        binding.btnTotalItem.setOnClickListener(this);
        presenter.GetCartProduct(CartActivity.this);
        presenter.GetSuggestionProduct(CartActivity.this);
        //  presenter.GetSaveForLater(CartActivity.this);

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
    public void onCartSuccess(CartProductResponse cartProductResponse, String message) {
        if (message.equalsIgnoreCase("ok") && cartProductResponse != null) {
            CartItemAdapter cartItemAdapter = new CartItemAdapter(CartActivity.this, cartProductResponse, "Save For Later", this);
            RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
            binding.cartReyclerView.setLayoutManager(mLayoutManager1);
            binding.cartReyclerView.setItemAnimator(new DefaultItemAnimator());
            binding.cartReyclerView.setAdapter(cartItemAdapter);
            TotalItem = String.valueOf(cartProductResponse.getProducts().size());
            binding.btnTotalItem.setText("Proceed to Buy (" + TotalItem + " items)");


        }

    }

    @Override
    public void onDeleteCartSuccess(ResponseBody responseBody, String message) {
        if (message.equalsIgnoreCase("ok")) {
            Sneaker.with(this)
                    .setTitle("Product Successful Delete in Cart")
                    .setMessage("")
                    .setCornerRadius(4)
                    .setDuration(1500)
                    .sneakSuccess();
            presenter.GetCartProduct(CartActivity.this);
        }
    }

    @Override
    public void onIncreaseQuentitySuccess(ResponseBody responseBody, String message) {
        if (message.equalsIgnoreCase("ok")) {
            presenter.GetCartProduct(CartActivity.this);
        }
    }

    @Override
    public void onSaveForLaterSuccess(ResponseBody responseBody, String message) {
        if (message.equalsIgnoreCase("ok")) {
            Sneaker.with(this)
                    .setTitle("Product successfully added in save for later")
                    .setMessage("")
                    .setCornerRadius(4)
                    .setDuration(1500)
                    .sneakSuccess();

            presenter.GetCartProduct(CartActivity.this);
            presenter.GetSaveForLater(CartActivity.this);
        }
    }

    @Override
    public void onGetSaveForLaterSuccess(SaveForLaterResponse saveForLaterResponse, String message) {
        if (message.equalsIgnoreCase("ok")) {
            if (saveForLaterResponse.getProducts()!=null && saveForLaterResponse.getProducts().size()>0){
                SaveForLaterAdapter cartItemAdapter = new SaveForLaterAdapter(CartActivity.this, saveForLaterResponse, "Move To Cart", this);
                RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                binding.SaveReyclerView.setLayoutManager(mLayoutManager1);
                binding.SaveReyclerView.setItemAnimator(new DefaultItemAnimator());
                binding.SaveReyclerView.setAdapter(cartItemAdapter);
                binding.linearSave.setVisibility(View.VISIBLE);
            }else {
                binding.linearSave.setVisibility(View.GONE);
            }

        }
    }

    @Override
    public void onDeleteSaveForLaterProductSuccess(ResponseBody responseBody, String message) {
        if (message.equalsIgnoreCase("ok")) {
            Sneaker.with(this)
                    .setTitle("Product Successful Delete in Save For Later")
                    .setMessage("")
                    .setCornerRadius(4)
                    .setDuration(1500)
                    .sneakSuccess();
            presenter.GetSaveForLater(CartActivity.this);
        }
    }

    @Override
    public void onMoveToCartSuccess(ResponseBody responseBody, String message) {
        if (message.equalsIgnoreCase("ok")) {
            Sneaker.with(this)
                    .setTitle("Product Successful Move in  Cart")
                    .setMessage("")
                    .setCornerRadius(4)
                    .setDuration(1500)
                    .sneakSuccess();
            presenter.GetSaveForLater(CartActivity.this);
            presenter.GetCartProduct(CartActivity.this);
        }
    }

    @Override
    public void onSuggestionProductSuccess(ContinueYourHuntResponse SuggestionProductResponse, String message) {
        if (message.equalsIgnoreCase("ok")) {

            if (SuggestionProductResponse.getProducts()!=null && SuggestionProductResponse.getProducts().size()>0){
                SuggestionProductAdapter review_product_adapter = new SuggestionProductAdapter(getApplicationContext(),SuggestionProductResponse);
                RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
                binding.relatedProductRcycler.setLayoutManager(mLayoutManager1);
                binding.relatedProductRcycler.setItemAnimator(new DefaultItemAnimator());
                binding.relatedProductRcycler.setAdapter(review_product_adapter);
                binding.relatedProductRcycler.setVisibility(View.VISIBLE);
                binding.relativeRelated.setVisibility(View.VISIBLE);
            }else{
                binding.relatedProductRcycler.setVisibility(View.GONE);
                binding.relativeRelated.setVisibility(View.GONE);
            }
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
    public void onIncreaseQuantityItemClickListener(String ProductId, int Quantity) {


        AddToCartBody addToCartBody = new AddToCartBody(ProductId, Quantity);
        presenter.IncreaseQuentity(CartActivity.this, addToCartBody);


    }

    @Override
    public void onSaveLaterItemClickListener(String ProductId, String Type) {
        if (Type.equalsIgnoreCase("Save For Later")) {
            presenter.SaveForLater(CartActivity.this, ProductId);

        } else {
            presenter.moveToCart(CartActivity.this, ProductId);

        }

    }

    @Override
    public void onDeleteItemClickListener(String ProductId, String Type) {
        if (Type.equalsIgnoreCase("Save For Later")) {
            presenter.DeleteCartProduct(CartActivity.this, ProductId);

        } else {
           presenter.DeleteSaveForLaterProduct(CartActivity.this, ProductId);


        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_totalItem:
                startActivity(new Intent(CartActivity.this, OrderSummary.class));
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.GetCartProduct(CartActivity.this);
        presenter.GetSaveForLater(CartActivity.this);

    }
}