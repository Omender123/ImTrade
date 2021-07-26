package com.trade.imtrade.SharedPrefernce;

public class User_Data {
    String id,email, Token,referral_code,UserName,PhoneNo;

    public User_Data(String id, String email, String token, String referral_code,String UserName,String PhoneNo) {
        this.id = id;
        this.email = email;
        this.Token = token;
        this.referral_code = referral_code;
        this.UserName = UserName;
        this.PhoneNo= PhoneNo;

    }

    public User_Data() {
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

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getReferral_code() {
        return referral_code;
    }

    public void setReferral_code(String referral_code) {
        this.referral_code = referral_code;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }
}
