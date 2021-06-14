package com.trade.imtrade.Model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerifyOTP_Body {
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("otp")
    @Expose
    private String otp;


    public VerifyOTP_Body() {
    }

    public VerifyOTP_Body(String email, String otp) {
        super();
        this.email = email;
        this.otp = otp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
