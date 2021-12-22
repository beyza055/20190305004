package com.beyza.beyzrestaurant.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.beyza.beyzrestaurant.R;
import maes.tech.intentanim.CustomIntent;

public class MainActivity extends AppCompatActivity {
    private ImageView imageViewMainExit;
    private CardView cardViewMenuMainCourses, cardViewMenuDesserts, cardViewMenuDrinks, cardViewMenuMyBasket;
    private ConstraintLayout constraintLayoutMenuMainCourses, constraintLayoutMenuDesserts, constraintLayoutMenuDrinks, constraintLayoutMenuMyBasket;
    private TextView textViewMenuMainCourses, textViewMenuDesserts, textViewMenuDrinks, textViewMenuMyBasket;
    private SharedPreferences sharedPreferences = null;
    private SharedPreferences.Editor editor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewsById();

        setOnTouchListeners();

        setOnClickListeners();

    }

    private void setOnTouchListeners() {
        cardViewMenuMainCourses.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    constraintLayoutMenuMainCourses.setBackgroundColor(getResources().getColor(R.color.background));
                    textViewMenuMainCourses.setTextColor(Color.WHITE);
                }else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    constraintLayoutMenuMainCourses.setBackground(getResources().getDrawable(R.drawable.menucardbackground));
                    textViewMenuMainCourses.setTextColor(getResources().getColor(R.color.background));
                }else if (motionEvent.getAction() == MotionEvent.ACTION_CANCEL) {
                    constraintLayoutMenuMainCourses.setBackground(getResources().getDrawable(R.drawable.menucardbackground));
                    textViewMenuMainCourses.setTextColor(getResources().getColor(R.color.background));
                }
                return false;
            }
        });
        cardViewMenuDesserts.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    constraintLayoutMenuDesserts.setBackgroundColor(getResources().getColor(R.color.background));
                    textViewMenuDesserts.setTextColor(Color.WHITE);
                }else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    constraintLayoutMenuDesserts.setBackground(getResources().getDrawable(R.drawable.menucardbackground));
                    textViewMenuDesserts.setTextColor(getResources().getColor(R.color.background));
                }else if (motionEvent.getAction() == MotionEvent.ACTION_CANCEL) {
                    constraintLayoutMenuDesserts.setBackground(getResources().getDrawable(R.drawable.menucardbackground));
                    textViewMenuDesserts.setTextColor(getResources().getColor(R.color.background));
                }
                return false;
            }
        });
        cardViewMenuDrinks.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    constraintLayoutMenuDrinks.setBackgroundColor(getResources().getColor(R.color.background));
                    textViewMenuDrinks.setTextColor(Color.WHITE);
                }else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    constraintLayoutMenuDrinks.setBackground(getResources().getDrawable(R.drawable.menucardbackground));
                    textViewMenuDrinks.setTextColor(getResources().getColor(R.color.background));
                }else if (motionEvent.getAction() == MotionEvent.ACTION_CANCEL) {
                    constraintLayoutMenuDrinks.setBackground(getResources().getDrawable(R.drawable.menucardbackground));
                    textViewMenuDrinks.setTextColor(getResources().getColor(R.color.background));
                }
                return false;
            }
        });

        cardViewMenuMyBasket.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    constraintLayoutMenuMyBasket.setBackgroundColor(getResources().getColor(R.color.background));
                    textViewMenuMyBasket.setTextColor(Color.WHITE);
                }else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    constraintLayoutMenuMyBasket.setBackground(getResources().getDrawable(R.drawable.menucardbackground));
                    textViewMenuMyBasket.setTextColor(getResources().getColor(R.color.background));
                }else if (motionEvent.getAction() == MotionEvent.ACTION_CANCEL) {
                    constraintLayoutMenuMyBasket.setBackground(getResources().getDrawable(R.drawable.menucardbackground));
                    textViewMenuMyBasket.setTextColor(getResources().getColor(R.color.background));
                }
                return false;
            }
        });
    }

    private void setOnClickListeners() {
        imageViewMainExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences = getSharedPreferences("authentication", Context.MODE_PRIVATE);
                editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();

                Intent intent = new Intent(MainActivity.this, SplashActivity.class);
                startActivity(intent);
                CustomIntent.customType(MainActivity.this, "fadein-to-fadeout");
                finish();

            }
        });

        cardViewMenuMainCourses.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View view) {
                constraintLayoutMenuMainCourses.setBackground(getResources().getDrawable(R.drawable.menucardbackground));
                textViewMenuMainCourses.setTextColor(getResources().getColor(R.color.background));

                Intent intent = new Intent(MainActivity.this, MealsActivity.class);
                intent.putExtra("categoryname", "Main Courses");
                startActivity(intent);
                CustomIntent.customType(MainActivity.this, "fadein-to-fadeout");
                finish();

            }
        });
        cardViewMenuDesserts.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View view) {
                constraintLayoutMenuDesserts.setBackground(getResources().getDrawable(R.drawable.menucardbackground));
                textViewMenuDesserts.setTextColor(getResources().getColor(R.color.background));

                Intent intent = new Intent(MainActivity.this, MealsActivity.class);
                intent.putExtra("categoryname", "Desserts");
                startActivity(intent);
                CustomIntent.customType(MainActivity.this, "fadein-to-fadeout");
                finish();

            }
        });
        cardViewMenuDrinks.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View view) {
                constraintLayoutMenuDrinks.setBackground(getResources().getDrawable(R.drawable.menucardbackground));
                textViewMenuDrinks.setTextColor(getResources().getColor(R.color.background));

                Intent intent = new Intent(MainActivity.this, MealsActivity.class);
                intent.putExtra("categoryname", "Drinks");
                startActivity(intent);
                CustomIntent.customType(MainActivity.this, "fadein-to-fadeout");
                finish();

            }
        });
        cardViewMenuMyBasket.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View view) {
                constraintLayoutMenuMyBasket.setBackground(getResources().getDrawable(R.drawable.menucardbackground));
                textViewMenuMyBasket.setTextColor(getResources().getColor(R.color.background));

                Intent intent = new Intent(MainActivity.this, BasketActivity.class);
                startActivity(intent);
                CustomIntent.customType(MainActivity.this, "fadein-to-fadeout");
                finish();

            }
        });
    }

    private void findViewsById() {
        imageViewMainExit = findViewById(R.id.imageViewMainExit);
        cardViewMenuMainCourses = findViewById(R.id.cardViewMenuMainCourses);
        cardViewMenuDesserts = findViewById(R.id.cardViewMenuDesserts);
        cardViewMenuDrinks = findViewById(R.id.cardViewMenuDrinks);
        cardViewMenuMyBasket = findViewById(R.id.cardViewMenuMyBasket);
        constraintLayoutMenuMainCourses = findViewById(R.id.constraintLayoutMenuMainCourses);
        constraintLayoutMenuDesserts = findViewById(R.id.constraintLayoutMenuDesserts);
        constraintLayoutMenuDrinks = findViewById(R.id.constraintLayoutMenuDrinks);
        constraintLayoutMenuMyBasket = findViewById(R.id.constraintLayoutMenuMyBasket);
        textViewMenuMainCourses = findViewById(R.id.textViewMenuMainCourses);
        textViewMenuDesserts = findViewById(R.id.textViewMenuDesserts);
        textViewMenuDrinks = findViewById(R.id.textViewMenuDrinks);
        textViewMenuMyBasket = findViewById(R.id.textViewMenuMyBasket);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("CLOSE APP");
        builder.setMessage("Are you sure you want to close the application?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}