package com.trade.imtrade.Model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ChangeEmailBody {

    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("newEmail")
    @Expose
    public String newemail;
    @SerializedName("otp2")
    @Expose
    public String otp2;
    @SerializedName("otp1")
    @Expose
    public String otp1;

    public ChangeEmailBody() {
    }

    public ChangeEmailBody(String email, String newemail, String otp2, String otp1) {
        super();
        this.email = email;
        this.newemail = newemail;
        this.otp2 = otp2;
        this.otp1 = otp1;
    }

}