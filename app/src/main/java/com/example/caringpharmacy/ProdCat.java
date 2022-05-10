package com.example.caringpharmacy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProdCat extends AppCompatActivity {

    Button addprod;
    Button categ1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prod_cat);

        addprod = findViewById(R.id.btn_addprod);
        addprod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProdCat.this,AddProd.class);
                startActivity(intent);
            }
        });

        categ1 = findViewById(R.id.btn_categ1);
        categ1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProdCat.this,ProdView.class);
                startActivity(intent);
            }
        });
    }
}