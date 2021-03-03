package com.capstone2.funiturear;

public class order {
    private String userid;
    private String name;
    private double price;
    private int quantity;

    public order() {
    }

    public order(String userid, String name, double price, int quantity) {
        this.userid = userid;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
