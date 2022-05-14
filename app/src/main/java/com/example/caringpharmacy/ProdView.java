package com.example.caringpharmacy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ProdView extends AppCompatActivity {

    RecyclerView recview;
    Prodadapter adapter;
    Button addprod;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prod_view);

        addprod = findViewById(R.id.btn_addprod);
        addprod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProdView.this,AddProd.class);
                startActivity(intent);
            }
        });

        back = findViewById(R.id.img_backhome);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProdView.this,ProdMainActivity.class);
                startActivity(intent);
            }
        });

        recview = (RecyclerView) findViewById(R.id.recview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recview.setLayoutManager(gridLayoutManager);

        FirebaseRecyclerOptions<modelprod> options =
                new FirebaseRecyclerOptions.Builder<modelprod>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Product"), modelprod.class)
                        .build();

        adapter = new Prodadapter(options);
        recview.setAdapter(adapter);

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
