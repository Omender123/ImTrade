package com.trade.imtrade.Model.ResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginResponse {
    @SerializedName("result")
    @Expose
    private Result result;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("message")
    @Expose
    private String message;


    public LoginResponse() {
    }

    public LoginResponse(Result result, String token, String message) {
        super();
        this.result = result;
        this.token = token;
        this.message = message;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
        private Object wishlist;
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
        @SerializedName("firstName")
        @Expose
        private String firstName;
        @SerializedName("lastName")
        @Expose
        private String lastName;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("countryCode")
        @Expose
        private String countryCode;
        @SerializedName("phone")
        @Expose
        private String phone;
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
        @SerializedName("otpDate")
        @Expose
        private String otpDate;

        public Result() {
        }


        public Result(Prime prime, String otp, Boolean isPhoneVerified, List<Object> recommendedProducts, List<Object> childrenUsers, Boolean active, String role, Integer roleId, Boolean newsLetter, Object wishlist, List<Object> cart, String category, Integer orderNo, Integer savedImxToken, Integer shoppingTillNow, Integer savingTillNow, String id, String firstName, String lastName, String email, String password, String countryCode, String phone, String myReferalcode, String parentReferalcode, List<Object> address, String createdAt, String updatedAt, String otpDate) {
            super();
            this.prime = prime;
            this.otp = otp;
            this.isPhoneVerified = isPhoneVerified;
            this.recommendedProducts = recommendedProducts;
            this.childrenUsers = childrenUsers;
            this.active = active;
            this.role = role;
            this.roleId = roleId;
            this.newsLetter = newsLetter;
            this.wishlist = wishlist;
            this.cart = cart;
            this.category = category;
            this.orderNo = orderNo;
            this.savedImxToken = savedImxToken;
            this.shoppingTillNow = shoppingTillNow;
            this.savingTillNow = savingTillNow;
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.password = password;
            this.countryCode = countryCode;
            this.phone = phone;
            this.myReferalcode = myReferalcode;
            this.parentReferalcode = parentReferalcode;
            this.address = address;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
            this.otpDate = otpDate;
        }

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

        public Object getWishlist() {
            return wishlist;
        }

        public void setWishlist(Object wishlist) {
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

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
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

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
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

        public String getOtpDate() {
            return otpDate;
        }

        public void setOtpDate(String otpDate) {
            this.otpDate = otpDate;
        }

        public class Prime {
            @SerializedName("active")
            @Expose
            private Boolean active;

            public Prime() {
            }

                public Prime(Boolean active) {
                super();
                this.active = active;
            }

            public Boolean getActive() {
                return active;
            }

            public void setActive(Boolean active) {
                this.active = active;
            }

        }
    }
    }
