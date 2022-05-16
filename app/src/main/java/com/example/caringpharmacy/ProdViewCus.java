package com.example.caringpharmacy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ProdViewCus extends AppCompatActivity {

    RecyclerView cusrecview;
    Cusprodadapter adapter;
    TextView txt_option;
    ImageButton cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prod_view_cus);

        cart = findViewById(R.id.btn_cart);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProdViewCus.this,Cart.class);
                startActivity(intent);
            }
        });

        cusrecview = (RecyclerView) findViewById(R.id.cusrecview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        cusrecview.setLayoutManager(gridLayoutManager);

        FirebaseRecyclerOptions<modelprod> options =
                new FirebaseRecyclerOptions.Builder<modelprod>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Product"), modelprod.class)
                        .build();

        adapter = new Cusprodadapter(options);
        cusrecview.setAdapter(adapter);

        txt_option = findViewById(R.id.txt_option);
        txt_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMenu(view);
            }
        });
    }

    private void showMenu(View view){
        PopupMenu popupMenu = new PopupMenu(ProdViewCus.this,view);
        popupMenu.getMenuInflater().inflate(R.menu.side_nav_menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if(menuItem.getItemId() == R.id.home){
                    Intent intent = new Intent(ProdViewCus.this, ProdViewCus.class);
                    startActivity(intent);
                }
                else if(menuItem.getItemId() == R.id.feedback){
/*                    Intent intent = getIntent();
                    String uemail = intent.getStringExtra("user");*/
                    Intent intent = new Intent(ProdViewCus.this, RVActivity.class);
/*                    intent2.putExtra("user", uemail);*/
                    startActivity(intent);
                }
                else if(menuItem.getItemId() == R.id.contactus){
                    Intent intent = new Intent(ProdViewCus.this, ContactUs.class);
                    startActivity(intent);
                }
                else if(menuItem.getItemId() == R.id.aboutus){
                    Intent intent = new Intent(ProdViewCus.this, AboutUs.class);
                    startActivity(intent);
                }
                else if(menuItem.getItemId() == R.id.profile){
                    Intent intent = getIntent();
                    String uid = intent.getStringExtra("user");
                    Intent intent2 = new Intent(ProdViewCus.this, ViewUserProfile.class);
                    intent2.putExtra("user", uid);
                    startActivity(intent2);
                }
                else if(menuItem.getItemId() == R.id.logout){
                    Intent intent = new Intent(ProdViewCus.this, CustomerLogin.class);
                    startActivity(intent);
                }
                return true;
            }
        });
        popupMenu.show();
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