package com.beyza.beyzrestaurant.databases;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.beyza.beyzrestaurant.models.Basket;
import java.util.ArrayList;

public class BasketDao {

    public void addBasket(DatabaseHelper2 databaseHelper2, String mealname, double price) {
        SQLiteDatabase dbx = databaseHelper2.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("mealname", mealname);
        contentValues.put("price", price);
        dbx.insertOrThrow("basket", null, contentValues);
        dbx.close();
    }

    public ArrayList<Basket> allBasketArrayList(DatabaseHelper2 databaseHelper2) {
        ArrayList<Basket> basketArrayList = new ArrayList<>();
        SQLiteDatabase dbx = databaseHelper2.getWritableDatabase();
        Cursor c = dbx.rawQuery("SELECT * FROM basket", null);

        while (c.moveToNext()) {
            @SuppressLint("Range") Basket basket = new Basket(c.getInt(c.getColumnIndex("id")), c.getString(c.getColumnIndex("mealname")), c.getInt(c.getColumnIndex("price")));
            basketArrayList.add(basket);
        }

        return basketArrayList;
    }

    public void deleteBasket(DatabaseHelper2 databaseHelper2) {
        SQLiteDatabase dbx = databaseHelper2.getWritableDatabase();
        dbx.delete("basket", null, null);
        dbx.close();
    }

    public void deleteMealFromBasket(DatabaseHelper2 databaseHelper2, int id) {
        SQLiteDatabase dbx = databaseHelper2.getWritableDatabase();
        dbx.delete("basket", "id=?", new String[]{String.valueOf(id)});
        dbx.close();
    }

}
