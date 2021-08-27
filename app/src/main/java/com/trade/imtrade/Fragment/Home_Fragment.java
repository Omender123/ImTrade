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
import android.widget.TextView;

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
import com.trade.imtrade.Model.ResponseModel.ContinueYourHuntResponse;
import com.trade.imtrade.Model.ResponseModel.DealofDayResponse;
import com.trade.imtrade.Model.ResponseModel.HomeProductResponse;
import com.trade.imtrade.Model.ResponseModel.HotelAndCoffeeResponse;
import com.trade.imtrade.Model.ResponseModel.StoriesResponse;
import com.trade.imtrade.R;
import com.trade.imtrade.SharedPerfence.MyPreferences;
import com.trade.imtrade.SharedPerfence.PrefConf;

import com.trade.imtrade.databinding.FragmentHomeBinding;
import com.trade.imtrade.utils.AppUtils;
import com.trade.imtrade.view_presenter.Home_Presenter;

import java.util.ArrayList;
import java.util.List;

public class Home_Fragment extends Fragment implements Home_Presenter.HomeView, View.OnClickListener, Categories_Adapter.OnCategoriesItemListener,
        HomeProduct_Adapter.HomeProductClickListener, Continue_HuntAdapter.ContinueYourHuntClickListener, Discount_categoriesAdapter.OnCategoriesItemListener, RoundAdapter.OnCategoriesItemListener, DealofDayAdapter.DealOfDaytClickListener {
    private FragmentHomeBinding binding;
    private Home_Presenter presenter;
    private View view;
    private Dialog dialog;

    Integer[] Image = {R.mipmap.game1, R.mipmap.game2, R.mipmap.game3};

    NavController navController;
    TextView text_cart_Count;

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
        //  dialog = AppUtils.hideShowProgress(getContext());

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
        presenter.GetSponsoredProduct(getContext());
        presenter.GetFestivalOfferProduct(getContext());
        presenter.GetHomeDealOfDayProduct(getContext());
        presenter.GetWinnerOfThisWeek(getContext());
        presenter.GetStories(getContext());
        presenter.GetAllCoffee(getContext(), "100111");
        presenter.GetAllHotel(getContext(), "100111");


        binding.cateAll.setOnClickListener(this);
        getAllGame();
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);


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
      /*  if (isShow) {
            dialog.show();
        } else {
            dialog.dismiss();
        }*/

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
            List<SlideModel> FirstImage = new ArrayList<>();
            List<SlideModel> SecondImage = new ArrayList<>();
            List<SlideModel> ThirdImage = new ArrayList<>();
            List<SlideModel> FourthImage = new ArrayList<>();
            List<SlideModel> FifthImage = new ArrayList<>();
            List<SlideModel> SexthImage = new ArrayList<>();
            List<SlideModel> SeventhImage = new ArrayList<>();

            List<String> ProductId = new ArrayList<>();
            if (bannerResponses != null) {
                for (int i = 0; i < bannerResponses.size(); i++) {
                    BannerResponse banner = bannerResponses.get(i);
                    if (banner.getPosition().equalsIgnoreCase("first")) {
                        FirstImage.add(new SlideModel(PrefConf.IMAGE_URL + banner.getImage(), ScaleTypes.FIT));
                        ProductId.add(banner.getProductId());
                    } else if (banner.getPosition().equalsIgnoreCase("second")) {
                        SecondImage.add(new SlideModel(PrefConf.IMAGE_URL + banner.getImage(), ScaleTypes.FIT));
                        ProductId.add(banner.getProductId());
                    } else if (banner.getPosition().equalsIgnoreCase("third")) {
                        ThirdImage.add(new SlideModel(PrefConf.IMAGE_URL + banner.getImage(), ScaleTypes.FIT));
                        ProductId.add(banner.getProductId());
                    } else if (banner.getPosition().equalsIgnoreCase("fourth")) {
                        FourthImage.add(new SlideModel(PrefConf.IMAGE_URL + banner.getImage(), ScaleTypes.FIT));
                        ProductId.add(banner.getProductId());
                    } else if (banner.getPosition().equalsIgnoreCase("fifth")) {
                        FifthImage.add(new SlideModel(PrefConf.IMAGE_URL + banner.getImage(), ScaleTypes.FIT));
                        ProductId.add(banner.getProductId());
                    } else if (banner.getPosition().equalsIgnoreCase("sixth")) {
                        SexthImage.add(new SlideModel(PrefConf.IMAGE_URL + banner.getImage(), ScaleTypes.FIT));
                        ProductId.add(banner.getProductId());
                    } else if (banner.getPosition().equalsIgnoreCase("seventh")) {
                        SeventhImage.add(new SlideModel(PrefConf.IMAGE_URL + banner.getImage(), ScaleTypes.FIT));
                        ProductId.add(banner.getProductId());
                    }
                }

                binding.slider.setImageList(FirstImage);
                binding.middleSlider.setImageList(SecondImage);
                binding.bottomSlider.setImageList(ThirdImage);
                binding.forthSlider.setImageList(FourthImage);
                binding.fivthSlider.setImageList(FifthImage);
                binding.sixthSlider.setImageList(SexthImage);
                binding.seventhSlider.setImageList(SeventhImage);


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
    public void onAllWinnerOfThisWeekSuccess(List<AllCategoriesResponse> allWinnerOfThisWeekResponses, String message) {
        if (message.equalsIgnoreCase("ok")) {
            RoundAdapter gameAdapter = new RoundAdapter(getContext(), allWinnerOfThisWeekResponses, this::onCategoriesItemClickListener);
            RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            binding.recyclerViewWinner.setLayoutManager(mLayoutManager1);
            binding.recyclerViewWinner.setItemAnimator(new DefaultItemAnimator());
            binding.recyclerViewWinner.setAdapter(gameAdapter);
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
    public void onContinueHuntYouSuccess(ContinueYourHuntResponse continueYourHuntResponse, String message) {
        if (message.equalsIgnoreCase("ok")) {
            if (continueYourHuntResponse.getProducts() != null && continueYourHuntResponse.getProducts().size() > 0) {
                Continue_HuntAdapter continue_huntAdapter = new Continue_HuntAdapter(getContext(), continueYourHuntResponse, this::onContinueYourHuntItemClickListener, false);
                RecyclerView.LayoutManager mLayoutManager1 = new GridLayoutManager(getContext(), 2, LinearLayoutManager.VERTICAL, false);
                binding.recyclerViewContiuneYour.setLayoutManager(mLayoutManager1);
                binding.recyclerViewContiuneYour.setItemAnimator(new DefaultItemAnimator());
                binding.recyclerViewContiuneYour.setAdapter(continue_huntAdapter);
                binding.linearContinueHunt.setVisibility(View.VISIBLE);
            } else {
                binding.linearContinueHunt.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onContinueHuntYouError(String message) {
        binding.linearContinueHunt.setVisibility(View.GONE);
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


        }
    }

    @Override
    public void onSponsoredProductSuccess(List<HomeProductResponse> SponsoredProductResponse, String message) {
        if (message.equalsIgnoreCase("ok")) {
            HomeProduct_Adapter SponsoredProductAdapter = new HomeProduct_Adapter(getContext(), SponsoredProductResponse, this::onHomeProductItemClickListener);
            RecyclerView.LayoutManager mLayoutManager4 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            binding.SponsoredRcycler.setLayoutManager(mLayoutManager4);
            binding.SponsoredRcycler.setItemAnimator(new DefaultItemAnimator());
            binding.SponsoredRcycler.setAdapter(SponsoredProductAdapter);
        }
    }

    @Override
    public void onFestivalOfferSuccess(List<HomeProductResponse> FestivalOfferResponse, String message) {
        if (message.equalsIgnoreCase("ok")) {
            if (FestivalOfferResponse.size() > 0) {
                HomeProduct_Adapter FestivalOfferAdapter = new HomeProduct_Adapter(getContext(), FestivalOfferResponse, this::onHomeProductItemClickListener);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                binding.FestivalRcycler.setLayoutManager(mLayoutManager);
                binding.FestivalRcycler.setItemAnimator(new DefaultItemAnimator());
                binding.FestivalRcycler.setAdapter(FestivalOfferAdapter);
                binding.linearFestival.setVisibility(View.VISIBLE);
            } else {
                binding.linearFestival.setVisibility(View.GONE);
            }

        }
    }

    @Override
    public void onHometDealofDayProductSuccess(List<DealofDayResponse> dealofDayResponses, String message) {
        if (message.equalsIgnoreCase("ok")) {
            DealofDayAdapter dealofDayAdapter = new DealofDayAdapter(getContext(), dealofDayResponses, this::onDealOfDayItemClickListener);
            RecyclerView.LayoutManager mLayoutManager1 = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            binding.recyclerViewDealofDay.setLayoutManager(mLayoutManager1);
            binding.recyclerViewDealofDay.setItemAnimator(new DefaultItemAnimator());
            binding.recyclerViewDealofDay.setAdapter(dealofDayAdapter);
        }

    }

    @Override
    public void onStoriesSuccess(List<StoriesResponse> storiesResponses, String message) {
        if (message.equalsIgnoreCase("ok")) {
            StoriesAdapter gameAdapter = new StoriesAdapter(getContext(), storiesResponses);
            RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            binding.recyclerViewStories.setLayoutManager(mLayoutManager1);
            binding.recyclerViewStories.setItemAnimator(new DefaultItemAnimator());
            binding.recyclerViewStories.setAdapter(gameAdapter);
        }

    }

    @Override
    public void onCoffeeSuccess(List<HotelAndCoffeeResponse> coffeeResponses, String message) {
        if (message.equalsIgnoreCase("ok")) {
            if (coffeeResponses != null && coffeeResponses.size() > 0) {
                RoundAdapter1 gameAdapter = new RoundAdapter1(getContext(), coffeeResponses);
                RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                binding.recyclerViewCoffee.setLayoutManager(mLayoutManager1);
                binding.recyclerViewCoffee.setItemAnimator(new DefaultItemAnimator());
                binding.recyclerViewCoffee.setAdapter(gameAdapter);
                binding.linearCoffee.setVisibility(View.VISIBLE);
            } else {
                binding.linearCoffee.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onHotelSuccess(List<HotelAndCoffeeResponse> hotelResponses, String message) {
        if (message.equalsIgnoreCase("ok")) {
            if (hotelResponses != null && hotelResponses.size() > 0) {
                HotelAdapter gameAdapter = new HotelAdapter(getContext(), hotelResponses);
                RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                binding.HotelRcycler.setLayoutManager(mLayoutManager1);
                binding.HotelRcycler.setItemAnimator(new DefaultItemAnimator());
                binding.HotelRcycler.setAdapter(gameAdapter);
                binding.linearHotel.setVisibility(View.VISIBLE);
            } else {
                binding.linearHotel.setVisibility(View.GONE);
            }
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
        MyPreferences.getInstance(getContext()).putString(PrefConf.ROUTEID, roleId);
        String ProductName = data.get(position).getName();

        Home_FragmentDirections.ActionHomeFragmentToProductDetailsFragment fragment = Home_FragmentDirections.actionHomeFragmentToProductDetailsFragment();
        fragment.setProductName(ProductName);
        Navigation.findNavController(view).navigate(fragment);
    }

    @Override
    public void onDealOfDayItemClickListener(List<DealofDayResponse> data, int position) {
        String roleId = data.get(position).getRoute();
        MyPreferences.getInstance(getContext()).putString(PrefConf.ROUTEID, roleId);
        String ProductName = data.get(position).getName();

        Home_FragmentDirections.ActionHomeFragmentToProductDetailsFragment fragment = Home_FragmentDirections.actionHomeFragmentToProductDetailsFragment();
        fragment.setProductName(ProductName);
        Navigation.findNavController(view).navigate(fragment);
    }

    @Override
    public void onContinueYourHuntItemClickListener(ContinueYourHuntResponse data, int position) {
        String roleId = data.getProducts().get(position).getRoute();
        MyPreferences.getInstance(getContext()).putString(PrefConf.ROUTEID, roleId);
        String ProductName = data.getProducts().get(position).getName();

        Home_FragmentDirections.ActionHomeFragmentToProductDetailsFragment fragment = Home_FragmentDirections.actionHomeFragmentToProductDetailsFragment();
        fragment.setProductName(ProductName);
        Navigation.findNavController(view).navigate(fragment);

    }

}