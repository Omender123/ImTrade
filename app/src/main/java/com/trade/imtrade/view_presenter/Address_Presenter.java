package com.trade.imtrade.view_presenter;

import android.content.Context;


import com.trade.imtrade.Model.ResponseModel.AddressResponse;
import com.trade.imtrade.Model.request.AddAddressBody;
import com.trade.imtrade.utils.AppUtils;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Address_Presenter {
    private AddressView view;

    public Address_Presenter(AddressView view) {
        this.view = view;
    }

    public void getCountry(Context context) {
        Call<ResponseBody> call = AppUtils.getApi(context).GetCountry();
        //  view.showHideProgress(true);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //        view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onCountrySuccess(response.body(), response.message());
                } else {
                    try {
                        String errorRes = response.errorBody().string();
                        JSONObject jsonObject = new JSONObject(errorRes);
                        String err_msg = jsonObject.getString("error");
                        view.onError(err_msg);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                //      view.showHideProgress(false);
                view.onFailure(t);
            }
        });

    }

    public void getState(Context context, String id) {
        Call<ResponseBody> call = AppUtils.getApi(context).GetState(id);
        //  view.showHideProgress(true);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //  view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onStateSuccess(response.body(), response.message());
                } else {
                    try {
                        String errorRes = response.errorBody().string();
                        JSONObject jsonObject = new JSONObject(errorRes);
                        String err_msg = jsonObject.getString("error");
                        view.onError(err_msg);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                //  view.showHideProgress(false);
                view.onFailure(t);
            }
        });

    }

    public void getCity(Context context, String id) {
        Call<ResponseBody> call = AppUtils.getApi(context).GetCity(id);
        //  view.showHideProgress(true);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //   view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onCitySuccess(response.body(), response.message());
                } else {
                    try {
                        String errorRes = response.errorBody().string();
                        JSONObject jsonObject = new JSONObject(errorRes);
                        String err_msg = jsonObject.getString("error");
                        view.onError(err_msg);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                //  view.showHideProgress(false);
                view.onFailure(t);
            }
        });

    }

    public void AddAddress(Context context, AddAddressBody addAddressBody) {
        Call<ResponseBody> call = AppUtils.getApi(context).AddUserAddress(addAddressBody);
        view.showHideProgress(true);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onAddAddressSuccess(response.body(), response.message());
                } else {
                    try {
                        String errorRes = response.errorBody().string();
                        JSONObject jsonObject = new JSONObject(errorRes);
                        String err_msg = jsonObject.getString("error");
                        view.onError(err_msg);
                    } catch (Exception ex) {
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

    public void GetAddAddressById(Context context, String id) {
        Call<AddressResponse> call = AppUtils.getApi(context).GetUserAddressById(id);
        view.showHideProgress(true);
        call.enqueue(new Callback<AddressResponse>() {
            @Override
            public void onResponse(Call<AddressResponse> call, Response<AddressResponse> response) {
                view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onGetUserAddressById(response.body(), response.message());
                } else {
                    try {
                        String errorRes = response.errorBody().string();
                        JSONObject jsonObject = new JSONObject(errorRes);
                        String err_msg = jsonObject.getString("error");
                        view.onError(err_msg);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<AddressResponse> call, Throwable t) {
                view.showHideProgress(false);
                view.onFailure(t);
            }
        });

    }

    public void EditAddAddress(Context context,String id, AddAddressBody addAddressBody) {
        Call<AddressResponse> call = AppUtils.getApi(context).EditUserAddressById(id,addAddressBody);
        view.showHideProgress(true);
        call.enqueue(new Callback<AddressResponse>() {
            @Override
            public void onResponse(Call<AddressResponse> call, Response<AddressResponse> response) {
                view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onEditUserAddressById(response.body(), response.message());
                } else {
                    try {
                        String errorRes = response.errorBody().string();
                        JSONObject jsonObject = new JSONObject(errorRes);
                        String err_msg = jsonObject.getString("error");
                        view.onError(err_msg);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<AddressResponse> call, Throwable t) {
                view.showHideProgress(false);
                view.onFailure(t);
            }
        });

    }


    public interface AddressView {
        void showHideProgress(boolean isShow);

        void onError(String message);

        void onCountrySuccess(ResponseBody responseBody, String message);

        void onStateSuccess(ResponseBody responseBody, String message);

        void onCitySuccess(ResponseBody responseBody, String message);

        void onAddAddressSuccess(ResponseBody responseBody, String message);

        void onGetUserAddressById(AddressResponse addressResponse, String message);

        void onEditUserAddressById(AddressResponse addressResponse, String message);


        void onFailure(Throwable t);
    }
}
