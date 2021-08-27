package com.trade.imtrade.view_presenter;

import android.content.Context;

import com.trade.imtrade.Model.ResponseModel.CustomerQuestionsResponse;
import com.trade.imtrade.Model.ResponseModel.OfferResponse;
import com.trade.imtrade.Model.ResponseModel.ProductDetailsResponse;
import com.trade.imtrade.Model.ResponseModel.ProductDetailsResponse;
import com.trade.imtrade.Model.request.AddToCartBody;
import com.trade.imtrade.utils.AppUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProductDetails_Presenter {
    private GetProductDetailsView view;

    public ProductDetails_Presenter(GetProductDetailsView view) {
        this.view = view;
    }

    public void GetProductDetails(Context context, String Route){
      //  view.showHideProgress(true);
        Call<ProductDetailsResponse> userCall = AppUtils.getApi(context).getProductFullDetails(Route);
        userCall.enqueue(new Callback<ProductDetailsResponse>() {
            @Override
            public void onResponse(Call<ProductDetailsResponse> call, Response<ProductDetailsResponse> response) {
               // view.showHideProgress(false);
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
              //  view.showHideProgress(false);
                view.onFailure(t);
            }
        });

    }
    public void AddToCart(Context context, AddToCartBody addToCartBody){
        view.showHideProgress(true);
        Call<ResponseBody> userCall = AppUtils.getApi(context).AddToCart(addToCartBody);
        userCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onAddToCartSuccess(response.body(),response.message());
                } else {
                    try {
                        String  errorRes = response.errorBody().string();
                        JSONObject object = new JSONObject(errorRes);
                        String err_msg  = object.getString("error");
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
    public void GetAllCustomerQuestions(Context context, String ProductId){
  //      view.showHideProgress(true);
        Call<List<CustomerQuestionsResponse>> userCall = AppUtils.getApi(context).getAllCustomerQuestions(ProductId);
        userCall.enqueue(new Callback<List<CustomerQuestionsResponse>>() {
            @Override
            public void onResponse(Call<List<CustomerQuestionsResponse>> call, Response<List<CustomerQuestionsResponse>> response) {
             //   view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onCustomerQuestionsSuccess(response.body(),response.message());
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
            public void onFailure(Call<List<CustomerQuestionsResponse>> call, Throwable t) {
              //  view.showHideProgress(false);
                view.onFailure(t);
            }
        });

    }

    public void GetAllOFFER(Context context){
      //  view.showHideProgress(true);
        Call<List<OfferResponse>> userCall = AppUtils.getApi(context).getAllOffer();
        userCall.enqueue(new Callback<List<OfferResponse>>() {
            @Override
            public void onResponse(Call<List<OfferResponse>> call, Response<List<OfferResponse>> response) {
                view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onOfferSuccess(response.body(),response.message());
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
            public void onFailure(Call<List<OfferResponse>> call, Throwable t) {
             //   view.showHideProgress(false);
                view.onFailure(t);
            }
        });

    }


    public interface GetProductDetailsView{
        void showHideProgress(boolean isShow);
        void onError(String message);
        void onProductDetailsSuccess(ProductDetailsResponse productDetailsResponse, String message);
        void onAddToCartSuccess(ResponseBody responseBody, String message);
        void onCustomerQuestionsSuccess(List<CustomerQuestionsResponse>customerQuestionsResponses, String message);
        void onOfferSuccess(List<OfferResponse>offerResponses, String message);

        void onFailure(Throwable t);
    }
}
