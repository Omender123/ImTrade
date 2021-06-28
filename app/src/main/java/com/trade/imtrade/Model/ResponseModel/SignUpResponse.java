package com.trade.imtrade.Model.ResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SignUpResponse {

    @SerializedName("result")
    @Expose
    private Result result;
    @SerializedName("message")
    @Expose
    private String message;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public class Result {

        @SerializedName("prime")
        @Expose
        private Prime prime;
        @SerializedName("otp")
        @Expose
        private String otp;
        @SerializedName("isPhoneVerified")
        @Expose
        private Boolean isPhoneVerified;
        @SerializedName("recommendedProducts")
        @Expose
        private List<Object> recommendedProducts = null;
        @SerializedName("childrenUsers")
        @Expose
        private List<Object> childrenUsers = null;
        @SerializedName("active")
        @Expose
        private Boolean active;
        @SerializedName("role")
        @Expose
        private String role;
        @SerializedName("roleId")
        @Expose
        private Integer roleId;
        @SerializedName("newsLetter")
        @Expose
        private Boolean newsLetter;
        @SerializedName("wishlist")
        @Expose
        private List<Object> wishlist = null;
        @SerializedName("cart")
        @Expose
        private List<Object> cart = null;
        @SerializedName("category")
        @Expose
        private String category;
        @SerializedName("orderNo")
        @Expose
        private Integer orderNo;
        @SerializedName("savedImxToken")
        @Expose
        private Integer savedImxToken;
        @SerializedName("shoppingTillNow")
        @Expose
        private Integer shoppingTillNow;
        @SerializedName("savingTillNow")
        @Expose
        private Integer savingTillNow;
        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("myReferalcode")
        @Expose
        private String myReferalcode;
        @SerializedName("parentReferalcode")
        @Expose
        private String parentReferalcode;
        @SerializedName("address")
        @Expose
        private List<Object> address = null;
        @SerializedName("createdAt")
        @Expose
        private String createdAt;
        @SerializedName("updatedAt")
        @Expose
        private String updatedAt;

        public Prime getPrime() {
            return prime;
        }

        public void setPrime(Prime prime) {
            this.prime = prime;
        }

        public String getOtp() {
            return otp;
        }

        public void setOtp(String otp) {
            this.otp = otp;
        }

        public Boolean getIsPhoneVerified() {
            return isPhoneVerified;
        }

        public void setIsPhoneVerified(Boolean isPhoneVerified) {
            this.isPhoneVerified = isPhoneVerified;
        }

        public List<Object> getRecommendedProducts() {
            return recommendedProducts;
        }

        public void setRecommendedProducts(List<Object> recommendedProducts) {
            this.recommendedProducts = recommendedProducts;
        }

        public List<Object> getChildrenUsers() {
            return childrenUsers;
        }

        public void setChildrenUsers(List<Object> childrenUsers) {
            this.childrenUsers = childrenUsers;
        }

        public Boolean getActive() {
            return active;
        }

        public void setActive(Boolean active) {
            this.active = active;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public Integer getRoleId() {
            return roleId;
        }

        public void setRoleId(Integer roleId) {
            this.roleId = roleId;
        }

        public Boolean getNewsLetter() {
            return newsLetter;
        }

        public void setNewsLetter(Boolean newsLetter) {
            this.newsLetter = newsLetter;
        }

        public List<Object> getWishlist() {
            return wishlist;
        }

        public void setWishlist(List<Object> wishlist) {
            this.wishlist = wishlist;
        }

        public List<Object> getCart() {
            return cart;
        }

        public void setCart(List<Object> cart) {
            this.cart = cart;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public Integer getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(Integer orderNo) {
            this.orderNo = orderNo;
        }

        public Integer getSavedImxToken() {
            return savedImxToken;
        }

        public void setSavedImxToken(Integer savedImxToken) {
            this.savedImxToken = savedImxToken;
        }

        public Integer getShoppingTillNow() {
            return shoppingTillNow;
        }

        public void setShoppingTillNow(Integer shoppingTillNow) {
            this.shoppingTillNow = shoppingTillNow;
        }

        public Integer getSavingTillNow() {
            return savingTillNow;
        }

        public void setSavingTillNow(Integer savingTillNow) {
            this.savingTillNow = savingTillNow;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getMyReferalcode() {
            return myReferalcode;
        }

        public void setMyReferalcode(String myReferalcode) {
            this.myReferalcode = myReferalcode;
        }

        public String getParentReferalcode() {
            return parentReferalcode;
        }

        public void setParentReferalcode(String parentReferalcode) {
            this.parentReferalcode = parentReferalcode;
        }

        public List<Object> getAddress() {
            return address;
        }

        public void setAddress(List<Object> address) {
            this.address = address;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public class Prime {

            @SerializedName("active")
            @Expose
            private Boolean active;

            public Boolean getActive() {
                return active;
            }

            public void setActive(Boolean active) {
                this.active = active;
            }

        }
    }
}


