package com.trade.imtrade.view_presenter;

import android.content.Context;

import com.trade.imtrade.Model.ResponseModel.AllCategoriesResponse;
import com.trade.imtrade.Model.ResponseModel.BannerResponse;
import com.trade.imtrade.utils.AppUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetAllCategories_Presenter {

    private GetAllCategoriesView view;

    public GetAllCategories_Presenter(GetAllCategoriesView view) {
        this.view = view;
    }

    public void GetAllCategories(Context context){
        view.showHideProgress(true);
        Call<List<AllCategoriesResponse>> userCall = AppUtils.getApi(context).getAllCategories();
        userCall.enqueue(new Callback<List<AllCategoriesResponse>>() {
            @Override
            public void onResponse(Call<List<AllCategoriesResponse>> call, Response<List<AllCategoriesResponse>> response) {
                view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onAllCategoriesSuccess(response.body(),response.message());
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
            public void onFailure(Call<List<AllCategoriesResponse>> call, Throwable t) {
                view.showHideProgress(false);
                view.onFailure(t);
            }
        });

    }


    public interface GetAllCategoriesView{
        void showHideProgress(boolean isShow);
        void onError(String message);
        void onAllCategoriesSuccess(List<AllCategoriesResponse>allCategoriesResponses, String message);
        void onFailure(Throwable t);
    }
}
