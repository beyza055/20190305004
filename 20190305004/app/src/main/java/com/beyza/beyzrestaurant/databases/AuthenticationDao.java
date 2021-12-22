package com.beyza.beyzrestaurant.databases;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class AuthenticationDao {

    public boolean addUser(DatabaseHelper3 databaseHelper3, String namelastname, String email, String password) {

        SQLiteDatabase dbx = databaseHelper3.getWritableDatabase();

        @SuppressLint("Recycle") Cursor c = dbx.rawQuery("SELECT * FROM authentication WHERE email =?", new String[]{email});

        if (c.moveToFirst()) {
            Log.e("Durum", "Böyle bir kullanıcı var");
        }else {
            ContentValues contentValues = new ContentValues();
            contentValues.put("namelastname", namelastname);
            contentValues.put("email", email);
            contentValues.put("password", password);
            dbx.insertOrThrow("authentication", null, contentValues);
            dbx.close();

        }

        return c.moveToFirst();
    }

    @SuppressLint("Recycle")
    public boolean checkuser(DatabaseHelper3 databaseHelper3, String email, String password) {
        SQLiteDatabase dbx = databaseHelper3.getWritableDatabase();
        Cursor c = dbx.rawQuery("SELECT * FROM authentication WHERE email=? AND password=?", new String[]{email, password});

        return c.moveToFirst();
    }

}
