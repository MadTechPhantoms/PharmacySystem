package com.example.caringpharmacy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomePage extends AppCompatActivity {

    Button btn_SignInWelcome, btn_SignUpWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        btn_SignInWelcome = findViewById(R.id.btn_SignInWelcome);
        btn_SignUpWelcome = findViewById(R.id.btn_SignUpWelcome);

/*      btn_SignInWelcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/
        btn_SignUpWelcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUp = new Intent(WelcomePage.this,SignUp.class);
                startActivity(signUp);
            }
        });

        btn_SignInWelcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signIn = new Intent(WelcomePage.this,CustomerLogin.class);
                startActivity(signIn);
            }
        });

    }
/*    public void openSignIn(View view) {
        Intent intent = new Intent(this, CustomerLogin.class);

        startActivity(intent);
    }*/
/*    public void openSignUp(View view) {
        Intent intent = new Intent(this, SignUp.class);

        startActivity(intent);
    }*/
}