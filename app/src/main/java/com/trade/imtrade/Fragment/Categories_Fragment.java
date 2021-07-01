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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trade.imtrade.Adapter.All_Categories_Adapter;
import com.trade.imtrade.Adapter.Categories_Adapter;
import com.trade.imtrade.R;
import com.trade.imtrade.databinding.FragmentCategoriesBinding;
import com.trade.imtrade.utils.AppUtils;
import com.trade.imtrade.view_presenter.Home_Presenter;

public class Categories_Fragment extends Fragment implements All_Categories_Adapter.OnAllCategoriesItemListener {
    FragmentCategoriesBinding binding;
    private View view;
    private Dialog dialog;
    NavController navController;
    String[] price = {"Mobiles", "Laptops", "Television", "Shose", "Fashion", "Women's", "Men's", "Furniture", "Headphone", "Appliances", "Grocery", "Sports", "Baby Toys"};

    public Categories_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_categories, container, false);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_categories, container, false);

        view = binding.getRoot();
        dialog = AppUtils.hideShowProgress(getContext());

        getAllCategories();

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);


    }

    private void getAllCategories() {


        All_Categories_Adapter all_categories_adapter = new All_Categories_Adapter(getContext(), price, this::onAllCategoriesItemClickListener);
        RecyclerView.LayoutManager mLayoutManager1 = new GridLayoutManager(getContext(), 3, RecyclerView.VERTICAL, false);
        binding.RecyclerView.setLayoutManager(mLayoutManager1);
        binding.RecyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.RecyclerView.setAdapter(all_categories_adapter);
    }

    @Override
    public void onAllCategoriesItemClickListener(int position) {

        String categories = price[position];
        Bundle bundle = new Bundle();
        bundle.putString("categories", categories);

        Categories_FragmentDirections.ActionCategoriesToProductFragemet categoriesToProductFragemet = Categories_FragmentDirections.actionCategoriesToProductFragemet();
        categoriesToProductFragemet.setCategoriesTitle(categories);
        Navigation.findNavController(view).navigate(categoriesToProductFragemet);


    }
}