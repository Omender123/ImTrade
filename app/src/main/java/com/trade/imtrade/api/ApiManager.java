package com.trade.imtrade.api;

import android.content.Context;


import com.trade.imtrade.SharedPrefernce.SharedPrefManager;
import com.trade.imtrade.SharedPrefernce.User_Data;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {

    private static final String BASE_URl = "http://35.154.44.155:8081/api/";

    private static Retrofit retrofit = null;
    private static OkHttpClient okHttpClient;
    private static int REQUEST_TIMEOUT = 30;
    private static String token;
    private static User_Data user_data;

    public static Retrofit getRetrofit(Context context) {
      //  token = MyPreferences.getInstance(context).getString(PrefConf.KEY_LOGIN_TOKEN, "");
        user_data = SharedPrefManager.getInstance(context).getLoginDATA();
        token = user_data.getToken();
        if (okHttpClient == null)
            initOkHttp(context);

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }

    private static void initOkHttp(Context context) {

        OkHttpClient.Builder httpClient = new OkHttpClient().newBuilder()
                .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClient.addInterceptor(interceptor);

        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Request.Builder requestBuilder = original.newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", "bearer " +token);

            Request request = requestBuilder.build();
            return chain.proceed(request);
        });

        okHttpClient = httpClient.build();
    }

}
