package com.trade.imtrade.Model.ResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RelatedResponse {

    @SerializedName("related")
    @Expose
    private Related related;

    public Related getRelated() {
        return related;
    }

    public void setRelated(Related related) {
        this.related = related;
    }

    public class Related {

        @SerializedName("recommended")
        @Expose
        private List<Object> recommended = null;
        @SerializedName("similar")
        @Expose
        private List<Similar> similar = null;

        public List<Object> getRecommended() {
            return recommended;
        }

        public void setRecommended(List<Object> recommended) {
            this.recommended = recommended;
        }

        public List<Similar> getSimilar() {
            return similar;
        }

        public void setSimilar(List<Similar> similar) {
            this.similar = similar;
        }

        public class Similar {

            @SerializedName("_id")
            @Expose
            private Integer id;
            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("route")
            @Expose
            private String route;
            @SerializedName("discount")
            @Expose
            private Integer discount;
            @SerializedName("variables")
            @Expose
            private List<Variable> variables = null;
            @SerializedName("brand")
            @Expose
            private Brand brand;
            @SerializedName("images")
            @Expose
            private List<String> images = null;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getRoute() {
                return route;
            }

            public void setRoute(String route) {
                this.route = route;
            }

            public Integer getDiscount() {
                return discount;
            }

            public void setDiscount(Integer discount) {
                this.discount = discount;
            }

            public List<Variable> getVariables() {
                return variables;
            }

            public void setVariables(List<Variable> variables) {
                this.variables = variables;
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

            public class Variable {

                @SerializedName("price")
                @Expose
                private Price price;
                @SerializedName("stock")
                @Expose
                private Stock stock;

                public Price getPrice() {
                    return price;
                }

                public void setPrice(Price price) {
                    this.price = price;
                }

                public Stock getStock() {
                    return stock;
                }

                public void setStock(Stock stock) {
                    this.stock = stock;
                }

                public class Stock {

                    @SerializedName("quantity")
                    @Expose
                    private Integer quantity;
                    @SerializedName("availableQuantity")
                    @Expose
                    private Integer availableQuantity;

                    public Integer getQuantity() {
                        return quantity;
                    }

                    public void setQuantity(Integer quantity) {
                        this.quantity = quantity;
                    }

                    public Integer getAvailableQuantity() {
                        return availableQuantity;
                    }

                    public void setAvailableQuantity(Integer availableQuantity) {
                        this.availableQuantity = availableQuantity;
                    }

                }

                public class Price {

                    @SerializedName("mrp")
                    @Expose
                    private Integer mrp;
                    @SerializedName("finalPrice")
                    @Expose
                    private Integer finalPrice;
                    @SerializedName("minPrice")
                    @Expose
                    private Integer minPrice;
                    @SerializedName("margin")
                    @Expose
                    private Integer margin;

                    public Integer getMrp() {
                        return mrp;
                    }

                    public void setMrp(Integer mrp) {
                        this.mrp = mrp;
                    }

                    public Integer getFinalPrice() {
                        return finalPrice;
                    }

                    public void setFinalPrice(Integer finalPrice) {
                        this.finalPrice = finalPrice;
                    }

                    public Integer getMinPrice() {
                        return minPrice;
                    }

                    public void setMinPrice(Integer minPrice) {
                        this.minPrice = minPrice;
                    }

                    public Integer getMargin() {
                        return margin;
                    }

                    public void setMargin(Integer margin) {
                        this.margin = margin;
                    }

                }


            }

            public class Brand {

                @SerializedName("brand")
                @Expose
                private Integer brand;
                @SerializedName("brandName")
                @Expose
                private String brandName;

                public Integer getBrand() {
                    return brand;
                }

                public void setBrand(Integer brand) {
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
    }
}