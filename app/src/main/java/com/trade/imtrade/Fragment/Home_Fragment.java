package com.trade.imtrade.Fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.trade.imtrade.Adapter.BrandsAdapter;
import com.trade.imtrade.Adapter.Categories_Adapter;
import com.trade.imtrade.Adapter.DealOfTheDayAdapter;
import com.trade.imtrade.Adapter.DiscountByBrandsAdapter;
import com.trade.imtrade.Adapter.GameAdapter;
import com.trade.imtrade.Adapter.MostPopularProductAdapter;
import com.trade.imtrade.Adapter.ProductToSeasonAdapter;
import com.trade.imtrade.Adapter.Recommended_Adapter;
import com.trade.imtrade.R;
import com.trade.imtrade.databinding.FragmentHomeBinding;
import com.trade.imtrade.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;

public class Home_Fragment extends Fragment {
    private FragmentHomeBinding binding;
    private View view;
    private Dialog dialog;

    String [] price = {"Mobiles","Mobiles","Mobiles","Mobiles","Mobiles","Mobiles","Mobiles","Mobiles","Mobiles","Mobiles","Mobiles","Mobiles"};
    String [] price1= {"Nike","Puma","Nike","Puma","Nike","Puma","Nike","Puma","Nike","Puma","Nike","Puma"};
    Integer [] Image= {R.mipmap.game1,R.mipmap.game2,R.mipmap.game3};


    public Home_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_home_, container, false);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_,container,false);

        
        view = binding.getRoot();
        dialog = AppUtils.hideShowProgress(getContext());

        SetImageSlider();
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



    private void SetImageSlider() {
        List<SlideModel> slideModels=new ArrayList<>();
        slideModels.add(new SlideModel("https://picsum.photos/id/896/300/200"));
        slideModels.add(new SlideModel("https://picsum.photos/id/894/300/200"));
        slideModels.add(new SlideModel("https://picsum.photos/id/892/300/200"));
        slideModels.add(new SlideModel("https://picsum.photos/id/891/300/200"));
        binding.slider.setImageList(slideModels,true);

        binding.slider.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemSelected(int i) {
                Toast.makeText(getContext(), ""+i, Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void getAllprouctCategories() {
        Categories_Adapter categories_adapter = new Categories_Adapter(getContext(),price);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL ,false);
        binding.categoriesRecycler.setLayoutManager(mLayoutManager1);
        binding.categoriesRecycler.setItemAnimator(new DefaultItemAnimator());
        binding.categoriesRecycler.setAdapter(categories_adapter);
    }
    private void getAllprouctRecommended() {
        Recommended_Adapter recommended_adapter = new Recommended_Adapter(getContext(),price);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL ,false);
        binding.recommendedRcycler.setLayoutManager(mLayoutManager1);
        binding.recommendedRcycler.setItemAnimator(new DefaultItemAnimator());
        binding.recommendedRcycler.setAdapter(recommended_adapter);

    }
    private void getDiscountByBrands() {
        DiscountByBrandsAdapter discountByBrandsAdapter = new DiscountByBrandsAdapter(getContext(),price);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL ,false);
        binding.DiscountByBrandsRecyclerView.setLayoutManager(mLayoutManager1);
        binding.DiscountByBrandsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.DiscountByBrandsRecyclerView.setAdapter(discountByBrandsAdapter);

    }
    private void getDealOfTheDay() {
        DealOfTheDayAdapter dealOfTheDayAdapter = new DealOfTheDayAdapter(getContext(),price);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL ,false);
        binding.DealRcycler.setLayoutManager(mLayoutManager1);
        binding.DealRcycler.setItemAnimator(new DefaultItemAnimator());
        binding.DealRcycler.setAdapter(dealOfTheDayAdapter);

    }
    private void getMostPopularProduct() {
        MostPopularProductAdapter mostPopularProductAdapter = new MostPopularProductAdapter(getContext(),price);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL ,false);
        binding.PopularProductRcycler.setLayoutManager(mLayoutManager1);
        binding.PopularProductRcycler.setItemAnimator(new DefaultItemAnimator());
        binding.PopularProductRcycler.setAdapter(mostPopularProductAdapter);

    }
    private void getProductToSeason() {
        ProductToSeasonAdapter productToSeasonAdapter = new ProductToSeasonAdapter(getContext(),price);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL ,false);
        binding.SeasonRcycler.setLayoutManager(mLayoutManager1);
        binding.SeasonRcycler.setItemAnimator(new DefaultItemAnimator());
        binding.SeasonRcycler.setAdapter(productToSeasonAdapter);

    }


    private void getAllBrands() {
        BrandsAdapter brandsAdapter = new BrandsAdapter(getContext(),price1);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL ,false);
        binding.RecyclerViewBrands.setLayoutManager(mLayoutManager1);
        binding.RecyclerViewBrands.setItemAnimator(new DefaultItemAnimator());
        binding.RecyclerViewBrands.setAdapter(brandsAdapter);

    }

    private void getAllGame() {
        GameAdapter gameAdapter = new GameAdapter(getContext(),Image);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL ,false);
        binding.gameRcycler.setLayoutManager(mLayoutManager1);
        binding.gameRcycler.setItemAnimator(new DefaultItemAnimator());
        binding.gameRcycler.setAdapter(gameAdapter);

    }

}