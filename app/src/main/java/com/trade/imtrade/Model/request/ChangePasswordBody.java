
package com.trade.imtrade.Model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangePasswordBody {

    @SerializedName("password")
    @Expose
    public String password;
    @SerializedName("newPassword")
    @Expose
    public String newPassword;
    @SerializedName("otp1")
    @Expose
    public String otp;
    public ChangePasswordBody() {
    }

    public ChangePasswordBody(String password, String newPassword, String otp) {
        super();
        this.password = password;
        this.newPassword = newPassword;
        this.otp = otp;
    }

}