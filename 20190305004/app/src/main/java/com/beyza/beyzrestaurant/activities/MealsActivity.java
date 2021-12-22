package com.beyza.beyzrestaurant.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.beyza.beyzrestaurant.databases.DatabaseCopyHelper;
import com.beyza.beyzrestaurant.databases.DatabaseHelper;
import com.beyza.beyzrestaurant.databases.MealDao;
import com.beyza.beyzrestaurant.models.Meal;
import com.beyza.beyzrestaurant.adapters.MealAdapter;
import com.beyza.beyzrestaurant.R;
import java.io.IOException;
import java.util.ArrayList;
import maes.tech.intentanim.CustomIntent;

public class MealsActivity extends AppCompatActivity {
    private ImageView imageViewMealsBack = null;
    private TextView textViewMeals = null;
    private RecyclerView recyclerViewBasket = null;
    private MealAdapter mealAdapter = null;
    private ArrayList<Meal> mealArrayList = new ArrayList<>();

    private DatabaseHelper databaseHelper = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals);

        findViewsById();

        copyDatabase();

        setOnClickListeners();

        setCategoryName();

        initializeRecyclerView();

        addMealToArrayList();

        setAdapterRecyclerView();

    }

    private void findViewsById() {
        imageViewMealsBack = findViewById(R.id.imageViewMealsBack);
        textViewMeals = findViewById(R.id.textViewMeals);
        recyclerViewBasket = findViewById(R.id.recyclerViewBasket);
    }

    private void copyDatabase() {
        DatabaseCopyHelper databaseCopyHelper = new DatabaseCopyHelper(MealsActivity.this);

        try {
            databaseCopyHelper.createDataBase();
        }catch (IOException e) {
            Log.e("Database Copy Hatası", e.getMessage());
        }


        databaseCopyHelper.openDataBase();
    }

    private void setOnClickListeners() {
        imageViewMealsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void setCategoryName() {
        Intent intent = getIntent();

        try {
            textViewMeals.setText(intent.getStringExtra("categoryname"));
        }catch (Exception e) {
            Log.e("Hata",e.getMessage());
        }

    }

    private void initializeRecyclerView() {
        recyclerViewBasket.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MealsActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerViewBasket.setLayoutManager(linearLayoutManager);
    }

    private void addMealToArrayList() {

        databaseHelper = new DatabaseHelper(MealsActivity.this);

        if (textViewMeals.getText().toString().equals("Main Courses")) {
            mealArrayList = MealDao.allMainCourses(databaseHelper, "Main Courses");
        }else if (textViewMeals.getText().toString().equals("Desserts")) {
            mealArrayList = MealDao.allDesserts(databaseHelper, "Desserts");
        }else if (textViewMeals.getText().toString().equals("Drinks")) {
            mealArrayList = MealDao.allDrinks(databaseHelper, "Drinks");
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    private void setAdapterRecyclerView() {
        mealAdapter = new MealAdapter(MealsActivity.this, mealArrayList);
        mealAdapter.notifyDataSetChanged();
        recyclerViewBasket.setAdapter(mealAdapter);
    }

    @Override
    public void onBackPressed() {

        if (textViewMeals.getText().equals("Main Courses")) {
            Intent intent = new Intent(MealsActivity.this, MainActivity.class);
            startActivity(intent);
            CustomIntent.customType(MealsActivity.this, "fadein-to-fadeout");
            finish();
        }else if (textViewMeals.getText().equals("Desserts")) {
            Intent intent = new Intent(MealsActivity.this, MainActivity.class);
            startActivity(intent);
            CustomIntent.customType(MealsActivity.this, "fadein-to-fadeout");
            finish();
        }else if (textViewMeals.getText().equals("Drinks")) {
            Intent intent = new Intent(MealsActivity.this, MainActivity.class);
            startActivity(intent);
            CustomIntent.customType(MealsActivity.this, "fadein-to-fadeout");
            finish();
        }


    }
}