package com.trade.imtrade.SharedPrefernce;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.trade.imtrade.Authentication.Login;
import com.trade.imtrade.Model.ResponseModel.LoginResponse;

public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "Prebhat";
    private static final String KEY_ID = "keyid";
    private static final String KEY_EMAIL = "keyemail";
    private static final String KEY_TOKEN = "KEY_TOKEN";
    private static final String KEY_REFERRAL_CODE = "KEY_REFERRAL_CODE";
    private static final String KEY_USERNAME = "KEY_USERNAME";
    private static final String KEY_USERPHONE = "KEY_USERPHONE";
    private static SharedPrefManager mInstance;

    private static Context mCtx;

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    //method to let the user login
    //this method will store the user data in shared preferences
    public void SetLoginData(User_Data user) {

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_ID, user.getId());
         editor.putString(KEY_EMAIL, user.getEmail());
        editor.putString(KEY_TOKEN, user.getToken());
        editor.putString(KEY_REFERRAL_CODE, user.getReferral_code());
        editor.putString(KEY_USERNAME, user.getUserName());
        editor.putString(KEY_USERPHONE, user.getPhoneNo());

        editor.apply();
    }

    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_TOKEN, null) != null;
    }

    //this method will give the logged in user
    public User_Data getLoginDATA() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User_Data(
                sharedPreferences.getString(KEY_ID, null),
                 sharedPreferences.getString(KEY_EMAIL, null),
                sharedPreferences.getString(KEY_TOKEN, null),
                sharedPreferences.getString(KEY_REFERRAL_CODE, null),
                sharedPreferences.getString(KEY_USERNAME,null ),
                sharedPreferences.getString(KEY_USERPHONE,null)

                );
    }
    //this method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        Intent intent = new Intent(mCtx, Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        mCtx.startActivity(intent);


    }
}