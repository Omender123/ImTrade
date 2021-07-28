package com.trade.imtrade.api;


import com.trade.imtrade.Model.ResponseModel.AllCategoriesResponse;
import com.trade.imtrade.Model.ResponseModel.BannerResponse;
import com.trade.imtrade.Model.ResponseModel.BrandsResponse;
import com.trade.imtrade.Model.ResponseModel.LoginResponse;
import com.trade.imtrade.Model.ResponseModel.HomeProductResponse;
import com.trade.imtrade.Model.ResponseModel.ProductDetailsResponse;
import com.trade.imtrade.Model.ResponseModel.ProductResponse;
import com.trade.imtrade.Model.ResponseModel.SignUpResponse;
import com.trade.imtrade.Model.ResponseModel.SocialLoginResponse;
import com.trade.imtrade.Model.ResponseModel.UpdateProfileResponse;
import com.trade.imtrade.Model.request.ChangeEmailBody;
import com.trade.imtrade.Model.request.ChangePasswordBody;
import com.trade.imtrade.Model.request.ForgetPasswordBody;
import com.trade.imtrade.Model.request.LoginBody;
import com.trade.imtrade.Model.request.SendNewEmailOtpBody;
import com.trade.imtrade.Model.request.SendOtpBody;
import com.trade.imtrade.Model.request.SignUpBody;
import com.trade.imtrade.Model.request.SocialLoginBody;
import com.trade.imtrade.Model.request.UpdateProfileBody;
import com.trade.imtrade.Model.request.VerifyOTP_Body;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ApiService {

    @POST("user/register")
    Call<SignUpResponse> createUser(@Body SignUpBody signUpBody);

    @GET("user/countryCodeList")
    Call<ResponseBody> getCountryCode();

    @POST("user/login")
    Call<LoginResponse> LoginUser(@Body LoginBody loginBody);

    @POST("user/sendOTP")
    Call<ResponseBody> SendOTP(@Body SendOtpBody sendOtpBody);

    @POST("user/sendOTP")
    Call<ResponseBody> SendNewOTP(@Body SendNewEmailOtpBody sendNewEmailOtpBody);


    @POST("user/emailVerify")
    Call<ResponseBody> VerifyOtp(@Body VerifyOTP_Body verifyOTP_body);

    @POST("user/social")
    Call<SocialLoginResponse> SocialLogin(@Body SocialLoginBody socialLoginBody);

    @POST("user/forget-password/update")
    Call<ResponseBody> ForgetPassword(@Body ForgetPasswordBody passwordBody);

    @GET("banner")
    Call<List<BannerResponse>> getBanner();

    @GET("categories/all")
    Call<List<AllCategoriesResponse>> getAllCategories();

    @GET("products/list")
    Call<ProductResponse> getAllProductDetails(@Query("category") String categoryName);

    @GET("products/route")
    Call<ProductDetailsResponse> getProductFullDetails(@Query("route") String Route);

    @GET("brand/header")
    Call<List<BrandsResponse>> getAllBrands();

    @GET("products/popularProducts")
    Call<List<HomeProductResponse>> getAllPopularProduct();

    @GET("products/DealOfTheDay")
    Call<List<HomeProductResponse>> getAllDealOfTheDay();

    @GET("products/discountForYou")
    Call<List<HomeProductResponse>> getDiscountForYou();

    @GET("products/sesionProducts")
    Call<List<HomeProductResponse>> getSeasonProduct();

    @GET("products/hb-recommended")
    Call<List<HomeProductResponse>> getReCommendedProduct();

    @PUT("user")
    Call<ResponseBody>UpdateProfile(@Body UpdateProfileBody updateProfileBody);

    @GET("user")
    Call<UpdateProfileResponse>GetUpdateProfile();

    @PUT("user/updateEmail")
    Call<ResponseBody>ChangeEmail(@Body ChangeEmailBody changeEmailBody);

    @POST("user/change-password")
    Call<ResponseBody>ChangePassword(@Body ChangePasswordBody passwordBody);


}
