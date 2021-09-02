package com.trade.imtrade.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.models.SlideModel;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;
import com.irozon.sneaker.Sneaker;
import com.trade.imtrade.Activity.OrderSummary;
import com.trade.imtrade.Adapter.BuyItWithAdapter;
import com.trade.imtrade.Adapter.ColorAdapter;
import com.trade.imtrade.Adapter.ColorAdapter.OnColorItemListener;
import com.trade.imtrade.Adapter.Customer_Questions_Adapter;
import com.trade.imtrade.Adapter.DetailsAdapter;
import com.trade.imtrade.Adapter.OfferAdapter;
import com.trade.imtrade.Adapter.Other_Info_Adapter;
import com.trade.imtrade.Adapter.ReviewAdapter;
import com.trade.imtrade.Adapter.ReviewImageAdapter;
import com.trade.imtrade.Adapter.ReviewVideoAdapter;
import com.trade.imtrade.Adapter.Review_product_Adapter;
import com.trade.imtrade.Adapter.StorageAdapter;
import com.trade.imtrade.Model.ResponseModel.CustomerQuestionsResponse;
import com.trade.imtrade.Model.ResponseModel.OfferResponse;
import com.trade.imtrade.Model.ResponseModel.ProductDetailsResponse;
import com.trade.imtrade.Model.ResponseModel.RelatedResponse;
import com.trade.imtrade.Model.ResponseModel.ReviewResponse;
import com.trade.imtrade.Model.request.AddToCartBody;
import com.trade.imtrade.R;
import com.trade.imtrade.SharedPerfence.MyPreferences;
import com.trade.imtrade.SharedPerfence.PrefConf;
import com.trade.imtrade.databinding.FragmentProductDetailsBinding;
import com.trade.imtrade.utils.AppUtils;
import com.trade.imtrade.view_presenter.ProductDetails_Presenter;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;


