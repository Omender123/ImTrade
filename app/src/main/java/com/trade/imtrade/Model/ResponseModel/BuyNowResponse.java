package com.trade.imtrade.Model.ResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BuyNowResponse {

    @SerializedName("totalMrp")
    @Expose
    private String totalMrp;
    @SerializedName("shippingCharges")
    @Expose
    private String shippingCharges;
    @SerializedName("totalWeight")
    @Expose
    private String totalWeight;
    @SerializedName("tax")
    @Expose
    private String tax;
    @SerializedName("totalSavings")
    @Expose
    private String totalSavings;
    @SerializedName("totalAmount")
    @Expose
    private String totalAmount;
    @SerializedName("totalPayableAmount")
    @Expose
    private String totalPayableAmount;
    @SerializedName("amountForCoupon")
    @Expose
    private String amountForCoupon;

    public String getTotalMrp() {
        return totalMrp;
    }

    public void setTotalMrp(String totalMrp) {
        this.totalMrp = totalMrp;
    }

    public String getShippingCharges() {
        return shippingCharges;
    }

    public void setShippingCharges(String shippingCharges) {
        this.shippingCharges = shippingCharges;
    }

    public String getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(String totalWeight) {
        this.totalWeight = totalWeight;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getTotalSavings() {
        return totalSavings;
    }

    public void setTotalSavings(String totalSavings) {
        this.totalSavings = totalSavings;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTotalPayableAmount() {
        return totalPayableAmount;
    }

    public void setTotalPayableAmount(String totalPayableAmount) {
        this.totalPayableAmount = totalPayableAmount;
    }

    public String getAmountForCoupon() {
        return amountForCoupon;
    }

    public void setAmountForCoupon(String amountForCoupon) {
        this.amountForCoupon = amountForCoupon;
    }

}