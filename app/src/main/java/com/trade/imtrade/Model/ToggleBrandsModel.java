package com.trade.imtrade.Model;

public class ToggleBrandsModel {
    String BrandName,checked;

    public ToggleBrandsModel(String brandName, String checked) {
        BrandName = brandName;
        this.checked = checked;
    }

    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String brandName) {
        BrandName = brandName;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }
}
