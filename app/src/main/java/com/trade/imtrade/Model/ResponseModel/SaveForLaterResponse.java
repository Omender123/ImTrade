package com.trade.imtrade.Model.ResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SaveForLaterResponse {

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
        @SerializedName("stockVisible")
        @Expose
        private Boolean stockVisible;
        @SerializedName("averageRating")
        @Expose
        private String averageRating;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("discount")
        @Expose
        private String discount;
        @SerializedName("variables")
        @Expose
        private List<Variable> variables = null;
        @SerializedName("brand")
        @Expose
        private Brand brand;
        @SerializedName("categories")
        @Expose
        private Categories categories;
        @SerializedName("images")
        @Expose
        private List<String> images = null;
        @SerializedName("storage")
        @Expose
        private List<Storage> storage = null;
        @SerializedName("productColor")
        @Expose
        private String productColor;
        @SerializedName("productStorage")
        @Expose
        private String productStorage;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Boolean getStockVisible() {
            return stockVisible;
        }

        public void setStockVisible(Boolean stockVisible) {
            this.stockVisible = stockVisible;
        }

        public String getAverageRating() {
            return averageRating;
        }

        public void setAverageRating(String averageRating) {
            this.averageRating = averageRating;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
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

        public Categories getCategories() {
            return categories;
        }

        public void setCategories(Categories categories) {
            this.categories = categories;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public List<Storage> getStorage() {
            return storage;
        }

        public void setStorage(List<Storage> storage) {
            this.storage = storage;
        }

        public String getProductColor() {
            return productColor;
        }

        public void setProductColor(String productColor) {
            this.productColor = productColor;
        }

        public String getProductStorage() {
            return productStorage;
        }

        public void setProductStorage(String productStorage) {
            this.productStorage = productStorage;
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

        public class Categories {


        }

        public class Brand {

            @SerializedName("brandName")
            @Expose
            private String brandName;

            public String getBrandName() {
                return brandName;
            }

            public void setBrandName(String brandName) {
                this.brandName = brandName;
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
    }
}