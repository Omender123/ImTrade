package com.trade.imtrade.Fragment;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
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
import android.widget.SeekBar;
import android.widget.Toast;

import com.trade.imtrade.Adapter.BrandsToggleAdapter;
import com.trade.imtrade.Adapter.NearByMeAdapter;
import com.trade.imtrade.Adapter.RatingToggleAdapter;
import com.trade.imtrade.Model.ToggleBrandsModel;
import com.trade.imtrade.R;
import com.trade.imtrade.databinding.FragmentFilterBinding;
import com.trade.imtrade.utils.AppUtils;

import java.util.ArrayList;


public class Filter_Fragment extends Fragment implements BrandsToggleAdapter.OnToggleItemListener, View.OnClickListener, RatingToggleAdapter.OnRatingItemListener, SeekBar.OnSeekBarChangeListener {
    FragmentFilterBinding binding;
    private View view;
    private Dialog dialog;

    ArrayList<ToggleBrandsModel> brandsModels;
    String[] price = {"Apple", "Nokia", "Coolpad", "Vivo", "Redmi", "Xoami", "Dell", "Panasonic", "Asus", "Hp", "Oppo", "Ibell", "Lava", "MacBook", "Amul", "Samsang", "Classic"};
    String[] rating = {"Upto 5", "Upto 4", "Upto 3", "Upto 2", "Upto 1"};
    NavController navController;

    public Filter_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_filter, container, false);

        view = binding.getRoot();
        dialog = AppUtils.hideShowProgress(getContext());

        brandsModels = new ArrayList<ToggleBrandsModel>();
        binding.brandsShow.setOnClickListener(this);
        binding.brandsUnshow.setOnClickListener(this);
        binding.RatingShow.setOnClickListener(this);
        binding.RatingUnshow.setOnClickListener(this);
        binding.seebar.setOnSeekBarChangeListener(this);
        getAllBrands();
        getAllRating();

        return binding.getRoot();

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);


    }

    private void getAllBrands() {

        BrandsToggleAdapter brandsToggleAdapter = new BrandsToggleAdapter(getContext(), price, this::onToggleItemClickListener);
        RecyclerView.LayoutManager mLayoutManager1 = new GridLayoutManager(getContext(), 4, LinearLayoutManager.VERTICAL, false);
        binding.RecyclerView.setLayoutManager(mLayoutManager1);
        binding.RecyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.RecyclerView.setAdapter(brandsToggleAdapter);
    }

    private void getAllRating() {

        RatingToggleAdapter ratingToggleAdapter = new RatingToggleAdapter(getContext(), rating, this::onRatingItemClickListener);
        RecyclerView.LayoutManager mLayoutManager1 = new GridLayoutManager(getContext(), 3, LinearLayoutManager.VERTICAL, false);
        binding.RatingRecyclerView.setLayoutManager(mLayoutManager1);
        binding.RatingRecyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.RatingRecyclerView.setAdapter(ratingToggleAdapter);
    }

    @Override
    public void onToggleItemClickListener(int position, boolean Checked) {
        String text = price[position];
        Toast.makeText(getContext(), text + "" + Checked, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onRatingItemClickListener(int position, boolean Checked) {
        String text = rating[position];
        Toast.makeText(getContext(), text + "" + Checked, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.brands_show:
                binding.brandsShow.setVisibility(View.GONE);
                binding.brandsUnshow.setVisibility(View.VISIBLE);
                binding.RecyclerView.setVisibility(View.VISIBLE);
                break;

            case R.id.brands_unshow:
                binding.brandsShow.setVisibility(View.VISIBLE);
                binding.brandsUnshow.setVisibility(View.GONE);
                binding.RecyclerView.setVisibility(View.GONE);

                break;

            case R.id.Rating_show:
                binding.RatingShow.setVisibility(View.GONE);
                binding.RatingUnshow.setVisibility(View.VISIBLE);
                binding.RatingRecyclerView.setVisibility(View.VISIBLE);
                break;

            case R.id.Rating_unshow:
                binding.RatingShow.setVisibility(View.VISIBLE);
                binding.RatingUnshow.setVisibility(View.GONE);
                binding.RatingRecyclerView.setVisibility(View.GONE);

                break;


        }
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        seekBar.setMin(1000);
        seekBar.setMax(10000);
        binding.textProgress.setText(String.valueOf(progress) + " Rs");
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}