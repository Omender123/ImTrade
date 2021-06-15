package com.trade.imtrade.Model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SocialLoginBody {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("isGoogle")
    @Expose
    private Boolean isGoogle;
    @SerializedName("isFacebook")
    @Expose
    private Boolean isFacebook;
    @SerializedName("socialId")
    @Expose
    private String socialId;

    /**
     * No args constructor for use in serialization
     *
     */
    public SocialLoginBody() {
    }


    public SocialLoginBody(String name, String email, Boolean isGoogle, Boolean isFacebook, String socialId) {
        super();
        this.name = name;
        this.email = email;
        this.isGoogle = isGoogle;
        this.isFacebook = isFacebook;
        this.socialId = socialId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIsGoogle() {
        return isGoogle;
    }

    public void setIsGoogle(Boolean isGoogle) {
        this.isGoogle = isGoogle;
    }

    public Boolean getIsFacebook() {
        return isFacebook;
    }

    public void setIsFacebook(Boolean isFacebook) {
        this.isFacebook = isFacebook;
    }

    public String getSocialId() {
        return socialId;
    }

    public void setSocialId(String socialId) {
        this.socialId = socialId;
    }

}
