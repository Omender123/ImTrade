package com.trade.imtrade.view_presenter;

import android.content.Context;

import com.trade.imtrade.Model.request.SendOtpBody;
import com.trade.imtrade.Model.request.ChangePasswordBody;
import com.trade.imtrade.utils.AppUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassword_Presenter {
    private ChangePasswordView view;

    public ChangePassword_Presenter(ChangePasswordView view) {
        this.view = view;
    }

    public void SendOTP(Context context, SendOtpBody body){
        view.showHideProgress(true);
        Call<ResponseBody> userCall = AppUtils.getApi(context).SendOTP(body);
        userCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onOTPSendSuccess(response.body(),response.message());
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
    public void ChangePassword(Context context, ChangePasswordBody body){
        view.showHideProgress(true);
        Call<ResponseBody> userCall = AppUtils.getApi(context).ChangePassword(body);
        userCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onChangePasswordSuccess(response.body(),response.message());
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


    public interface ChangePasswordView{
        void showHideProgress(boolean isShow);
        void onError(String message);
        void onOTPSendSuccess(ResponseBody responseBody,String message);
        void onChangePasswordSuccess(ResponseBody responseBody,String message);
        void onFailure(Throwable t);
    }

}
