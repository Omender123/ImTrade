package com.trade.imtrade.Model.ResponseModel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductDetailsResponse {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("color")
    @Expose
    private List<Color> color = null;
    @SerializedName("storage")
    @Expose
    private List<Storage> storage = null;
    @SerializedName("details")
    @Expose
    private List<Detail> details = null;
    @SerializedName("variables")
    @Expose
    private List<Variable> variables = null;
    @SerializedName("description")
    @Expose
    private Description description;
    @SerializedName("categories")
    @Expose
    private Categories categories;
    @SerializedName("brand")
    @Expose
    private Brand brand;
    @SerializedName("discount")
    @Expose
    private String discount;
    @SerializedName("images")
    @Expose
    private List<String> images = null;
    @SerializedName("inactivePayment")
    @Expose
    private List<Object> inactivePayment = null;
    @SerializedName("reviews")
    @Expose
    private List<Object> reviews = null;
    @SerializedName("route")
    @Expose
    private String route;
    @SerializedName("averageRating")
    @Expose
    private String averageRating;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Color> getColor() {
        return color;
    }

    public void setColor(List<Color> color) {
        this.color = color;
    }

    public List<Storage> getStorage() {
        return storage;
    }

    public void setStorage(List<Storage> storage) {
        this.storage = storage;
    }

    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }

    public List<Variable> getVariables() {
        return variables;
    }

    public void setVariables(List<Variable> variables) {
        this.variables = variables;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<Object> getInactivePayment() {
        return inactivePayment;
    }

    public void setInactivePayment(List<Object> inactivePayment) {
        this.inactivePayment = inactivePayment;
    }

    public List<Object> getReviews() {
        return reviews;
    }

    public void setReviews(List<Object> reviews) {
        this.reviews = reviews;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(String averageRating) {
        this.averageRating = averageRating;
    }

    public class Color {

        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("code")
        @Expose
        private String code;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

    }

    public class Storage {

        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("size")
        @Expose
        private String size;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

    }

    public class Detail {

        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("detail")
        @Expose
        private String detail;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

    }
    public class Variable {

        @SerializedName("price")
        @Expose
        private Price price;

        public Price getPrice() {
            return price;
        }

        public void setPrice(Price price) {
            this.price = price;
        }
        public class Price {

            @SerializedName("mrp")
            @Expose
            private String mrp;
            @SerializedName("finalPrice")
            @Expose
            private String finalPrice;
            @SerializedName("minPrice")
            @Expose
            private String minPrice;
            @SerializedName("margin")
            @Expose
            private String margin;

            public String getMrp() {
                return mrp;
            }

            public void setMrp(String mrp) {
                this.mrp = mrp;
            }

            public String getFinalPrice() {
                return finalPrice;
            }

            public void setFinalPrice(String finalPrice) {
                this.finalPrice = finalPrice;
            }

            public String getMinPrice() {
                return minPrice;
            }

            public void setMinPrice(String minPrice) {
                this.minPrice = minPrice;
            }

            public String getMargin() {
                return margin;
            }

            public void setMargin(String margin) {
                this.margin = margin;
            }

        }
    }

    public class Description {

        @SerializedName("activeIngredients")
        @Expose
        private List<Object> activeIngredients = null;
        @SerializedName("short")
        @Expose
        private String _short;
        @SerializedName("long")
        @Expose
        private String _long;

        public List<Object> getActiveIngredients() {
            return activeIngredients;
        }

        public void setActiveIngredients(List<Object> activeIngredients) {
            this.activeIngredients = activeIngredients;
        }

        public String getShort() {
            return _short;
        }

        public void setShort(String _short) {
            this._short = _short;
        }

        public String getLong() {
            return _long;
        }

        public void setLong(String _long) {
            this._long = _long;
        }

    }

    public class Categories {

        @SerializedName("category")
        @Expose
        private List<String> category = null;
        @SerializedName("subCategory")
        @Expose
        private List<Object> subCategory = null;
        @SerializedName("subSubCategory")
        @Expose
        private List<Object> subSubCategory = null;

        public List<String> getCategory() {
            return category;
        }

        public void setCategory(List<String> category) {
            this.category = category;
        }

        public List<Object> getSubCategory() {
            return subCategory;
        }

        public void setSubCategory(List<Object> subCategory) {
            this.subCategory = subCategory;
        }

        public List<Object> getSubSubCategory() {
            return subSubCategory;
        }

        public void setSubSubCategory(List<Object> subSubCategory) {
            this.subSubCategory = subSubCategory;
        }

    }

    public class Brand {

        @SerializedName("brand")
        @Expose
        private String brand;
        @SerializedName("brandName")
        @Expose
        private String brandName;

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getBrandName() {
            return brandName;
        }

        public void setBrandName(String brandName) {
            this.brandName = brandName;
        }

    }
}