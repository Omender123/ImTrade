package com.trade.imtrade.view_presenter;

import android.content.Context;

import com.trade.imtrade.Model.request.ChangeEmailBody;
import com.trade.imtrade.Model.request.SendNewEmailOtpBody;
import com.trade.imtrade.Model.request.SendOtpBody;
import com.trade.imtrade.Model.request.UpdateProfileBody;
import com.trade.imtrade.utils.AppUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangeEmailPresenter {
    private ChangeEmailView view;

    public ChangeEmailPresenter(ChangeEmailView view) {
        this.view = view;
    }

    public void SendOldEmailOTP(Context context, SendOtpBody body){
        view.showHideProgress(true);
        Call<ResponseBody> userCall = AppUtils.getApi(context).SendOTP(body);
        userCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onOTPSendOldSuccess(response.body(),response.message());
                } else if (response.code()==400){
                    try {
                        String  errorRes = response.errorBody().string();
                        JSONObject object = new JSONObject(errorRes);
                        String err_msg  = object.getString("error");
                        view.onError(err_msg);

                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    view.onError(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                view.showHideProgress(false);
                view.onFailure(t);
            }
        });
    }
    public void SendNewEmailOTP(Context context, SendNewEmailOtpBody body){
        view.showHideProgress(true);
        Call<ResponseBody> userCall = AppUtils.getApi(context).SendNewOTP(body);
        userCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onOTPSendNewSuccess(response.body(),response.message());
                } else if (response.code()==400){
                    try {
                        String  errorRes = response.errorBody().string();
                        JSONObject object = new JSONObject(errorRes);
                        String err_msg  = object.getString("error");
                        view.onError(err_msg);

                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    view.onError(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                view.showHideProgress(false);
                view.onFailure(t);
            }
        });
    }

    public void ChangeEmail(Context context, ChangeEmailBody body){
        view.showHideProgress(true);
        Call<ResponseBody> userCall = AppUtils.getApi(context).ChangeEmail(body);
        userCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onChangeEmailSuccess(response.body(),response.message());
                } else if (response.code()==400){
                    try {
                        String  errorRes = response.errorBody().string();
                        JSONObject object = new JSONObject(errorRes);
                        String err_msg  = object.getString("error");
                        view.onError(err_msg);

                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    view.onError(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                view.showHideProgress(false);
                view.onFailure(t);
            }
        });
    }


    public interface ChangeEmailView{
        void showHideProgress(boolean isShow);
        void onError(String message);
        void onOTPSendOldSuccess(ResponseBody responseBody,String message);
        void onOTPSendNewSuccess(ResponseBody responseBody,String message);
        void onChangeEmailSuccess(ResponseBody responseBody,String message);
        void onFailure(Throwable t);
    }

}
