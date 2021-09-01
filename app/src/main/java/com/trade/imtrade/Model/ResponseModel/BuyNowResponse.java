package com.trade.imtrade.Model.ResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BuyNowResponse {

    @SerializedName("totalMrp")
    @Expose
    private Integer totalMrp;
    @SerializedName("shippingCharges")
    @Expose
    private Integer shippingCharges;
    @SerializedName("totalWeight")
    @Expose
    private Object totalWeight;
    @SerializedName("tax")
    @Expose
    private Integer tax;
    @SerializedName("totalSavings")
    @Expose
    private Integer totalSavings;
    @SerializedName("totalAmount")
    @Expose
    private Integer totalAmount;
    @SerializedName("totalPayableAmount")
    @Expose
    private Integer totalPayableAmount;

    public Integer getTotalMrp() {
        return totalMrp;
    }

    public void setTotalMrp(Integer totalMrp) {
        this.totalMrp = totalMrp;
    }

    public Integer getShippingCharges() {
        return shippingCharges;
    }

    public void setShippingCharges(Integer shippingCharges) {
        this.shippingCharges = shippingCharges;
    }

    public Object getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Object totalWeight) {
        this.totalWeight = totalWeight;
    }

    public Integer getTax() {
        return tax;
    }

    public void setTax(Integer tax) {
        this.tax = tax;
    }

    public Integer getTotalSavings() {
        return totalSavings;
    }

    public void setTotalSavings(Integer totalSavings) {
        this.totalSavings = totalSavings;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getTotalPayableAmount() {
        return totalPayableAmount;
    }

    public void setTotalPayableAmount(Integer totalPayableAmount) {
        this.totalPayableAmount = totalPayableAmount;
    }

}