public class Product_Details_Fragment extends Fragment implements ProductDetails_Presenter.GetProductDetailsView, View.OnClickListener, OnColorItemListener, StorageAdapter.OnToggleItemListener,
        Other_Info_Adapter.OnOtherInfoItemListener, BuyItWithAdapter.BuyitwithClickListener {
    TextView text_cart_Count;
    FragmentProductDetailsBinding binding;
    private View view;
    private Dialog dialog;
    NavController navController;
    ProductDetails_Presenter presenter;
    String RouteId;
    ProductDetailsResponse productDetailsResponses;
    Boolean Check, CheckedLogin;
    int TotalPrice;

    public Product_Details_Fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        text_cart_Count = (TextView) getActivity().findViewById(R.id.text_cart_Count);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_product__details, container, false);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product__details, container, false);
        RouteId = MyPreferences.getInstance(getActivity()).getString(PrefConf.ROUTEID, "");
        CheckedLogin = MyPreferences.getInstance(getActivity()).getBoolean(PrefConf.LOGINCHECK, false);

        view = binding.getRoot();
        presenter = new ProductDetails_Presenter(this);

        dialog = AppUtils.hideShowProgress(getContext());

        presenter.GetProductDetails(getActivity(), RouteId);
        presenter.GetAllOFFER(getActivity());

        binding.showMore.setOnClickListener(this);
        binding.HideMore.setOnClickListener(this);
        binding.showMore1.setOnClickListener(this);
        binding.HideMore1.setOnClickListener(this);
        binding.showDetails.setOnClickListener(this);
        binding.HideDetails.setOnClickListener(this);
        binding.textAddtocart.setOnClickListener(this);
        binding.textBuyNow.setOnClickListener(this);

        CheckBoxList();
        return binding.getRoot();
    }

    public void CheckBoxList() {
        binding.imageUnselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Check = true;
                binding.imageSelect.setVisibility(View.VISIBLE);
                binding.imageUnselect.setVisibility(View.GONE);
                Toast.makeText(getContext(), "" + Check, Toast.LENGTH_SHORT).show();
            }
        });
        binding.imageSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Check = false;
                binding.imageUnselect.setVisibility(View.VISIBLE);
                binding.imageSelect.setVisibility(View.GONE);
                Toast.makeText(getContext(), "" + Check, Toast.LENGTH_SHORT).show();
            }

        });
    }

    @Override
    public void showHideProgress(boolean isShow) {
        if (isShow) {
            dialog.show();
        } else {
            dialog.dismiss();
        }
    }

    @Override
    public void onError(String message) {
        Sneaker.with(getActivity())
                .setTitle(message)
                .setMessage("")
                .setCornerRadius(4)
                .setDuration(1500)
                .sneakError();


    }

    @Override
    public void onProductDetailsSuccess(ProductDetailsResponse productDetailsResponse, String message) {
        productDetailsResponses = productDetailsResponse;
        if (message.equalsIgnoreCase("ok")) {
            /*--------------ProductBanner--------------*/
            List<SlideModel> BannerImage = new ArrayList<>();
            if (productDetailsResponse.getImages().size() > 0 && productDetailsResponse.getImages() != null) {
                for (int i = 0; i < productDetailsResponse.getImages().size(); i++) {
                    BannerImage.add(new SlideModel(PrefConf.IMAGE_URL + productDetailsResponse.getImages().get(i), null));
                }
                binding.slider.setImageList(BannerImage);

            } else {
                Sneaker.with(this)
                        .setTitle("No Product Image are Available")
                        .setMessage("")
                        .setCornerRadius(4)
                        .setDuration(1500)
                        .sneakError();
            }

            /*--------------SetProductDetails--------------*/

            SimpleRatingBar.AnimationBuilder builder = binding.textRating.getAnimationBuilder()
                    .setRatingTarget(Float.parseFloat(productDetailsResponse.getAverageRating()))
                    .setDuration(2000)
                    .setRepeatMode(1)
                    .setInterpolator(new BounceInterpolator());
            builder.start();
            presenter.GetAllCustomerQuestions(getActivity(), productDetailsResponse.getId());
            presenter.GetAllReview(getActivity(), productDetailsResponse.getId());
            presenter.GetAllReviewImages(getActivity(), productDetailsResponse.getId());
            presenter.GetAllReviewVideo(getActivity(), productDetailsResponse.getId());
            presenter.GetAllRelatedProduct(getActivity(), productDetailsResponse.getId());
            presenter.AddContinueYouHuntProduct(getActivity(), productDetailsResponse.getId());

            binding.productName.setText(productDetailsResponse.getName());

            if (productDetailsResponse.getDiscount() != null) {
                binding.productPrice.setText(productDetailsResponse.getDiscount() + " Rs");
                binding.productOffPrice.setText(productDetailsResponse.getVariables().get(0).getPrice().getMargin() + " %off");
                binding.productWorngPrice.setText(productDetailsResponse.getVariables().get(0).getPrice().getMrp() + " Rs");
            } else {
                binding.productPrice.setText(productDetailsResponse.getVariables().get(0).getPrice().getMrp() + " Rs");
                binding.productOffPrice.setVisibility(View.GONE);
                binding.productWorngPrice.setVisibility(View.GONE);

            }

            getColorList(productDetailsResponse);
            getStorageList(productDetailsResponse);
            getDelatilsList(productDetailsResponse, false);
            getAllBuyItWith(productDetailsResponse);
            getOtherInfo(productDetailsResponse);

        }

    }

    @Override
    public void onAddToCartSuccess(ResponseBody responseBody, String message) {
        if (message.equalsIgnoreCase("ok")) {
            Sneaker.with(getActivity())
                    .setTitle("Successfully add product in cart ")
                    .setMessage("")
                    .setCornerRadius(4)
                    .setDuration(1500)
                    .sneakSuccess();

            int cart = Integer.parseInt(text_cart_Count.getText().toString()) + 1;
            text_cart_Count.setText(String.valueOf(cart));

        }
    }

    @Override
    public void onCustomerQuestionsSuccess(List<CustomerQuestionsResponse> customerQuestionsResponses, String message) {
        if (message.equalsIgnoreCase("ok")) {
            if (customerQuestionsResponses.size() > 0 && customerQuestionsResponses != null) {
                Customer_Questions_Adapter profile_itemAdapter = new Customer_Questions_Adapter(getContext(), customerQuestionsResponses, false);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                binding.custQuesRecycler.setLayoutManager(mLayoutManager);
                binding.custQuesRecycler.setItemAnimator(new DefaultItemAnimator());
                binding.custQuesRecycler.setAdapter(profile_itemAdapter);
                binding.custQuesRecycler.setVisibility(View.VISIBLE);
            } else {

                binding.custQuesRecycler.setVisibility(View.GONE);
                binding.textSeeQA.setVisibility(View.GONE);
            }

        }
    }

    @Override
    public void onOfferSuccess(List<OfferResponse> offerResponses, String message) {
        if (message.equalsIgnoreCase("ok")) {
            if (offerResponses.size() > 0 && offerResponses != null) {
                OfferAdapter profile_itemAdapter = new OfferAdapter(getContext(), offerResponses);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
                binding.offerRecycler.setLayoutManager(mLayoutManager);
                binding.offerRecycler.setItemAnimator(new DefaultItemAnimator());
                binding.offerRecycler.setAdapter(profile_itemAdapter);
                binding.offerRecycler.setVisibility(View.VISIBLE);
                binding.txtOffer.setVisibility(View.VISIBLE);
            } else {

                binding.txtOffer.setVisibility(View.GONE);
                binding.offerRecycler.setVisibility(View.GONE);
            }

        }
    }

    @Override
    public void oAllReviewSuccess(List<ReviewResponse> offerResponses, String message) {
        if (message.equalsIgnoreCase("ok")) {
            if (offerResponses.size() > 0 && offerResponses != null) {
                ReviewAdapter reviewAdapter = new ReviewAdapter(getContext(), offerResponses);
                RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                binding.ReviewRecyclerView.setLayoutManager(mLayoutManager1);
                binding.ReviewRecyclerView.setItemAnimator(new DefaultItemAnimator());
                binding.ReviewRecyclerView.setAdapter(reviewAdapter);
                binding.textSeeAllReview.setText("See all " + offerResponses.size() + " Reviews ");
                int total = 0;
                for (int i = 0; i < offerResponses.size(); i++) {
                    int review = Integer.parseInt(offerResponses.get(i).getRating());
                    total = total + review;

                }
                float totalReview = total / offerResponses.size();
                SimpleRatingBar.AnimationBuilder builder = binding.textReviewrating.getAnimationBuilder()
                        .setRatingTarget(totalReview)
                        .setDuration(2000)
                        .setRepeatMode(1)
                        .setInterpolator(new BounceInterpolator());
                builder.start();
                binding.textOutOfRating.setText(totalReview + " out of 5");
                binding.txtTotalReviewrating.setText(total + " rating and " + offerResponses.size() + " reviews");

                binding.linReview.setVisibility(View.VISIBLE);
            } else {
                binding.linReview.setVisibility(View.GONE);


            }
        }

    }

    @Override
    public void oAllReviewImagesSuccess(List<ReviewResponse> offerResponses, String message) {

        if (message.equalsIgnoreCase("ok")) {
            if (offerResponses.size() > 0 && offerResponses != null) {
                ReviewImageAdapter gameAdapter = new ReviewImageAdapter(getContext(), offerResponses);
                RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                binding.imageRecycler.setLayoutManager(mLayoutManager1);
                binding.imageRecycler.setItemAnimator(new DefaultItemAnimator());
                binding.imageRecycler.setAdapter(gameAdapter);
                binding.imageRecycler.setVisibility(View.VISIBLE);
                binding.textReviewImages.setVisibility(View.VISIBLE);
                binding.viewReviewImages.setVisibility(View.VISIBLE);
            } else {
                binding.imageRecycler.setVisibility(View.GONE);
                binding.textReviewImages.setVisibility(View.GONE);
                binding.viewReviewImages.setVisibility(View.GONE);

            }
        }
    }

    @Override
    public void oAllReviewVideoSuccess(List<ReviewResponse> offerResponses, String message) {
        if (message.equalsIgnoreCase("ok")) {
            if (offerResponses.size() > 0 && offerResponses != null) {
                ReviewVideoAdapter gameAdapter = new ReviewVideoAdapter(getContext(), offerResponses);
                RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                binding.videoRecycler.setLayoutManager(mLayoutManager1);
                binding.videoRecycler.setItemAnimator(new DefaultItemAnimator());
                binding.videoRecycler.setAdapter(gameAdapter);
                binding.videoRecycler.setVisibility(View.VISIBLE);
                binding.textReviewVideo.setVisibility(View.VISIBLE);
                binding.viewReviewVideo.setVisibility(View.VISIBLE);
            } else {
                binding.videoRecycler.setVisibility(View.GONE);
                binding.textReviewVideo.setVisibility(View.GONE);
                binding.viewReviewVideo.setVisibility(View.GONE);

            }
        }

    }

    @Override
    public void onRelatedProductSuccess(RelatedResponse relatedResponse, String message) {
        if (message.equalsIgnoreCase("ok")) {
            if (relatedResponse.getRelated().getSimilar().size() > 0 && relatedResponse.getRelated().getSimilar() != null) {
                Review_product_Adapter review_product_adapter = new Review_product_Adapter(getActivity(), relatedResponse);
                RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                binding.relatedProductRcycler.setLayoutManager(mLayoutManager1);
                binding.relatedProductRcycler.setItemAnimator(new DefaultItemAnimator());
                binding.relatedProductRcycler.setAdapter(review_product_adapter);
                binding.relatedProductRcycler.setVisibility(View.VISIBLE);
                binding.linRealted.setVisibility(View.VISIBLE);
                binding.viewRealted.setVisibility(View.VISIBLE);

            } else {
                binding.relatedProductRcycler.setVisibility(View.GONE);
                binding.linRealted.setVisibility(View.GONE);
                binding.viewRealted.setVisibility(View.GONE);

            }
        }

    }

    @Override
    public void onADDContinueYouHuntProductSuccess(String result, String message) {
        if (message.equalsIgnoreCase("ok")) {
           /* Sneaker.with(getActivity())
                    .setTitle(result)
                    .setMessage("")
                    .setCornerRadius(4)
                    .setDuration(1500)
                    .sneakError();*/

        }
    }

    @Override
    public void onFailure(Throwable t) {
        Sneaker.with(getActivity())
                .setTitle(t.getLocalizedMessage())
                .setMessage("")
                .setCornerRadius(4)
                .setDuration(1500)
                .sneakError();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.showMore:
                binding.showMore.setVisibility(View.GONE);
                binding.HideMore.setVisibility(View.VISIBLE);
                binding.ColorRecyclerView.setVisibility(View.VISIBLE);
                break;
            case R.id.HideMore:
                binding.showMore.setVisibility(View.VISIBLE);
                binding.HideMore.setVisibility(View.GONE);
                binding.ColorRecyclerView.setVisibility(View.GONE);
                break;

            case R.id.showMore1:
                binding.showMore1.setVisibility(View.GONE);
                binding.HideMore1.setVisibility(View.VISIBLE);
                binding.storageRecyclerView.setVisibility(View.VISIBLE);
                break;
            case R.id.HideMore1:
                binding.showMore1.setVisibility(View.VISIBLE);
                binding.HideMore1.setVisibility(View.GONE);
                binding.storageRecyclerView.setVisibility(View.GONE);
                break;
            case R.id.showDetails:
                binding.showDetails.setVisibility(View.GONE);
                binding.HideDetails.setVisibility(View.VISIBLE);
                getDelatilsList(productDetailsResponses, true);
                break;
            case R.id.HideDetails:
                binding.showDetails.setVisibility(View.VISIBLE);
                binding.HideDetails.setVisibility(View.GONE);
                getDelatilsList(productDetailsResponses, false);
                break;

            case R.id.text_addtocart:
                if (CheckedLogin == true) {
                    AddToCartBody addToCartBody = new AddToCartBody(productDetailsResponses.getId(), 1);
                    presenter.AddToCart(getActivity(), addToCartBody);

                } else {
                    Sneaker.with(getActivity())
                            .setTitle("Your Can't access this app  please First Login ")
                            .setMessage("")
                            .setCornerRadius(4)
                            .setDuration(1500)
                            .sneakError();
                }
                break;

            case R.id.text_BuyNow:
                if (CheckedLogin == true) {

                    startActivity(new Intent(getActivity(), OrderSummary.class));
                    MyPreferences.getInstance(getActivity()).putString(PrefConf.BUYNOWTYPE, "false");
                    MyPreferences.getInstance(getActivity()).putString(PrefConf.ProductID, productDetailsResponses.getId());

                } else {
                    Sneaker.with(getActivity())
                            .setTitle("Your Can't access this app  please First Login ")
                            .setMessage("")
                            .setCornerRadius(4)
                            .setDuration(1500)
                            .sneakError();
                }
                break;

        }
    }

    private void getColorList(ProductDetailsResponse productDetailsResponse) {
        if (productDetailsResponse.getColor().size() > 0 && productDetailsResponse.getColor() != null) {

            ColorAdapter colorAdapter = new ColorAdapter(getActivity(), productDetailsResponse, this::onOnColorClickListener);
            RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            binding.ColorRecyclerView.setLayoutManager(mLayoutManager1);
            binding.ColorRecyclerView.setItemAnimator(new DefaultItemAnimator());
            binding.ColorRecyclerView.setAdapter(colorAdapter);
            binding.textColor.setText(productDetailsResponse.getProductColor().toUpperCase());
            binding.showMore.setText(productDetailsResponse.getColor().size() + " more");
        } else {
            Sneaker.with(this)
                    .setTitle("No Product Color are Available")
                    .setMessage("")
                    .setCornerRadius(4)
                    .setDuration(1500)
                    .sneakError();
        }


    }

    @Override
    public void onOnColorClickListener(ProductDetailsResponse productDetailsResponse, int position) {

        presenter.GetProductDetails(getActivity(), productDetailsResponse.getColor().get(position).getRoute());
        presenter.GetAllOFFER(getActivity());
    }

    private void getStorageList(ProductDetailsResponse productDetailsResponse) {
        if (productDetailsResponse.getStorage().size() > 0 && productDetailsResponse.getStorage() != null) {


            StorageAdapter storageAdapter = new StorageAdapter(getActivity(), productDetailsResponse, this::onToggleItemClickListener);
            RecyclerView.LayoutManager mLayoutManager1 = new GridLayoutManager(getActivity(), 4, LinearLayoutManager.VERTICAL, false);
            binding.storageRecyclerView.setLayoutManager(mLayoutManager1);
            binding.storageRecyclerView.setItemAnimator(new DefaultItemAnimator());
            binding.storageRecyclerView.setAdapter(storageAdapter);
            binding.textStorage.setText(productDetailsResponse.getProductStorage());
            binding.showMore1.setText(productDetailsResponse.getColor().size() + " more");
        } else {
            Sneaker.with(this)
                    .setTitle("No Product Size and Storage are Available")
                    .setMessage("")
                    .setCornerRadius(4)
                    .setDuration(1500)
                    .sneakError();
        }


    }

    @Override
    public void onToggleItemClickListener(ProductDetailsResponse productDetailsResponse, int position) {

        presenter.GetProductDetails(getActivity(), productDetailsResponse.getStorage().get(position).getDetailRoute());
        presenter.GetAllOFFER(getActivity());


    }

    private void getDelatilsList(ProductDetailsResponse productDetailsResponse, Boolean count) {
        if (count == true) {
            DetailsAdapter detailsAdapter = new DetailsAdapter(getActivity(), productDetailsResponse, true);
            RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            binding.detailsRecyclerView.setLayoutManager(mLayoutManager1);
            binding.detailsRecyclerView.setItemAnimator(new DefaultItemAnimator());
            binding.detailsRecyclerView.setAdapter(detailsAdapter);
        } else {
            DetailsAdapter detailsAdapter = new DetailsAdapter(getActivity(), productDetailsResponse, false);
            RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            binding.detailsRecyclerView.setLayoutManager(mLayoutManager1);
            binding.detailsRecyclerView.setItemAnimator(new DefaultItemAnimator());
            binding.detailsRecyclerView.setAdapter(detailsAdapter);
        }

    }

    private void getOtherInfo(ProductDetailsResponse productDetailsResponse) {

        if (productDetailsResponse.getOtherInfo().size() > 0 && productDetailsResponse.getOtherInfo() != null) {
            Other_Info_Adapter profile_itemAdapter = new Other_Info_Adapter(productDetailsResponse, getContext(), this::onOtherInfoItemClickListener);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
            binding.OtherRecyclerView.setLayoutManager(mLayoutManager);
            binding.OtherRecyclerView.setItemAnimator(new DefaultItemAnimator());
            binding.OtherRecyclerView.setAdapter(profile_itemAdapter);
            binding.textOtherInfo.setVisibility(View.VISIBLE);
            binding.OtherRecyclerView.setVisibility(View.VISIBLE);
            binding.viewOtherInfo.setVisibility(View.VISIBLE);
        } else {
            binding.textOtherInfo.setVisibility(View.GONE);
            binding.OtherRecyclerView.setVisibility(View.GONE);
            binding.viewOtherInfo.setVisibility(View.GONE);
        }


    }

    @Override
    public void onOtherInfoItemClickListener(ProductDetailsResponse ItemList, int position) {

    }


    private void getAllBuyItWith(ProductDetailsResponse productDetailsResponse) {

        if (productDetailsResponse.getAddOn().size() > 0 && productDetailsResponse.getAddOn() != null) {
            BuyItWithAdapter buyItWithAdapter = new BuyItWithAdapter(getContext(), productDetailsResponse, this);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
            binding.buyRecycler.setLayoutManager(mLayoutManager);
            binding.buyRecycler.setItemAnimator(new DefaultItemAnimator());
            binding.buyRecycler.setAdapter(buyItWithAdapter);
            binding.txtAddButton.setText("Add all " + productDetailsResponse.getAddOn().size() + " to Cart");
            binding.textBuyitWith.setVisibility(View.VISIBLE);
            binding.linerBuy.setVisibility(View.VISIBLE);
            binding.viewBuy.setVisibility(View.VISIBLE);
            ArrayList<String> ProductId = new ArrayList<String>();
            for (int i = 0; i < productDetailsResponse.getAddOn().size(); i++) {
                if (productDetailsResponse.getAddOn().get(i).getDiscount() != null) {
                    int price = Integer.parseInt(productDetailsResponse.getAddOn().get(i).getDiscount());
                    TotalPrice = TotalPrice + price;
                    ProductId.add(productDetailsResponse.getAddOn().get(i).getId());
                } else {
                    int price = Integer.parseInt(productDetailsResponse.getAddOn().get(i).getVariables().get(0).getPrice().getMrp());
                    TotalPrice = TotalPrice + price;
                    ProductId.add(productDetailsResponse.getAddOn().get(i).getId());

                }


                addtoCartMulti(ProductId);
                binding.totalPrice.setText("" + TotalPrice);
            }
        } else {

            binding.textBuyitWith.setVisibility(View.GONE);
            binding.linerBuy.setVisibility(View.GONE);
            binding.viewBuy.setVisibility(View.GONE);

        }


    }


    @Override
    public void onBuyitwithOnClickListener(ProductDetailsResponse productDetailsResponse, int Position) {

    }

    @Override
    public void OnCheckBoxClickListener(ArrayList<String> ProductId, String totalPrice, Boolean checked) {

        binding.txtAddButton.setText("Add all " + ProductId.size() + " to Cart");

        if (checked == true) {
            TotalPrice = TotalPrice + Integer.parseInt(totalPrice);
        } else {
            TotalPrice = TotalPrice - Integer.parseInt(totalPrice);
        }

        binding.totalPrice.setText("" + TotalPrice);
        if (ProductId.size() == 0) {
            binding.txtAddButton.setVisibility(View.GONE);
        } else {
            binding.txtAddButton.setVisibility(View.VISIBLE);
        }

        addtoCartMulti(ProductId);
    }

    private void addtoCartMulti(ArrayList<String> productId) {

        binding.txtAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckedLogin == true) {
                    for (int i = 0; i < productId.size(); i++) {

                        AddToCartBody addToCartBody = new AddToCartBody(productId.get(i), 1);
                        presenter.AddToCart(getActivity(), addToCartBody);

                    }

                } else {
                    Sneaker.with(getActivity())
                            .setTitle("Your Can't access this app  please First Login ")
                            .setMessage("")
                            .setCornerRadius(4)
                            .setDuration(1500)
                            .sneakError();
                }

            }
        });
    }


}