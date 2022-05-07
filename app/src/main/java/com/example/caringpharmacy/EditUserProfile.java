package com.example.caringpharmacy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditUserProfile extends AppCompatActivity {

    EditText et_UserEmail, et_UserFname, et_UserLname,  et_UserPhone;
    Button btn_SaveUserProf;

    String userName;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_profile);

        et_UserEmail = findViewById(R.id.et_UserEmail);
        et_UserFname = findViewById(R.id.et_UserFname);
        et_UserLname = findViewById(R.id.et_UserLname);
        et_UserPhone = findViewById(R.id.et_UserPhone);

        btn_SaveUserProf = findViewById(R.id.btn_SaveUserProf);

        Intent intent = getIntent();
        email= intent.getStringExtra("em");
        userName = intent.getStringExtra("un");

        et_UserEmail.setText(email);
        et_UserFname.setText(userName);
    }
    public void backUserProfile(View view) {
        Intent intent = new Intent(this, ViewUserProfile.class);

        startActivity(intent);
    }
    public void openDeleteProfile(View view) {
        Intent intent = new Intent(this, MsgDeleteProfile.class);

        startActivity(intent);
    }
}