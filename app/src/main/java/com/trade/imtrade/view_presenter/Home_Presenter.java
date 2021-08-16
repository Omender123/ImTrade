package com.trade.imtrade.view_presenter;

import android.content.Context;

import com.trade.imtrade.Model.ResponseModel.AllCategoriesResponse;
import com.trade.imtrade.Model.ResponseModel.BannerResponse;
import com.trade.imtrade.Model.ResponseModel.BrandsResponse;
import com.trade.imtrade.Model.ResponseModel.HomeProductResponse;
import com.trade.imtrade.utils.AppUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home_Presenter {
    private HomeView view;

    public Home_Presenter(HomeView view) {
        this.view = view;
    }


    public void GetBanner(Context context) {
        view.showHideProgress(true);
        Call<List<BannerResponse>> userCall = AppUtils.getApi(context).getBanner();
        userCall.enqueue(new Callback<List<BannerResponse>>() {
            @Override
            public void onResponse(Call<List<BannerResponse>> call, Response<List<BannerResponse>> response) {
                view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onBannerSuccess(response.body(), response.message());
                } else if (response.code() == 400 || response.code() == 401) {
                    try {
                        String errorRes = response.errorBody().string();
                        JSONObject object = new JSONObject(errorRes);
                        String err_msg = object.getString("body");
                        view.onError(err_msg);

                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    view.onError(response.message());
                }
            }

            @Override
            public void onFailure(Call<List<BannerResponse>> call, Throwable t) {
                view.showHideProgress(false);
                view.onFailure(t);
            }
        });
    }

    public void GetAllCategories(Context context) {
        view.showHideProgress(true);
        Call<List<AllCategoriesResponse>> userCall = AppUtils.getApi(context).getAllCategories();
        userCall.enqueue(new Callback<List<AllCategoriesResponse>>() {
            @Override
            public void onResponse(Call<List<AllCategoriesResponse>> call, Response<List<AllCategoriesResponse>> response) {
                view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onAllCategoriesSuccess(response.body(), response.message());
                } else if (response.code() == 400 || response.code() == 401) {
                    try {
                        String errorRes = response.errorBody().string();
                        JSONObject object = new JSONObject(errorRes);
                        String err_msg = object.getString("body");
                        view.onError(err_msg);

                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    view.onError(response.message());
                }
            }

            @Override
            public void onFailure(Call<List<AllCategoriesResponse>> call, Throwable t) {
                view.showHideProgress(false);
                view.onFailure(t);
            }
        });

    }

    public void GetAllBrands(Context context) {
        view.showHideProgress(true);
        Call<List<BrandsResponse>> userCall = AppUtils.getApi(context).getAllBrands();
        userCall.enqueue(new Callback<List<BrandsResponse>>() {
            @Override
            public void onResponse(Call<List<BrandsResponse>> call, Response<List<BrandsResponse>> response) {
                view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onAllBrandsSuccess(response.body(), response.message());
                } else if (response.code() == 400 || response.code() == 401) {
                    try {
                        String errorRes = response.errorBody().string();
                        JSONObject object = new JSONObject(errorRes);
                        String err_msg = object.getString("body");
                        view.onError(err_msg);

                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    view.onError(response.message());
                }
            }

            @Override
            public void onFailure(Call<List<BrandsResponse>> call, Throwable t) {
                view.showHideProgress(false);
                view.onFailure(t);
            }
        });

    }

    public void GetAllPopularProduct(Context context) {
        view.showHideProgress(true);
        Call<List<HomeProductResponse>> userCall = AppUtils.getApi(context).getAllPopularProduct();
        userCall.enqueue(new Callback<List<HomeProductResponse>>() {
            @Override
            public void onResponse(Call<List<HomeProductResponse>> call, Response<List<HomeProductResponse>> response) {
                view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onAllPopularProductSuccess(response.body(), response.message());
                } else if (response.code() == 400 || response.code() == 401) {
                    try {
                        String errorRes = response.errorBody().string();
                        JSONObject object = new JSONObject(errorRes);
                        String err_msg = object.getString("body");
                        view.onError(err_msg);

                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    view.onError(response.message());
                }
            }

            @Override
            public void onFailure(Call<List<HomeProductResponse>> call, Throwable t) {
                view.showHideProgress(false);
                view.onFailure(t);
            }
        });

    }

    public void GetDealOfTheDay(Context context) {
        view.showHideProgress(true);
        Call<List<HomeProductResponse>> userCall = AppUtils.getApi(context).getAllDealOfTheDay();
        userCall.enqueue(new Callback<List<HomeProductResponse>>() {
            @Override
            public void onResponse(Call<List<HomeProductResponse>> call, Response<List<HomeProductResponse>> response) {
                view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onDealOfTheDaySuccess(response.body(), response.message());
                } else if (response.code() == 400 || response.code() == 401) {
                    try {
                        String errorRes = response.errorBody().string();
                        JSONObject object = new JSONObject(errorRes);
                        String err_msg = object.getString("body");
                        view.onError(err_msg);

                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    view.onError(response.message());

                }
            }

            @Override
            public void onFailure(Call<List<HomeProductResponse>> call, Throwable t) {
                view.showHideProgress(false);
                view.onFailure(t);
            }
        });

    }

    public void GetContinueHuntYou(Context context) {
        view.showHideProgress(true);
        Call<List<HomeProductResponse>> userCall = AppUtils.getApi(context).getGetContinueHuntYou();
        userCall.enqueue(new Callback<List<HomeProductResponse>>() {
            @Override
            public void onResponse(Call<List<HomeProductResponse>> call, Response<List<HomeProductResponse>> response) {
                view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onContinueHuntYouSuccess(response.body(), response.message());
                } else if (response.code() == 400 || response.code() == 401) {
                    try {
                        String errorRes = response.errorBody().string();
                        JSONObject object = new JSONObject(errorRes);
                        String err_msg = object.getString("body");
                        view.onError(err_msg);

                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    view.onError(response.message());

                }
            }

            @Override
            public void onFailure(Call<List<HomeProductResponse>> call, Throwable t) {
                view.showHideProgress(false);
                view.onFailure(t);
            }
        });

    }

    public void GetSeasonProduct(Context context) {
        view.showHideProgress(true);
        Call<List<HomeProductResponse>> userCall = AppUtils.getApi(context).getSeasonProduct();
        userCall.enqueue(new Callback<List<HomeProductResponse>>() {
            @Override
            public void onResponse(Call<List<HomeProductResponse>> call, Response<List<HomeProductResponse>> response) {
                view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onSeasonProductSuccess(response.body(), response.message());
                } else if (response.code() == 400 || response.code() == 401) {
                    try {
                        String errorRes = response.errorBody().string();
                        JSONObject object = new JSONObject(errorRes);
                        String err_msg = object.getString("body");
                        view.onError(err_msg);

                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    view.onError(response.message());

                }
            }

            @Override
            public void onFailure(Call<List<HomeProductResponse>> call, Throwable t) {
                view.showHideProgress(false);
                view.onFailure(t);
            }
        });

    }

    public void GetRecommendedProduct(Context context) {
        view.showHideProgress(true);
        Call<List<HomeProductResponse>> userCall = AppUtils.getApi(context).getReCommendedProduct();
        userCall.enqueue(new Callback<List<HomeProductResponse>>() {
            @Override
            public void onResponse(Call<List<HomeProductResponse>> call, Response<List<HomeProductResponse>> response) {
                view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onReCommendedProductSuccess(response.body(), response.message());
                } else if (response.code() == 400 || response.code() == 401) {
                    try {
                        String errorRes = response.errorBody().string();
                        JSONObject object = new JSONObject(errorRes);
                        String err_msg = object.getString("body");
                        view.onError(err_msg);

                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    view.onError(response.message());

                }
            }

            @Override
            public void onFailure(Call<List<HomeProductResponse>> call, Throwable t) {
                view.showHideProgress(false);
                view.onFailure(t);
            }
        });

    }

    public void GetDiscountCategories(Context context) {
        view.showHideProgress(true);
        Call<List<AllCategoriesResponse>> userCall = AppUtils.getApi(context).getAllDiscountCategories();
        userCall.enqueue(new Callback<List<AllCategoriesResponse>>() {
            @Override
            public void onResponse(Call<List<AllCategoriesResponse>> call, Response<List<AllCategoriesResponse>> response) {
                view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onAllDiscountCategoriesSuccess(response.body(), response.message());
                } else if (response.code() == 400 || response.code() == 401) {
                    try {
                        String errorRes = response.errorBody().string();
                        JSONObject object = new JSONObject(errorRes);
                        String err_msg = object.getString("body");
                        view.onError(err_msg);

                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    view.onError(response.message());

                }
            }

            @Override
            public void onFailure(Call<List<AllCategoriesResponse>> call, Throwable t) {
                view.showHideProgress(false);
                view.onFailure(t);
            }
        });

    }

    public void GetDailyUsableCategories(Context context) {
        view.showHideProgress(true);
        Call<List<AllCategoriesResponse>> userCall = AppUtils.getApi(context).getAllDailyUsableCategories();
        userCall.enqueue(new Callback<List<AllCategoriesResponse>>() {
            @Override
            public void onResponse(Call<List<AllCategoriesResponse>> call, Response<List<AllCategoriesResponse>> response) {
                view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onAllDailyUsableCategoriesSuccess(response.body(), response.message());
                } else if (response.code() == 400 || response.code() == 401) {
                    try {
                        String errorRes = response.errorBody().string();
                        JSONObject object = new JSONObject(errorRes);
                        String err_msg = object.getString("body");
                        view.onError(err_msg);

                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    view.onError(response.message());

                }
            }

            @Override
            public void onFailure(Call<List<AllCategoriesResponse>> call, Throwable t) {
                view.showHideProgress(false);
                view.onFailure(t);
            }
        });

    }

    public void GetFreshArrival(Context context) {
        view.showHideProgress(true);
        Call<List<HomeProductResponse>> userCall = AppUtils.getApi(context).getFreshArrival();
        userCall.enqueue(new Callback<List<HomeProductResponse>>() {
            @Override
            public void onResponse(Call<List<HomeProductResponse>> call, Response<List<HomeProductResponse>> response) {
                view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onFreshArrivalSuccess(response.body(), response.message());
                } else if (response.code() == 400 || response.code() == 401) {
                    try {
                        String errorRes = response.errorBody().string();
                        JSONObject object = new JSONObject(errorRes);
                        String err_msg = object.getString("body");
                        view.onError(err_msg);

                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    view.onError(response.message());

                }
            }

            @Override
            public void onFailure(Call<List<HomeProductResponse>> call, Throwable t) {
                view.showHideProgress(false);
                view.onFailure(t);
            }
        });

    }


    public interface HomeView {

        void showHideProgress(boolean isShow);

        void onError(String message);

        void onBannerSuccess(List<BannerResponse> bannerResponses, String message);

        void onAllCategoriesSuccess(List<AllCategoriesResponse> allCategoriesResponses, String message);

        void onAllDiscountCategoriesSuccess(List<AllCategoriesResponse> allCategoriesResponses, String message);

        void onAllDailyUsableCategoriesSuccess(List<AllCategoriesResponse> allCategoriesResponses, String message);

        void onAllBrandsSuccess(List<BrandsResponse> brandsResponses, String message);

        void onAllPopularProductSuccess(List<HomeProductResponse> PopularProductResponse, String message);

        void onDealOfTheDaySuccess(List<HomeProductResponse> DealOfTheDayResponse, String message);

        void onContinueHuntYouSuccess(List<HomeProductResponse> DiscountForYouResponse, String message);

        void onFreshArrivalSuccess(List<HomeProductResponse> FreshArrivalResponse, String message);

        void onSeasonProductSuccess(List<HomeProductResponse> SeasonProductResponse, String message);

        void onReCommendedProductSuccess(List<HomeProductResponse> ReCommendedProductResponse, String message);

        void onFailure(Throwable t);


    }
}
