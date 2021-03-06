package com.trade.imtrade.view_presenter;

import android.content.Context;

import com.trade.imtrade.Model.ResponseModel.UpdateProfileResponse;
import com.trade.imtrade.Model.request.UpdateProfileBody;
import com.trade.imtrade.utils.AppUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity_Presenter {
       private MainActivityView view;

    public MainActivity_Presenter(MainActivityView view) {
        this.view = view;
    }


    public void GetUpdateProfile(Context context){
        view.showHideProgress(true);
        Call<UpdateProfileResponse> userCall = AppUtils.getApi(context).GetUpdateProfile();
        userCall.enqueue(new Callback<UpdateProfileResponse>() {
            @Override
            public void onResponse(Call<UpdateProfileResponse> call, Response<UpdateProfileResponse> response) {
                view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onUpdateProfileSuccess(response.body(),response.message());
                } else if (response.code()==400){
                    try {
                        String  errorRes = response.errorBody().string();
                        JSONObject object = new JSONObject(errorRes);
                        String err_msg  = object.getString("body");
                        view.onError(err_msg);

                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    view.onError(response.message());
                }
            }

            @Override
            public void onFailure(Call<UpdateProfileResponse> call, Throwable t) {
                view.showHideProgress(false);
                view.onFailure(t);
            }
        });
    }

    public void GetCartCount(Context context){
        view.showHideProgress(true);
        Call<ResponseBody> userCall = AppUtils.getApi(context).getCartCount();
        userCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onCartCountSuccess(response.body(),response.message());
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




    public interface MainActivityView{
        void showHideProgress(boolean isShow);
        void onError(String message);
        void onUpdateProfileSuccess(UpdateProfileResponse updateProfileResponse, String message);
        void onCartCountSuccess(ResponseBody responseBody, String message);
        void onFailure(Throwable t);
    }
}
