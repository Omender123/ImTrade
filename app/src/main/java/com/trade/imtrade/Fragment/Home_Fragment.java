package com.trade.imtrade.Fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.irozon.sneaker.Sneaker;
import com.trade.imtrade.Adapter.HotelAdapter;
import com.trade.imtrade.Adapter.RoundAdapter;
import com.trade.imtrade.Adapter.Categories_Adapter;
import com.trade.imtrade.Adapter.Continue_HuntAdapter;
import com.trade.imtrade.Adapter.DealofDayAdapter;
import com.trade.imtrade.Adapter.Discount_categoriesAdapter;
import com.trade.imtrade.Adapter.GameAdapter;
import com.trade.imtrade.Adapter.HomeProduct_Adapter;
import com.trade.imtrade.Adapter.RoundAdapter1;
import com.trade.imtrade.Adapter.StoriesAdapter;
import com.trade.imtrade.Model.ResponseModel.AllCategoriesResponse;
import com.trade.imtrade.Model.ResponseModel.BannerResponse;
import com.trade.imtrade.Model.ResponseModel.BrandsResponse;
import com.trade.imtrade.Model.ResponseModel.HomeProductResponse;
import com.trade.imtrade.R;
import com.trade.imtrade.SharedPerfence.MyPreferences;
import com.trade.imtrade.SharedPerfence.PrefConf;

import com.trade.imtrade.databinding.FragmentHomeBinding;
import com.trade.imtrade.utils.AppUtils;
import com.trade.imtrade.view_presenter.Home_Presenter;

import java.util.ArrayList;
import java.util.List;

