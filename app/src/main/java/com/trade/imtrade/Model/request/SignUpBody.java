package com.trade.imtrade.Model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUpBody {
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("referalCode")
    @Expose
    private String referalCode;
    @SerializedName("password")
    @Expose
    private String password;

    public SignUpBody() {
    }


    public SignUpBody(String email, String referalCode, String password) {
        super();
        this.email = email;
        this.referalCode = referalCode;
        this.password = password;

    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReferalCode() {
        return referalCode;
    }

    public void setReferalCode(String referalCode) {
        this.referalCode = referalCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
