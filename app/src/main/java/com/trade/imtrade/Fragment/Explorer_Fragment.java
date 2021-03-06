package com.trade.imtrade.Fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trade.imtrade.Adapter.Explorer_Adapter;
import com.trade.imtrade.Adapter.My_Address_Adapter;
import com.trade.imtrade.R;
import com.trade.imtrade.databinding.FragmentExplorerBinding;
import com.trade.imtrade.utils.AppUtils;


public class Explorer_Fragment extends Fragment {

    FragmentExplorerBinding binding;
    private View view;
    private Dialog dialog;

    String [] price = {"26 Jan 2021","26 Jan 2021","26 Jan 2021","26 Jan 2021","26 Jan 2021"};

    NavController navController;
    public Explorer_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         binding = DataBindingUtil.inflate(inflater,R.layout.fragment_explorer_, container, false);

        view = binding.getRoot();
        dialog = AppUtils.hideShowProgress(getContext());


        GetExplorer();


        return binding.getRoot();
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
    }

    private void GetExplorer() {
        Explorer_Adapter explorer_adapter = new Explorer_Adapter(getContext(),price);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL ,false);
        binding.RecyclerView.setLayoutManager(mLayoutManager1);
        binding.RecyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.RecyclerView.setAdapter(explorer_adapter);
    }
}
