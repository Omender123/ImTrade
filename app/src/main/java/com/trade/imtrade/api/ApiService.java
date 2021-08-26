package com.trade.imtrade.api;


import com.trade.imtrade.Model.ResponseModel.AddProfileResponse;
import com.trade.imtrade.Model.ResponseModel.AllCategoriesResponse;
import com.trade.imtrade.Model.ResponseModel.BannerResponse;
import com.trade.imtrade.Model.ResponseModel.CartProductResponse;
import com.trade.imtrade.Model.ResponseModel.ContinueYourHuntResponse;
import com.trade.imtrade.Model.ResponseModel.CustomerQuestionsResponse;
import com.trade.imtrade.Model.ResponseModel.DealofDayResponse;
import com.trade.imtrade.Model.ResponseModel.HotelAndCoffeeResponse;
import com.trade.imtrade.Model.ResponseModel.LoginResponse;
import com.trade.imtrade.Model.ResponseModel.HomeProductResponse;
import com.trade.imtrade.Model.ResponseModel.ProductDetailsResponse;
import com.trade.imtrade.Model.ResponseModel.ProductResponse;
import com.trade.imtrade.Model.ResponseModel.SaveForLaterResponse;
import com.trade.imtrade.Model.ResponseModel.SignUpResponse;
import com.trade.imtrade.Model.ResponseModel.SocialLoginResponse;
import com.trade.imtrade.Model.ResponseModel.StoriesResponse;
import com.trade.imtrade.Model.ResponseModel.UpdateProfileResponse;
import com.trade.imtrade.Model.request.AddToCartBody;
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

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
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


    @GET("products/popularProducts")
    Call<List<HomeProductResponse>> getAllPopularProduct();

    @GET("products/DealOfTheDay")
    Call<List<HomeProductResponse>> getAllDealOfTheDay();


    @GET("products/sesionProducts")
    Call<List<HomeProductResponse>> getSeasonProduct();

    @GET("products/hb-recommended")
    Call<List<HomeProductResponse>> getReCommendedProduct();

    @GET("products/freshArrival")
    Call<List<HomeProductResponse>> getFreshArrival();

    @GET("products/sponsoredProduct")
    Call<List<HomeProductResponse>> getSponsoredProduct();

    @GET("products/festivalOffer")
    Call<List<HomeProductResponse>> getFestivalOfferProduct();

    @GET("products/dashboardDod")
    Call<List<DealofDayResponse>> getHometDealofDayProduct();


    @PUT("user")
    Call<ResponseBody> UpdateProfile(@Body UpdateProfileBody updateProfileBody);

    @GET("user")
    Call<UpdateProfileResponse> GetUpdateProfile();

    @PUT("user/updateEmail")
    Call<ResponseBody> ChangeEmail(@Body ChangeEmailBody changeEmailBody);

    @POST("user/change-password")
    Call<ResponseBody> ChangePassword(@Body ChangePasswordBody passwordBody);

    @Multipart
    @PUT("user/addProfile")
    Call<AddProfileResponse> uploadPic(@Part MultipartBody.Part image);

    @POST("cart")
    Call<ResponseBody> AddToCart(@Body AddToCartBody addToCartBody);

    @GET("cart")
    Call<CartProductResponse> GetCartProduct();

    @DELETE("cart")
    Call<ResponseBody> DeleteCartProduct(@Query("id") String ProductId);

    @PUT("cart/updateProduct")
    Call<ResponseBody> IncreaseQuantity(@Body AddToCartBody addToCartBody);

    @POST("saveForLater")
    Call<ResponseBody> SaveForLater(@Query("id") String ProductId);

    @GET("saveForLater")
    Call<SaveForLaterResponse> GetSaveForLater();

    @DELETE("saveForLater")
    Call<ResponseBody> DeleteSaveForLaterProduct(@Query("id") String ProductId);

    @POST("saveForLater/moveTocart")
    Call<ResponseBody> moveToCart(@Query("id") String ProductId);

    @GET("categories/discountAll")
    Call<List<AllCategoriesResponse>> getAllDiscountCategories();

    @GET("categories/usableAllCategories")
    Call<List<AllCategoriesResponse>> getAllDailyUsableCategories();

    @GET("WOTW")
    Call<List<AllCategoriesResponse>> getAllWinnerOfThisWeek();

    @GET("CYH")
    Call<ContinueYourHuntResponse> getAllContinueYourHunt();

    @GET("story")
    Call<List<StoriesResponse>> getAllStories();

    @FormUrlEncoded
    @POST("coffee/locate")
    Call<List<HotelAndCoffeeResponse>> getAllCoffee(@Field("pinCode") String pinCode);

    @FormUrlEncoded
    @POST("hotel/locate")
    Call<List<HotelAndCoffeeResponse>> getAllHotel(@Field("pinCode") String pinCode);

    @GET("qsns")
    Call<List<CustomerQuestionsResponse>> getAllCustomerQuestions(@Query("id") String ProductId);

    @GET("cart/count")
    Call<ResponseBody> getCartCount();


}
