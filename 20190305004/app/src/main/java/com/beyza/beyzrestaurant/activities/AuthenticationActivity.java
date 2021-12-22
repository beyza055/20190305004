package com.beyza.beyzrestaurant.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.beyza.beyzrestaurant.R;
import maes.tech.intentanim.CustomIntent;

public class AuthenticationActivity extends AppCompatActivity {
    private CardView cardViewAuthenticationSignin, cardViewAuthenticationSignup = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        findViewsById();

        clickListeners();

    }

    private void findViewsById() {
        cardViewAuthenticationSignin = findViewById(R.id.cardViewAuthenticationSignin);
        cardViewAuthenticationSignup = findViewById(R.id.cardViewAuthenticationSignup);
    }

    private void clickListeners() {
        cardViewAuthenticationSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AuthenticationActivity.this, SigninActivity.class);
                startActivity(intent);
                CustomIntent.customType(AuthenticationActivity.this, "left-to-right");
                finish();
            }
        });

        cardViewAuthenticationSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AuthenticationActivity.this, SignupActivity.class);
                startActivity(intent);
                CustomIntent.customType(AuthenticationActivity.this, "right-to-left");
                finish();
            }
        });
    }

}