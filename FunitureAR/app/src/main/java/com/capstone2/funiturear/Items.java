package com.capstone2.funiturear;

import java.util.List;

public class Items {
    private String name;
    private String isAR;
    private String image;
    private String price;
    private String quantity;
    private String quantity1;
    private String title;
    private  String description;
    private String model;
    public Items() {
    }

    public Items(String name, String isAR, String image, String price, String quantity, String title, String description,String model) {
        this.name = name;
        this.isAR = isAR;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.title = title;
        this.description = description;
        this.model=model;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getisAR() {
        return isAR;
    }

    public void setisAR(String isAR) {
        this.isAR = isAR;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    public String getQuantity1() {
        return quantity1;
    }

    public void setQuantity1(String quantity1) {
        this.quantity1 = quantity1;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
