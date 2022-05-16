package com.example.caringpharmacy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Cart extends AppCompatActivity {

    RecyclerView cartview;
    Cartadapter adapter;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        back = findViewById(R.id.backabout);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Cart.this,ProdViewCus.class);
                startActivity(intent);
            }
        });

        cartview = (RecyclerView) findViewById(R.id.cartview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        cartview.setLayoutManager(gridLayoutManager);

        FirebaseRecyclerOptions<modelprod> options =
                new FirebaseRecyclerOptions.Builder<modelprod>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Cart"), modelprod.class)
                        .build();

        adapter = new Cartadapter(options);
        cartview.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}