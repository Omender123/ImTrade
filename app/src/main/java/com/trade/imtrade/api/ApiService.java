package com.trade.imtrade.api;



import com.trade.imtrade.Model.ResponseModel.AllCategoriesResponse;
import com.trade.imtrade.Model.ResponseModel.BannerResponse;
import com.trade.imtrade.Model.ResponseModel.LoginResponse;
import com.trade.imtrade.Model.ResponseModel.ProductDetailsResponse;
import com.trade.imtrade.Model.ResponseModel.ProductResponse;
import com.trade.imtrade.Model.ResponseModel.SignUpResponse;
import com.trade.imtrade.Model.ResponseModel.SocialLoginResponse;
import com.trade.imtrade.Model.request.ChangePasswordBody;
import com.trade.imtrade.Model.request.LoginBody;
import com.trade.imtrade.Model.request.SendOtpBody;
import com.trade.imtrade.Model.request.SignUpBody;
import com.trade.imtrade.Model.request.SocialLoginBody;
import com.trade.imtrade.Model.request.VerifyOTP_Body;

import java.util.List;

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
import retrofit2.http.Query;

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

    @POST("user/social")
    Call<SocialLoginResponse>SocialLogin(@Body SocialLoginBody socialLoginBody);

    @POST("user/forget-password/update")
    Call<ResponseBody>ChangePassword(@Body ChangePasswordBody passwordBody);

    @GET("banner")
    Call<List<BannerResponse>>getBanner();

    @GET("categories/all")
    Call<List<AllCategoriesResponse>>getAllCategories();

    @GET("products/list")
    Call<ProductResponse>getAllProductDetails(@Query("category") String categoryName);

    @GET("products/route")
    Call<ProductDetailsResponse>getProductFullDetails(@Query("route") String Route);

}
