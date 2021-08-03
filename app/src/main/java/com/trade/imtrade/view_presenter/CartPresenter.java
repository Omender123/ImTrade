package com.trade.imtrade.view_presenter;

import android.content.Context;

import com.trade.imtrade.Model.ResponseModel.CartProductResponse;
import com.trade.imtrade.Model.ResponseModel.CartProductResponse;
import com.trade.imtrade.Model.ResponseModel.SaveForLaterResponse;
import com.trade.imtrade.Model.request.AddToCartBody;
import com.trade.imtrade.utils.AppUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartPresenter {
    private CartPresenterView view;

    public CartPresenter(CartPresenterView view) {
        this.view = view;
    }

    public void GetCartProduct(Context context) {
        view.showHideProgress(true);
        Call<CartProductResponse> userCall = AppUtils.getApi(context).GetCartProduct();
        userCall.enqueue(new Callback<CartProductResponse>() {
            @Override
            public void onResponse(Call<CartProductResponse> call, Response<CartProductResponse> response) {
                view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onCartSuccess(response.body(), response.message());
                } else {
                    try {
                        String errorRes = response.errorBody().string();
                        JSONObject object = new JSONObject(errorRes);
                        String err_msg = object.getString("error");
                        view.onError(err_msg);

                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<CartProductResponse> call, Throwable t) {
                view.showHideProgress(false);
                view.onFailure(t);
            }
        });

    }

    public void DeleteCartProduct(Context context, String ProductId) {
        view.showHideProgress(true);
        Call<ResponseBody> userCall = AppUtils.getApi(context).DeleteCartProduct(ProductId);
        userCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onDeleteCartSuccess(response.body(), response.message());
                } else {
                    try {
                        String errorRes = response.errorBody().string();
                        JSONObject object = new JSONObject(errorRes);
                        String err_msg = object.getString("error");
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

    public void IncreaseQuentity(Context context, AddToCartBody addToCartBody){
        view.showHideProgress(true);
        Call<ResponseBody> userCall = AppUtils.getApi(context).AddToCart(addToCartBody);
        userCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onIncreaseQuentitySuccess(response.body(),response.message());
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

    public void SaveForLater(Context context, String ProductId){
        view.showHideProgress(true);
        Call<ResponseBody> userCall = AppUtils.getApi(context).SaveForLater(ProductId);
        userCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onSaveForLaterSuccess(response.body(),response.message());
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

    public void GetSaveForLater(Context context) {
        view.showHideProgress(true);
        Call<SaveForLaterResponse> userCall = AppUtils.getApi(context).GetSaveForLater();
        userCall.enqueue(new Callback<SaveForLaterResponse>() {
            @Override
            public void onResponse(Call<SaveForLaterResponse> call, Response<SaveForLaterResponse> response) {
                view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onGetSaveForLaterSuccess(response.body(), response.message());
                } else {
                    try {
                        String errorRes = response.errorBody().string();
                        JSONObject object = new JSONObject(errorRes);
                        String err_msg = object.getString("error");
                        view.onError(err_msg);

                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<SaveForLaterResponse> call, Throwable t) {
                view.showHideProgress(false);
                view.onFailure(t);
            }
        });

    }


    public interface CartPresenterView {
        void showHideProgress(boolean isShow);

        void onError(String message);

        void onCartSuccess(CartProductResponse cartProductResponse, String message);

        void onDeleteCartSuccess(ResponseBody responseBody, String message);

        void onIncreaseQuentitySuccess(ResponseBody responseBody,String message);

        void onSaveForLaterSuccess(ResponseBody responseBody,String message);

        void onGetSaveForLaterSuccess(SaveForLaterResponse saveForLaterResponse,String message);

        void onFailure(Throwable t);
    }
}
