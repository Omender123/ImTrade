package com.trade.imtrade.Authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.irozon.sneaker.Sneaker;
import com.trade.imtrade.Model.ResponseModel.SignUpResponse;
import com.trade.imtrade.Model.request.SendOtpBody;
import com.trade.imtrade.Model.request.SignUpBody;
import com.trade.imtrade.R;
import com.trade.imtrade.SharedPerfence.MyPreferences;
import com.trade.imtrade.SharedPerfence.PrefConf;
import com.trade.imtrade.databinding.ActivitySignUpBinding;
import com.trade.imtrade.utils.AppUtils;
import com.trade.imtrade.utils.Validation;
import com.trade.imtrade.view_presenter.Send_OTP_Presenter;
import com.trade.imtrade.view_presenter.SignUpPresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import de.mateware.snacky.Snacky;
import okhttp3.ResponseBody;

public class SignUp extends AppCompatActivity implements View.OnClickListener, SignUpPresenter.SignUpView, Send_OTP_Presenter.OTP_SendView {
ActivitySignUpBinding binding;
    private Context context;
    private Dialog dialog;
    private SignUpPresenter presenter;
    private Send_OTP_Presenter presenter1;
    private View view;
    ArrayList<String> code;
    String codeName,referral_code;
    public SignUp() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up);
        binding.cardNext.setOnClickListener(this);
        AppUtils.FullScreen(this);

        code = new ArrayList<String>();

        view = binding.getRoot();
        context = SignUp.this;
        presenter = new SignUpPresenter(this);
        presenter1 = new Send_OTP_Presenter(this);
        dialog = AppUtils.hideShowProgress(context);

        referral_code = MyPreferences.getInstance(this).getString(PrefConf.REFERRAL_CODE,"");
         presenter.getCountryCode();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.card_next:
              doRegister();
                AppUtils.hideKeyboard(v,getApplicationContext());
                AppUtils.FullScreen(this);

                 break;
        }
    }

    private void doRegister() {
        String Email= binding.email.getText().toString();
        String password = binding.password.getText().toString();
        String C_password = binding.CPassword.getText().toString();

       if(Email.isEmpty()){
            binding.email.requestFocus();
            Snacky.builder()
                    .setActivity(SignUp.this)
                    .setText("Please enter Email !")
                    .setTextColor(getResources().getColor(R.color.white))
                    .warning()
                    .show();

        }else if(!Validation.isValidEmail(Email)){
            binding.email.requestFocus();
            Snacky.builder()
                    .setActivity(SignUp.this)
                    .setText("Enter a valid email!")
                    .setTextColor(getResources().getColor(R.color.white))
                    .warning()
                    .show();
        }else if(password.isEmpty()){
            binding.password.requestFocus();
            Snacky.builder()
                    .setActivity(SignUp.this)
                    .setText("Please enter Password !")
                    .setTextColor(getResources().getColor(R.color.white))
                    .warning()
                    .show();
        }else if(C_password.isEmpty()){
           binding.CPassword.requestFocus();
           Snacky.builder()
                   .setActivity(SignUp.this)
                   .setText("Please enter Confirm Password !")
                   .setTextColor(getResources().getColor(R.color.white))
                   .warning()
                   .show();
       }else if(!C_password.equals(password)){
           Snacky.builder()
                   .setActivity(SignUp.this)
                   .setText("Password not Match !!!!!!!!!")
                   .setTextColor(getResources().getColor(R.color.white))
                   .warning()
                   .show();
       }else {
            AppUtils.FullScreen(this);
           SignUpBody user = new SignUpBody(Email,referral_code,password);
             presenter.createSignUp_User(user);

         //  startActivity(new Intent(getApplicationContext(),Email_Verify.class));

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
        Sneaker.with(this)
                .setTitle(message)
                .setMessage("")
                .setCornerRadius(4)
                .setDuration(1500)
                .sneakError();
    }

    @Override
    public void onSignUpSuccess(SignUpResponse response, String message) {
        MyPreferences.getInstance(SignUp.this).putString(PrefConf.User_ID,response.getResult().getId());
        MyPreferences.getInstance(SignUp.this).putString(PrefConf.EMAIL,response.getResult().getEmail());
        if (message.equalsIgnoreCase("ok")){
            SendOtpBody sendOtpBody = new SendOtpBody(response.getResult().getEmail());
           presenter1.SendOTP(sendOtpBody);
            Sneaker.with(this)
                    .setTitle(response.getMessage())
                    .setMessage("")
                    .setCornerRadius(4)
                    .setDuration(1500)
                    .sneakSuccess();
        }

    }

    @Override
    public void onCountryCodeSuccess(ResponseBody responseBody, String message) {
        if (message.equalsIgnoreCase("ok")){
            String  s=null;
            try {
               s  = responseBody.string();

                JSONObject object = new JSONObject(s);

                String result = object.getString("result");

                JSONArray jsonArray = new JSONArray(result);

                for (int i = 0; i <= jsonArray.length(); i++) {
                    code.add(jsonArray.getString(i));
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            ArrayAdapter<String> adp = new ArrayAdapter<String>(SignUp.this, R.layout.spinner_list, code);
          //  binding.countryCode.setAdapter(adp);

            //  adp.setDropDownViewResource(R.layout.spinner_list);

           /* binding.countryCode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    codeName = parent.getItemAtPosition(position).toString();
                    // Toast.makeText(parent.getContext(), city, Toast.LENGTH_LONG).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });*/
        }

    }

    @Override
    public void onFailure(Throwable t) {
        Sneaker.with(this)
                .setTitle(t.getLocalizedMessage())
                .setMessage("")
                .setCornerRadius(4)
                .setDuration(1500)
                .sneakError();
    }

    @Override
    public void showOTPHideProgress(boolean isShow) {
        if (isShow){
            dialog.show();
        }else{
            dialog.dismiss();
        }
    }

    @Override
    public void onOTPError(String message) {
        Sneaker.with(this)
                .setTitle(message)
                .setMessage("")
                .setCornerRadius(4)
                .setDuration(1500)
                .sneakError();
    }

    @Override
    public void onOTPSuccess(String message) {
        if (message.equalsIgnoreCase("ok")){
            MyPreferences.getInstance(SignUp.this).putString(PrefConf.TYPE1,"SignUp");

            startActivity(new Intent(getApplicationContext(),Email_Verify.class));


            Toast.makeText(context, "OTP send in Email", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onOTPFailure(Throwable t) {
        Sneaker.with(this)
                .setTitle(t.getLocalizedMessage())
                .setMessage("")
                .setCornerRadius(4)
                .setDuration(1500)
                .sneakError();
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppUtils.FullScreen(this);
    }
}