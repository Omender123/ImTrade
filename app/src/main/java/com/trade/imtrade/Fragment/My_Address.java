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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.irozon.sneaker.Sneaker;
import com.trade.imtrade.Adapter.My_Address_Adapter;
import com.trade.imtrade.Model.ResponseModel.AddressResponse;
import com.trade.imtrade.R;
import com.trade.imtrade.databinding.FragmentMyAddressBinding;
import com.trade.imtrade.utils.AppUtils;
import com.trade.imtrade.view_presenter.MyAddress_Presenter;

import java.util.List;

import okhttp3.ResponseBody;


public class My_Address extends Fragment implements View.OnClickListener, MyAddress_Presenter.MyAddressView, My_Address_Adapter.OnClickAddressListener {
    FragmentMyAddressBinding binding;
    private View view;
    private Dialog dialog;
    MyAddress_Presenter presenter;
    NavController navController;

    public My_Address() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my__address, container, false);

        view = binding.getRoot();
        dialog = AppUtils.hideShowProgress(getContext());

        presenter = new MyAddress_Presenter(this);

        presenter.getUserAddress(getContext());

        binding.textAdd.setOnClickListener(this);

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

            case R.id.text_add:
                Bundle bundle = new Bundle();
                bundle.putString("id",null);
                navController.navigate(R.id.address,bundle);

                break;
        }

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
    public void onGetAddressSuccess(List<AddressResponse> addressResponses, String message) {
        if (message.equalsIgnoreCase("ok")){
            My_Address_Adapter my_address_adapter = new My_Address_Adapter(getContext(), addressResponses,this);
            RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            binding.RecyclerView.setLayoutManager(mLayoutManager1);
            binding.RecyclerView.setItemAnimator(new DefaultItemAnimator());
            binding.RecyclerView.setAdapter(my_address_adapter);
        }

    }

    @Override
    public void onDeleteAddressSuccess(ResponseBody responseBody, String message) {
        if (message.equalsIgnoreCase("ok")){

            presenter.getUserAddress(getContext());
            Sneaker.with(getActivity())
                    .setTitle("Successful Remove Address")
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
    public void OnClickEditItemAddressListener(String id) {
        Bundle bundle = new Bundle();
        bundle.putString("id",id);
       navController.navigate(R.id.address,bundle);


    }

    @Override
    public void OnClickDeleteItemAddressListener(String id) {

        presenter.DeleteUserAddress(getContext(),id);
    }
}