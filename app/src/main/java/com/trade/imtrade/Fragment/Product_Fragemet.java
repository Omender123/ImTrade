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
import com.trade.imtrade.Adapter.All_Categories_Adapter;
import com.trade.imtrade.Adapter.ProductAdapter;
import com.trade.imtrade.MainActivity;
import com.trade.imtrade.R;
import com.trade.imtrade.databinding.FragmentProductBinding;
import com.trade.imtrade.utils.AppUtils;


public class Product_Fragemet extends Fragment implements View.OnClickListener {
    FragmentProductBinding binding;
    private View view;
    private Dialog dialog;
    NavController navController;
    String categoriesId;
    String[] price = {"8,999 Rs", "8,999 Rs", "8,999 Rs", "8,999 Rs", "8,999 Rs", "8,999 Rs", "8,999 Rs", "8,999 Rs", "8,999 Rs", "8,999 Rs"};

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
        dialog = AppUtils.hideShowProgress(getContext());
        binding.imgShort.setOnClickListener(this);
        binding.textFilter.setOnClickListener(this);

        categoriesId = Product_FragemetArgs.fromBundle(getArguments()).getCategoriesId();

        Toast.makeText(getContext(), ""+categoriesId, Toast.LENGTH_SHORT).show();

        getAllProduct();

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);


    }

    private void getAllProduct() {

        ProductAdapter productAdapter = new ProductAdapter(getContext(), price);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.RecyclerView.setLayoutManager(mLayoutManager1);
        binding.RecyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.RecyclerView.setAdapter(productAdapter);
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
}