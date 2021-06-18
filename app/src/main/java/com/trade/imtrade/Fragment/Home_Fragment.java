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
import com.trade.imtrade.Adapter.Categories_Adapter;
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
}