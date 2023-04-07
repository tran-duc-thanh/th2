package com.example.th2.model;

public class Cat {
    private int img;
    private String name;
    private double price;
    private String info;

    public Cat() {
    }

    public Cat(int img, String name, double price, String info) {
        this.img = img;
        this.name = name;
        this.price = price;
        this.info = info;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
