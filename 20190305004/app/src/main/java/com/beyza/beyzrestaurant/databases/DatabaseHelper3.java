package com.beyza.beyzrestaurant.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper3 extends SQLiteOpenHelper {
    public DatabaseHelper3(@Nullable Context context) {
        super(context, "authentication.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS authentication (id INTEGER PRIMARY KEY AUTOINCREMENT, namelastname TEXT, email TEXT, password TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS authentication");
        onCreate(db);
    }
}
