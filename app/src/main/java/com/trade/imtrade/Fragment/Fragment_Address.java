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
import com.trade.imtrade.Authentication.Login;
import com.trade.imtrade.Model.CountryModel;
import com.trade.imtrade.Model.ResponseModel.AddressResponse;
import com.trade.imtrade.Model.request.AddAddressBody;
import com.trade.imtrade.R;

import com.trade.imtrade.databinding.FragmentAddressBinding;
import com.trade.imtrade.utils.AppUtils;
import com.trade.imtrade.view_presenter.Address_Presenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import de.mateware.snacky.Snacky;
import okhttp3.ResponseBody;


public class Fragment_Address extends Fragment implements Address_Presenter.AddressView, View.OnClickListener {

    FragmentAddressBinding binding;
    private View view;
    private Dialog dialog;
    NavController navController;
    Address_Presenter presenter;
    CountryModel countryModel;
    String Name, phone_no, Country, State, City, pincode, address, compareValue, compareState, compareCity;

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
        binding.textDone.setOnClickListener(this);


        if (getArguments().getString("id") != null) {
            presenter.GetAddAddressById(getContext(), getArguments().getString("id"));
        }

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
        ArrayList<CountryModel> countryModels = new ArrayList<CountryModel>();
        countryModel = new CountryModel();

