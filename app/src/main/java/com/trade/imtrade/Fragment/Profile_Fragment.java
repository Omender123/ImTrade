package com.trade.imtrade.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.irozon.sneaker.Sneaker;
import com.trade.imtrade.Adapter.Profile_itemAdapter;
import com.trade.imtrade.MainActivity;
import com.trade.imtrade.Model.ResponseModel.AddProfileResponse;
import com.trade.imtrade.R;

import com.trade.imtrade.SharedPerfence.MyPreferences;
import com.trade.imtrade.SharedPerfence.PrefConf;
import com.trade.imtrade.SharedPrefernce.SharedPrefManager;
import com.trade.imtrade.SharedPrefernce.User_Data;
import com.trade.imtrade.databinding.FragmentProfileBinding;
import com.trade.imtrade.utils.AppUtils;
import com.trade.imtrade.utils.ImagePath;
import com.trade.imtrade.view_presenter.ProfilePresenter;

import java.io.File;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;


public class Profile_Fragment extends Fragment implements View.OnClickListener, Profile_itemAdapter.OnProfileItemListener, ProfilePresenter.ProfileView {
    FragmentProfileBinding binding;
    private View view;
    private Dialog dialog;
    public boolean permissionStatus;
    private int PICK_PHOTO_FOR_AVATAR = 1;
    private Dialog dialogBox;
    private ProfilePresenter presenter;

    NavController navController;

    User_Data user_data;
    Boolean CheckedLogin;

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
        CheckedLogin = MyPreferences.getInstance(getContext()).getBoolean(PrefConf.LOGINCHECK, false);

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
        binding.imgUpload.setOnClickListener(this::onClick);


        String profileImage = MyPreferences.getInstance(getContext()).getString(PrefConf.ProfileImage, null);
        if (profileImage == null) {
            Glide.with(getContext()).load(profileImage).apply(new RequestOptions().circleCrop()).placeholder(R.drawable.ic_profile_image).into(binding.imageProfile);

        } else if (!profileImage.equalsIgnoreCase("https://stargazeevents.s3.ap-south-1.amazonaws.com/pfiles/profile.png")) {
            Glide.with(getContext()).load(PrefConf.IMAGE_URL + profileImage).apply(new RequestOptions().circleCrop()).placeholder(R.drawable.ic_profile_image).into(binding.imageProfile);

        } else {
            Glide.with(getContext()).load(profileImage).apply(new RequestOptions().circleCrop()).placeholder(R.drawable.ic_profile_image).into(binding.imageProfile);

        }

        presenter = new ProfilePresenter(this);

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
                if (CheckedLogin == true) {
                    navController.navigate(R.id.action_profile_to_update_profile);
                } else {
                    Sneaker.with(getActivity())
                            .setTitle("Your Can't access this app  please First Login ")
                            .setMessage("")
                            .setCornerRadius(4)
                            .setDuration(1500)
                            .sneakError();
                }

                break;

            case R.id.add_username:

                if (CheckedLogin == true) {
                    navController.navigate(R.id.action_profile_to_update_profile);
                } else {
                    Sneaker.with(getActivity())
                            .setTitle("Your Can't access this app  please First Login ")
                            .setMessage("")
                            .setCornerRadius(4)
                            .setDuration(1500)
                            .sneakError();
                }
                break;

            case R.id.img_upload:

                if (CheckedLogin == true) {

                    galleryPicker();
                } else {
                    Sneaker.with(getActivity())
                            .setTitle("Your Can't access this app  please First Login ")
                            .setMessage("")
                            .setCornerRadius(4)
                            .setDuration(1500)
                            .sneakError();
                }
                break;

        }
    }


    private void getProfileItemList() {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("My Account ");
        arrayList.add("My Addresses");
        arrayList.add("Change Password");
        arrayList.add("Change Email");
        arrayList.add("My Rewards");
        arrayList.add("My Orders");
        arrayList.add("My Review");
        arrayList.add("My iMX");
        arrayList.add("Add Events ");
        arrayList.add("My Personalise Notifications ");

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
        if (CheckedLogin == true) {
            switch (position) {
                case 0:
                    navController.navigate(R.id.action_profile_to_update_profile);
                    break;

                case 1:
                    navController.navigate(R.id.action_profile_to_My_address);
                    break;

                case 2:
                    navController.navigate(R.id.action_profile_to_ChangePassword);

                    break;

                case 3:
                    navController.navigate(R.id.action_profile_to_ChangeEmail);

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
        } else {
            Sneaker.with(getActivity())
                    .setTitle("Your Can't access this app  please First Login ")
                    .setMessage("")
                    .setCornerRadius(4)
                    .setDuration(1500)
                    .sneakError();
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {

            case AppUtils.PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    permissionStatus = true;

                } else {
                    permissionStatus = false;
                    String msg = "Please Allow Permission to share.";
                    customAlert(msg);

                }
                return;
        }
    }

    private void customAlert(String msg) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setMessage(msg);
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialogBox.dismiss();
            }
        }).show();
    }

    private void galleryPicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_PHOTO_FOR_AVATAR);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_PHOTO_FOR_AVATAR && resultCode == RESULT_OK) {
            if (data == null)
                return;
            Uri uri = data.getData();
            System.out.println("urii  "+uri +" "+uri.getPath());
            String path  = ImagePath.getPath(getContext(),uri);
            System.out.println("urii path "+path );
            if(path!=null && !path.equals("")) {
                File file = new File(path);
                uploadImage(file);
            }

        }
    }

    @Override
    public void showHideProgress(boolean isShow) {
        if (isShow){
            dialog.show();
        }else{
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
    public void onProfileUpload(AddProfileResponse addProfileResponse) {
     String Image = addProfileResponse.getResult().getProfileImage();
     MyPreferences.getInstance(getContext()).putString(PrefConf.ProfileImage, Image);
        Sneaker.with(getActivity())
                .setTitle("Successfully Upload Profile image")
                .setMessage("")
                .setCornerRadius(4)
                .setDuration(1500)
                .sneakSuccess();
        Glide.with(getContext()).load(PrefConf.IMAGE_URL + Image).apply(new RequestOptions().circleCrop()).placeholder(R.drawable.ic_profile_image).into(binding.imageProfile);


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

    private void uploadImage( File file){
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part image =
                MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        presenter.uploadImage(getContext(),image);
    }
}