package com.trade.imtrade.view_presenter;

import android.content.Context;

import com.trade.imtrade.Model.ResponseModel.ProductDetailsResponse;
import com.trade.imtrade.Model.ResponseModel.ProductDetailsResponse;
import com.trade.imtrade.utils.AppUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProductDetails_Presenter {
    private GetProductDetailsView view;

    public ProductDetails_Presenter(GetProductDetailsView view) {
        this.view = view;
    }

    public void GetProductDetails(Context context, String Route){
        view.showHideProgress(true);
        Call<ProductDetailsResponse> userCall = AppUtils.getApi(context).getProductFullDetails(Route);
        userCall.enqueue(new Callback<ProductDetailsResponse>() {
            @Override
            public void onResponse(Call<ProductDetailsResponse> call, Response<ProductDetailsResponse> response) {
                view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onProductDetailsSuccess(response.body(),response.message());
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
            public void onFailure(Call<ProductDetailsResponse> call, Throwable t) {
                view.showHideProgress(false);
                view.onFailure(t);
            }
        });

    }


    public interface GetProductDetailsView{
        void showHideProgress(boolean isShow);
        void onError(String message);
        void onProductDetailsSuccess(ProductDetailsResponse productDetailsResponse, String message);
        void onFailure(Throwable t);
    }
}
