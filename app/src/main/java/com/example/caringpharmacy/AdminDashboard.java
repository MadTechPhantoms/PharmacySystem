package com.example.caringpharmacy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
    }

    public void backAdminLogin(View view) {
        Intent intent = new Intent(this, AdminLogin.class);

        startActivity(intent);
    }

    public void openCustomers(View view) {
        Intent intent = new Intent(this, ViewCustomers.class);

        startActivity(intent);
    }
}