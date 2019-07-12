package com.ddg.project.model;

import java.io.Serializable;

/**
 * Created by mostafa kamal khedr on 09,July,2019
 */
public class ProductsModel implements Serializable {

    private int productImage;
    private String productName;
    private String productLink;

    public ProductsModel(String productName, int productImage, String productLink) {
        this.productImage = productImage;
        this.productName = productName;
        this.productLink = productLink;
    }


    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductLink() {
        return productLink;
    }

    public void setProductLink(String productLink) {
        this.productLink = productLink;
    }
}
