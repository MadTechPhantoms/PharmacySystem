package com.example.caringpharmacy;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.caringpharmacy.Fragment.FeedbackFragment;
import com.example.caringpharmacy.Fragment.HomeFragment;
import com.example.caringpharmacy.Fragment.MenuFragment;
import com.example.caringpharmacy.Fragment.OrderFragment;
import com.example.caringpharmacy.Fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FeedbackMainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView bottom_navigation_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_main);
        bottom_navigation_view = findViewById(R.id.bottom_navigation_view);
        bottom_navigation_view.setOnNavigationItemSelectedListener(this);
        bottom_navigation_view.setSelectedItemId(R.id.navigation_feedback);
    }

    FeedbackFragment feedbackFragment = new FeedbackFragment();
    HomeFragment homeFragment = new HomeFragment();
    OrderFragment orderFragment = new OrderFragment();
    MenuFragment menuFragment = new MenuFragment();
    ProfileFragment profileFragment = new ProfileFragment();
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.navigation_feedback:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, feedbackFragment).commit();
                return true;
            case R.id.navigation_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                return true;
            case R.id.navigation_order:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, orderFragment).commit();
                return true;
            case R.id.navigation_menu:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, menuFragment).commit();
                return true;
            case R.id.navigation_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, profileFragment).commit();
                return true;
        }

        return false;
    }
}