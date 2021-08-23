package com.trade.imtrade.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;
import com.irozon.sneaker.Sneaker;
import com.trade.imtrade.Activity.CartActivity;
import com.trade.imtrade.Activity.Product_Details;
import com.trade.imtrade.Adapter.ColorAdapter;
import com.trade.imtrade.Adapter.ColorAdapter.OnColorItemListener;
import com.trade.imtrade.Adapter.DetailsAdapter;
import com.trade.imtrade.Adapter.Other_Info_Adapter;
import com.trade.imtrade.Adapter.Profile_itemAdapter;
import com.trade.imtrade.Adapter.StorageAdapter;
import com.trade.imtrade.Model.ResponseModel.ProductDetailsResponse;
import com.trade.imtrade.Model.request.AddToCartBody;
import com.trade.imtrade.R;
import com.trade.imtrade.SharedPerfence.MyPreferences;
import com.trade.imtrade.SharedPerfence.PrefConf;
import com.trade.imtrade.databinding.FragmentProductDetailsBinding;
import com.trade.imtrade.utils.AppUtils;
import com.trade.imtrade.view_presenter.Home_Presenter;
import com.trade.imtrade.view_presenter.ProductDetails_Presenter;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;


public class Product_Details_Fragment extends Fragment implements ProductDetails_Presenter.GetProductDetailsView, View.OnClickListener, OnColorItemListener, StorageAdapter.OnToggleItemListener,
        Other_Info_Adapter.OnOtherInfoItemListener {
    TextView text_cart_Count;
    FragmentProductDetailsBinding binding;
    private View view;
    private Dialog dialog;
    NavController navController;
    ProductDetails_Presenter presenter;
    String RouteId;
    ProductDetailsResponse productDetailsResponses;


    public Product_Details_Fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        text_cart_Count = (TextView) getActivity().findViewById(R.id.text_cart_Count);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_product__details, container, false);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product__details, container, false);
        RouteId = MyPreferences.getInstance(getActivity()).getString(PrefConf.ROUTEID, "");

        view = binding.getRoot();
        presenter = new ProductDetails_Presenter(this);

        dialog = AppUtils.hideShowProgress(getContext());

        presenter.GetProductDetails(getActivity(), RouteId);

        binding.showMore.setOnClickListener(this);
        binding.HideMore.setOnClickListener(this);
        binding.showMore1.setOnClickListener(this);
        binding.HideMore1.setOnClickListener(this);
        binding.showDetails.setOnClickListener(this);
        binding.HideDetails.setOnClickListener(this);

        getOtherInfo();

        return binding.getRoot();
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
        Sneaker.with(getActivity())
                .setTitle(message)
                .setMessage("")
                .setCornerRadius(4)
                .setDuration(1500)
                .sneakError();
    }

    @Override
    public void onProductDetailsSuccess(ProductDetailsResponse productDetailsResponse, String message) {
        productDetailsResponses = productDetailsResponse;
        if (message.equalsIgnoreCase("ok")) {
            /*--------------ProductBanner--------------*/
            List<SlideModel> BannerImage = new ArrayList<>();
            if (productDetailsResponse.getImages().size() > 0 && productDetailsResponse.getImages() != null) {
                for (int i = 0; i < productDetailsResponse.getImages().size(); i++) {
                    BannerImage.add(new SlideModel(PrefConf.IMAGE_URL + productDetailsResponse.getImages().get(i), ScaleTypes.CENTER_CROP));
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

            SimpleRatingBar.AnimationBuilder builder = binding.textRating.getAnimationBuilder()
                    .setRatingTarget(Float.parseFloat(productDetailsResponse.getAverageRating()))
                    .setDuration(2000)
                    .setRepeatMode(1)
                    .setInterpolator(new BounceInterpolator());
            builder.start();

            binding.productName.setText(productDetailsResponse.getName());

            if (productDetailsResponse.getDiscount() != null) {
                binding.productPrice.setText(productDetailsResponse.getDiscount() + " Rs");
                binding.productOffPrice.setText(productDetailsResponse.getVariables().get(0).getPrice().getMargin() + " %off");
                binding.productWorngPrice.setText(productDetailsResponse.getVariables().get(0).getPrice().getMrp() + " Rs");
            } else {
                binding.productPrice.setText(productDetailsResponse.getVariables().get(0).getPrice().getMrp() + " Rs");
                binding.productOffPrice.setVisibility(View.GONE);
                binding.productWorngPrice.setVisibility(View.GONE);

            }

            getColorList(productDetailsResponse);
            getStorageList(productDetailsResponse);
            getDelatilsList(productDetailsResponse, false);
            // getReviewList(productDetailsResponse);


        }

    }

    @Override
    public void onAddToCartSuccess(ResponseBody responseBody, String message) {

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
    public void onClick(View v) {
        switch (v.getId()) {
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
                getDelatilsList(productDetailsResponses, true);
                break;
            case R.id.HideDetails:
                binding.showDetails.setVisibility(View.VISIBLE);
                binding.HideDetails.setVisibility(View.GONE);
                getDelatilsList(productDetailsResponses, false);
                break;
        }
    }

    private void getColorList(ProductDetailsResponse productDetailsResponse) {
        if (productDetailsResponse.getColor().size() > 0 && productDetailsResponse.getColor() != null) {

            ColorAdapter colorAdapter = new ColorAdapter(getActivity(), productDetailsResponse, this::onOnColorClickListener);
            RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            binding.ColorRecyclerView.setLayoutManager(mLayoutManager1);
            binding.ColorRecyclerView.setItemAnimator(new DefaultItemAnimator());
            binding.ColorRecyclerView.setAdapter(colorAdapter);
            binding.textColor.setText(productDetailsResponse.getColor().get(0).getName().toUpperCase());
            binding.showMore.setText(productDetailsResponse.getColor().size() + " more");
        } else {
            Sneaker.with(this)
                    .setTitle("No Product Color are Available")
                    .setMessage("")
                    .setCornerRadius(4)
                    .setDuration(1500)
                    .sneakError();
        }


    }

    @Override
    public void onOnColorClickListener(ProductDetailsResponse productDetailsResponse, int position) {
        binding.textColor.setText(productDetailsResponse.getColor().get(position).getName().toUpperCase());
        Toast.makeText(getActivity(), "" + productDetailsResponse.getColor().get(position).getName(), Toast.LENGTH_SHORT).show();
    }

    private void getStorageList(ProductDetailsResponse productDetailsResponse) {
        if (productDetailsResponse.getStorage().size() > 0 && productDetailsResponse.getStorage() != null) {


            StorageAdapter storageAdapter = new StorageAdapter(getActivity(), productDetailsResponse, this::onToggleItemClickListener);
            RecyclerView.LayoutManager mLayoutManager1 = new GridLayoutManager(getActivity(), 4, LinearLayoutManager.VERTICAL, false);
            binding.storageRecyclerView.setLayoutManager(mLayoutManager1);
            binding.storageRecyclerView.setItemAnimator(new DefaultItemAnimator());
            binding.storageRecyclerView.setAdapter(storageAdapter);
            binding.textStorage.setText(productDetailsResponse.getStorage().get(0).getSize());
            binding.showMore1.setText(productDetailsResponse.getColor().size() + " more");
        } else {
            Sneaker.with(this)
                    .setTitle("No Product Size and Storage are Available")
                    .setMessage("")
                    .setCornerRadius(4)
                    .setDuration(1500)
                    .sneakError();
        }


    }

    @Override
    public void onToggleItemClickListener(ProductDetailsResponse productDetailsResponse, int position) {

        binding.textStorage.setText(productDetailsResponse.getStorage().get(position).getSize());

        Toast.makeText(getActivity(), "" + productDetailsResponse.getStorage().get(position).getSize(), Toast.LENGTH_SHORT).show();

    }

    private void getDelatilsList(ProductDetailsResponse productDetailsResponse, Boolean count) {
        if (count == true) {
            DetailsAdapter detailsAdapter = new DetailsAdapter(getActivity(), productDetailsResponse, true);
            RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            binding.detailsRecyclerView.setLayoutManager(mLayoutManager1);
            binding.detailsRecyclerView.setItemAnimator(new DefaultItemAnimator());
            binding.detailsRecyclerView.setAdapter(detailsAdapter);
        } else {
            DetailsAdapter detailsAdapter = new DetailsAdapter(getActivity(), productDetailsResponse, false);
            RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            binding.detailsRecyclerView.setLayoutManager(mLayoutManager1);
            binding.detailsRecyclerView.setItemAnimator(new DefaultItemAnimator());
            binding.detailsRecyclerView.setAdapter(detailsAdapter);
        }

    }

    private void getOtherInfo() {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("Free Home Delivery Availables");
        arrayList.add("7 Days Replacement Policy");
        arrayList.add("1 Year Brand Warranty");

        Other_Info_Adapter profile_itemAdapter = new Other_Info_Adapter(arrayList, getContext(), this::onOtherInfoItemClickListener);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        binding.OtherRecyclerView.setLayoutManager(mLayoutManager);
        binding.OtherRecyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.OtherRecyclerView.setAdapter(profile_itemAdapter);


    }

    @Override
    public void onOtherInfoItemClickListener(ArrayList<String> ItemList, int position) {

    }
}