package com.trade.imtrade.Model.ResponseModel;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerQuestionsResponse {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("productId")
    @Expose
    private String productId;
    @SerializedName("qsn")
    @Expose
    private String qsn;
    @SerializedName("answer")
    @Expose
    private String answer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getQsn() {
        return qsn;
    }

    public void setQsn(String qsn) {
        this.qsn = qsn;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}