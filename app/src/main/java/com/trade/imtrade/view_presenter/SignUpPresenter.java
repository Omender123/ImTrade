package com.trade.imtrade.view_presenter;

import android.content.Context;

import com.trade.imtrade.Model.ResponseModel.SignUpResponse;
import com.trade.imtrade.Model.request.SignUpBody;
import com.trade.imtrade.utils.AppUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpPresenter {

    private SignUpView view;

    public SignUpPresenter(SignUpView view) {
        this.view = view;
    }

    public void createSignUp_User(SignUpBody user){
        view.showHideProgress(true);
        Call<SignUpResponse> userCall = AppUtils.getApi((Context)view).createUser(user);
        userCall.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onSignUpSuccess(response.body(),response.message());
                } else if (response.code()==400){
                    try {
                        String  errorRes = response.errorBody().string();
                        JSONObject object = new JSONObject(errorRes);
                        String err_msg  = object.getString("body");
                        view.onError(err_msg);

                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                view.showHideProgress(false);
                view.onFailure(t);
            }
        });
    }

    public void getCountryCode(){
        view.showHideProgress(true);
        Call<ResponseBody> getCountry_code = AppUtils.getApi((Context)view).getCountryCode();
        getCountry_code.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onCountryCodeSuccess(response.body(),response.message());
                } else if (response.code()==400){
                    try {
                        String  errorRes = response.errorBody().string();
                        JSONObject object = new JSONObject(errorRes);
                        String err_msg  = object.getString("body");
                        view.onError(err_msg);

                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                view.showHideProgress(false);
                view.onFailure(t);
            }
        });
    }


    public interface SignUpView{
        void showHideProgress(boolean isShow);
        void onError(String message);
        void onSignUpSuccess(SignUpResponse response, String message);
        void onCountryCodeSuccess(ResponseBody responseBody, String message);
        void onFailure(Throwable t);
    }
}
