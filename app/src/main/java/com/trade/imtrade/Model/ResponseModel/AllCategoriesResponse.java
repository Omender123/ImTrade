package com.trade.imtrade.Model.ResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllCategoriesResponse {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("metaTitle")
    @Expose
    private String metaTitle;
    @SerializedName("metaDescription")
    @Expose
    private String metaDescription;
    @SerializedName("metaTags")
    @Expose
    private List<String> metaTags = null;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("bannerImage")
    @Expose
    private String bannerImage;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("sequence")
    @Expose
    private Integer sequence;
    @SerializedName("subCategories")
    @Expose
    private List<Object> subCategories = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMetaTitle() {
        return metaTitle;
    }

    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public List<String> getMetaTags() {
        return metaTags;
    }

    public void setMetaTags(List<String> metaTags) {
        this.metaTags = metaTags;
    }

    public String  getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public List<Object> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<Object> subCategories) {
        this.subCategories = subCategories;
    }
}
