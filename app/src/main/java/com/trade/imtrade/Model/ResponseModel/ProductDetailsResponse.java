package com.trade.imtrade.Model.ResponseModel;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductDetailsResponse {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("imageTags")
    @Expose
    private List<Object> imageTags = null;
    @SerializedName("sellers")
    @Expose
    private List<String> sellers = null;
    @SerializedName("edited")
    @Expose
    private Object edited;
    @SerializedName("published")
    @Expose
    private Object published;
    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("metaTags")
    @Expose
    private List<Object> metaTags = null;
    @SerializedName("seasons")
    @Expose
    private List<Object> seasons = null;
    @SerializedName("stockVisible")
    @Expose
    private Boolean stockVisible;
    @SerializedName("inactivePayment")
    @Expose
    private List<Object> inactivePayment = null;
    @SerializedName("averageRating")
    @Expose
    private String averageRating;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("route")
    @Expose
    private String route;
    @SerializedName("discount")
    @Expose
    private String discount;
    @SerializedName("variables")
    @Expose
    private List<Variable> variables = null;
    @SerializedName("reviews")
    @Expose
    private List<Review> reviews = null;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("brand")
    @Expose
    private Brand brand;
    @SerializedName("categories")
    @Expose
    private Categories categories;
    @SerializedName("description")
    @Expose
    private Description description;
    @SerializedName("images")
    @Expose
    private List<String> images = null;
    @SerializedName("storage")
    @Expose
    private List<Storage> storage = null;
    @SerializedName("details")
    @Expose
    private List<Detail> details = null;
    @SerializedName("popularProducts")
    @Expose
    private Boolean popularProducts;
    @SerializedName("discountForYou")
    @Expose
    private Boolean discountForYou;
    @SerializedName("sesionProducts")
    @Expose
    private Boolean sesionProducts;
    @SerializedName("is")
    @Expose
    private Is is;
    @SerializedName("related")
    @Expose
    private Related related;
    @SerializedName("dashboardDod")
    @Expose
    private String dashboardDod;
    @SerializedName("productColor")
    @Expose
    private String productColor;
    @SerializedName("productStorage")
    @Expose
    private String productStorage;
    @SerializedName("color")
    @Expose
    private List<Color> color = null;
    @SerializedName("addOn")
    @Expose
    private List<AddOn> addOn = null;
    @SerializedName("otherInfo")
    @Expose
    private List<OtherInfo__1> otherInfo = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Object> getImageTags() {
        return imageTags;
    }

    public void setImageTags(List<Object> imageTags) {
        this.imageTags = imageTags;
    }

    public List<String> getSellers() {
        return sellers;
    }

    public void setSellers(List<String> sellers) {
        this.sellers = sellers;
    }

    public Object getEdited() {
        return edited;
    }

    public void setEdited(Object edited) {
        this.edited = edited;
    }

    public Object getPublished() {
        return published;
    }

    public void setPublished(Object published) {
        this.published = published;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Object> getMetaTags() {
        return metaTags;
    }

    public void setMetaTags(List<Object> metaTags) {
        this.metaTags = metaTags;
    }

    public List<Object> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<Object> seasons) {
        this.seasons = seasons;
    }

    public Boolean getStockVisible() {
        return stockVisible;
    }

    public void setStockVisible(Boolean stockVisible) {
        this.stockVisible = stockVisible;
    }

    public List<Object> getInactivePayment() {
        return inactivePayment;
    }

    public void setInactivePayment(List<Object> inactivePayment) {
        this.inactivePayment = inactivePayment;
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

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
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

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
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

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
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

    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }

    public Boolean getPopularProducts() {
        return popularProducts;
    }

    public void setPopularProducts(Boolean popularProducts) {
        this.popularProducts = popularProducts;
    }

    public Boolean getDiscountForYou() {
        return discountForYou;
    }

    public void setDiscountForYou(Boolean discountForYou) {
        this.discountForYou = discountForYou;
    }

    public Boolean getSesionProducts() {
        return sesionProducts;
    }

    public void setSesionProducts(Boolean sesionProducts) {
        this.sesionProducts = sesionProducts;
    }

    public Is getIs() {
        return is;
    }

    public void setIs(Is is) {
        this.is = is;
    }

    public Related getRelated() {
        return related;
    }

    public void setRelated(Related related) {
        this.related = related;
    }

    public String getDashboardDod() {
        return dashboardDod;
    }

    public void setDashboardDod(String dashboardDod) {
        this.dashboardDod = dashboardDod;
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

    public List<Color> getColor() {
        return color;
    }

    public void setColor(List<Color> color) {
        this.color = color;
    }

    public List<AddOn> getAddOn() {
        return addOn;
    }

    public void setAddOn(List<AddOn> addOn) {
        this.addOn = addOn;
    }

    public List<OtherInfo__1> getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(List<OtherInfo__1> otherInfo) {
        this.otherInfo = otherInfo;
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

    public class Review {

        @SerializedName("images")
        @Expose
        private List<Object> images = null;
        @SerializedName("verified")
        @Expose
        private Boolean verified;
        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("review")
        @Expose
        private Review__1 review;

        public List<Object> getImages() {
            return images;
        }

        public void setImages(List<Object> images) {
            this.images = images;
        }

        public Boolean getVerified() {
            return verified;
        }

        public void setVerified(Boolean verified) {
            this.verified = verified;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Review__1 getReview() {
            return review;
        }

        public void setReview(Review__1 review) {
            this.review = review;
        }

        public class Review__1 {

            @SerializedName("_id")
            @Expose
            private String id;
            @SerializedName("like")
            @Expose
            private String like;
            @SerializedName("dislike")
            @Expose
            private String dislike;
            @SerializedName("active")
            @Expose
            private Boolean active;
            @SerializedName("review")
            @Expose
            private String review;
            @SerializedName("image")
            @Expose
            private String image;
            @SerializedName("user")
            @Expose
            private String user;
            @SerializedName("userDetail")
            @Expose
            private String userDetail;
            @SerializedName("createdAt")
            @Expose
            private String createdAt;
            @SerializedName("updatedAt")
            @Expose
            private String updatedAt;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLike() {
                return like;
            }

            public void setLike(String like) {
                this.like = like;
            }

            public String getDislike() {
                return dislike;
            }

            public void setDislike(String dislike) {
                this.dislike = dislike;
            }

            public Boolean getActive() {
                return active;
            }

            public void setActive(Boolean active) {
                this.active = active;
            }

            public String getReview() {
                return review;
            }

            public void setReview(String review) {
                this.review = review;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getUser() {
                return user;
            }

            public void setUser(String user) {
                this.user = user;
            }

            public String getUserDetail() {
                return userDetail;
            }

            public void setUserDetail(String userDetail) {
                this.userDetail = userDetail;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }

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
        @SerializedName("discountedCategories")
        @Expose
        private List<Object> discountedCategories = null;

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

        public List<Object> getDiscountedCategories() {
            return discountedCategories;
        }

        public void setDiscountedCategories(List<Object> discountedCategories) {
            this.discountedCategories = discountedCategories;
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

    public class Storage {

        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("size")
        @Expose
        private String size;
        @SerializedName("detailRoute")
        @Expose
        private String detailRoute;

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

        public String getDetailRoute() {
            return detailRoute;
        }

        public void setDetailRoute(String detailRoute) {
            this.detailRoute = detailRoute;
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

    public class Is {

        @SerializedName("hbRecommended")
        @Expose
        private Boolean hbRecommended;

        public Boolean getHbRecommended() {
            return hbRecommended;
        }

        public void setHbRecommended(Boolean hbRecommended) {
            this.hbRecommended = hbRecommended;
        }

    }

    public class Related {

        @SerializedName("recommended")
        @Expose
        private List<Object> recommended = null;
        @SerializedName("similar")
        @Expose
        private List<String> similar = null;

        public List<Object> getRecommended() {
            return recommended;
        }

        public void setRecommended(List<Object> recommended) {
            this.recommended = recommended;
        }

        public List<String> getSimilar() {
            return similar;
        }

        public void setSimilar(List<String> similar) {
            this.similar = similar;
        }

    }

    public class Color {

        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("image")
        @Expose
        private String image;
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

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getRoute() {
            return route;
        }

        public void setRoute(String route) {
            this.route = route;
        }

    }

    public class AddOn {

        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("imageTags")
        @Expose
        private List<Object> imageTags = null;
        @SerializedName("sellers")
        @Expose
        private List<String> sellers = null;
        @SerializedName("edited")
        @Expose
        private Object edited;
        @SerializedName("published")
        @Expose
        private Object published;
        @SerializedName("active")
        @Expose
        private Boolean active;
        @SerializedName("metaTags")
        @Expose
        private List<Object> metaTags = null;
        @SerializedName("seasons")
        @Expose
        private List<Object> seasons = null;
        @SerializedName("stockVisible")
        @Expose
        private Boolean stockVisible;
        @SerializedName("inactivePayment")
        @Expose
        private List<Object> inactivePayment = null;
        @SerializedName("averageRating")
        @Expose
        private String averageRating;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("route")
        @Expose
        private String route;
        @SerializedName("discount")
        @Expose
        private String discount;
        @SerializedName("variables")
        @Expose
        private List<Variable__1> variables = null;
        @SerializedName("reviews")
        @Expose
        private List<Review__2> reviews = null;
        @SerializedName("createdAt")
        @Expose
        private String createdAt;
        @SerializedName("updatedAt")
        @Expose
        private String updatedAt;
        @SerializedName("brand")
        @Expose
        private Brand__1 brand;
        @SerializedName("categories")
        @Expose
        private Categories__1 categories;
        @SerializedName("description")
        @Expose
        private Description__1 description;
        @SerializedName("images")
        @Expose
        private List<String> images = null;
        @SerializedName("storage")
        @Expose
        private List<Storage__1> storage = null;
        @SerializedName("details")
        @Expose
        private List<Detail__1> details = null;
        @SerializedName("popularProducts")
        @Expose
        private Boolean popularProducts;
        @SerializedName("DealOfTheDay")
        @Expose
        private Boolean dealOfTheDay;
        @SerializedName("discountForYou")
        @Expose
        private Boolean discountForYou;
        @SerializedName("productColor")
        @Expose
        private String productColor;
        @SerializedName("productStorage")
        @Expose
        private String productStorage;
        @SerializedName("noOfProductSold")
        @Expose
        private String noOfProductSold;
        @SerializedName("freshArrival")
        @Expose
        private Boolean freshArrival;
        @SerializedName("festivalOffer")
        @Expose
        private Boolean festivalOffer;
        @SerializedName("sponsoredProduct")
        @Expose
        private Boolean sponsoredProduct;
        @SerializedName("dashboardDod")
        @Expose
        private String dashboardDod;
        @SerializedName("related")
        @Expose
        private Related__1 related;
        @SerializedName("color")
        @Expose
        private List<Color__1> color = null;
        @SerializedName("addOn")
        @Expose
        private List<String> addOn = null;
        @SerializedName("otherInfo")
        @Expose
        private List<OtherInfo> otherInfo = null;
        @SerializedName("sesionProducts")
        @Expose
        private Boolean sesionProducts;
        @SerializedName("is")
        @Expose
        private Is__1 is;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<Object> getImageTags() {
            return imageTags;
        }

        public void setImageTags(List<Object> imageTags) {
            this.imageTags = imageTags;
        }

        public List<String> getSellers() {
            return sellers;
        }

        public void setSellers(List<String> sellers) {
            this.sellers = sellers;
        }

        public Object getEdited() {
            return edited;
        }

        public void setEdited(Object edited) {
            this.edited = edited;
        }

        public Object getPublished() {
            return published;
        }

        public void setPublished(Object published) {
            this.published = published;
        }

        public Boolean getActive() {
            return active;
        }

        public void setActive(Boolean active) {
            this.active = active;
        }

        public List<Object> getMetaTags() {
            return metaTags;
        }

        public void setMetaTags(List<Object> metaTags) {
            this.metaTags = metaTags;
        }

        public List<Object> getSeasons() {
            return seasons;
        }

        public void setSeasons(List<Object> seasons) {
            this.seasons = seasons;
        }

        public Boolean getStockVisible() {
            return stockVisible;
        }

        public void setStockVisible(Boolean stockVisible) {
            this.stockVisible = stockVisible;
        }

        public List<Object> getInactivePayment() {
            return inactivePayment;
        }

        public void setInactivePayment(List<Object> inactivePayment) {
            this.inactivePayment = inactivePayment;
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

        public String getRoute() {
            return route;
        }

        public void setRoute(String route) {
            this.route = route;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public List<Variable__1> getVariables() {
            return variables;
        }

        public void setVariables(List<Variable__1> variables) {
            this.variables = variables;
        }

        public List<Review__2> getReviews() {
            return reviews;
        }

        public void setReviews(List<Review__2> reviews) {
            this.reviews = reviews;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public Brand__1 getBrand() {
            return brand;
        }

        public void setBrand(Brand__1 brand) {
            this.brand = brand;
        }

        public Categories__1 getCategories() {
            return categories;
        }

        public void setCategories(Categories__1 categories) {
            this.categories = categories;
        }

        public Description__1 getDescription() {
            return description;
        }

        public void setDescription(Description__1 description) {
            this.description = description;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public List<Storage__1> getStorage() {
            return storage;
        }

        public void setStorage(List<Storage__1> storage) {
            this.storage = storage;
        }

        public List<Detail__1> getDetails() {
            return details;
        }

        public void setDetails(List<Detail__1> details) {
            this.details = details;
        }

        public Boolean getPopularProducts() {
            return popularProducts;
        }

        public void setPopularProducts(Boolean popularProducts) {
            this.popularProducts = popularProducts;
        }

        public Boolean getDealOfTheDay() {
            return dealOfTheDay;
        }

        public void setDealOfTheDay(Boolean dealOfTheDay) {
            this.dealOfTheDay = dealOfTheDay;
        }

        public Boolean getDiscountForYou() {
            return discountForYou;
        }

        public void setDiscountForYou(Boolean discountForYou) {
            this.discountForYou = discountForYou;
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

        public String getNoOfProductSold() {
            return noOfProductSold;
        }

        public void setNoOfProductSold(String noOfProductSold) {
            this.noOfProductSold = noOfProductSold;
        }

        public Boolean getFreshArrival() {
            return freshArrival;
        }

        public void setFreshArrival(Boolean freshArrival) {
            this.freshArrival = freshArrival;
        }

        public Boolean getFestivalOffer() {
            return festivalOffer;
        }

        public void setFestivalOffer(Boolean festivalOffer) {
            this.festivalOffer = festivalOffer;
        }

        public Boolean getSponsoredProduct() {
            return sponsoredProduct;
        }

        public void setSponsoredProduct(Boolean sponsoredProduct) {
            this.sponsoredProduct = sponsoredProduct;
        }

        public String getDashboardDod() {
            return dashboardDod;
        }

        public void setDashboardDod(String dashboardDod) {
            this.dashboardDod = dashboardDod;
        }

        public Related__1 getRelated() {
            return related;
        }

        public void setRelated(Related__1 related) {
            this.related = related;
        }

        public List<Color__1> getColor() {
            return color;
        }

        public void setColor(List<Color__1> color) {
            this.color = color;
        }

        public List<String> getAddOn() {
            return addOn;
        }

        public void setAddOn(List<String> addOn) {
            this.addOn = addOn;
        }

        public List<OtherInfo> getOtherInfo() {
            return otherInfo;
        }

        public void setOtherInfo(List<OtherInfo> otherInfo) {
            this.otherInfo = otherInfo;
        }

        public Boolean getSesionProducts() {
            return sesionProducts;
        }

        public void setSesionProducts(Boolean sesionProducts) {
            this.sesionProducts = sesionProducts;
        }

        public Is__1 getIs() {
            return is;
        }

        public void setIs(Is__1 is) {
            this.is = is;
        }

        public class Variable__1 {

            @SerializedName("price")
            @Expose
            private ProductDetailsResponse.Variable.Price price;

            public ProductDetailsResponse.Variable.Price getPrice() {
                return price;
            }

            public void setPrice(ProductDetailsResponse.Variable.Price price) {
                this.price = price;
            }

            public class Price__1 {

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

        public class Review__2 {

            @SerializedName("images")
            @Expose
            private List<Object> images = null;
            @SerializedName("verified")
            @Expose
            private Boolean verified;
            @SerializedName("_id")
            @Expose
            private String id;
            @SerializedName("review")
            @Expose
            private String review;

            public List<Object> getImages() {
                return images;
            }

            public void setImages(List<Object> images) {
                this.images = images;
            }

            public Boolean getVerified() {
                return verified;
            }

            public void setVerified(Boolean verified) {
                this.verified = verified;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getReview() {
                return review;
            }

            public void setReview(String review) {
                this.review = review;
            }

        }
        public class Brand__1 {

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

        public class Categories__1 {

            @SerializedName("category")
            @Expose
            private List<String> category = null;
            @SerializedName("subCategory")
            @Expose
            private List<Object> subCategory = null;
            @SerializedName("subSubCategory")
            @Expose
            private List<Object> subSubCategory = null;
            @SerializedName("discountedCategories")
            @Expose
            private List<Object> discountedCategories = null;

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

            public List<Object> getDiscountedCategories() {
                return discountedCategories;
            }

            public void setDiscountedCategories(List<Object> discountedCategories) {
                this.discountedCategories = discountedCategories;
            }

        }

        public class Description__1 {

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

        public class Storage__1 {

            @SerializedName("_id")
            @Expose
            private String id;
            @SerializedName("size")
            @Expose
            private String size;
            @SerializedName("detailRoute")
            @Expose
            private String detailRoute;

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

            public String getDetailRoute() {
                return detailRoute;
            }

            public void setDetailRoute(String detailRoute) {
                this.detailRoute = detailRoute;
            }

        }

        public class Detail__1 {

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

        public class Is__1 {

            @SerializedName("hbRecommended")
            @Expose
            private Boolean hbRecommended;

            public Boolean getHbRecommended() {
                return hbRecommended;
            }

            public void setHbRecommended(Boolean hbRecommended) {
                this.hbRecommended = hbRecommended;
            }

        }

        public class Related__1 {

            @SerializedName("recommended")
            @Expose
            private List<Object> recommended = null;
            @SerializedName("similar")
            @Expose
            private List<String> similar = null;

            public List<Object> getRecommended() {
                return recommended;
            }

            public void setRecommended(List<Object> recommended) {
                this.recommended = recommended;
            }

            public List<String> getSimilar() {
                return similar;
            }

            public void setSimilar(List<String> similar) {
                this.similar = similar;
            }

        }

        public class Color__1 {

            @SerializedName("_id")
            @Expose
            private String id;
            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("image")
            @Expose
            private String image;
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

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getRoute() {
                return route;
            }

            public void setRoute(String route) {
                this.route = route;
            }

        }

        public class OtherInfo {

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
    }

    public class OtherInfo__1 {

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
}


