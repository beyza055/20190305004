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

public class SignupActivity extends AppCompatActivity {
    private ImageView imageViewSignupCancel = null;
    private EditText editTextSignupNameLastname, editTextSignupEmail, editTextSignupPassword;
    private CardView cardViewSignup = null;
    private ConstraintLayout constraintLayoutCardSignupHolder = null;
    private TextView textViewCardSignup = null;

    private SharedPreferences sharedPreferences = null;
    private SharedPreferences.Editor editor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        findViewsById();

        setOnClickListeners();

        copyDatabase();

        signUpController();

    }

    private void findViewsById() {
        imageViewSignupCancel = findViewById(R.id.imageViewSignupCancel);
        editTextSignupNameLastname = findViewById(R.id.editTextSignupNameLastname);
        editTextSignupEmail = findViewById(R.id.editTextSignupEmail);
        editTextSignupPassword = findViewById(R.id.editTextSignupPassword);
        cardViewSignup = findViewById(R.id.cardViewSignup);
        constraintLayoutCardSignupHolder = findViewById(R.id.constraintLayoutCardSignupHolder);
        textViewCardSignup = findViewById(R.id.textViewCardSignup);
    }

    private void setOnClickListeners() {
        imageViewSignupCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void copyDatabase() {
        final DatabaseCopyHelper3 databaseCopyHelper3 = new DatabaseCopyHelper3(SignupActivity.this);
        try {
            databaseCopyHelper3.createDataBase();
        }catch (IOException e) {
            Log.e("Database Copy Hatası", e.getMessage());
        }
    }

    private void signUpController() {
        cardViewSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namelastname = editTextSignupNameLastname.getText().toString().trim();
                String email = editTextSignupEmail.getText().toString().trim();
                String password = editTextSignupPassword.getText().toString().trim();

                if (namelastname.length() != 3 && Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length() != 3) {

                    final DatabaseHelper3 databaseHelper3 = new DatabaseHelper3(SignupActivity.this);

                    boolean controller = new AuthenticationDao().addUser(databaseHelper3, namelastname, email, password);

                    int v = (textViewCardSignup.getVisibility() == View.VISIBLE) ? View.GONE : View.VISIBLE;

                    if (v == View.GONE) {
                        TransitionManager.beginDelayedTransition(constraintLayoutCardSignupHolder, new ChangeClipBounds());
                        textViewCardSignup.setVisibility(v);

                        CountDownTimer countDownTimer = new CountDownTimer(3000, 1000) {
                            @Override
                            public void onTick(long l) {

                            }

                            @Override
                            public void onFinish() {

                                sharedPreferences = getSharedPreferences("authentication", Context.MODE_PRIVATE);
                                editor = sharedPreferences.edit();

                                if (controller) {
                                    Snackbar.make(view, "This email address has been used before.", Snackbar.LENGTH_SHORT).show();
                                    textViewCardSignup.setVisibility(View.VISIBLE);
                                }else {
                                    editor.clear();
                                    editor.putBoolean("authentication", true);
                                    editor.apply();

                                    Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    CustomIntent.customType(SignupActivity.this, "fadein-to-fadeout");
                                    finish();
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
        Intent intent = new Intent(SignupActivity.this, AuthenticationActivity.class);
        startActivity(intent);
        CustomIntent.customType(SignupActivity.this, "left-to-right");
        finish();
    }
}