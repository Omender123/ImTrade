package com.trade.imtrade.view_presenter;

import android.content.Context;

import com.trade.imtrade.Model.ResponseModel.AddProfileResponse;
import com.trade.imtrade.utils.AppUtils;

import org.json.JSONObject;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class ProfilePresenter {
    private ProfileView view;

    public ProfilePresenter(ProfileView view) {
        this.view = view;
    }

    public void uploadImage(Context context,MultipartBody.Part image){
        Call<AddProfileResponse> call = AppUtils.getApi(context).uploadPic(image);
        view.showHideProgress(true);
        call.enqueue(new Callback<AddProfileResponse>() {
            @Override
            public void onResponse(Call<AddProfileResponse> call, Response<AddProfileResponse> response) {
                view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onProfileUpload(response.body());
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
            public void onFailure(Call<AddProfileResponse> call, Throwable t) {
                view.showHideProgress(false);
                view.onFailure(t);
            }
        });

    }

    public interface ProfileView{
        void showHideProgress(boolean isShow);
        void onError(String message);
        void onProfileUpload(AddProfileResponse addProfileResponse);
        void onFailure(Throwable t);
    }
}
