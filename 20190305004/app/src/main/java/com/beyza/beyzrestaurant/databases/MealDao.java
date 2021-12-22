package com.beyza.beyzrestaurant.databases;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.beyza.beyzrestaurant.models.Meal;

import java.util.ArrayList;

public class MealDao {

    public static ArrayList<Meal> allMainCourses(DatabaseHelper databaseHelper, String where) {
        ArrayList<Meal> mealArrayList = new ArrayList<>();

        SQLiteDatabase dbx = databaseHelper.getWritableDatabase();

        @SuppressLint("Recycle") Cursor c = dbx.rawQuery("SELECT * FROM meal WHERE category = ?; ", new String[]{where}, null);

        while (c.moveToNext()) {
            @SuppressLint("Range") Meal meal = new Meal(c.getInt(c.getColumnIndex("id")), c.getString(c.getColumnIndex("category")), c.getString(c.getColumnIndex("mealname")), c.getInt(c.getColumnIndex("price")));
            mealArrayList.add(meal);
        }

        return mealArrayList;

    }

    public static ArrayList<Meal> allDesserts(DatabaseHelper databaseHelper, String where) {
        ArrayList<Meal> mealArrayList = new ArrayList<>();

        SQLiteDatabase dbx = databaseHelper.getWritableDatabase();

        @SuppressLint("Recycle") Cursor c = dbx.rawQuery("SELECT * FROM meal WHERE category=?", new String[] {where}, null);

        while (c.moveToNext()) {
            @SuppressLint("Range") Meal meal = new Meal(c.getInt(c.getColumnIndex("id")), c.getString(c.getColumnIndex("category")), c.getString(c.getColumnIndex("mealname")), c.getInt(c.getColumnIndex("price")));
            mealArrayList.add(meal);
        }

        return mealArrayList;

    }

    public static ArrayList<Meal> allDrinks(DatabaseHelper databaseHelper, String where) {
        ArrayList<Meal> mealArrayList = new ArrayList<>();

        SQLiteDatabase dbx = databaseHelper.getWritableDatabase();

        @SuppressLint("Recycle") Cursor c = dbx.rawQuery("SELECT * FROM meal WHERE category=?", new String[] {where}, null);

        while (c.moveToNext()) {
            @SuppressLint("Range") Meal meal = new Meal(c.getInt(c.getColumnIndex("id")), c.getString(c.getColumnIndex("category")), c.getString(c.getColumnIndex("mealname")), c.getInt(c.getColumnIndex("price")));
            mealArrayList.add(meal);
        }

        return mealArrayList;
    }
}
