package com.trade.imtrade.Model.ResponseModel;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductResponse {
    @SerializedName("response")
    @Expose
    private List<Response> response = null;
    @SerializedName("numbOfActiveProd")
    @Expose
    private String numbOfActiveProd;

    public List<Response> getResponse() {
        return response;
    }

    public void setResponse(List<Response> response) {
        this.response = response;
    }

    public String getNumbOfActiveProd() {
        return numbOfActiveProd;
    }

    public void setNumbOfActiveProd(String numbOfActiveProd) {
        this.numbOfActiveProd = numbOfActiveProd;
    }

    public class Response {

        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("stockVisible")
        @Expose
        private Boolean stockVisible;
        @SerializedName("averageRating")
        @Expose
        private String averageRating;
        @SerializedName("categories")
        @Expose
        private Categories categories;
        @SerializedName("brand")
        @Expose
        private Brand brand;
        @SerializedName("images")
        @Expose
        private List<String> images = null;
        @SerializedName("variables")
        @Expose
        private List<Variable> variables = null;
        @SerializedName("discount")
        @Expose
        private String discount;
        @SerializedName("route")
        @Expose
        private String route;

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

        public String getAverageRating() {
            return averageRating;
        }

        public Boolean getStockVisible() {
            return stockVisible;
        }

        public void setStockVisible(Boolean stockVisible) {
            this.stockVisible = stockVisible;
        }

        public void setAverageRating(String averageRating) {
            this.averageRating = averageRating;
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

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public List<Variable> getVariables() {
            return variables;
        }

        public void setVariables(List<Variable> variables) {
            this.variables = variables;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getRoute() {
            return route;
        }

        public void setRoute(String route) {
            this.route = route;
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
    }
}