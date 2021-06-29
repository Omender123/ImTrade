package com.trade.imtrade.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.irozon.sneaker.Sneaker;
import com.trade.imtrade.Adapter.BrandsAdapter;
import com.trade.imtrade.Adapter.Categories_Adapter;
import com.trade.imtrade.Adapter.DealOfTheDayAdapter;
import com.trade.imtrade.Adapter.DiscountByBrandsAdapter;
import com.trade.imtrade.Adapter.GameAdapter;
import com.trade.imtrade.Adapter.MostPopularProductAdapter;
import com.trade.imtrade.Adapter.ProductToSeasonAdapter;
import com.trade.imtrade.Adapter.Recommended_Adapter;
import com.trade.imtrade.Authentication.Change_Password;
import com.trade.imtrade.Authentication.Success_screen;
import com.trade.imtrade.Model.ResponseModel.BannerResponse;
import com.trade.imtrade.R;
import com.trade.imtrade.SharedPerfence.MyPreferences;
import com.trade.imtrade.SharedPerfence.PrefConf;
import com.trade.imtrade.databinding.FragmentHomeBinding;
import com.trade.imtrade.utils.AppUtils;
import com.trade.imtrade.view_presenter.Home_Presenter;

import java.util.ArrayList;
import java.util.List;

public class Home_Fragment extends Fragment implements Home_Presenter.HomeView, View.OnClickListener {
    private FragmentHomeBinding binding;
    private Home_Presenter presenter;
    private View view;
    private Dialog dialog;

    String[] price = {"Mobiles", "Mobiles", "Mobiles", "Mobiles", "Mobiles", "Mobiles", "Mobiles", "Mobiles", "Mobiles", "Mobiles", "Mobiles", "Mobiles"};
    String[] price1 = {"Nike", "Puma", "Nike", "Puma", "Nike", "Puma", "Nike", "Puma", "Nike", "Puma", "Nike", "Puma"};
    String[] bannerImage = {"https://image.shutterstock.com/image-vector/flash-sale-promotion-media-banner-260nw-1557251186.jpg",
            "https://img.freepik.com/free-vector/sale-banner-with-product-description_1361-1333.jpg?size=626&ext=jpg",
            "https://i.pinimg.com/736x/ce/19/1f/ce191f137fdb797b99a1c2930b61c57c.jpg"};
    Integer[] Image = {R.mipmap.game1, R.mipmap.game2, R.mipmap.game3};
    NavController navController;

    public Home_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_home_, container, false);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_, container, false);

        presenter = new Home_Presenter(this);
        view = binding.getRoot();
        dialog = AppUtils.hideShowProgress(getContext());

        presenter.GetBanner(getContext());
        binding.cateAll.setOnClickListener(this);
        // SetImageSlider();
        getAllprouctCategories();
        getAllprouctRecommended();
        getDealOfTheDay();
        getDiscountByBrands();
        getMostPopularProduct();
        getProductToSeason();
        getAllBrands();
        getAllGame();


        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);


    }


    private void getAllprouctCategories() {
        Categories_Adapter categories_adapter = new Categories_Adapter(getContext(), price);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.categoriesRecycler.setLayoutManager(mLayoutManager1);
        binding.categoriesRecycler.setItemAnimator(new DefaultItemAnimator());
        binding.categoriesRecycler.setAdapter(categories_adapter);
    }

    private void getAllprouctRecommended() {
        Recommended_Adapter recommended_adapter = new Recommended_Adapter(getContext(), price);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.recommendedRcycler.setLayoutManager(mLayoutManager1);
        binding.recommendedRcycler.setItemAnimator(new DefaultItemAnimator());
        binding.recommendedRcycler.setAdapter(recommended_adapter);

    }

    private void getDiscountByBrands() {
        DiscountByBrandsAdapter discountByBrandsAdapter = new DiscountByBrandsAdapter(getContext(), price);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.DiscountByBrandsRecyclerView.setLayoutManager(mLayoutManager1);
        binding.DiscountByBrandsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.DiscountByBrandsRecyclerView.setAdapter(discountByBrandsAdapter);

    }

    private void getDealOfTheDay() {
        DealOfTheDayAdapter dealOfTheDayAdapter = new DealOfTheDayAdapter(getContext(), price);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.DealRcycler.setLayoutManager(mLayoutManager1);
        binding.DealRcycler.setItemAnimator(new DefaultItemAnimator());
        binding.DealRcycler.setAdapter(dealOfTheDayAdapter);

    }

    private void getMostPopularProduct() {
        MostPopularProductAdapter mostPopularProductAdapter = new MostPopularProductAdapter(getContext(), price);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.PopularProductRcycler.setLayoutManager(mLayoutManager1);
        binding.PopularProductRcycler.setItemAnimator(new DefaultItemAnimator());
        binding.PopularProductRcycler.setAdapter(mostPopularProductAdapter);

    }

    private void getProductToSeason() {
        ProductToSeasonAdapter productToSeasonAdapter = new ProductToSeasonAdapter(getContext(), price);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.SeasonRcycler.setLayoutManager(mLayoutManager1);
        binding.SeasonRcycler.setItemAnimator(new DefaultItemAnimator());
        binding.SeasonRcycler.setAdapter(productToSeasonAdapter);

    }


    private void getAllBrands() {
        BrandsAdapter brandsAdapter = new BrandsAdapter(getContext(), price1);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.RecyclerViewBrands.setLayoutManager(mLayoutManager1);
        binding.RecyclerViewBrands.setItemAnimator(new DefaultItemAnimator());
        binding.RecyclerViewBrands.setAdapter(brandsAdapter);

    }

    private void getAllGame() {
        GameAdapter gameAdapter = new GameAdapter(getContext(), Image);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.gameRcycler.setLayoutManager(mLayoutManager1);
        binding.gameRcycler.setItemAnimator(new DefaultItemAnimator());
        binding.gameRcycler.setAdapter(gameAdapter);

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
    public void onBannerSuccess(List<BannerResponse> bannerResponses, String message) {
        if (message.equalsIgnoreCase("ok")) {
            List<SlideModel> BannerImage = new ArrayList<>();
            List<String> ProductId = new ArrayList<>();
            if (bannerResponses != null) {
                for (int i = 0; i < bannerResponses.size(); i++) {
                    BannerResponse banner = bannerResponses.get(i);
                    BannerImage.add(new SlideModel(PrefConf.IMAGE_URL + banner.getImage()/*bannerImage[i]*/, ScaleTypes.CENTER_CROP));

                    ProductId.add(banner.getProductId());
                }

                binding.slider.setImageList(BannerImage);

                binding.slider.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onItemSelected(int i) {
                        Toast.makeText(getContext(), "" + ProductId.get(i), Toast.LENGTH_SHORT).show();
                    }
                });

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
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.cate_all:
                navController.navigate(R.id.action_home_Fragment_to_categories);
                break;
        }

    }
}