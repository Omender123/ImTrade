package com.trade.imtrade.Model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SendNewEmailOtpBody {
    @SerializedName("email")
    @Expose
    private String OldEmail;
    @SerializedName("newEmail")
    @Expose
    private String NewEmail;

    public SendNewEmailOtpBody(String oldEmail, String newEmail) {
        OldEmail = oldEmail;
        NewEmail = newEmail;
    }

    public SendNewEmailOtpBody() {

    }

    public String getOldEmail() {
        return OldEmail;
    }

    public void setOldEmail(String oldEmail) {
        OldEmail = oldEmail;
    }

    public String getNewEmail() {
        return NewEmail;
    }

    public void setNewEmail(String newEmail) {
        NewEmail = newEmail;
    }
}
