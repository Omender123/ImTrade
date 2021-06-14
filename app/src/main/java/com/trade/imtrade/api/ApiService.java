package com.trade.imtrade.api;



import com.trade.imtrade.Model.ResponseModel.LoginResponse;
import com.trade.imtrade.Model.ResponseModel.SignUpResponse;
import com.trade.imtrade.Model.request.LoginBody;
import com.trade.imtrade.Model.request.SendOtpBody;
import com.trade.imtrade.Model.request.SignUpBody;
import com.trade.imtrade.Model.request.VerifyOTP_Body;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService {


    @POST("user/register")
    Call<SignUpResponse> createUser(@Body SignUpBody signUpBody);

    @GET("user/countryCodeList")
    Call<ResponseBody>getCountryCode();

    @POST("user/login")
    Call<LoginResponse>LoginUser(@Body LoginBody loginBody);

    @POST("user/sendOTP")
    Call<ResponseBody>SendOTP(@Body SendOtpBody sendOtpBody);

    @POST("user/emailVerify")
    Call<ResponseBody>VerifyOtp(@Body VerifyOTP_Body verifyOTP_body);



}
