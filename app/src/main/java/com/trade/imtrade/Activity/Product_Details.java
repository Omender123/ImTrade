package com.trade.imtrade.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.irozon.sneaker.Sneaker;
import com.trade.imtrade.Adapter.ColorAdapter;
import com.trade.imtrade.Adapter.DetailsAdapter;
import com.trade.imtrade.Adapter.ReviewAdapter;
import com.trade.imtrade.Adapter.Review_product_Adapter;
import com.trade.imtrade.Adapter.StorageAdapter;
import com.trade.imtrade.Model.ResponseModel.ProductDetailsResponse;
import com.trade.imtrade.R;
import com.trade.imtrade.SharedPerfence.MyPreferences;
import com.trade.imtrade.SharedPerfence.PrefConf;
import com.trade.imtrade.databinding.ActivityProductDetailsBinding;
import com.trade.imtrade.utils.AppUtils;
import com.trade.imtrade.view_presenter.ProductDetails_Presenter;

import java.util.ArrayList;
import java.util.List;

public class Product_Details extends AppCompatActivity implements View.OnClickListener, ColorAdapter.OnColorItemListener, StorageAdapter.OnToggleItemListener, ProductDetails_Presenter.GetProductDetailsView {
    ActivityProductDetailsBinding binding;
    Boolean Check;
    String RouteId;
    ProductDetails_Presenter presenter;
    private Context context;
    private Dialog dialog;
    private View view;
    ProductDetailsResponse productDetailsResponses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_product__details);
        RouteId = MyPreferences.getInstance(Product_Details.this).getString(PrefConf.ROUTEID, "");


        view = binding.getRoot();
        context = Product_Details.this;
        presenter = new ProductDetails_Presenter(this);
        dialog = AppUtils.hideShowProgress(context);


        binding.back.setOnClickListener(this);
        binding.relativeCart.setOnClickListener(this);
        binding.showMore.setOnClickListener(this);
        binding.HideMore.setOnClickListener(this);
        binding.showMore1.setOnClickListener(this);
        binding.HideMore1.setOnClickListener(this);
        binding.showDetails.setOnClickListener(this);
        binding.HideDetails.setOnClickListener(this);


        presenter.GetProductDetails(Product_Details.this, RouteId);
        CheckBoxList();
        changeStatusBarColor();
        getReviewList();
         getAllReview_Product();


    }


    public void CheckBoxList() {
        binding.imageUnselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Check = true;
                binding.imageSelect.setVisibility(View.VISIBLE);
                binding.imageUnselect.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "" + Check, Toast.LENGTH_SHORT).show();
            }
        });
        binding.imageSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Check = false;
                binding.imageUnselect.setVisibility(View.VISIBLE);
                binding.imageSelect.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "" + Check, Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.card_background));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.back:
                onBackPressed();
                break;

            case R.id.relative_cart:
                Toast.makeText(this, "Cart", Toast.LENGTH_SHORT).show();
                break;

            case R.id.showMore:
                binding.showMore.setVisibility(View.GONE);
                binding.HideMore.setVisibility(View.VISIBLE);
                binding.ColorRecyclerView.setVisibility(View.VISIBLE);
                break;
            case R.id.HideMore:
                binding.showMore.setVisibility(View.VISIBLE);
                binding.HideMore.setVisibility(View.GONE);
                binding.ColorRecyclerView.setVisibility(View.GONE);
                break;

            case R.id.showMore1:
                binding.showMore1.setVisibility(View.GONE);
                binding.HideMore1.setVisibility(View.VISIBLE);
                binding.storageRecyclerView.setVisibility(View.VISIBLE);
                break;
            case R.id.HideMore1:
                binding.showMore1.setVisibility(View.VISIBLE);
                binding.HideMore1.setVisibility(View.GONE);
                binding.storageRecyclerView.setVisibility(View.GONE);
                break;
            case R.id.showDetails:
                binding.showDetails.setVisibility(View.GONE);
                binding.HideDetails.setVisibility(View.VISIBLE);
                getDelatilsList(productDetailsResponses,true);
                break;
            case R.id.HideDetails:
                binding.showDetails.setVisibility(View.VISIBLE);
                binding.HideDetails.setVisibility(View.GONE);
                getDelatilsList(productDetailsResponses,false);
                break;
        }
    }

    private void getReviewList() {
        String[] price = {"Full Name", "Full Name"};


        ReviewAdapter reviewAdapter = new ReviewAdapter(getApplicationContext(), price);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        binding.ReviewRecyclerView.setLayoutManager(mLayoutManager1);
        binding.ReviewRecyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.ReviewRecyclerView.setAdapter(reviewAdapter);

    }

    private void getAllReview_Product() {
        String price[] = {"7999", "8999", "10000", "7999", "8999", "10000"};
        Review_product_Adapter review_product_adapter = new Review_product_Adapter(this, price);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.reviewProducRcycler.setLayoutManager(mLayoutManager1);
        binding.reviewProducRcycler.setItemAnimator(new DefaultItemAnimator());
        binding.reviewProducRcycler.setAdapter(review_product_adapter);

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
    public void onProductDetailsSuccess(ProductDetailsResponse productDetailsResponse, String message) {
        productDetailsResponses =productDetailsResponse;
        if (message.equalsIgnoreCase("ok")) {
            /*--------------ProductBanner--------------*/
            List<SlideModel> BannerImage = new ArrayList<>();
            if (productDetailsResponse.getImages().size() > 0 && productDetailsResponse.getImages() != null) {
                for (int i = 0; i < productDetailsResponse.getImages().size(); i++) {
                    BannerImage.add(new SlideModel(PrefConf.IMAGE_URL + productDetailsResponse.getImages().get(i), ScaleTypes.FIT));
                }
                binding.slider.setImageList(BannerImage);

            } else {
                Sneaker.with(this)
                        .setTitle("No Product Image are Available")
                        .setMessage("")
                        .setCornerRadius(4)
                        .setDuration(1500)
                        .sneakError();
            }

            /*--------------SetProductDetails--------------*/
            binding.productName.setText(productDetailsResponse.getName());
            binding.textRating.setText(productDetailsResponse.getAverageRating());
            binding.productPrice.setText(productDetailsResponse.getDiscount()+" Rs");
            binding.productOffPrice.setText(productDetailsResponse.getVariables().get(0).getPrice().getMargin()+" %off");
            binding.productWorngPrice.setText(productDetailsResponse.getVariables().get(0).getPrice().getMrp()+" Rs");

            getColorList(productDetailsResponse);
            getStorageList(productDetailsResponse);
            getDelatilsList(productDetailsResponse,false);


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

    private void getColorList(ProductDetailsResponse productDetailsResponse) {

        if (productDetailsResponse.getColor().size()>0&& productDetailsResponse.getColor()!=null){

            ColorAdapter colorAdapter = new ColorAdapter(getApplicationContext(),productDetailsResponse, this);
            RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
            binding.ColorRecyclerView.setLayoutManager(mLayoutManager1);
            binding.ColorRecyclerView.setItemAnimator(new DefaultItemAnimator());
            binding.ColorRecyclerView.setAdapter(colorAdapter);
            binding.textColor.setText(productDetailsResponse.getColor().get(0).getName().toUpperCase());
        }else {
            Sneaker.with(this)
                    .setTitle("No Product Color are Available")
                    .setMessage("")
                    .setCornerRadius(4)
                    .setDuration(1500)
                    .sneakError();
        }


    }

    @Override
    public void onOnColorClickListener(ProductDetailsResponse productDetailsResponse,int position) {
        binding.textColor.setText(productDetailsResponse.getColor().get(position).getName().toUpperCase());
        Toast.makeText(context, ""+productDetailsResponse.getColor().get(position).getName(), Toast.LENGTH_SHORT).show();
    }

    private void getStorageList(ProductDetailsResponse productDetailsResponse) {
        if (productDetailsResponse.getStorage().size()>0&& productDetailsResponse.getStorage()!=null){


            StorageAdapter storageAdapter = new StorageAdapter(getApplicationContext(),productDetailsResponse , this);
            RecyclerView.LayoutManager mLayoutManager1 = new GridLayoutManager(getApplicationContext(), 4, LinearLayoutManager.VERTICAL, false);
            binding.storageRecyclerView.setLayoutManager(mLayoutManager1);
            binding.storageRecyclerView.setItemAnimator(new DefaultItemAnimator());
            binding.storageRecyclerView.setAdapter(storageAdapter);
            binding.textStorage.setText(productDetailsResponse.getStorage().get(0).getSize());
        }else {
            Sneaker.with(this)
                    .setTitle("No Product Size and Storage are Available")
                    .setMessage("")
                    .setCornerRadius(4)
                    .setDuration(1500)
                    .sneakError();
        }


    }
    @Override
    public void onToggleItemClickListener(ProductDetailsResponse productDetailsResponse,int position) {

            binding.textStorage.setText(productDetailsResponse.getStorage().get(position).getSize());

        Toast.makeText(context, ""+productDetailsResponse.getStorage().get(position).getSize(), Toast.LENGTH_SHORT).show();

    }

    private void getDelatilsList(ProductDetailsResponse productDetailsResponse,Boolean count) {
        String DetailsType[] = {"Brands Name", "OS", "RAM", "ROM", "FRONT CAMERA", "BACK CAMERA", "BATTERY"};
        String Details[] = {"VIVO", "ANDROID", "8 GB", "512 GB", "64 MP", "128 MP", "5000 MH"};


        if (count == true) {
            DetailsAdapter detailsAdapter = new DetailsAdapter(getApplicationContext(),productDetailsResponse, true);
            RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
            binding.detailsRecyclerView.setLayoutManager(mLayoutManager1);
            binding.detailsRecyclerView.setItemAnimator(new DefaultItemAnimator());
            binding.detailsRecyclerView.setAdapter(detailsAdapter);
        } else {
            DetailsAdapter detailsAdapter = new DetailsAdapter(getApplicationContext(), productDetailsResponse, false);
            RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
            binding.detailsRecyclerView.setLayoutManager(mLayoutManager1);
            binding.detailsRecyclerView.setItemAnimator(new DefaultItemAnimator());
            binding.detailsRecyclerView.setAdapter(detailsAdapter);
        }

    }

}