public class Home_Fragment extends Fragment implements Home_Presenter.HomeView, View.OnClickListener, Categories_Adapter.OnCategoriesItemListener,
        HomeProduct_Adapter.HomeProductClickListener, Continue_HuntAdapter.HomeProductClickListener, Discount_categoriesAdapter.OnCategoriesItemListener, RoundAdapter.OnCategoriesItemListener {
    private FragmentHomeBinding binding;
    private Home_Presenter presenter;
    private View view;
    private Dialog dialog;

    Integer[] Image = {R.mipmap.game1, R.mipmap.game2, R.mipmap.game3};
    Integer[] cate_Image = {R.mipmap.discounted_categories_1, R.mipmap.discounted_categories_2, R.mipmap.discounted_categories_3, R.mipmap.discounted_categories_2, R.mipmap.discounted_categories_1};
    String[] cate_name = {"Fresh Fruits & Vegetables", "Appliances", "Packet Food", "Appliances", "Fresh Fruits & Vegetables"};

    NavController navController;

    public Home_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_, container, false);

        presenter = new Home_Presenter(this);
        view = binding.getRoot();
        dialog = AppUtils.hideShowProgress(getContext());

        presenter.GetBanner(getContext());
        presenter.GetAllCategories(getContext());
        //   presenter.GetAllBrands(getContext());
        presenter.GetAllPopularProduct(getContext());
        //presenter.GetDealOfTheDay(getContext());
        presenter.GetContinueHuntYou(getContext());
        //presenter.GetSeasonProduct(getContext());

        presenter.GetFreshArrival(getContext());
        presenter.GetDiscountCategories(getContext());
        presenter.GetRecommendedProduct(getContext());
        presenter.GetDailyUsableCategories(getContext());


        binding.cateAll.setOnClickListener(this);
        getAllGame();
        getDealOfDay();
        getallWinnerOfThisWeek();
        getallStories();
        getallCoffeeProduct();
        getallHotel();

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);


    }


    private void getallWinnerOfThisWeek() {
        Integer[] cate_Image = {R.mipmap.winner_1, R.mipmap.winner_2, R.mipmap.winner_3, R.mipmap.winner_4, R.mipmap.winner_5, R.mipmap.winner_1, R.mipmap.winner_2, R.mipmap.winner_3};
        String[] cate_name = {"Aayansh", "Avyukt", "Kiyansh", "Yuvaan", "Aayansh", "Avyukt", "Kiyansh", "Yuvaan"};

        RoundAdapter1 gameAdapter = new RoundAdapter1(getContext(), cate_name, cate_Image);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerViewWinner.setLayoutManager(mLayoutManager1);
        binding.recyclerViewWinner.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerViewWinner.setAdapter(gameAdapter);

    }

    private void getallStories() {
        Integer[] cate_Image = {R.mipmap.stories_1, R.mipmap.stories_2, R.mipmap.stories_3, R.mipmap.stories_4, R.mipmap.stories_1, R.mipmap.stories_2, R.mipmap.stories_3, R.mipmap.stories_4};

        StoriesAdapter gameAdapter = new StoriesAdapter(getContext(), cate_Image);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerViewStories.setLayoutManager(mLayoutManager1);
        binding.recyclerViewStories.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerViewStories.setAdapter(gameAdapter);

    }

    private void getallHotel() {
        Integer[] cate_Image = {R.mipmap.hotel1, R.mipmap.hotel2, R.mipmap.hotel3, R.mipmap.hotel1, R.mipmap.hotel2, R.mipmap.hotel3, R.mipmap.hotel1, R.mipmap.hotel2};

        HotelAdapter gameAdapter = new HotelAdapter(getContext(), cate_Image);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.HotelRcycler.setLayoutManager(mLayoutManager1);
        binding.HotelRcycler.setItemAnimator(new DefaultItemAnimator());
        binding.HotelRcycler.setAdapter(gameAdapter);

    }

    private void getallCoffeeProduct() {
        Integer[] cate_Image = {R.mipmap.coffee_1, R.mipmap.coffee_2, R.mipmap.coffee_3, R.mipmap.coffee_1, R.mipmap.coffee_2, R.mipmap.coffee_3, R.mipmap.coffee_1, R.mipmap.coffee_3};
        String[] cate_name = {"Coffee 1", "Coffee 2", "Coffee 3", "Coffee 4", "Coffee 5", "Coffee 6", "Coffee 7", "Coffee 7"};

        RoundAdapter1 gameAdapter = new RoundAdapter1(getContext(), cate_name, cate_Image);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerViewCoffee.setLayoutManager(mLayoutManager1);
        binding.recyclerViewCoffee.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerViewCoffee.setAdapter(gameAdapter);

    }

    private void getAllGame() {
        GameAdapter gameAdapter = new GameAdapter(getContext(), Image);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.gameRcycler.setLayoutManager(mLayoutManager1);
        binding.gameRcycler.setItemAnimator(new DefaultItemAnimator());
        binding.gameRcycler.setAdapter(gameAdapter);

    }


    private void getDealOfDay() {
        Integer[] cate_image = {R.mipmap.deal1, R.mipmap.deal3, R.mipmap.deal3};
        DealofDayAdapter dealofDayAdapter = new DealofDayAdapter(getContext(), cate_image);
        RecyclerView.LayoutManager mLayoutManager1 = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        binding.recyclerViewDealofDay.setLayoutManager(mLayoutManager1);
        binding.recyclerViewDealofDay.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerViewDealofDay.setAdapter(dealofDayAdapter);

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
            List<SlideModel> TopImage = new ArrayList<>();
            List<SlideModel> MiddleImage = new ArrayList<>();
            List<SlideModel> BottomImage = new ArrayList<>();
            List<String> ProductId = new ArrayList<>();
            if (bannerResponses != null) {
                for (int i = 0; i < bannerResponses.size(); i++) {
                    BannerResponse banner = bannerResponses.get(i);
                    if (banner.getPosition().equalsIgnoreCase("top")) {
                        TopImage.add(new SlideModel(PrefConf.IMAGE_URL + banner.getImage(), ScaleTypes.FIT));
                        ProductId.add(banner.getProductId());
                    } else if (banner.getPosition().equalsIgnoreCase("middle")) {
                        MiddleImage.add(new SlideModel(PrefConf.IMAGE_URL + banner.getImage(), ScaleTypes.FIT));
                        ProductId.add(banner.getProductId());
                    } else if (banner.getPosition().equalsIgnoreCase("bottom")) {
                        BottomImage.add(new SlideModel(PrefConf.IMAGE_URL + banner.getImage(), ScaleTypes.FIT));
                        ProductId.add(banner.getProductId());
                    }
                }

                binding.slider.setImageList(TopImage);
                binding.middleSlider.setImageList(MiddleImage);
                binding.bottomSlider.setImageList(BottomImage);
                binding.forthSlider.setImageList(TopImage);
                binding.fivthSlider.setImageList(MiddleImage);
                binding.sixthSlider.setImageList(BottomImage);
                binding.seventhSlider.setImageList(TopImage);


                binding.slider.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onItemSelected(int i) {
                        //  Toast.makeText(getContext(), "" + ProductId.get(i), Toast.LENGTH_SHORT).show();
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
    public void onAllDiscountCategoriesSuccess(List<AllCategoriesResponse> allCategoriesResponses, String message) {
        if (message.equalsIgnoreCase("ok")) {
            Discount_categoriesAdapter discount_categoriesAdapter = new Discount_categoriesAdapter(getContext(), allCategoriesResponses, this::onCategoriesItemClickListener);
            RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            binding.recyclerViewDiscountCategories.setLayoutManager(mLayoutManager1);
            binding.recyclerViewDiscountCategories.setItemAnimator(new DefaultItemAnimator());
            binding.recyclerViewDiscountCategories.setAdapter(discount_categoriesAdapter);
        }

    }

    @Override
    public void onAllDailyUsableCategoriesSuccess(List<AllCategoriesResponse> allCategoriesResponses, String message) {
        if (message.equalsIgnoreCase("ok")) {
            RoundAdapter gameAdapter = new RoundAdapter(getContext(), allCategoriesResponses, this::onCategoriesItemClickListener);
            RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            binding.recyclerViewDaily.setLayoutManager(mLayoutManager1);
            binding.recyclerViewDaily.setItemAnimator(new DefaultItemAnimator());
            binding.recyclerViewDaily.setAdapter(gameAdapter);
        }
    }

    @Override
    public void onAllBrandsSuccess(List<BrandsResponse> brandsResponses, String message) {
        if (message.equalsIgnoreCase("ok")) {
            // RoundAdapter roundAdapter = new RoundAdapter(getContext(), brandsResponses);
            //RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            //  binding.RecyclerViewBrands.setLayoutManager(mLayoutManager1);
            // binding.RecyclerViewBrands.setItemAnimator(new DefaultItemAnimator());
            // binding.RecyclerViewBrands.setAdapter(brandsAdapter);

        }
    }

    @Override
    public void onAllPopularProductSuccess(List<HomeProductResponse> PopularProductResponse, String message) {

        if (message.equalsIgnoreCase("ok")) {
            HomeProduct_Adapter mostPopularProductAdapter = new HomeProduct_Adapter(getContext(), PopularProductResponse, this::onHomeProductItemClickListener);
            RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            binding.recyclerViewPopularThisWeek.setLayoutManager(mLayoutManager1);
            binding.recyclerViewPopularThisWeek.setItemAnimator(new DefaultItemAnimator());
            binding.recyclerViewPopularThisWeek.setAdapter(mostPopularProductAdapter);

        }
    }

    @Override
    public void onDealOfTheDaySuccess(List<HomeProductResponse> DealOfTheDayResponses, String message) {
        if (message.equalsIgnoreCase("ok")) {
            HomeProduct_Adapter homeProduct_adapter = new HomeProduct_Adapter(getContext(), DealOfTheDayResponses, this::onHomeProductItemClickListener);
            RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            // binding.DealRcycler.setLayoutManager(mLayoutManager1);
            //binding.DealRcycler.setItemAnimator(new DefaultItemAnimator());
            // binding.DealRcycler.setAdapter(homeProduct_adapter);
        }
    }

    @Override
    public void onContinueHuntYouSuccess(List<HomeProductResponse> DiscountForYouResponses, String message) {

        if (message.equalsIgnoreCase("ok")) {
            Continue_HuntAdapter continue_huntAdapter = new Continue_HuntAdapter(getContext(), DiscountForYouResponses, this::onHomeProductItemClickListener);
            RecyclerView.LayoutManager mLayoutManager1 = new GridLayoutManager(getContext(), 2, LinearLayoutManager.VERTICAL, false);
            binding.recyclerViewContiuneYour.setLayoutManager(mLayoutManager1);
            binding.recyclerViewContiuneYour.setItemAnimator(new DefaultItemAnimator());
            binding.recyclerViewContiuneYour.setAdapter(continue_huntAdapter);
        }
    }

    @Override
    public void onFreshArrivalSuccess(List<HomeProductResponse> FreshArrivalResponse, String message) {
        if (message.equalsIgnoreCase("ok")) {

            HomeProduct_Adapter FreshArrival_adapter = new HomeProduct_Adapter(getContext(), FreshArrivalResponse, this::onHomeProductItemClickListener);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            binding.FreshRcycler.setLayoutManager(mLayoutManager);
            binding.FreshRcycler.setItemAnimator(new DefaultItemAnimator());
            binding.FreshRcycler.setAdapter(FreshArrival_adapter);
        }
    }

    @Override
    public void onSeasonProductSuccess(List<HomeProductResponse> SeasonProductResponse, String message) {

        if (message.equalsIgnoreCase("ok")) {
            HomeProduct_Adapter productToSeasonAdapter = new HomeProduct_Adapter(getContext(), SeasonProductResponse, this::onHomeProductItemClickListener);
            RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            // binding.SeasonRcycler.setLayoutManager(mLayoutManager1);
            // binding.SeasonRcycler.setItemAnimator(new DefaultItemAnimator());
            // binding.SeasonRcycler.setAdapter(productToSeasonAdapter);

        }
    }

    @Override
    public void onReCommendedProductSuccess(List<HomeProductResponse> ReCommendedProductResponse, String message) {
        if (message.equalsIgnoreCase("ok")) {
            HomeProduct_Adapter recommended_adapter = new HomeProduct_Adapter(getContext(), ReCommendedProductResponse, this::onHomeProductItemClickListener);
            RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            binding.recommendedRcycler.setLayoutManager(mLayoutManager1);
            binding.recommendedRcycler.setItemAnimator(new DefaultItemAnimator());
            binding.recommendedRcycler.setAdapter(recommended_adapter);

            /*Festival Offer on Products*/
            RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            binding.FestivalRcycler.setLayoutManager(mLayoutManager2);
            binding.FestivalRcycler.setItemAnimator(new DefaultItemAnimator());
            binding.FestivalRcycler.setAdapter(recommended_adapter);


            /*Sponsored Products*/
            RecyclerView.LayoutManager mLayoutManager4 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            binding.SponsoredRcycler.setLayoutManager(mLayoutManager4);
            binding.SponsoredRcycler.setItemAnimator(new DefaultItemAnimator());
            binding.SponsoredRcycler.setAdapter(recommended_adapter);

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
    public void onHomeProductItemClickListener(List<HomeProductResponse> data, int position) {
        String roleId = data.get(position).getRoute();
        // Toast.makeText(getContext(), ""+roleId, Toast.LENGTH_SHORT).show();
        MyPreferences.getInstance(getContext()).putString(PrefConf.ROUTEID, roleId);
        navController.navigate(R.id.action_home_Fragment_to_product_Details);
    }
}