package com.beyza.beyzrestaurant.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper2 extends SQLiteOpenHelper {
    public DatabaseHelper2(@Nullable Context context) {
        super(context, "basket.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS basket (id INTEGER PRIMARY KEY AUTOINCREMENT, mealname TEXT, price INTEGER);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS basket");
        onCreate(db);
    }
}
