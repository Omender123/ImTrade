package com.trade.imtrade.view_presenter;

import android.content.Context;

import com.trade.imtrade.Model.request.SendOtpBody;
import com.trade.imtrade.Model.request.VerifyOTP_Body;
import com.trade.imtrade.utils.AppUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Send_OTP_Presenter {
    private OTP_SendView view;

    public Send_OTP_Presenter(OTP_SendView view) {
        this.view = view;
    }

    public void SendOTP(SendOtpBody body){
        view.showOTPHideProgress(true);
        Call<ResponseBody> userCall = AppUtils.getApi((Context)view).SendOTP(body);
        userCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                view.showOTPHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onOTPSuccess(response.message());
                } else if (response.code()==400){
                    try {
                        String  errorRes = response.errorBody().string();
                        JSONObject object = new JSONObject(errorRes);
                        String err_msg  = object.getString("body");
                        view.onOTPError(err_msg);

                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                view.showOTPHideProgress(false);
                view.onOTPFailure(t);
            }
        });
    }

    public interface OTP_SendView{
        void showOTPHideProgress(boolean isShow);
        void onOTPError(String message);
        void onOTPSuccess(String message);
        void onOTPFailure(Throwable t);
    }
}