        if (message.equalsIgnoreCase("ok")) {
            countryModels.clear();
            try {
                countryModel.setName("Select Country");
                countryModel.setId("00");
                countryModels.add(countryModel);
                String response = responseBody.string();
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i <= jsonArray.length(); i++) {
                    CountryModel countryModel = new CountryModel();
                    JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                    countryModel.setId(jsonObject2.getString("id"));
                    countryModel.setName(jsonObject2.getString("name"));
                    countryModels.add(countryModel);
                }

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            CountrySpinnerAdapter countrySpinnerAdapter = new CountrySpinnerAdapter(getContext(), countryModels);
            binding.countrySpinner.setAdapter(countrySpinnerAdapter);
            for (int i = 0; i < countryModels.size(); i++) {
                if (compareValue != null && compareValue.equalsIgnoreCase(countryModels.get(i).getName())) {
                    binding.countrySpinner.setSelection(i);
                }
            }
            binding.countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    if (!countryModels.get(position).getId().equalsIgnoreCase("00")) {
                        presenter.getState(getContext(), countryModels.get(position).getId());
                        binding.StateRecycler.setVisibility(View.VISIBLE);
                        Country = countryModels.get(position).getName();
                    } else {
                        binding.StateRecycler.setVisibility(View.GONE);

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
        ArrayList<CountryModel> countryModels = new ArrayList<CountryModel>();
        countryModel = new CountryModel();

        if (message.equalsIgnoreCase("ok")) {
            countryModels.clear();
            try {
                countryModel.setName("Select State");
                countryModel.setId("00");
                countryModels.add(countryModel);
                String response = responseBody.string();
                Log.d("resss", response);
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i <= jsonArray.length(); i++) {
                    CountryModel countryModel = new CountryModel();
                    JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                    countryModel.setId(jsonObject2.getString("id"));
                    countryModel.setName(jsonObject2.getString("name"));
                    countryModels.add(countryModel);
                }

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            CountrySpinnerAdapter countrySpinnerAdapter = new CountrySpinnerAdapter(getContext(), countryModels);
            binding.StateSpinner.setAdapter(countrySpinnerAdapter);
            for (int i = 0; i < countryModels.size(); i++) {
                if (compareState != null && compareState.equalsIgnoreCase(countryModels.get(i).getName())) {
                    binding.StateSpinner.setSelection(i);
                }
            }
            binding.StateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    if (!countryModels.get(position).getId().equalsIgnoreCase("00")) {
                        presenter.getCity(getContext(), countryModels.get(position).getId());
                        binding.cityRelative.setVisibility(View.VISIBLE);
                        State = countryModels.get(position).getName();
                    } else {
                        binding.cityRelative.setVisibility(View.GONE);

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
        ArrayList<CountryModel> countryModels = new ArrayList<CountryModel>();
        countryModel = new CountryModel();

        if (message.equalsIgnoreCase("ok")) {
            countryModels.clear();
            try {
                countryModel.setName("Select City");
                countryModel.setId("00");
                countryModels.add(countryModel);
                String response = responseBody.string();
                JSONArray jsonArray = new JSONArray(response);
                if (jsonArray.length() > 0) {
                    for (int i = 0; i <= jsonArray.length(); i++) {
                        CountryModel countryModel = new CountryModel();
                        JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                        countryModel.setId(jsonObject2.getString("id"));
                        countryModel.setName(jsonObject2.getString("name"));
                        countryModels.add(countryModel);
                    }
                    binding.cityRelative.setVisibility(View.VISIBLE);

                } else {
                    binding.cityRelative.setVisibility(View.GONE);
                }


            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            CountrySpinnerAdapter countrySpinnerAdapter = new CountrySpinnerAdapter(getContext(), countryModels);
            binding.CitySpinner.setAdapter(countrySpinnerAdapter);
            for (int i = 0; i < countryModels.size(); i++) {
                if (compareCity != null && compareCity.equalsIgnoreCase(countryModels.get(i).getName())) {
                    binding.CitySpinner.setSelection(i);
                }
            }
            binding.CitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    if (position == 0) {
                        City = null;
                    } else {
                        City = countryModels.get(position).getName();

                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }
    }

    @Override
    public void onAddAddressSuccess(ResponseBody responseBody, String message) {
        if (message.equalsIgnoreCase("ok")) {
            binding.textName.setText("");
            binding.textPhoneNo.setText("");
            binding.textPincode.setText("");
            binding.textAddress.setText("");
            presenter.getCountry(getContext());
            binding.cityRelative.setVisibility(View.GONE);
            Sneaker.with(getActivity())
                    .setTitle("Successfully add address")
                    .setMessage("")
                    .setCornerRadius(4)
                    .setDuration(1500)
                    .sneakSuccess();

        }

    }

    @Override
    public void onGetUserAddressById(AddressResponse addressResponse, String message) {
        if (message.equalsIgnoreCase("ok")) {
            binding.textName.setText(addressResponse.getName());
            binding.textPhoneNo.setText(addressResponse.getPhoneNo());
            binding.textPincode.setText(addressResponse.getPinCode());
            binding.textAddress.setText(addressResponse.getAddress());
            presenter.getCountry(getContext());
            compareValue = addressResponse.getCountry();
            compareState = addressResponse.getState();
            compareCity = addressResponse.getCity();


        }

    }

    @Override
    public void onEditUserAddressById(ResponseBody responseBody, String message) {
        if (message.equalsIgnoreCase("ok")) {
            binding.textName.setText(Name);
            binding.textPhoneNo.setText(phone_no);
            binding.textPincode.setText(pincode);
            binding.textAddress.setText(address);

            Sneaker.with(getActivity())
                    .setTitle("Sucessfully Update Address")
                    .setMessage("")
                    .setCornerRadius(4)
                    .setDuration(1500)
                    .sneakSuccess();


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
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.text_done:

                getData();
                break;
        }
    }

    private void getData() {
        Name = binding.textName.getText().toString();
        phone_no = binding.textPhoneNo.getText().toString();
        pincode = binding.textPincode.getText().toString();
        address = binding.textAddress.getText().toString();

        if (Name.isEmpty()) {
            binding.textName.requestFocus();
            Snacky.builder()
                    .setActivity(getActivity())
                    .setText("Please enter Name !!!!!!")
                    .setTextColor(getResources().getColor(R.color.product_name))
                    .warning()
                    .show();
        } else if (phone_no.isEmpty()) {
            binding.textPhoneNo.requestFocus();
            Snacky.builder()
                    .setActivity(getActivity())
                    .setText("Please enter Phone Number !!!!!!")
                    .setTextColor(getResources().getColor(R.color.product_name))
                    .warning()
                    .show();
        } else if (binding.countrySpinner.getSelectedItemPosition() == 0) {
            binding.countrySpinner.requestFocus();
            Snacky.builder()
                    .setActivity(getActivity())
                    .setText("Please Select Your Country !!!!!!")
                    .setTextColor(getResources().getColor(R.color.product_name))
                    .warning()
                    .show();
        } else if (binding.StateSpinner.getSelectedItemPosition() == 0) {
            binding.StateSpinner.requestFocus();
            Snacky.builder()
                    .setActivity(getActivity())
                    .setText("Please Select Your State !!!!!!")
                    .setTextColor(getResources().getColor(R.color.product_name))
                    .warning()
                    .show();
        } else if (pincode.isEmpty()) {
            binding.textPincode.requestFocus();
            Snacky.builder()
                    .setActivity(getActivity())
                    .setText("Please enter pincode !!!!!!")
                    .setTextColor(getResources().getColor(R.color.product_name))
                    .warning()
                    .show();
        } else if (address.isEmpty()) {
            binding.textPincode.requestFocus();
            Snacky.builder()
                    .setActivity(getActivity())
                    .setText("Please enter Address !!!!!!")
                    .setTextColor(getResources().getColor(R.color.product_name))
                    .warning()
                    .show();
        } else {
            if (getArguments().getString("id") != null) {
                AddAddressBody addAddressBody = new AddAddressBody(Name, phone_no, Country, State, City, pincode, address);
                presenter.EditAddAddress(getContext(), getArguments().getString("id"), addAddressBody);
            } else {
                AddAddressBody addAddressBody = new AddAddressBody(Name, phone_no, Country, State, City, pincode, address);
                presenter.AddAddress(getContext(), addAddressBody);
            }

        }
    }
}