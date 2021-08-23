package com.trade.imtrade.Model.ResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HotelAndCoffeeResponse {
    @SerializedName("images")
    @Expose
    private List<String> images = null;
    @SerializedName("pinCode")
    @Expose
    private List<String> pinCode = null;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("reviews")
    @Expose
    private String reviews;
    @SerializedName("averageRating")
    @Expose
    private String averageRating;
    @SerializedName("menu")
    @Expose
    private List<Menu> menu = null;
    @SerializedName("price")
    @Expose
    private String price;

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getPinCode() {
        return pinCode;
    }

    public void setPinCode(List<String> pinCode) {
        this.pinCode = pinCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public String getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(String averageRating) {
        this.averageRating = averageRating;
    }

    public List<Menu> getMenu() {
        return menu;
    }

    public void setMenu(List<Menu> menu) {
        this.menu = menu;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    public class Menu {

        @SerializedName("addOn")
        @Expose
        private List<Object> addOn = null;
        @SerializedName("dishName")
        @Expose
        private String dishName;
        @SerializedName("price")
        @Expose
        private Integer price;
        @SerializedName("shopId")
        @Expose
        private String shopId;

        public List<Object> getAddOn() {
            return addOn;
        }

        public void setAddOn(List<Object> addOn) {
            this.addOn = addOn;
        }

        public String getDishName() {
            return dishName;
        }

        public void setDishName(String dishName) {
            this.dishName = dishName;
        }

        public Integer getPrice() {
            return price;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }

    }
}