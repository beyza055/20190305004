package com.beyza.beyzrestaurant.models;

public class Meal {
    private int id;
    private String category;
    private String mealname;
    private double price;

    public Meal() {
    }

    public Meal(int id, String category, String mealname, double price) {
        this.id = id;
        this.category = category;
        this.mealname = mealname;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
