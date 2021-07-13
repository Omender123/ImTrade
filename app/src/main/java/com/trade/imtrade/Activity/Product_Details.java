package com.trade.imtrade.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.Toast;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.trade.imtrade.Adapter.BrandsToggleAdapter;
import com.trade.imtrade.Adapter.ColorAdapter;
import com.trade.imtrade.Adapter.DetailsAdapter;
import com.trade.imtrade.Adapter.ProductToSeasonAdapter;
import com.trade.imtrade.Adapter.ReviewAdapter;
import com.trade.imtrade.Adapter.Review_product_Adapter;
import com.trade.imtrade.Model.ResponseModel.BannerResponse;
import com.trade.imtrade.R;
import com.trade.imtrade.SharedPerfence.PrefConf;
import com.trade.imtrade.databinding.ActivityProductDetailsBinding;

import java.util.ArrayList;
import java.util.List;

public class Product_Details extends AppCompatActivity implements View.OnClickListener, ColorAdapter.OnColorItemListener, BrandsToggleAdapter.OnToggleItemListener {
    ActivityProductDetailsBinding binding;
    Boolean Check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_product__details);
        binding.back.setOnClickListener(this);
        binding.relativeCart.setOnClickListener(this);
        binding.showMore.setOnClickListener(this);
        binding.HideMore.setOnClickListener(this);
        binding.showMore1.setOnClickListener(this);
        binding.HideMore1.setOnClickListener(this);
        binding.showDetails.setOnClickListener(this);
        binding.HideDetails.setOnClickListener(this);



        getProductBanner();
        CheckBoxList();
        changeStatusBarColor();
        getReviewList();
        getColorList();
        getStorageList();
        getAllReview_Product();
        getDelatilsList(false);

    }




    private void getProductBanner() {
        List<SlideModel> BannerImage = new ArrayList<>();

        BannerImage.add(new SlideModel("https://image.shutterstock.com/image-vector/flash-sale-promotion-media-banner-260nw-1557251186.jpg", ScaleTypes.FIT));
        BannerImage.add(new SlideModel("https://img.freepik.com/free-vector/sale-banner-with-product-description_1361-1333.jpg?size=626&ext=jpg", ScaleTypes.FIT));
        BannerImage.add(new SlideModel("https://i.pinimg.com/736x/ce/19/1f/ce191f137fdb797b99a1c2930b61c57c.jpg", ScaleTypes.FIT));


        binding.slider.setImageList(BannerImage);

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
                Toast.makeText(getApplicationContext(),""+Check, Toast.LENGTH_SHORT).show();
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
        switch (v.getId()){

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
                getDelatilsList(true);
                break;
            case R.id.HideDetails:
                binding.showDetails.setVisibility(View.VISIBLE);
                binding.HideDetails.setVisibility(View.GONE);
                getDelatilsList(false);
                break;
        }
    }

    private void getColorList() {

        ArrayList<String>color = new ArrayList<String>();

        color.add("#FFFFFF");
        color.add("#000000");
        color.add("#FF8888");
        color.add("#FF0000");
        color.add("#000080");

        String colorName[] = {"White","Black","Grey","Red","Navy Blue"};

        ColorAdapter colorAdapter = new ColorAdapter(getApplicationContext(),color,colorName,this);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.ColorRecyclerView.setLayoutManager(mLayoutManager1);
        binding.ColorRecyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.ColorRecyclerView.setAdapter(colorAdapter);
     }

    @Override
    public void onOnColorClickListener(int position) {

    }

    private void getStorageList() {
        String [] price = {"64 GB","128 GB","256 GB","512 GB","1028 GB"};


        BrandsToggleAdapter brandsToggleAdapter = new BrandsToggleAdapter(getApplicationContext(),price,this);
        RecyclerView.LayoutManager mLayoutManager1 = new GridLayoutManager(getApplicationContext(), 4,LinearLayoutManager.VERTICAL ,false);
        binding.storageRecyclerView.setLayoutManager(mLayoutManager1);
        binding.storageRecyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.storageRecyclerView.setAdapter(brandsToggleAdapter);
    }

    @Override
    public void onToggleItemClickListener(int position, boolean Checked) {

    }

    private void getDelatilsList(Boolean count) {
        String DetailsType[]={"Brands Name","OS","RAM","ROM","FRONT CAMERA","BACK CAMERA","BATTERY"};
       String Details[]={"VIVO","ANDROID","8 GB","512 GB","64 MP","128 MP","5000 MH"};


        if (count==true){
            DetailsAdapter detailsAdapter = new DetailsAdapter(getApplicationContext(),DetailsType,Details,true);
            RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL ,false);
            binding.detailsRecyclerView.setLayoutManager(mLayoutManager1);
            binding.detailsRecyclerView.setItemAnimator(new DefaultItemAnimator());
            binding.detailsRecyclerView.setAdapter(detailsAdapter);
        }else {
            DetailsAdapter detailsAdapter = new DetailsAdapter(getApplicationContext(),DetailsType,Details,false);
            RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL ,false);
            binding.detailsRecyclerView.setLayoutManager(mLayoutManager1);
            binding.detailsRecyclerView.setItemAnimator(new DefaultItemAnimator());
            binding.detailsRecyclerView.setAdapter(detailsAdapter);
        }

    }

    private void getReviewList() {
        String [] price = {"Full Name","Full Name"};


        ReviewAdapter reviewAdapter = new ReviewAdapter(getApplicationContext(),price);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL ,false);
        binding.ReviewRecyclerView.setLayoutManager(mLayoutManager1);
        binding.ReviewRecyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.ReviewRecyclerView.setAdapter(reviewAdapter);

    }

    private void getAllReview_Product() {
        String price[]={"7999","8999","10000","7999","8999","10000"};
        Review_product_Adapter review_product_adapter = new Review_product_Adapter(this, price);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.reviewProducRcycler.setLayoutManager(mLayoutManager1);
        binding.reviewProducRcycler.setItemAnimator(new DefaultItemAnimator());
        binding.reviewProducRcycler.setAdapter(review_product_adapter);

    }
}
