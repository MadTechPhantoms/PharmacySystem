package com.example.caringpharmacy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProdMainActivity extends AppCompatActivity {

    Button prod, feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_prod);

        prod = findViewById(R.id.btn_prod);
        prod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProdMainActivity.this,ProdView.class);
                startActivity(intent);
            }
        });

        feedback = findViewById(R.id.btn_feed);
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProdMainActivity.this,AdminFeedbackView.class);
                startActivity(intent);
            }
        });
    }
}