package com.trade.imtrade.view_presenter;


import android.content.Context;

import com.trade.imtrade.Model.ResponseModel.AllCategoriesResponse;
import com.trade.imtrade.Model.ResponseModel.ProductResponse;
import com.trade.imtrade.utils.AppUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllProductPresenter {

    private GetAllProductView view;

    public AllProductPresenter(GetAllProductView view) {
        this.view = view;
    }


    public void GetAllProduct(Context context,String Category){
        view.showHideProgress(true);
        Call<ProductResponse> userCall = AppUtils.getApi(context).getAllProductDetails(Category);
        userCall.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onAllProductSuccess(response.body(),response.message());
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
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                view.showHideProgress(false);
                view.onFailure(t);
            }
        });

    }


    public interface GetAllProductView{
        void showHideProgress(boolean isShow);
        void onError(String message);
        void onAllProductSuccess(ProductResponse productResponse, String message);
        void onFailure(Throwable t);
    }
}
