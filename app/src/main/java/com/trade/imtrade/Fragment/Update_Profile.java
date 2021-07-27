package com.trade.imtrade.Fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.service.autofill.UserData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chaos.view.PinView;
import com.irozon.sneaker.Sneaker;
import com.trade.imtrade.Authentication.SignUp;
import com.trade.imtrade.Model.request.SendOtpBody;
import com.trade.imtrade.Model.request.UpdateProfileBody;
import com.trade.imtrade.R;
import com.trade.imtrade.SharedPerfence.MyPreferences;
import com.trade.imtrade.SharedPerfence.PrefConf;
import com.trade.imtrade.SharedPrefernce.SharedPrefManager;
import com.trade.imtrade.SharedPrefernce.User_Data;
import com.trade.imtrade.databinding.FragmentUpdateProfileBinding;
import com.trade.imtrade.utils.AppUtils;
import com.trade.imtrade.utils.Validation;
import com.trade.imtrade.view_presenter.UpdateProfile_Presenter;

import de.mateware.snacky.Snacky;
import okhttp3.ResponseBody;

public class Update_Profile extends Fragment implements View.OnClickListener, UpdateProfile_Presenter.UpdateProfileView {
    FragmentUpdateProfileBinding binding;
    private View view;
    private Dialog dialog;
    UpdateProfile_Presenter presenter;
    User_Data userData;

    String FullName, Email,Phone;
    private Dialog dialogbox;

    public Update_Profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_update__profile, container, false);

        view = binding.getRoot();
        dialog = AppUtils.hideShowProgress(getContext());
        presenter = new UpdateProfile_Presenter(this);

        binding.btnDone.setOnClickListener(this);

        userData = SharedPrefManager.getInstance(getContext()).getLoginDATA();

        if (userData.getUserName()!=null){
            binding.edUserPhone.setText(userData.getPhoneNo());
            binding.edUserName.setText(userData.getUserName());
            binding.edUserEmail.setText(userData.getEmail());
        }else {
            binding.edUserPhone.setHint("Phone No.");
            binding.edUserName.setHint("Full Name ");
            binding.edUserEmail.setText(userData.getEmail());
        }

        String profileImage = MyPreferences.getInstance(getContext()).getString(PrefConf.ProfileImage, null);
        if (profileImage==null){
            Glide.with(getContext()).load(profileImage).apply(new RequestOptions().circleCrop()).placeholder(R.drawable.ic_profile_image).into(binding.imageProfile);

        }else if (!profileImage.equalsIgnoreCase("https://stargazeevents.s3.ap-south-1.amazonaws.com/pfiles/profile.png")){
            Glide.with(getContext()).load(profileImage).apply(new RequestOptions().circleCrop()).placeholder(R.drawable.ic_profile_image).into(binding.imageProfile);

        }else{
            Glide.with(getContext()).load(profileImage).apply(new RequestOptions().circleCrop()).placeholder(R.drawable.ic_profile_image).into(binding.imageProfile);

        }

        return binding.getRoot();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_done:
              setUserData();
                 break;


        }

    }

    public void setUserData() {
         FullName = binding.edUserName.getText().toString();
         Email = binding.edUserEmail.getText().toString();
         Phone = binding.edUserPhone.getText().toString();

        if (FullName.isEmpty()) {
            Sneaker.with(getActivity())
                    .setTitle("Enter Full Name")
                    .setCornerRadius(4)
                    .setDuration(2500)
                    .sneakWarning();
            binding.edUserName.requestFocus();
        } else if (Email.isEmpty()) {
            Sneaker.with(getActivity())
                    .setTitle("Enter Email")
                    .setCornerRadius(4)
                    .setDuration(2500)
                    .sneakWarning();
            binding.edUserEmail.requestFocus();
        }else if(!Validation.isValidEmail(Email)){
            binding.edUserEmail.requestFocus();
            Sneaker.with(getActivity())
                    .setTitle(" Enter a valid email!!!!")
                    .setCornerRadius(4)
                    .setDuration(2500)
                    .sneakWarning();
        } else if (Phone.isEmpty()) {
            Sneaker.with(getActivity())
                    .setTitle("Enter Phone No.")
                    .setCornerRadius(4)
                    .setDuration(2500)
                    .sneakWarning();
            binding.edUserPhone.requestFocus();
        } else {
            SendOtpBody sendOtpBody = new SendOtpBody(Email);
            presenter.SendOTP(getContext(),sendOtpBody);


        }


    }

    private void showRightCustomDialog(String fullName, String email, String phone) {
        dialogbox = new Dialog(getContext());
        dialogbox.setContentView(R.layout.custom_verify_dialogbox);

        TextView text_email = (TextView) dialogbox.findViewById(R.id.text_email);
        TextView text_resend = (TextView) dialogbox.findViewById(R.id.text_resend);
        TextView text_cancel = (TextView) dialogbox.findViewById(R.id.text_cancel);
        TextView text_save = (TextView) dialogbox.findViewById(R.id.text_save);
        PinView enter_otp = (PinView)  dialogbox.findViewById(R.id.enter_otp);

        text_email.setText("OTP has sent to you at your email address \n"+email);
        text_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogbox.dismiss();
            }
        });
        text_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otp = enter_otp.getText().toString();
                UpdateProfileBody updateProfileBody = new UpdateProfileBody(Email,Phone,FullName,otp);
               presenter.UpdateProfile(getContext(),updateProfileBody);
            }
        });

        text_resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendOtpBody sendOtpBody = new SendOtpBody(Email);
                presenter.SendOTP(getContext(),sendOtpBody);
            }
        });


        dialogbox.show();
        dialogbox.setCancelable(false);
        dialogbox.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
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
    public void onOTPSendSuccess(ResponseBody responseBody, String message) {
        Sneaker.with(getActivity())
                .setTitle("OTP Send in Your Email")
                .setCornerRadius(4)
                .setDuration(2500)
                .sneakSuccess();
        showRightCustomDialog(FullName, Email,Phone);
    }

    @Override
    public void onUpdateProfileSuccess(ResponseBody responseBody, String message) {
        if (message.equalsIgnoreCase("ok")){
            User_Data user_data = new User_Data(userData.getId(),Email,userData.getToken(),userData.getReferral_code(),FullName,Phone);
            SharedPrefManager.getInstance(getContext()).SetLoginData(user_data);
            binding.edUserPhone.setText(Phone);
            binding.edUserName.setText(FullName);


            Sneaker.with(getActivity())
                    .setTitle("Successfully Update ")
                    .setCornerRadius(4)
                    .setDuration(2500)
                    .sneakSuccess();
            dialogbox.dismiss();
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