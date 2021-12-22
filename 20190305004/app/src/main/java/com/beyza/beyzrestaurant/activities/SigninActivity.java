package com.beyza.beyzrestaurant.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.transition.ChangeClipBounds;
import android.transition.TransitionManager;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.beyza.beyzrestaurant.R;
import com.beyza.beyzrestaurant.databases.AuthenticationDao;
import com.beyza.beyzrestaurant.databases.DatabaseCopyHelper3;
import com.beyza.beyzrestaurant.databases.DatabaseHelper3;
import com.google.android.material.snackbar.Snackbar;
import java.io.IOException;
import maes.tech.intentanim.CustomIntent;

public class SigninActivity extends AppCompatActivity {
    private ImageView imageViewSigninCancel = null;
    private EditText editTextSigninEmail, editTextSigninPassword = null;
    private CardView cardViewSignin = null;
    private ConstraintLayout constraintLayoutCardSignin = null;
    private TextView textViewCardSignin = null;

    private SharedPreferences sharedPreferences = null;
    private SharedPreferences.Editor editor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        findViewsById();

        copyDatabase();
        
        setOnClickListeners();

        signInController();

    }

    private void findViewsById() {
        imageViewSigninCancel = findViewById(R.id.imageViewSigninCancel);
        editTextSigninEmail = findViewById(R.id.editTextSigninEmail);
        editTextSigninPassword = findViewById(R.id.editTextSigninPassword);
        cardViewSignin = findViewById(R.id.cardViewSignin);
        constraintLayoutCardSignin = findViewById(R.id.constraintLayoutCardSignin);
        textViewCardSignin = findViewById(R.id.textViewCardSignin);
    }

    private void copyDatabase() {
        DatabaseCopyHelper3 databaseCopyHelper3 = new DatabaseCopyHelper3(SigninActivity.this);

        try {
            databaseCopyHelper3.createDataBase();
        }catch (IOException e) {
            Log.e("Database copy hatası", e.getMessage());
        }
    }

    private void setOnClickListeners() {
        imageViewSigninCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void signInController() {
        cardViewSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextSigninEmail.getText().toString().trim();
                String password = editTextSigninPassword.getText().toString().trim();

                if (Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length() != 3) {

                    final DatabaseHelper3 databaseHelper3 = new DatabaseHelper3(SigninActivity.this);

                    boolean userExists = new AuthenticationDao().checkuser(databaseHelper3, email, password);

                    int v = (textViewCardSignin.getVisibility() == View.VISIBLE) ? View.GONE : View.VISIBLE;

                    if (v == View.GONE) {
                        TransitionManager.beginDelayedTransition(constraintLayoutCardSignin, new ChangeClipBounds());
                        textViewCardSignin.setVisibility(v);

                        CountDownTimer countDownTimer = new CountDownTimer(3000, 1000) {
                            @Override
                            public void onTick(long l) {

                            }

                            @Override
                            public void onFinish() {

                                sharedPreferences = getSharedPreferences("authentication", Context.MODE_PRIVATE);
                                editor = sharedPreferences.edit();

                                if (userExists) {
                                    editor.clear();
                                    editor.putBoolean("authentication", true);
                                    editor.apply();

                                    Intent intent = new Intent(SigninActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    CustomIntent.customType(SigninActivity.this, "fadein-to-fadeout");
                                    finish();
                                }else {
                                    textViewCardSignin.setVisibility(View.VISIBLE);
                                    Snackbar.make(view, "No user found.", Snackbar.LENGTH_SHORT).show();
                                }


                            }
                        }.start();

                    }

                }else {
                    Snackbar.make(view, "Please check your authentication information", Snackbar.LENGTH_INDEFINITE).setAction("OK", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    }).show();
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SigninActivity.this, AuthenticationActivity.class);
        startActivity(intent);
        CustomIntent.customType(SigninActivity.this, "right-to-left");
        finish();
    }
}