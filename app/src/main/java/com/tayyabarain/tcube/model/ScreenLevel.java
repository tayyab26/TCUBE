package com.tayyabarain.tcube.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Tayyab Arain on 17/05/2017.
 */
public class ScreenLevel {

    @SerializedName("ProductSubCategory")
    @Expose
    private List<ProductSubCategory> productSubCategory = null;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("isPromotion")
    @Expose
    private Boolean isPromotion;
    @SerializedName("title")
    @Expose
    private String title;

    public List<ProductSubCategory> getProductSubCategory() {
        return productSubCategory;
    }

    public void setProductSubCategory(List<ProductSubCategory> productSubCategory) {
        this.productSubCategory = productSubCategory;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIsPromotion() {
        return isPromotion;
    }

    public void setIsPromotion(Boolean isPromotion) {
        this.isPromotion = isPromotion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
