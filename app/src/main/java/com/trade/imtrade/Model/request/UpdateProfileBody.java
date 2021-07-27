package com.trade.imtrade.Model.request;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class UpdateProfileBody {

    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("otp")
    @Expose
    private String otp;

    public UpdateProfileBody(String email, String phone, String firstName, String otp) {
        this.email = email;
        this.phone = phone;
        this.firstName = firstName;
        this.otp = otp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

}