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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.snackbar.Snackbar;
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
import com.trade.imtrade.Model.ResponseModel.AllCategoriesResponse;
import com.trade.imtrade.Model.ResponseModel.BannerResponse;
import com.trade.imtrade.Model.ResponseModel.BrandsResponse;
import com.trade.imtrade.Model.ResponseModel.DealOfTheDayResponse;
import com.trade.imtrade.Model.ResponseModel.PopularProductsResponse;
import com.trade.imtrade.R;
import com.trade.imtrade.SharedPerfence.MyPreferences;
import com.trade.imtrade.SharedPerfence.PrefConf;
import com.trade.imtrade.SharedPrefernce.SharedPrefManager;
import com.trade.imtrade.SharedPrefernce.User_Data;
import com.trade.imtrade.databinding.FragmentHomeBinding;
import com.trade.imtrade.utils.AppUtils;
import com.trade.imtrade.view_presenter.Home_Presenter;

import java.util.ArrayList;
import java.util.List;

public class Home_Fragment extends Fragment implements Home_Presenter.HomeView, View.OnClickListener, Categories_Adapter.OnCategoriesItemListener, MostPopularProductAdapter.MostPopularProductClickListener, DealOfTheDayAdapter.DealOfTheDayClickListener
        , DiscountByBrandsAdapter.DiscountForYouClickListener {
    private FragmentHomeBinding binding;
    private Home_Presenter presenter;
    private View view;
    private Dialog dialog;

    String[] price = {"Mobiles", "Laptops", "Television", "Shose", "Fashion", "Women's", "Men's", "Furniture", "Headphone", "Appliances", "Grocery", "Sports", "Baby Toys"};
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
        presenter.GetAllCategories(getContext());
        presenter.GetAllBrands(getContext());
        presenter.GetAllPopularProduct(getContext());
        presenter.GetDealOfTheDay(getContext());
        presenter.GetDiscountForYou(getContext());
        binding.cateAll.setOnClickListener(this);
        getAllprouctRecommended();
        getProductToSeason();
        getAllGame();


        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);


    }


    private void getAllprouctRecommended() {
        Recommended_Adapter recommended_adapter = new Recommended_Adapter(getContext(), price);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.recommendedRcycler.setLayoutManager(mLayoutManager1);
        binding.recommendedRcycler.setItemAnimator(new DefaultItemAnimator());
        binding.recommendedRcycler.setAdapter(recommended_adapter);

    }


    private void getProductToSeason() {
        ProductToSeasonAdapter productToSeasonAdapter = new ProductToSeasonAdapter(getContext(), price);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.SeasonRcycler.setLayoutManager(mLayoutManager1);
        binding.SeasonRcycler.setItemAnimator(new DefaultItemAnimator());
        binding.SeasonRcycler.setAdapter(productToSeasonAdapter);

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
        Sneaker.with(getActivity())
                .setTitle(message)
                .setMessage("")
                .setCornerRadius(4)
                .setDuration(2000)
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
                    BannerImage.add(new SlideModel(PrefConf.IMAGE_URL + banner.getImage()/*bannerImage[i]*/, ScaleTypes.FIT));

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
    public void onAllCategoriesSuccess(List<AllCategoriesResponse> allCategoriesResponses, String message) {
        if (message.equalsIgnoreCase("ok")) {
            Categories_Adapter categories_adapter = new Categories_Adapter(getContext(), allCategoriesResponses, this::onCategoriesItemClickListener);
            RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            binding.categoriesRecycler.setLayoutManager(mLayoutManager1);
            binding.categoriesRecycler.setItemAnimator(new DefaultItemAnimator());
            binding.categoriesRecycler.setAdapter(categories_adapter);
        }


    }

    @Override
    public void onAllBrandsSuccess(List<BrandsResponse> brandsResponses, String message) {
        if (message.equalsIgnoreCase("ok")) {
            BrandsAdapter brandsAdapter = new BrandsAdapter(getContext(), brandsResponses);
            RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            binding.RecyclerViewBrands.setLayoutManager(mLayoutManager1);
            binding.RecyclerViewBrands.setItemAnimator(new DefaultItemAnimator());
            binding.RecyclerViewBrands.setAdapter(brandsAdapter);

        }
    }

    @Override
    public void onAllPopularProductSuccess(List<PopularProductsResponse> popularProductsResponses, String message) {

        if (message.equalsIgnoreCase("ok")) {
            MostPopularProductAdapter mostPopularProductAdapter = new MostPopularProductAdapter(getContext(), popularProductsResponses, this::onMostPopularProductItemClickListener);
            RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            binding.PopularProductRcycler.setLayoutManager(mLayoutManager1);
            binding.PopularProductRcycler.setItemAnimator(new DefaultItemAnimator());
            binding.PopularProductRcycler.setAdapter(mostPopularProductAdapter);

        }
    }

    @Override
    public void onDealOfTheDaySuccess(List<DealOfTheDayResponse> dealOfTheDayResponses, String message) {
        if (message.equalsIgnoreCase("ok")) {
            DealOfTheDayAdapter dealOfTheDayAdapter = new DealOfTheDayAdapter(getContext(), dealOfTheDayResponses, this::onDealOfTheDayItemClickListener);
            RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            binding.DealRcycler.setLayoutManager(mLayoutManager1);
            binding.DealRcycler.setItemAnimator(new DefaultItemAnimator());
            binding.DealRcycler.setAdapter(dealOfTheDayAdapter);
        }
    }

    @Override
    public void onDiscountForYouSuccess(List<DealOfTheDayResponse> dealOfTheDayResponses, String message) {

        if (message.equalsIgnoreCase("ok")) {
            DiscountByBrandsAdapter discountByBrandsAdapter = new DiscountByBrandsAdapter(getContext(), dealOfTheDayResponses, this::onDiscountForYouItemClickListener);
            RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            binding.DiscountByBrandsRecyclerView.setLayoutManager(mLayoutManager1);
            binding.DiscountByBrandsRecyclerView.setItemAnimator(new DefaultItemAnimator());
            binding.DiscountByBrandsRecyclerView.setAdapter(discountByBrandsAdapter);
        }
    }

    @Override
    public void onFailure(Throwable t) {
        Sneaker.with(getActivity())
                .setTitle(t.getLocalizedMessage())
                .setMessage("")
                .setCornerRadius(4)
                .setDuration(2000)
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

    @Override
    public void onCategoriesItemClickListener(List<AllCategoriesResponse> data, int position) {
        String categories = data.get(position).getName();
        String categoriesId = data.get(position).getId();
        Bundle bundle = new Bundle();
        bundle.putString("categories", categories);
        bundle.putString("categoriesId", categoriesId);

        Home_FragmentDirections.ActionHomeFragmentToProductFragemet homeFragmentToProductFragemet = Home_FragmentDirections.actionHomeFragmentToProductFragemet();
        homeFragmentToProductFragemet.setCategoriesTitle(categories);
        homeFragmentToProductFragemet.setCategoriesId(categoriesId);
        Navigation.findNavController(view).navigate(homeFragmentToProductFragemet);


    }

    @Override
    public void onMostPopularProductItemClickListener(List<PopularProductsResponse> data, int position) {
        Toast.makeText(getContext(), "" + data.get(position).getRoute(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDealOfTheDayItemClickListener(List<DealOfTheDayResponse> data, int position) {
        Toast.makeText(getContext(), "" + data.get(position).getRoute(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDiscountForYouItemClickListener(List<DealOfTheDayResponse> data, int position) {
        Toast.makeText(getContext(), "" + data.get(position).getRoute(), Toast.LENGTH_SHORT).show();

    }
}