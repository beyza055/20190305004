package com.beyza.beyzrestaurant.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.beyza.beyzrestaurant.databases.BasketDao;
import com.beyza.beyzrestaurant.databases.DatabaseCopyHelper2;
import com.beyza.beyzrestaurant.databases.DatabaseHelper2;
import com.beyza.beyzrestaurant.databases.MealDao;
import com.beyza.beyzrestaurant.models.Basket;
import com.beyza.beyzrestaurant.adapters.BasketAdapter;
import com.beyza.beyzrestaurant.adapters.MealAdapter;
import com.beyza.beyzrestaurant.R;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import maes.tech.intentanim.CustomIntent;

public class BasketActivity extends AppCompatActivity {
    private ImageView imageViewBasketBack, imageViewBasketDelete, imageViewBasketPrint = null;
    private RecyclerView recyclerViewBasket = null;
    @SuppressLint("StaticFieldLeak")
    public static TextView textViewBasketPrice = null;
    private BasketAdapter basketAdapter = null;
    private ArrayList<Basket> inMyBasketArrayList = new ArrayList<>();

    private DatabaseHelper2 databaseHelper2 = new DatabaseHelper2(BasketActivity.this);
    private BasketDao basketDao = new BasketDao();
    public static double totalprice = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        findViewsById();

        copyDatabase();

        setTotalPrice();

        setOnClickListeners();

        initializeRecyclerView();

        addBasketToArrayList();

        setAdapterRecyclerView();

    }

    private void findViewsById() {
        imageViewBasketBack = findViewById(R.id.imageViewBasketBack);
        imageViewBasketDelete = findViewById(R.id.imageViewBasketDelete);
        imageViewBasketPrint = findViewById(R.id.imageViewBasketPrint);
        recyclerViewBasket = findViewById(R.id.recyclerViewBasket);
        textViewBasketPrice = findViewById(R.id.textViewBasketPrice);
    }

    private void copyDatabase() {
        DatabaseCopyHelper2 databaseCopyHelper2 = new DatabaseCopyHelper2(BasketActivity.this);

        try {
            databaseCopyHelper2.createDataBase();
        }catch (IOException e) {
            Log.e("Database Copy Hatası", e.getMessage());
        }


        databaseCopyHelper2.openDataBase();
    }

    private void setTotalPrice() {
        textViewBasketPrice.setText(String.valueOf(totalprice));
    }

    private void setOnClickListeners() {
        imageViewBasketBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        imageViewBasketDelete.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                basketDao.deleteBasket(databaseHelper2);
                MealAdapter.inMyBasketArrayList.clear();
                inMyBasketArrayList.clear();
                totalprice = 0.0;
                basketAdapter.notifyDataSetChanged();
                textViewBasketPrice.setText("0.0");

                Snackbar.make(view, "Items in your basket have been successfully deleted.", Snackbar.LENGTH_SHORT)
                        .setBackgroundTint(getResources().getColor(R.color.white))
                        .setTextColor(getResources().getColor(R.color.background))
                        .show();
            }
        });
        imageViewBasketPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuffer stringBuffer = new StringBuffer();
                String name = "BeyzRestaurant.txt";
                String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();

                File file = new File(path, name);

                for (Basket basket : inMyBasketArrayList
                     ) {
                    stringBuffer.append("Id: "+basket.getId()+"\n"+"Meal name: "+basket.getMealname()+"\n"+"Price: "+basket.getPrice()+"\n\n");
                }



                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(stringBuffer.toString().getBytes());
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void initializeRecyclerView() {
        recyclerViewBasket.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BasketActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerViewBasket.setLayoutManager(linearLayoutManager);
    }

    private void addBasketToArrayList() {

        totalprice = 0.0;

        inMyBasketArrayList = basketDao.allBasketArrayList(databaseHelper2);

        for (Basket basket : inMyBasketArrayList
             ) {
            totalprice+= basket.getPrice();
            textViewBasketPrice.setText(String.valueOf(totalprice));
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    private void setAdapterRecyclerView() {
        basketAdapter = new BasketAdapter(BasketActivity.this, inMyBasketArrayList);
        basketAdapter.notifyDataSetChanged();
        recyclerViewBasket.setAdapter(basketAdapter);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(BasketActivity.this, MainActivity.class);
        startActivity(intent);
        CustomIntent.customType(BasketActivity.this, "fadein-to-fadeout");
        finish();
    }
}