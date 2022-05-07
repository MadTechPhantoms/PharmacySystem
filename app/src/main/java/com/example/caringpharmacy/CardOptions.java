package com.example.caringpharmacy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CardOptions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_options);
    }

    public void openAddCard(View view) {
        Intent intent = new Intent(this, AddCard.class);

        startActivity(intent);
    }
    public void openSelectViewCard(View view) {
        Intent intent = new Intent(this, SelectViewCard.class);

        startActivity(intent);
    }
    public void openSelectUpdateCard(View view) {
        Intent intent = new Intent(this, SelectUpdateCard.class);

        startActivity(intent);
    }
}