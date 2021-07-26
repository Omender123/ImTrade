package com.trade.imtrade.Fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.trade.imtrade.Adapter.Profile_itemAdapter;
import com.trade.imtrade.R;

import com.trade.imtrade.SharedPrefernce.SharedPrefManager;
import com.trade.imtrade.SharedPrefernce.User_Data;
import com.trade.imtrade.databinding.FragmentProfileBinding;
import com.trade.imtrade.databinding.FragmentProfileBindingImpl;
import com.trade.imtrade.utils.AppUtils;

import java.util.ArrayList;


public class Profile_Fragment extends Fragment implements View.OnClickListener, Profile_itemAdapter.OnProfileItemListener {
    FragmentProfileBinding binding;
    private View view;
    private Dialog dialog;

    NavController navController;

    User_Data user_data;

    public Profile_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);

        view = binding.getRoot();
        dialog = AppUtils.hideShowProgress(getContext());
        getProfileItemList();
        user_data = SharedPrefManager.getInstance(getContext()).getLoginDATA();

        if (user_data.getUserName() == null) {
            binding.userEmail.setVisibility(View.GONE);
            binding.userName.setVisibility(View.GONE);
            binding.addUsername.setVisibility(View.VISIBLE);
            binding.view.setVisibility(View.VISIBLE);
        } else {
            binding.userEmail.setVisibility(View.VISIBLE);
            binding.userName.setVisibility(View.VISIBLE);
            binding.view.setVisibility(View.GONE);
            binding.addUsername.setVisibility(View.GONE);
            binding.userEmail.setText(user_data.getEmail());
            binding.userName.setText(user_data.getUserName());

        }
        binding.addUsername.setOnClickListener(this::onClick);
        binding.userName.setOnClickListener(this::onClick);

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
            case R.id.userName:
                navController.navigate(R.id.action_profile_to_update_profile);
                break;

            case R.id.add_username:
                navController.navigate(R.id.action_profile_to_update_profile);
                break;

        }
    }

    private void getProfileItemList() {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("My account ");
        arrayList.add("My Addresses");
        arrayList.add("Change password");
        arrayList.add("My Rewards");
        arrayList.add("My orders");
        arrayList.add("My Review");
        arrayList.add("My iMX");
        arrayList.add("Add Events ");
        arrayList.add("My Personalise notifications ");

        Profile_itemAdapter profile_itemAdapter = new Profile_itemAdapter(arrayList, getContext(), this::onProfileItemClickListener);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        binding.RecyclerView.setLayoutManager(mLayoutManager);
        binding.RecyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.RecyclerView.setAdapter(profile_itemAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.RecyclerView.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.divider));
        binding.RecyclerView.addItemDecoration(dividerItemDecoration);


    }

    @Override
    public void onProfileItemClickListener(ArrayList<String> ItemList, int position) {
        switch (position) {

            case 0:
                navController.navigate(R.id.action_profile_to_update_profile);
                break;

            case 1:
                navController.navigate(R.id.action_profile_to_My_address);
                break;

            case 2:
                Toast.makeText(getContext(), "" + ItemList.get(position), Toast.LENGTH_SHORT).show();

                break;

            case 3:
                Toast.makeText(getContext(), "" + ItemList.get(position), Toast.LENGTH_SHORT).show();

                break;

            case 4:
                Toast.makeText(getContext(), "" + ItemList.get(position), Toast.LENGTH_SHORT).show();

                break;

            case 5:
                Toast.makeText(getContext(), "" + ItemList.get(position), Toast.LENGTH_SHORT).show();

                break;

            case 6:
                Toast.makeText(getContext(), "" + ItemList.get(position), Toast.LENGTH_SHORT).show();

                break;

            case 7:
                Toast.makeText(getContext(), "" + ItemList.get(position), Toast.LENGTH_SHORT).show();

                break;

            case 8:
                Toast.makeText(getContext(), "" + ItemList.get(position), Toast.LENGTH_SHORT).show();

                break;

            case 9:
                Toast.makeText(getContext(), "" + ItemList.get(position), Toast.LENGTH_SHORT).show();

                break;
        }

    }
}