package com.example.caringpharmacy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MsgDeleteProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_delete_profile);
    }
    public void backEditProfile(View view) {
        Intent intent = new Intent(this, EditUserProfile.class);

        startActivity(intent);
    }
}