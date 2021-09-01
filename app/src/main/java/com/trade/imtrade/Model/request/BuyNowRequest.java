package com.trade.imtrade.Model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class BuyNowRequest {

        @SerializedName("product")
        @Expose
        public Product product;
        public BuyNowRequest(Product product) {
            super();
            this.product = product;
        }


    public static class Product {

        @SerializedName("product")
        @Expose
        private String product;
        @SerializedName("quantity")
        @Expose
        private String quantity;

        public Product(String product, String quantity) {
            super();
            this.product = product;
            this.quantity = quantity;
        }
    }
}