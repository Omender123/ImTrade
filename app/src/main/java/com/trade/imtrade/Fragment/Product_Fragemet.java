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
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.irozon.sneaker.Sneaker;
import com.trade.imtrade.Adapter.All_Categories_Adapter;
import com.trade.imtrade.Adapter.ProductAdapter;
import com.trade.imtrade.MainActivity;
import com.trade.imtrade.Model.ResponseModel.ProductResponse;
import com.trade.imtrade.R;
import com.trade.imtrade.SharedPerfence.MyPreferences;
import com.trade.imtrade.SharedPerfence.PrefConf;
import com.trade.imtrade.databinding.FragmentProductBinding;
import com.trade.imtrade.utils.AppUtils;
import com.trade.imtrade.view_presenter.AllProductPresenter;
import com.trade.imtrade.view_presenter.Home_Presenter;


public class Product_Fragemet extends Fragment implements View.OnClickListener, ProductAdapter.ProductClickListener, AllProductPresenter.GetAllProductView {
    FragmentProductBinding binding;
    private View view;
    private Dialog dialog;
    NavController navController;
    String categoriesName;

    AllProductPresenter presenter;
    public Product_Fragemet() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //  return inflater.inflate(R.layout.fragment_product, container, false);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product, container, false);

        view = binding.getRoot();
        presenter = new AllProductPresenter(this);

        dialog = AppUtils.hideShowProgress(getContext());
        binding.imgShort.setOnClickListener(this);
        binding.textFilter.setOnClickListener(this);

        categoriesName = Product_FragemetArgs.fromBundle(getArguments()).getCategoriesTitle();

        presenter.GetAllProduct(getContext(),categoriesName);

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);


    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_short:
                showBottomSheetDialog();
                break;

            case R.id.text_filter:
              navController.navigate(R.id.action_product_Fragemet_to_filter_Fragment);
                break;
        }

    }

    private void showBottomSheetDialog() {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
        bottomSheetDialog.setContentView(R.layout.sort_bottom_sheet);

        RadioGroup radioGroup = bottomSheetDialog.findViewById(R.id.radioGroup1);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (group.getCheckedRadioButtonId()) {
                    case R.id.radio_Relevance:

                        Toast.makeText(getContext(), "radio_Relevance", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.radio_Popularity:
                        Toast.makeText(getContext(), "radio_Popularity", Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.radio_LtoH:
                        Toast.makeText(getContext(), "radio_LtoH", Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.radio_HtoL:
                        Toast.makeText(getContext(), "radio_HtoL", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radio_First:
                        Toast.makeText(getContext(), "radio_First", Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        });

        bottomSheetDialog.show();
    }

    @Override
    public void ProductOnClickListener(ProductResponse productResponse,int Position) {
        String roleId = productResponse.getResponse().get(Position).getRoute();

        MyPreferences.getInstance(getContext()).putString(PrefConf.ROUTEID,roleId);


        navController.navigate(R.id.action_product_Fragemet_to_product_Details);
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
    public void onAllProductSuccess(ProductResponse productResponse, String message) {
        if (message.equalsIgnoreCase("ok")) {
            ProductAdapter productAdapter = new ProductAdapter(getContext(),productResponse ,this::ProductOnClickListener);
            RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
            binding.RecyclerView.setLayoutManager(mLayoutManager1);
            binding.RecyclerView.setItemAnimator(new DefaultItemAnimator());
            binding.RecyclerView.setAdapter(productAdapter);
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
}