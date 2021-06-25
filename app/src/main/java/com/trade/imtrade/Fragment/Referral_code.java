package com.trade.imtrade.Fragment;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.irozon.sneaker.Sneaker;
import com.trade.imtrade.Authentication.Login;
import com.trade.imtrade.R;
import com.trade.imtrade.SharedPrefernce.SharedPrefManager;
import com.trade.imtrade.SharedPrefernce.User_Data;
import com.trade.imtrade.databinding.FragmentReferralCodeBinding;
import com.trade.imtrade.databinding.FragmentReferralCodeBindingImpl;
import com.trade.imtrade.utils.AppUtils;

public class Referral_code extends Fragment implements View.OnClickListener {
    FragmentReferralCodeBinding binding;
    User_Data user_data;

    public Referral_code() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //  return inflater.inflate(R.layout.fragment_referral_code, container, false);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_referral_code, container, false);

        user_data = SharedPrefManager.getInstance(getContext()).getLoginDATA();
        binding.referral.setText(user_data.getReferral_code());

        binding.share.setOnClickListener(this);
        binding.textCopy.setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.text_copy:
                ClipboardManager cm = (ClipboardManager)getContext().getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText( binding.referral.getText().toString());
                Sneaker.with(getActivity())
                        .setTitle("Copied......")
                        .setMessage("")
                        .setCornerRadius(4)
                        .setDuration(1500)
                        .sneakSuccess();
                break;

            case R.id.share:
                AppUtils.shareApp(getContext(),user_data.getReferral_code());
                break;
        }
    }
}