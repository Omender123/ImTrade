package com.trade.imtrade.Model.ResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContinueYourHuntResponse {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("products")
    @Expose
    private List<Product> products = null;
    @SerializedName("userId")
    @Expose
    private String userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public class Product {

        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("discount")
        @Expose
        private String discount;
        @SerializedName("route")
        @Expose
        private String route;
        @SerializedName("variables")
        @Expose
        private List<Variable> variables = null;
        @SerializedName("images")
        @Expose
        private List<String> images = null;

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

        public List<Variable> getVariables() {
            return variables;
        }

        public void setVariables(List<Variable> variables) {
            this.variables = variables;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
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

            public class Stock {

                @SerializedName("quantity")
                @Expose
                private String quantity;
                @SerializedName("availableQuantity")
                @Expose
                private String availableQuantity;

                public String getQuantity() {
                    return quantity;
                }

                public void setQuantity(String quantity) {
                    this.quantity = quantity;
                }

                public String getAvailableQuantity() {
                    return availableQuantity;
                }

                public void setAvailableQuantity(String availableQuantity) {
                    this.availableQuantity = availableQuantity;
                }

            }
        }
    }
}
