package com.beyza.beyzrestaurant.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import com.beyza.beyzrestaurant.R;
import maes.tech.intentanim.CustomIntent;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        delayerAndToAuth();

    }

    private void delayerAndToAuth() {

        sharedPreferences = getSharedPreferences("authentication", Context.MODE_PRIVATE);

        CountDownTimer countDownTimer = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {

                if (sharedPreferences.getBoolean("authentication", false)) {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    CustomIntent.customType(SplashActivity.this, "fadein-to-fadeout");
                    finish();
                }else {
                    Intent intent = new Intent(SplashActivity.this, AuthenticationActivity.class);
                    startActivity(intent);
                    CustomIntent.customType(SplashActivity.this, "fadein-to-fadeout");
                    finish();
                }

            }
        }.start();
    }
}