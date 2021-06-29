package com.trade.imtrade.view_presenter;

import android.content.Context;

import com.trade.imtrade.Model.ResponseModel.BannerResponse;
import com.trade.imtrade.Model.request.VerifyOTP_Body;
import com.trade.imtrade.utils.AppUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home_Presenter {
private HomeView view;

    public Home_Presenter(HomeView view) {
        this.view = view;
    }


    public void GetBanner(Context context){
        view.showHideProgress(true);
        Call<List<BannerResponse>> userCall = AppUtils.getApi(context).getBanner();
        userCall.enqueue(new Callback<List<BannerResponse>>() {
            @Override
            public void onResponse(Call<List<BannerResponse>> call, Response<List<BannerResponse>> response) {
                view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onBannerSuccess(response.body(),response.message());
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
            public void onFailure(Call<List<BannerResponse>> call, Throwable t) {
                view.showHideProgress(false);
                view.onFailure(t);
            }
        });
    }


    public interface HomeView{
        void showHideProgress(boolean isShow);
        void onError(String message);
        void onBannerSuccess(List<BannerResponse>bannerResponses,String message);
        void onFailure(Throwable t);
    }
}
