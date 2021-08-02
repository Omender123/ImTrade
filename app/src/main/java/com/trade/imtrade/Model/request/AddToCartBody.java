package com.trade.imtrade.Model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class AddToCartBody {

    @SerializedName("productId")
    @Expose
    public String productId;
    @SerializedName("quantity")
    @Expose
    public Integer quantity;

   public AddToCartBody() {
    }

    public AddToCartBody(String productId, Integer quantity) {
        super();
        this.productId = productId;
        this.quantity = quantity;
    }

}