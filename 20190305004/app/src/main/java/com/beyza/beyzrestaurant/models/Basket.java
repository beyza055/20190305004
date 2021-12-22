package com.beyza.beyzrestaurant.models;

public class Basket {
    private int id;
    private String mealname;
    private double price;

    public Basket() {
    }

    public Basket(int id, String mealname, double price) {
        this.id = id;
        this.mealname = mealname;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMealname() {
        return mealname;
    }

    public void setMealname(String mealname) {
        this.mealname = mealname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
