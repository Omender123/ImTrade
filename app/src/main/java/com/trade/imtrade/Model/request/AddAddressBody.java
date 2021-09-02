package com.trade.imtrade.Model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddAddressBody {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("phoneNo")
    @Expose
    public String phoneNo;
    @SerializedName("country")
    @Expose
    public String country;
    @SerializedName("state")
    @Expose
    public String state;
    @SerializedName("city")
    @Expose
    public String city;
    @SerializedName("pinCode")
    @Expose
    public String pinCode;
    @SerializedName("address")
    @Expose
    public String address;

    public AddAddressBody() {
    }

    public AddAddressBody(String name, String phoneNo, String country, String state, String city, String pinCode, String address) {
        super();
        this.name = name;
        this.phoneNo = phoneNo;
        this.country = country;
        this.state = state;
        this.city = city;
        this.pinCode = pinCode;
        this.address = address;
    }

}