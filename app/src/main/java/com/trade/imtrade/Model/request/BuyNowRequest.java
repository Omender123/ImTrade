package com.trade.imtrade.Model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class BuyNowRequest {

    @SerializedName("products")
    @Expose
    public ArrayList<Product> products = null;

    public BuyNowRequest() {
    }

    public BuyNowRequest(ArrayList<Product> products) {
        super();
        this.products = products;
    }
    public static class Product {

        @SerializedName("product")
        @Expose
        public String product;
        @SerializedName("quantity")
        @Expose
        public Integer quantity;

         public Product() {
        }


        public Product(String product, Integer quantity) {
            super();
            this.product = product;
            this.quantity = quantity;
        }

    }
   }
