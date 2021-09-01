package com.trade.imtrade.Fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.irozon.sneaker.Sneaker;
import com.trade.imtrade.Adapter.CountrySpinnerAdapter;
import com.trade.imtrade.Model.CountryModel;
import com.trade.imtrade.R;
import com.trade.imtrade.databinding.FragmentAddressBinding;
import com.trade.imtrade.utils.AppUtils;
import com.trade.imtrade.view_presenter.Address_Presenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;


public class Fragment_Address extends Fragment implements Address_Presenter.AddressView {

    FragmentAddressBinding binding;
    private View view;
    private Dialog dialog;
    NavController navController;
    Address_Presenter presenter;
    CountryModel countryModel;

    public Fragment_Address() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment__address, container, false);
        view = binding.getRoot();
        dialog = AppUtils.hideShowProgress(getContext());
        presenter = new Address_Presenter(this);

        presenter.getCountry(getContext());



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
    public void onCountrySuccess(ResponseBody responseBody, String message) {
        ArrayList<CountryModel>countryModels = new ArrayList<CountryModel>();
        countryModel = new CountryModel();

        if (message.equalsIgnoreCase("ok")){
            countryModels.clear();
            try {
                countryModel.setName("Select Country");
                countryModel.setId("00");
                countryModels.add(countryModel);
                String  response = responseBody.string();
                JSONArray jsonArray = new JSONArray(response);
                for (int i =0 ; i<=jsonArray.length();i++){
                    CountryModel countryModel = new CountryModel();
                    JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                    countryModel.setId(jsonObject2.getString("id"));
                    countryModel.setName(jsonObject2.getString("name"));
                    countryModels.add(countryModel);
                }

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            CountrySpinnerAdapter countrySpinnerAdapter = new CountrySpinnerAdapter(getContext(),countryModels);
            binding.countrySpinner.setAdapter(countrySpinnerAdapter);
            binding.countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    if (!countryModels.get(position).getId().equalsIgnoreCase("00")){
                        presenter.getState(getContext(),countryModels.get(position).getId());
                    }

                 }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }
    }

    @Override
    public void onStateSuccess(ResponseBody responseBody, String message) {
        ArrayList<CountryModel>countryModels = new ArrayList<CountryModel>();
        countryModel = new CountryModel();

        if (message.equalsIgnoreCase("ok")){
            countryModels.clear();
            try {
                countryModel.setName("Select State");
                countryModel.setId("00");
                countryModels.add(countryModel);
                String  response = responseBody.string();
                Log.d("resss",response);
                JSONArray jsonArray = new JSONArray(response);
                for (int i =0 ; i<=jsonArray.length();i++){
                    CountryModel countryModel = new CountryModel();
                    JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                    countryModel.setId(jsonObject2.getString("id"));
                    countryModel.setName(jsonObject2.getString("name"));
                    countryModels.add(countryModel);
                }

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            CountrySpinnerAdapter countrySpinnerAdapter = new CountrySpinnerAdapter(getContext(),countryModels);
            binding.StateSpinner.setAdapter(countrySpinnerAdapter);
            binding.StateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    if (!countryModels.get(position).getId().equalsIgnoreCase("00")){
                        presenter.getCity(getContext(),countryModels.get(position).getId());
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }
    }

    @Override
    public void onCitySuccess(ResponseBody responseBody, String message) {
        ArrayList<CountryModel>countryModels = new ArrayList<CountryModel>();
        countryModel = new CountryModel();

        if (message.equalsIgnoreCase("ok")){
            countryModels.clear();
            try {
                countryModel.setName("Select City");
                countryModel.setId("00");
                countryModels.add(countryModel);
                String  response = responseBody.string();
                JSONArray jsonArray = new JSONArray(response);
                if (jsonArray.length()>0){
                    for (int i =0 ; i<=jsonArray.length();i++){
                        CountryModel countryModel = new CountryModel();
                        JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                        countryModel.setId(jsonObject2.getString("id"));
                        countryModel.setName(jsonObject2.getString("name"));
                        countryModels.add(countryModel);
                    }
                    binding.cityRelative.setVisibility(View.VISIBLE);
                }else {
                    binding.cityRelative.setVisibility(View.GONE);
                }


            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            CountrySpinnerAdapter countrySpinnerAdapter = new CountrySpinnerAdapter(getContext(),countryModels);
            binding.CitySpinner.setAdapter(countrySpinnerAdapter);
            binding.CitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    Toast.makeText(getContext(), ""+countryModels.get(position).getName(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

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