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

import com.irozon.sneaker.Sneaker;
import com.trade.imtrade.Adapter.All_Categories_Adapter;
import com.trade.imtrade.Adapter.Categories_Adapter;
import com.trade.imtrade.Model.ResponseModel.AllCategoriesResponse;
import com.trade.imtrade.R;
import com.trade.imtrade.databinding.FragmentCategoriesBinding;
import com.trade.imtrade.utils.AppUtils;
import com.trade.imtrade.view_presenter.GetAllCategories_Presenter;
import com.trade.imtrade.view_presenter.Home_Presenter;

import java.util.List;

public class Categories_Fragment extends Fragment implements All_Categories_Adapter.OnAllCategoriesItemListener, GetAllCategories_Presenter.GetAllCategoriesView {
    FragmentCategoriesBinding binding;
    GetAllCategories_Presenter presenter;
    private View view;
    private Dialog dialog;
    NavController navController;
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
        presenter = new GetAllCategories_Presenter(this);
        presenter.GetAllCategories(getContext());



        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);


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
    public void onAllCategoriesSuccess(List<AllCategoriesResponse> allCategoriesResponses, String message) {
        if (message.equalsIgnoreCase("ok")) {
            All_Categories_Adapter all_categories_adapter = new All_Categories_Adapter(getContext(), allCategoriesResponses, this::onAllCategoriesItemClickListener);
            RecyclerView.LayoutManager mLayoutManager1 = new GridLayoutManager(getContext(), 3, RecyclerView.VERTICAL, false);
            binding.RecyclerView.setLayoutManager(mLayoutManager1);
            binding.RecyclerView.setItemAnimator(new DefaultItemAnimator());
            binding.RecyclerView.setAdapter(all_categories_adapter);
        }
    }

    @Override
    public void onFailure(Throwable t) {
        Sneaker.with(getActivity())
                .setTitle(t.getLocalizedMessage())
                .setMessage("")
                .setCornerRadius(4)
                .setDuration(1500)
                .sneakError();
    }

    @Override
    public void onAllCategoriesItemClickListener(List<AllCategoriesResponse> data, int position) {
        String categories = data.get(position).getName();
        String categoriesId = data.get(position).getId();
        Bundle bundle = new Bundle();
        bundle.putString("categories", categories);
        bundle.putString("categoriesId", categoriesId);


        Categories_FragmentDirections.ActionCategoriesToProductFragemet categoriesToProductFragemet = Categories_FragmentDirections.actionCategoriesToProductFragemet();
        categoriesToProductFragemet.setCategoriesTitle(categories);
        categoriesToProductFragemet.setCategoriesId(categoriesId);
        Navigation.findNavController(view).navigate(categoriesToProductFragemet);
    }

}