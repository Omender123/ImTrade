package com.trade.imtrade.view_presenter;

import android.content.Context;

import com.trade.imtrade.Model.ResponseModel.BuyNowResponse;
import com.trade.imtrade.Model.ResponseModel.BuyNowResponse;
import com.trade.imtrade.Model.request.BuyNowRequest;
import com.trade.imtrade.utils.AppUtils;

import org.json.JSONObject;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderSummary_Presenter {
    
    private OrderSummaryView view;

    public OrderSummary_Presenter(OrderSummaryView view) {
        this.view = view;
    }

    public void BuyNowOneProduct(Context context, BuyNowRequest buyNowRequest){
        Call<BuyNowResponse> call = AppUtils.getApi(context).BuyNowOneOrder(buyNowRequest);
        view.showHideProgress(true);
        call.enqueue(new Callback<BuyNowResponse>() {
            @Override
            public void onResponse(Call<BuyNowResponse> call, Response<BuyNowResponse> response) {
                view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onBuyNowOneProductSuccess(response.body(),response.message());
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
            public void onFailure(Call<BuyNowResponse> call, Throwable t) {
                view.showHideProgress(false);
                view.onFailure(t);
            }
        });

    }

    public interface OrderSummaryView{
        void showHideProgress(boolean isShow);
        void onError(String message);
        void onBuyNowOneProductSuccess(BuyNowResponse buyNowResponse,String message);
        void onFailure(Throwable t);
    }
}
