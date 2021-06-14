package com.trade.imtrade.Model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUpBody {
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("referalCode")
    @Expose
    private String referalCode;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("countryCode")
    @Expose
    private String countryCode;
    @SerializedName("phone")
    @Expose
    private String phone;


    public SignUpBody() {
    }


    public SignUpBody(String firstName, String lastName, String email, String referalCode, String password, String countryCode, String phone) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.referalCode = referalCode;
        this.password = password;
        this.countryCode = countryCode;
        this.phone = phone;
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

}
