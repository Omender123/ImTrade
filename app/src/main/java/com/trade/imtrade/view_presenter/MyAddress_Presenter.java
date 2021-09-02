package com.trade.imtrade.view_presenter;

import android.content.Context;

import com.trade.imtrade.Model.ResponseModel.AddressResponse;
import com.trade.imtrade.utils.AppUtils;

import org.json.JSONObject;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyAddress_Presenter {
    private MyAddressView view;

    public MyAddress_Presenter(MyAddressView view) {
        this.view = view;
    }

    public void getUserAddress(Context context){
        Call<List<AddressResponse>> call = AppUtils.getApi(context).GetUserAddress();
        view.showHideProgress(true);
        call.enqueue(new Callback<List<AddressResponse>>() {
            @Override
            public void onResponse(Call<List<AddressResponse>> call, Response<List<AddressResponse>> response) {
                view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onGetAddressSuccess(response.body(),response.message());
                } else {
                    try {
                        String errorRes = response.errorBody().string();
                        JSONObject jsonObject =new JSONObject(errorRes);
                        String err_msg  = jsonObject.getString("error");
                        view.onError(err_msg);
                    }
                    catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<AddressResponse>> call, Throwable t) {
                view.showHideProgress(false);
                view.onFailure(t);
            }
        });

    }
    public void DeleteUserAddress(Context context,String id){
        Call<ResponseBody> call = AppUtils.getApi(context).DeleteUserAddress(id);
        view.showHideProgress(true);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onDeleteAddressSuccess(response.body(),response.message());
                } else {
                    try {
                        String errorRes = response.errorBody().string();
                        JSONObject jsonObject =new JSONObject(errorRes);
                        String err_msg  = jsonObject.getString("error");
                        view.onError(err_msg);
                    }
                    catch (Exception ex){
                        ex.printStackTrace();
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

    public interface MyAddressView{
        void showHideProgress(boolean isShow);
        void onError(String message);
        void onGetAddressSuccess(List<AddressResponse> addressResponses, String message);
        void onDeleteAddressSuccess(ResponseBody responseBody, String message);

        void onFailure(Throwable t);
    }
}
